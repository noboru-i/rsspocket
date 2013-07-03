package hm.orz.chaos114.gae.rsspocket.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.slim3.tester.ControllerTestCase;

public class RssControllerTest extends ControllerTestCase {

    @Test
    public void 未認証() throws Exception {
        // TODO filterが呼び出せない
        // SetUp
        whenNotLogin();
        // Exercise
        tester.start("/rss");
        final RssController controller = tester.getController();
        // Verify
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/rss.jsp"));
    }

    @Test
    public void 認証済み() throws Exception {
        // SetUp
        whenLoginBy("test@example.com", "999");
        // Exercise
        tester.start("/rss");
        final RssController controller = tester.getController();
        // Verify
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/rss.jsp"));
        assertThat(tester.requestScope("userRssList"), is(notNullValue()));
    }

    private static final String KEY_USER_ID =
            "com.google.appengine.api.users.UserService.user_id_key";

    private void whenLoginBy(final String email, final String userId) {
        // ログイン
        tester.environment.setEmail(email);
        tester.environment.getAttributes().put(KEY_USER_ID, userId);
    }

    private void whenNotLogin() {
        // ログアウト
        tester.environment.setEmail(null);
        tester.environment.getAttributes().remove(KEY_USER_ID);
    }
}
