package hm.orz.chaos114.gae.rsspocket.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.slim3.tester.ControllerTestCase;

public class IndexControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/");
        final IndexController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/index.jsp"));
        assertThat(tester.requestScope("login"), is(notNullValue()));
        assertThat(tester.requestScope("loginUrl"), is(notNullValue()));
        assertThat(tester.requestScope("userName"), is(notNullValue()));
    }
}
