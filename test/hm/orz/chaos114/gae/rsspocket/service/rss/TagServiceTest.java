package hm.orz.chaos114.gae.rsspocket.service.rss;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import hm.orz.chaos114.gae.rsspocket.dao.UserRssDao;
import hm.orz.chaos114.gae.rsspocket.meta.RssFeedMeta;
import hm.orz.chaos114.gae.rsspocket.model.RssFeed;
import hm.orz.chaos114.gae.rsspocket.model.UserRss;

import java.util.List;

import org.junit.Test;
import org.slim3.datastore.Datastore;
import org.slim3.tester.AppEngineTestCase;

import com.google.appengine.api.users.User;

public class TagServiceTest extends AppEngineTestCase {

    private final TagService service = new TagService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }

    @Test
    public void 編集できる() throws Exception {
        // SetUp
        final User user = new User("test@example.com", "example.com");
        final String jsonStr = "["
                + "{'url':'http://sample.com/rss', 'tags':['test','test2']},"
                + "{'url':'http://sample.com/rss2', 'tags':['hoge','hoge2']}"
                + "]";
        final String jsonStr2 = "["
                + "{'url':'http://sample.com/rss', 'tags':['test','test2']},"
                + "{'url':'http://sample.com/rss2', 'tags':['fuga','fuga2']}"
                + "]";
        final RssService rssService = new RssService();
        final UserRssDao userRssDao = new UserRssDao();
        rssService.add(user, jsonStr);
        // Exercise
        service.edit(user, jsonStr2);
        final List<UserRss> actual = userRssDao.getByUser(user);
        final List<RssFeed> actualRssFeedList = getAllRssFeed();
        // Verify
        assertThat(service, is(notNullValue()));
        assertThat(actual.size(), is(2));
        assertThat(actual.get(0).getUser(), is(user));
        assertThat(actual.get(0).getRssFeed().getModel().getUrl(), is("http://sample.com/rss"));
        assertThat(actual.get(0).getTags(), is(hasItems("test", "test2")));
        assertThat(actual.get(1).getUser(), is(user));
        assertThat(actual.get(1).getRssFeed().getModel().getUrl(), is("http://sample.com/rss2"));
        assertThat(actual.get(1).getTags(), is(hasItems("fuga", "fuga2")));
        assertThat(actualRssFeedList.size(), is(2));
    }

    public List<RssFeed> getAllRssFeed() {
        final RssFeedMeta meta = RssFeedMeta.get();
        return Datastore.query(meta).asList();
    }
}
