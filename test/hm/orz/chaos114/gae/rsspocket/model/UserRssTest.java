package hm.orz.chaos114.gae.rsspocket.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class UserRssTest extends AppEngineTestCase {

    private UserRss model = new UserRss();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
