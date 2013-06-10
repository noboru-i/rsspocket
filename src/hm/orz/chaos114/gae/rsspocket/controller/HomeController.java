package hm.orz.chaos114.gae.rsspocket.controller;

import hm.orz.chaos114.gae.rsspocket.dao.UserInfoDao;
import hm.orz.chaos114.gae.rsspocket.model.UserInfo;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class HomeController extends Controller {

    @Override
    public Navigation run() throws Exception {
        final UserService userService = UserServiceFactory.getUserService();
        if (userService.isUserLoggedIn()) {
            // ログイン済みの場合

            final User user = userService.getCurrentUser();
            // 認証情報の保存
            final UserInfoDao dao = new UserInfoDao();
            UserInfo userInfo = dao.getByUser(user);
            if (userInfo == null) {
                userInfo = new UserInfo();
                userInfo.setUser(user);
                dao.putAsync(userInfo);
            }

            // ログインユーザの情報を設定
            requestScope("userInfo", userInfo);
        }
        return forward("home.jsp");
    }
}
