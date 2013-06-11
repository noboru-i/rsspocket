package hm.orz.chaos114.gae.rsspocket.service.rss;

import hm.orz.chaos114.gae.rsspocket.dao.FeedsDao;
import hm.orz.chaos114.gae.rsspocket.dao.RssFeedDao;
import hm.orz.chaos114.gae.rsspocket.dao.UserRssDao;
import hm.orz.chaos114.gae.rsspocket.model.Feeds;
import hm.orz.chaos114.gae.rsspocket.model.RssFeed;
import hm.orz.chaos114.gae.rsspocket.model.UserRss;

import java.util.List;

import net.arnx.jsonic.JSON;

import com.google.appengine.api.users.User;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.sun.syndication.io.FeedException;

public class RssService {

    final RssFeedDao rssFeedDao = new RssFeedDao();
    final UserRssDao userRssDao = new UserRssDao();
    final FeedsDao feedsDao = new FeedsDao();

    public void add(final User user, final String jsonStr) {

        final RssTagParameter[] addParameters = JSON.decode(jsonStr, RssTagParameter[].class);

        for (final RssTagParameter parameter : addParameters) {
            final RssFeed rssFeed = new RssFeed();
            rssFeed.setUrl(parameter.getUrl());
            rssFeedDao.put(rssFeed);

            final CrawlService crawlService = new CrawlService();
            try {
                crawlService.crawl(rssFeed);
            } catch (final FeedException e) {
                // no-op
            }

            // crawl終了後に登録（初回のcrawlではPocketに登録しない）
            final UserRss userRss = new UserRss();
            userRss.setUser(user);
            userRss.getRssFeed().setModel(rssFeed);
            userRss.setTags(parameter.getTags());
            userRssDao.put(userRss);
        }
    }

    public JSONObject getFeedsJson(final String url) {
        final RssFeed rssFeed = rssFeedDao.getByUrl(url);
        if (rssFeed == null) {
            return null;
        }
        final List<Feeds> list = feedsDao.getList(rssFeed, 0, 50);
        final JSONObject result = new JSONObject();

        try {
            // rss情報を設定
            final JSONObject rssInfo = new JSONObject();
            rssInfo.put("title", rssFeed.getTitle());
            rssInfo.put("site_url", rssFeed.getSiteUrl());
            rssInfo.put("url", rssFeed.getUrl());
            result.put("rss", rssInfo);

            // feed情報を設定
            final JSONArray feedArray = new JSONArray();
            for (final Feeds feed : list) {
                final JSONObject object = new JSONObject();
                object.put("title", feed.getTitle());
                object.put("link", feed.getLink());
                feedArray.put(object);
            }
            result.put("feeds", feedArray);
        } catch (final JSONException e) {
            return null;
        }

        return result;
    }
}
