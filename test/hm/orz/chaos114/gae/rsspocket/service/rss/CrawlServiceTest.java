package hm.orz.chaos114.gae.rsspocket.service.rss;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import hm.orz.chaos114.gae.rsspocket.dao.FeedsDao;
import hm.orz.chaos114.gae.rsspocket.dao.RssFeedDao;
import hm.orz.chaos114.gae.rsspocket.model.Feeds;
import hm.orz.chaos114.gae.rsspocket.model.RssFeed;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Future;

import org.junit.Test;
import org.slim3.tester.AppEngineTestCase;

import com.google.appengine.api.datastore.Key;

public class CrawlServiceTest extends AppEngineTestCase {

    private final CrawlService service = new CrawlService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }

    @Test
    public void crawlを実行する() throws Exception {
        // SetUp
        final RssFeed rssFeed = create("rss2.0.xml");
        final RssFeed rssFeed2 = create("rss2.0_2.xml");
        // Exercise
        service.crawl();
        final RssFeedDao rssFeedDao = new RssFeedDao();
        final List<RssFeed> actualRssList = rssFeedDao.getAll();
        final FeedsDao feedsDao = new FeedsDao();
        final List<Feeds> actualFeedList = feedsDao.getList(rssFeed, 0, 5);
        final List<Feeds> actualFeedList2 = feedsDao.getList(rssFeed2, 0, 5);
        // Verify
        assertThat(actualRssList.size(), is(2));
        assertThat(actualRssList.get(0).getLatestPublishedDate(),
                is(getDate("Mon, 15 Apr 2013 12:02:45 +0900")));
        assertThat(actualRssList.get(1).getLatestPublishedDate(),
                is(getDate("Mon, 15 Apr 2013 18:06:01 +0900")));

        assertThat(actualFeedList.size(), is(2));
        assertThat(actualFeedList.get(0).getPublishedDate(),
                is(getDate("Mon, 15 Apr 2013 12:02:45 +0900")));

        assertThat(actualFeedList2.size(), is(3));
        assertThat(actualFeedList2.get(0).getPublishedDate(),
                is(getDate("Mon, 15 Apr 2013 18:06:01 +0900")));
        assertThat(actualFeedList2.get(1).getPublishedDate(),
                is(getDate("Mon, 15 Apr 2013 12:02:45 +0900")));
    }

    // TODO 同一URLで、2回実行し、1回目の結果が重複して登録されないことを確認する

    private Date getDate(final String date) throws Exception {
        return new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z",
                Locale.ENGLISH).parse(date);
    }

    private RssFeed create(final String fileName) throws Exception {
        final String url = getClass().getResource(fileName).toString();
        final RssFeedDao dao = new RssFeedDao();
        final RssFeed rssFeed = new RssFeed(url);
        final Future<Key> future = dao.putAsync(rssFeed);
        future.get();
        return rssFeed;
    }
}
