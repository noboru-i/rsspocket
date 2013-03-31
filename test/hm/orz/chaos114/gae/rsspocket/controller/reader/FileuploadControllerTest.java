package hm.orz.chaos114.gae.rsspocket.controller.reader;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.junit.Test;
import org.slim3.controller.upload.FileItem;
import org.slim3.tester.ControllerTestCase;

public class FileuploadControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/reader/fileupload");
        final FileuploadController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/reader/fileupload.jsp"));
    }

    @Test
    public void fileItemがpostされた場合() throws Exception {
        // SetUp
        tester.requestScope("xmlfile", getFileItem());
        // Exercise
        tester.start("/reader/fileupload");
        final FileuploadController controller = tester.getController();
        // Verify
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/reader/fileupload.jsp"));
        assertThat(tester.requestScope("urls"), is(notNullValue()));
    }

    private FileItem getFileItem() throws Exception{
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (InputStream is =
                getClass().getResourceAsStream("subscriptions.xml")) {
            int c;
            while ((c = is.read()) != -1) {
                baos.write(c);
            }
        }
        final byte[] data = baos.toByteArray();

        return new FileItem("subscriptions.xml", "application/xml", data);
    }
}
