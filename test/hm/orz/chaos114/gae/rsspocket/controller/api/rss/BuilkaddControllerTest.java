package hm.orz.chaos114.gae.rsspocket.controller.api.rss;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;

import org.junit.Test;
import org.slim3.tester.ControllerTestCase;

public class BuilkaddControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.request.setInputStream(getStream());
        tester.request.setMethod("POST");
        tester.start("/api/rss/builkadd");
        final BuilkaddController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is(nullValue()));
    }

    private ServletInputStream getStream() throws Exception {
        final String json = "[{'url':'http://example.com/rss', 'tags':[]}]";
        final ServletInputStream sis = new ServletInputStream() {

            InputStream is =
                    new ByteArrayInputStream(json.getBytes());
            @Override
            public int read() throws IOException {
                return is.read();
            }
        };
        return sis;
    }
}
