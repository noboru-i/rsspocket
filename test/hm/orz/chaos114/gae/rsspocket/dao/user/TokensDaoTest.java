package hm.orz.chaos114.gae.rsspocket.dao.user;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import hm.orz.chaos114.gae.rsspocket.model.user.Tokens;

import org.junit.Test;
import org.slim3.tester.AppEngineTestCase;

public class TokensDaoTest extends AppEngineTestCase {

    private final TokensDao dao = new TokensDao();

    @Test
    public void test() throws Exception {
        assertThat(dao, is(notNullValue()));
    }

    @Test
    public void putしgetする() throws Exception {
        // SetUp
        // Exercise
        final Tokens tokens = new Tokens();
        tokens.setUserId("1");
        tokens.setReaderToken("999");
        dao.put(tokens);
        final Tokens actualToken = dao.getByUserId("1");
        // Verify
        assertThat(actualToken.getUserId(), is("1"));
        assertThat(actualToken.getReaderToken(), is("999"));
    }

    @Test
    public void putせずにgetする() throws Exception {
        // SetUp
        // Exercise
        final Tokens actuTokens = dao.getByUserId("1");
        // Verify
        assertThat(actuTokens, is(nullValue()));
    }
}
