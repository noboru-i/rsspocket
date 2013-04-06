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
        assertThat(tester.isRedirect(), is(true));
        assertThat(tester.getDestinationPath(), is("/home"));
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
