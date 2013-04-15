package hm.orz.chaos114.gae.rsspocket.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import hm.orz.chaos114.gae.rsspocket.meta.RssFeedMeta;
import hm.orz.chaos114.gae.rsspocket.model.RssFeed;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

import org.junit.Test;
import org.slim3.datastore.Datastore;
import org.slim3.tester.AppEngineTestCase;

import com.google.appengine.api.datastore.Key;

public class RssFeedDaoTest extends AppEngineTestCase {

    private final RssFeedDao dao = new RssFeedDao();

    @Test
    public void test() throws Exception {
        assertThat(dao, is(notNullValue()));
    }

    @Test
    public void 同一URLを2件登録しても取得できるのは1件() throws Exception {
        final String url = "http://example.com/rss";
        // SetUp
        final RssFeed rssFeed = new RssFeed(url);
        final RssFeed rssFeed2 = new RssFeed(url);
        // Exercise
        final Future<Key> future = dao.putAsync(rssFeed);
        final Future<Key> future2 = dao.putAsync(rssFeed2);
        future.get();
        future2.get();
        final List<RssFeed> list = getByUrl(url);
        // Verify
        assertThat(list, is(notNullValue()));
        assertThat(list.size(), is(1));
    }

    @Test
    public void 別URLを2件登録すると取得できるのは2件() throws Exception {
        final String url = "http://example.com/rss";
        final String url2 = "http://example.com/rss2";
        // SetUp
        final RssFeed rssFeed = new RssFeed(url);
        final RssFeed rssFeed2 = new RssFeed(url2);
        // Exercise
        final Future<Key> future = dao.putAsync(rssFeed);
        final Future<Key> future2 = dao.putAsync(rssFeed2);
        future.get();
        future2.get();
        final List<RssFeed> list = getByUrl(url);
        final List<RssFeed> list2 = getByUrl(url2);
        // Verify
        assertThat(list, is(notNullValue()));
        assertThat(list.size(), is(1));
        assertThat(list2, is(notNullValue()));
        assertThat(list2.size(), is(1));
    }

    @Test
    public void getAllで2件取得できる() throws Exception {
        // SetUp
        final String url = "http://example.com/rss";
        final String url2 = "http://example.com/rss2";
        final RssFeed rssFeed = new RssFeed(url);
        final RssFeed rssFeed2 = new RssFeed(url2);
        // Exercise
        final Future<Key> future = dao.putAsync(rssFeed);
        final Future<Key> future2 = dao.putAsync(rssFeed2);
        future.get();
        future2.get();
        final List<RssFeed> actual = dao.getAll();
        // Verify
        assertThat(actual.size(), is(2));
        final String[] actualUrls = {actual.get(0).getUrl(), actual.get(1).getUrl()};
        assertThat(Arrays.asList(actualUrls), is(hasItems(url, url2)));
    }

    private List<RssFeed> getByUrl(final String url) {
        final RssFeedMeta meta = RssFeedMeta.get();
        return Datastore.query(meta).filter(meta.url.equal(url)).asList();
    }
}
