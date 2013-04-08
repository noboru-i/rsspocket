package hm.orz.chaos114.gae.rsspocket.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import hm.orz.chaos114.gae.rsspocket.model.RssFeed;
import hm.orz.chaos114.gae.rsspocket.model.UserRss;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

import org.junit.Test;
import org.slim3.tester.AppEngineTestCase;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

public class UserRssDaoTest extends AppEngineTestCase {

    private final UserRssDao dao = new UserRssDao();

    @Test
    public void getByUserで2件取得できる() throws Exception {
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

    @Test
    public void 同一URLを2件登録しても取得できるのは1件() throws Exception {
        // SetUp
        final User sampleUser = new User("test@example.com", "example.com");
        final UserRss userRss = create(sampleUser, "http://sample.com/rss", new String[]{"test", "test2"});
        final UserRss userRss2 = create(sampleUser, "http://sample.com/rss", new String[]{"hoge", "hoge2"});
        // Exercise
        final Future<Key> future = dao.putAsync(userRss);
        final Future<Key> future2 = dao.putAsync(userRss2);
        future.get();
        future2.get();
        final List<UserRss> list = dao.getByUser(sampleUser);
        // Verify
        assertThat(list, is(notNullValue()));
        assertThat(list.size(), is(1));
        assertThat(list.get(0).getTags(), is(hasItems("hoge", "hoge2")));
    }

    @Test
    public void 別URLを2件登録すると取得できるのは2件() throws Exception {
        // SetUp
        final User sampleUser = new User("test@example.com", "example.com");
        final UserRss userRss = create(sampleUser, "http://sample.com/rss", new String[]{"test", "test2"});
        final UserRss userRss2 = create(sampleUser, "http://sample.com/rss2", new String[]{"hoge", "hoge2"});
        // Exercise
        final Future<Key> future = dao.putAsync(userRss);
        final Future<Key> future2 = dao.putAsync(userRss2);
        future.get();
        future2.get();
        final List<UserRss> list = dao.getByUser(sampleUser);
        // Verify
        assertThat(list, is(notNullValue()));
        assertThat(list.size(), is(2));
        assertThat(list.get(0), is(notNullValue()));
        final String[] urls = {list.get(0).getRssFeed().getModel().getUrl(),
                list.get(1).getRssFeed().getModel().getUrl()};
        assertThat(Arrays.asList(urls), is(hasItems("http://sample.com/rss", "http://sample.com/rss2")));
    }

    @Test
    public void 別ユーザが同一URLを2件登録すると取得できるのは2件() throws Exception {
        // SetUp
        final String url = "http://sample.com/rss";
        final User sampleUser = new User("test@example.com", "example.com");
        final User sampleUser2 = new User("test@example.com", "example.com");
        final UserRss userRss = create(sampleUser, url, new String[]{"test", "test2"});
        final UserRss userRss2 = create(sampleUser2, url, new String[]{"hoge", "hoge2"});
        // Exercise
        final Future<Key> future = dao.putAsync(userRss);
        final Future<Key> future2 = dao.putAsync(userRss2);
        future.get();
        future2.get();
        final List<UserRss> list = dao.getByUser(sampleUser);
        final List<UserRss> list2 = dao.getByUser(sampleUser2);
        // Verify
        assertThat(list, is(notNullValue()));
        assertThat(list.size(), is(1));
        assertThat(list.get(0), is(notNullValue()));
        assertThat(list.get(0).getRssFeed().getModel().getUrl(), is("http://sample.com/rss"));
        assertThat(list2, is(notNullValue()));
        assertThat(list2.size(), is(1));
        assertThat(list2.get(0), is(notNullValue()));
        assertThat(list2.get(0).getRssFeed().getModel().getUrl(), is("http://sample.com/rss"));
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
