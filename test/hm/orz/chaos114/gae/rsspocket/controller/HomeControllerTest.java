package hm.orz.chaos114.gae.rsspocket.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import hm.orz.chaos114.gae.rsspocket.dao.UserInfoDao;
import hm.orz.chaos114.gae.rsspocket.model.UserInfo;

import org.junit.Before;
import org.junit.Test;
import org.slim3.datastore.Datastore;
import org.slim3.tester.ControllerTestCase;

import com.google.appengine.api.users.User;

public class HomeControllerTest extends ControllerTestCase {

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        Datastore.setGlobalCipherKey("hogehogehogehoge");
    }

    @Test
    public void 未認証のテスト() throws Exception {
        // TODO filterが呼び出せない
        // SetUp
        final User user = new User("example@gmail.com", "gmail.com");
        whenNotLogin();
        // Exercise
        tester.start("/home");
        final UserInfoDao dao = new UserInfoDao();
        final UserInfo userInfo = dao.getByUser(user);
        // Verify
        final HomeController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/home.jsp"));
        assertThat(userInfo, is(nullValue()));
    }

    @Test
    public void 認証済みのテスト() throws Exception {
        // SetUp
        final User user = new User("example@gmail.com", "gmail.com");
        whenLoginBy("example@gmail.com", "999");
        // Exercise
        tester.start("/home");
        final UserInfoDao dao = new UserInfoDao();
        final UserInfo userInfo = dao.getByUser(user);
        // Verify
        final HomeController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/home.jsp"));
        assertThat(userInfo.getUser(), is(user));
    }

    @Test
    public void 認証済みかつPocket認証済み() throws Exception {
        // SetUp
        final User user = new User("example@gmail.com", "gmail.com");
        final UserInfoDao userInfoDao = new UserInfoDao();
        final UserInfo userInfo = new UserInfo();
        userInfo.setUser(user);
        userInfo.setPocketAccessToken("hogehoge");
        userInfoDao.put(userInfo);
        whenLoginBy("example@gmail.com", "999");
        // Exercise
        tester.start("/home");
        // Verify
        final HomeController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/home.jsp"));
        final UserInfo userInfo2 = tester.requestScope("userInfo");
        assertThat(userInfo2, is(userInfo));
    }

    private static final String KEY_USER_ID =
            "com.google.appengine.api.users.UserService.user_id_key";

    private void whenLoginBy(final String email, final String userId) {
        // ログイン
        tester.environment.setEmail(email);
        tester.environment.getAttributes().put(KEY_USER_ID, userId);
    }

    private void whenNotLogin() {
        // ログアウト
        tester.environment.setEmail(null);
        tester.environment.getAttributes().remove(KEY_USER_ID);
    }
}
