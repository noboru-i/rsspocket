package hm.orz.chaos114.gae.rsspocket.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.slim3.datastore.Datastore;
import org.slim3.tester.ControllerTestCase;

public class IndexControllerTest extends ControllerTestCase {

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        Datastore.setGlobalCipherKey("hogehogehogehoge");
    }

    @Test
    public void 未認証() throws Exception {
        // SetUp
        whenNotLogin();
        // Exercise
        tester.start("/");
        // Verify
        final IndexController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/index.jsp"));
        assertThat(tester.requestScope("loggedIn"), is(nullValue()));
    }

    @Test
    public void 認証済み() throws Exception {
        // SetUp
        final String email = "example@gmail.com";
        whenLoginBy(email, "999");
        // Exercise
        tester.start("/");
        // Verify
        final IndexController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));

        assertThat((Boolean)tester.requestScope("loggedIn"), is(Boolean.TRUE));
    }

    @Test
    public void エラーでリダイレクトされた場合() throws Exception {
        // SetUp
        whenNotLogin();
        // Exercise
        tester.param("error", "no_login");
        tester.start("/");
        // Verify
        final IndexController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/index.jsp"));
        assertThat(tester.requestScope("loggedIn"), is(nullValue()));
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
