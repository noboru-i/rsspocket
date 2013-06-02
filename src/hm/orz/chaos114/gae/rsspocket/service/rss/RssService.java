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

public class RssService {

    public void add(final User user, final String jsonStr) {

        final RssTagParameter[] addParameters = JSON.decode(jsonStr, RssTagParameter[].class);

        final RssFeedDao rssFeedDao = new RssFeedDao();
        final UserRssDao userRssDao = new UserRssDao();

        for (final RssTagParameter parameter : addParameters) {
            final RssFeed rssFeed = new RssFeed();
            rssFeed.setUrl(parameter.getUrl());
            rssFeedDao.put(rssFeed);

            final UserRss userRss = new UserRss();
            userRss.setUser(user);
            userRss.getRssFeed().setModel(rssFeed);
            userRss.setTags(parameter.getTags());
            userRssDao.put(userRss);
        }
    }

    public JSONArray getFeedsJson(final String url) {
        final RssFeedDao rssFeedDao = new RssFeedDao();
        final FeedsDao feedsDao = new FeedsDao();

        final RssFeed rssFeed = rssFeedDao.getByUrl(url);
        final List<Feeds> list = feedsDao.getList(rssFeed, 0, 50);

        final JSONArray array = new JSONArray();
        try {
            for (final Feeds feed : list) {
                final JSONObject object = new JSONObject();
                object.put("title", feed.getTitle());
                object.put("link", feed.getLink());
                array.put(object);
            }
        } catch (final JSONException e) {
            return null;
        }

        return array;
    }
}
