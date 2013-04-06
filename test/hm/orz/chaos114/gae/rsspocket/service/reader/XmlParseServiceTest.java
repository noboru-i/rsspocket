package hm.orz.chaos114.gae.rsspocket.service.reader;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import hm.orz.chaos114.gae.rsspocket.model.UserRss;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.slim3.controller.upload.FileItem;
import org.slim3.tester.AppEngineTestCase;

public class XmlParseServiceTest extends AppEngineTestCase {

    private final XmlParseService service = new XmlParseService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }

    @Test
    public void 試験データよりurlリストを取得() throws Exception {
        // SetUp
        final FileItem fileItem =
                new FileItem("subscriptions.xml", "application/xml", getFixture());
        // Exercise
        final List<UserRss> actual = service.parseXml(fileItem);
        // Verify
        assertThat(actual, is(notNullValue()));
        assertThat(actual.get(0).getRssFeed().getModel().getUrl(), is("http://d.hatena.ne.jp/wistery_k/rss"));
        assertThat(actual.get(0).getTags(), is(hasItems("Blog", "#define int ll")));
    }

    private byte[] getFixture() throws Exception {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (InputStream is = getClass().getResourceAsStream("subscriptions.xml")) {
            int c;
            while((c = is.read()) != -1) {
                baos.write(c);
            }
        }
        return baos.toByteArray();
    }
}
