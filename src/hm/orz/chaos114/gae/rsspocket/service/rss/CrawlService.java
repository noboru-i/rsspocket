package hm.orz.chaos114.gae.rsspocket.service.rss;

import hm.orz.chaos114.gae.rsspocket.dao.FeedsDao;
import hm.orz.chaos114.gae.rsspocket.dao.RssFeedDao;
import hm.orz.chaos114.gae.rsspocket.dao.UserInfoDao;
import hm.orz.chaos114.gae.rsspocket.dao.UserRssDao;
import hm.orz.chaos114.gae.rsspocket.model.Feeds;
import hm.orz.chaos114.gae.rsspocket.model.RssFeed;
import hm.orz.chaos114.gae.rsspocket.model.UserInfo;
import hm.orz.chaos114.gae.rsspocket.model.UserRss;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pocket4j.Pocket;
import pocket4j.action.add.AddAction;
import pocket4j.auth.Authorization;
import pocket4j.conf.Configuration;

import com.google.appengine.api.datastore.Text;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class CrawlService {

    RssFeedDao rssFeedDao = new RssFeedDao();
    FeedsDao feedsDao = new FeedsDao();
    UserRssDao userRssDao = new UserRssDao();
    UserInfoDao userInfoDao = new UserInfoDao();

    Pocket pocket = new Pocket(null, new Configuration());

    public void crawl() throws IOException, FeedException {
        final List<RssFeed> list = rssFeedDao.getAll();
        for (final RssFeed feed : list) {
            crawl(feed);
        }
    }

    public void crawl(final RssFeed rssFeed) throws FeedException {
        final String url = rssFeed.getUrl();

        final SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed;
        try {
            feed = input.build(new XmlReader(new URL(url)));
        } catch (final IOException e) {
            // 例外発生時は該当rssに対する処理終了
            return;
        }
        // サイトのタイトル
        rssFeed.setTitle(feed.getTitle());
        // サイトのURL
        rssFeed.setSiteUrl(feed.getLink());

        final List<Feeds> newEntries = new ArrayList<>();
        Date newLatestPublishedDate = rssFeed.getLatestPublishedDate();
        for (final Object obj : feed.getEntries()) {
            final Feeds model = new Feeds();
            final SyndEntry entry = (SyndEntry) obj;
            // RssFeed
            model.getRssFeed().setModel(rssFeed);
            // 記事タイトル
            model.setTitle(entry.getTitle());
            // 記事のURL
            model.setLink(entry.getLink());
            // 記事の詳細
            if (entry.getDescription() != null) {
                model.setDescription(new Text(entry.getDescription().getValue()));
            }
            // 記事の公開日
            model.setPublishedDate(entry.getPublishedDate());

            if (rssFeed.getLatestPublishedDate().compareTo(
                    entry.getPublishedDate()) < 0) {
                // 前回保存した時点より新しい記事
                newEntries.add(model);
                if (newLatestPublishedDate.compareTo(entry.getPublishedDate()) < 0) {
                    // entries内の一番新しい日時を保存
                    newLatestPublishedDate = entry.getPublishedDate();
                }
            }
        }

        // 新着をPocketに保存
        addPocketAll(rssFeed, newEntries);

        // 新着を保存
        feedsDao.putAsync(newEntries);

        // 一番新しい日時を保存
        rssFeed.setLatestPublishedDate(newLatestPublishedDate);
        rssFeedDao.putAsync(rssFeed);
    }

    private void addPocketAll(final RssFeed rssFeed, final List<Feeds> feedsList) {
        for (final Feeds feeds : feedsList) {
            final List<UserRss> userRssList = userRssDao.getByRss(rssFeed.getUrl());
            for (final UserRss userRss : userRssList) {
                addPocket(userRss, feeds);
            }
        }
    }

    private void addPocket(final UserRss userRss, final Feeds feeds) {
        final UserInfo userInfo = userInfoDao.getByUser(userRss.getUser());
        final String accessToken = userInfo.getPocketAccessToken();
        if (accessToken == null) {
            return;
        }
        pocket.setAuthorization(new Authorization(accessToken));

        final AddAction action = new AddAction();
        action.setTitle(feeds.getTitle());
        action.setUrl(feeds.getLink());
        action.setTags(userRss.getTags());
        try {
            pocket.add(action);
        } catch (final IOException e) {
            // TODO 例外処理
        }
    }
}
