package hm.orz.chaos114.gae.rsspocket.controller.api.rss;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;

import org.junit.Test;
import org.slim3.tester.ControllerTestCase;

public class FeedControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.request.setInputStream(getStream());
        tester.start("/api/rss/feed?url=http%3A%2F%2Fblog.kushii.net%2Findex.rdf");
        final FeedController controller = tester.getController();
        assertThat(controller, is(nullValue()));
        assertThat(tester.isRedirect(), is(false));
    }

    private ServletInputStream getStream() throws Exception {
        final String json = "";
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
