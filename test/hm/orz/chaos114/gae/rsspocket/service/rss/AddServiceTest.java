package hm.orz.chaos114.gae.rsspocket.service.rss;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import hm.orz.chaos114.gae.rsspocket.dao.UserRssDao;
import hm.orz.chaos114.gae.rsspocket.model.UserRss;

import java.util.List;

import org.junit.Test;
import org.slim3.tester.AppEngineTestCase;

import com.google.appengine.api.users.User;

public class AddServiceTest extends AppEngineTestCase {

    private final AddService service = new AddService();

    @Test
    public void データが登録される() throws Exception {
        // SetUp
        final User user = new User("test@example.com", "example.com");
        final String jsonStr = "["
                + "{'url':'http://sample.com/rss', 'tags':['test','test2']},"
                + "{'url':'http://sample.com/rss2', 'tags':['hoge','hoge2']}"
                + "]";
        final UserRssDao dao = new UserRssDao();
        // Exercise
        service.exec(user, jsonStr);
        final List<UserRss> actual = dao.getByUser(user);
        // Verify
        assertThat(service, is(notNullValue()));
        assertThat(actual.size(), is(2));
        assertThat(actual.get(0).getUser(), is(user));
        assertThat(actual.get(0).getRssFeed().getModel().getUrl(), is("http://sample.com/rss"));
        assertThat(actual.get(0).getTags(), is(hasItems("test", "test2")));
        assertThat(actual.get(1).getUser(), is(user));
        assertThat(actual.get(1).getRssFeed().getModel().getUrl(), is("http://sample.com/rss2"));
        assertThat(actual.get(1).getTags(), is(hasItems("hoge", "hoge2")));
    }
}
