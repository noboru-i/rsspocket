package hm.orz.chaos114.gae.rsspocket.filter;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.slim3.tester.ControllerTestCase;

public class AuthFilterTest extends ControllerTestCase {

    AuthFilter authFilter = new AuthFilter();

    @Test
    public void 認証していない場合() throws Exception {
        // SetUp
        whenNotLogin();
        // Exercise
        tester.start("/reader/fileupload");
        // Verify
        // TODO filterを通っていない
        //        assertThat(tester.isRedirect(), is(true));
        //        assertThat(tester.getDestinationPath(), is("/"));
    }

    @Test
    public void 認証している場合() throws Exception {
        // TODO filterを通っていない
        // SetUp
        whenLoginBy("test@example.com", "999");
        // Exercise
        tester.start("/reader/fileupload");
        // Verify
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/reader/fileupload"));
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
