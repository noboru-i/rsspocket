package hm.orz.chaos114.gae.rsspocket.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import hm.orz.chaos114.gae.rsspocket.model.UserInfo;

import java.util.ArrayList;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.EntityNotFoundRuntimeException;
import org.slim3.tester.AppEngineTestCase;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

public class UserInfoDaoTest extends AppEngineTestCase {

    private static final User EXAMPLE_USER = new User("test@example.com", "example.com");;

    private final UserInfoDao dao = new UserInfoDao();

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        Datastore.setGlobalCipherKey("hogehogehogehoge");
    }

    @Test
    public void test() throws Exception {
        assertThat(dao, is(notNullValue()));
    }

    @Test
    public void putしてgetByUserで取得できる() throws Exception {
        // SetUp
        final UserInfo userInfo = new UserInfo();
        final User user = EXAMPLE_USER;
        userInfo.setUser(user);
        // Exercise
        dao.put(userInfo);
        final UserInfo actual = dao.getByUser(user);
        // Verify
        assertThat(actual, is(equalTo(userInfo)));
    }

    @Test
    public void 見つからなかった場合はnullを返す() throws Exception {
        // SetUp
        final User user = EXAMPLE_USER;
        // Exercise
        final UserInfo userInfo = dao.getByUser(user);
        // Verify
        assertThat(userInfo, is(nullValue()));
    }

    @Test(expected=UnsupportedOperationException.class)
    public void putにListを与えた場合は例外() throws Exception {
        // SetUp
        // Exercise
        dao.put(new ArrayList<UserInfo>());
        // Verify
    }

    @Test
    public void putAsyncにUserInfoを与えた場合は例外() throws Exception {
        // SetUp
        final UserInfo userInfo = new UserInfo();
        final User user = EXAMPLE_USER;
        userInfo.setUser(user);
        // Exercise
        final Future<Key> putAsync = dao.putAsync(userInfo);
        putAsync.get(); // 実行を完了させる
        final UserInfo actual = dao.getByUser(user);
        // Verify
        assertThat(actual, is(equalTo(userInfo)));
    }

    @Test(expected=UnsupportedOperationException.class)
    public void putAsyncにListを与えた場合は例外() throws Exception {
        // SetUp
        // Exercise
        dao.putAsync(new ArrayList<UserInfo>());
        // Verify
    }

    @Test(expected=EntityNotFoundRuntimeException.class)
    public void userが登録されていない場合は例外() throws Exception {
        // SetUp
        // Exercise
        dao.updatePocketAccessTokenAsync(EXAMPLE_USER, "test");
        // Verify
    }

    @Test
    public void userが登録されている場合はそのユーザの情報が変更される() throws Exception {
        // SetUp
        final UserInfo userInfo = new UserInfo();
        userInfo.setUser(EXAMPLE_USER);
        dao.put(userInfo);
        // Exercise
        final Future<Key> future = dao.updatePocketAccessTokenAsync(EXAMPLE_USER, "test_token");
        future.get();
        final UserInfo actual = dao.getByUser(EXAMPLE_USER);
        // Verify
        assertThat(actual.getPocketAccessToken(), is("test_token"));
    }
}
