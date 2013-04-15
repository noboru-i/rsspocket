package hm.orz.chaos114.gae.rsspocket.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FeedsTest extends AppEngineTestCase {

    private Feeds model = new Feeds();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
