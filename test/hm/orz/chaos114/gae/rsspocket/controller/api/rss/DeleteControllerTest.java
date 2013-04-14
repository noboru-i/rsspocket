package hm.orz.chaos114.gae.rsspocket.controller.api.rss;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;

import org.junit.Test;
import org.slim3.tester.ControllerTestCase;

public class DeleteControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        // SetUp
        whenLoginBy("test@example.com", "999");
        tester.request.setInputStream(getStream());
        // Exercise
        tester.start("/api/rss/delete");
        final DeleteController controller = tester.getController();
        // Verify
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is(nullValue()));
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

    private static final String KEY_USER_ID =
            "com.google.appengine.api.users.UserService.user_id_key";

    private void whenLoginBy(final String email, final String userId) {
        // ログイン
        tester.environment.setEmail(email);
        tester.environment.getAttributes().put(KEY_USER_ID, userId);
    }
}
