package hm.orz.chaos114.gae.rsspocket.service.rss;

import hm.orz.chaos114.gae.rsspocket.dao.RssFeedDao;
import hm.orz.chaos114.gae.rsspocket.dao.UserRssDao;
import hm.orz.chaos114.gae.rsspocket.model.RssFeed;
import hm.orz.chaos114.gae.rsspocket.model.UserRss;

import java.util.Set;

import net.arnx.jsonic.JSON;

import com.google.appengine.api.users.User;

public class AddService {

    public void exec(final User user, final String jsonStr) {

        final AddParameter[] addParameters = JSON.decode(jsonStr, AddParameter[].class);

        final RssFeedDao rssFeedDao = new RssFeedDao();
        final UserRssDao userRssDao = new UserRssDao();

        for (final AddParameter parameter : addParameters) {
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

    class AddParameter {
        private String url;

        private Set<String> tags;

        public String getUrl() {
            return url;
        }

        public void setUrl(final String url) {
            this.url = url;
        }

        public Set<String> getTags() {
            return tags;
        }

        public void setTags(final Set<String> tags) {
            this.tags = tags;
        }

        @Override
        public String toString() {
            final StringBuilder builder = new StringBuilder();
            builder.append("AddParameter [url=");
            builder.append(url);
            builder.append(", tags=");
            builder.append(tags);
            builder.append("]");
            return builder.toString();
        }

    }
}
