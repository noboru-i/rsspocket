package hm.orz.chaos114.gae.rsspocket.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import hm.orz.chaos114.gae.rsspocket.model.Feeds;
import hm.orz.chaos114.gae.rsspocket.model.RssFeed;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.slim3.tester.AppEngineTestCase;

public class FeedsDaoTest extends AppEngineTestCase {

    private final FeedsDao dao = new FeedsDao();

    @Test
    public void test() throws Exception {
        assertThat(dao, is(notNullValue()));
    }

    @Test
    public void testCase() throws Exception {
        // SetUp
        final RssFeed rssFeed = putRssFeed("http://example.com/rss");
        putFeed(rssFeed, "test1", "2013/01/01");
        putFeed(rssFeed, "test2", "2013/01/02");
        putFeed(rssFeed, "test3", "2013/01/03");
        putFeed(rssFeed, "test4", "2013/01/04");
        putFeed(rssFeed, "test5", "2013/01/05");
        // Exercise
        final List<Feeds> actual = dao.getList(rssFeed, 0, 4);
        // Verify
        assertThat(actual.size(), is(4));
        assertThat(actual.get(0).getTitle(), is("test5"));
        assertThat(actual.get(1).getTitle(), is("test4"));
        assertThat(actual.get(2).getTitle(), is("test3"));
        assertThat(actual.get(3).getTitle(), is("test2"));
    }

    private RssFeed putRssFeed(final String feedUrl) {
        final RssFeed rssFeed = new RssFeed(feedUrl);
        final RssFeedDao rssFeedDao = new RssFeedDao();
        rssFeedDao.put(rssFeed);
        return rssFeed;
    }

    private void putFeed(final RssFeed rssFeed, final String title, final String dateStr) throws Exception {

        final Feeds feeds = new Feeds();
        feeds.getRssFeed().setModel(rssFeed);
        feeds.setTitle(title);
        feeds.setPublishedDate(new SimpleDateFormat("yyyy/MM/dd").parse(dateStr));
        dao.put(feeds);
    }
}
