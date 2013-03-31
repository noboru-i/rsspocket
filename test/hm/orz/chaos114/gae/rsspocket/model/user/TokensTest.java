package hm.orz.chaos114.gae.rsspocket.model.user;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TokensTest extends AppEngineTestCase {

    private Tokens model = new Tokens();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
