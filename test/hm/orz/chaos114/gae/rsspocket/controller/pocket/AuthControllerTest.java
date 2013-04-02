package hm.orz.chaos114.gae.rsspocket.controller.pocket;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.slim3.tester.ControllerTestCase;

public class AuthControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/pocket/auth");
        final AuthController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(true));
        assertThat(tester.getDestinationPath(), is(notNullValue()));
    }
}
