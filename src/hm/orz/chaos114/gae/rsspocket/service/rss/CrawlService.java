package hm.orz.chaos114.gae.rsspocket.service.rss;

import hm.orz.chaos114.gae.rsspocket.dao.FeedsDao;
import hm.orz.chaos114.gae.rsspocket.dao.RssFeedDao;
import hm.orz.chaos114.gae.rsspocket.model.Feeds;
import hm.orz.chaos114.gae.rsspocket.model.RssFeed;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class CrawlService {

    RssFeedDao rssFeedDao = new RssFeedDao();
    FeedsDao feedsDao = new FeedsDao();

    public void crawl() throws IOException, FeedException {
        final List<RssFeed> list = rssFeedDao.getAll();
        for (final RssFeed feed : list) {
            crawl(feed);
        }
    }

    public void crawl(final RssFeed rssFeed) throws IOException, FeedException {
        final String url = rssFeed.getUrl();

        final URL feedUrl = new URL(url);
        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(feedUrl));
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
                model.setDescription(entry.getDescription().getValue());
            }
            // 記事の公開日
            model.setPublishedDate(entry.getPublishedDate());

            if (rssFeed.getLatestPublishedDate().compareTo(
                    entry.getPublishedDate()) < 0) {
                // 前回保存した時点より新しい記事
                rssFeed.setLatestPublishedDate(entry.getPublishedDate());
                newEntries.add(model);
                if (newLatestPublishedDate.compareTo(entry.getPublishedDate()) < 0) {
                    // entries内の一番新しい日時を保存
                    newLatestPublishedDate = entry.getPublishedDate();
                }
            }
        }

        // TODO 新着をPocketに保存

        // 新着を保存
        feedsDao.putAsync(newEntries);

        // 一番新しい日時を保存
        rssFeed.setLatestPublishedDate(newLatestPublishedDate);
        rssFeedDao.putAsync(rssFeed);
    }
}
