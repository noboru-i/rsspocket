package hm.orz.chaos114.gae.rsspocket.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import hm.orz.chaos114.gae.rsspocket.model.RssFeed;
import hm.orz.chaos114.gae.rsspocket.model.UserRss;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.slim3.tester.AppEngineTestCase;

import com.google.appengine.api.users.User;

public class UserRssDaoTest extends AppEngineTestCase {

    private final UserRssDao dao = new UserRssDao();

    @Test
    public void testCase() throws Exception {
        // SetUp
        final User sampleUser = new User("test@example.com", "example.com");

        final UserRss userRss = create(sampleUser, "http://sample.com/rss", new String[]{"test", "test2"});
        final UserRss userRss2 = create(sampleUser, "http://sample.com/rss2", new String[]{"hoge", "hoge2"});
        dao.put(userRss);
        dao.put(userRss2);
        // Exercise
        final List<UserRss> actual = dao.getByUser(sampleUser);
        // Verify
        assertThat(dao, is(notNullValue()));
        assertThat(actual.size(), is(2));
        assertThat(actual.get(0).getUser(), is(sampleUser));
        assertThat(actual.get(0).getRssFeed().getModel().getUrl(), is("http://sample.com/rss"));
        assertThat(actual.get(0).getTags(), is(hasItems("test", "test2")));
        assertThat(actual.get(1).getUser(), is(sampleUser));
        assertThat(actual.get(1).getRssFeed().getModel().getUrl(), is("http://sample.com/rss2"));
        assertThat(actual.get(1).getTags(), is(hasItems("hoge", "hoge2")));
    }

    private UserRss create(final User user, final String url, final String[] tags) {
        final UserRss userRss = new UserRss();
        userRss.setUser(user);
        final Set<String> tagsSet = new HashSet<>();
        for(final String tag : tags) {
            tagsSet.add(tag);
        }
        userRss.setTags(tagsSet);
        final RssFeed rssFeed = createRssFeed(url);
        userRss.getRssFeed().setModel(rssFeed);

        return userRss;
    }

    private RssFeed createRssFeed(final String url) {
        final RssFeed rssFeed = new RssFeed();
        rssFeed.setUrl(url);
        final RssFeedDao rssFeedDao = new RssFeedDao();
        rssFeedDao.put(rssFeed);

        return rssFeed;
    }
}
