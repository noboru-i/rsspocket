package hm.orz.chaos114.gae.rsspocket.model.reader;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class RssFeedTest extends AppEngineTestCase {

    private RssFeed model = new RssFeed();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
