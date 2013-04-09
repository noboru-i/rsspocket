package hm.orz.chaos114.gae.rsspocket.service.rss;

import hm.orz.chaos114.gae.rsspocket.dao.RssFeedDao;
import hm.orz.chaos114.gae.rsspocket.dao.UserRssDao;
import hm.orz.chaos114.gae.rsspocket.model.RssFeed;
import hm.orz.chaos114.gae.rsspocket.model.UserRss;
import net.arnx.jsonic.JSON;

import com.google.appengine.api.users.User;


public class TagService {

    public void edit(final User user, final String jsonStr) {
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
}
