package hm.orz.chaos114.gae.rsspocket.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.slim3.tester.ControllerTestCase;

public class IndexControllerTest extends ControllerTestCase {

    @Test
    public void 未認証のテスト() throws Exception {
        // SetUp
        whenNotLogin();
        // Exercise
        tester.start("/");
        // Verify
        final IndexController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/index.jsp"));
        assertThat((Boolean)tester.requestScope("login"), is(Boolean.FALSE));
        assertThat(tester.requestScope("loginUrl"), is(notNullValue()));
    }

    @Test
    public void 認証済みのテスト() throws Exception {
        // SetUp
        final String email = "example@gmail.com";
        whenLoginBy(email, "999");
        // Exercise
        tester.start("/");
        // Verify
        final IndexController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/index.jsp"));
        assertThat((Boolean) tester.requestScope("login"), is(Boolean.TRUE));
        assertThat(tester.requestScope("logoutUrl"), is(notNullValue()));
        assertThat((String)tester.requestScope("userName"), is(email));
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
