package hm.orz.chaos114.gae.rsspocket.controller;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class HomeControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/home");
        HomeController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/home.jsp"));
    }
}
