package hm.orz.chaos114.gae.rsspocket.dao.reader;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class RssFeedDaoTest extends AppEngineTestCase {

    private RssFeedDao dao = new RssFeedDao();

    @Test
    public void test() throws Exception {
        assertThat(dao, is(notNullValue()));
    }
}
