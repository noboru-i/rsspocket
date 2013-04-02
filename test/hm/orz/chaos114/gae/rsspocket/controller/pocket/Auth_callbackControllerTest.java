package hm.orz.chaos114.gae.rsspocket.controller.pocket;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.slim3.tester.ControllerTestCase;

public class Auth_callbackControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/pocket/auth_callback");
        final Auth_callbackController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(true));
        assertThat(tester.getDestinationPath(), is("/?error=no_session"));
    }

    @Test(expected=RuntimeException.class) // TODO 本来は通常の遷移をするテストを行う
    public void sessionを設定した場合() throws Exception {
        // SetUp
        tester.sessionScope("requestToken", "test");
        // Exercise
        tester.start("/pocket/auth_callback");
        final Auth_callbackController controller = tester.getController();
        // Verify
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(true));
        assertThat(tester.getDestinationPath(), is("/"));
    }
}
