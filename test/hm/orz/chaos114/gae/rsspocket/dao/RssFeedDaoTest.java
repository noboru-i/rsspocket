package hm.orz.chaos114.gae.rsspocket.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import hm.orz.chaos114.gae.rsspocket.meta.RssFeedMeta;
import hm.orz.chaos114.gae.rsspocket.model.RssFeed;

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

    private List<RssFeed> getByUrl(final String url) {
        final RssFeedMeta meta = RssFeedMeta.get();
        return Datastore.query(meta).filter(meta.url.equal(url)).asList();
    }
}
