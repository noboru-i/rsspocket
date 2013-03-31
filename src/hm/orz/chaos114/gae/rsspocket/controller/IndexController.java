package hm.orz.chaos114.gae.rsspocket.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        final UserService userService = UserServiceFactory.getUserService();
        final String currentURI = request.getRequestURI();
        if (userService.isUserLoggedIn()) {
            // ログイン済み
            requestScope("login", true);
            requestScope("userName", userService.getCurrentUser().getEmail());
            requestScope("logoutUrl", userService.createLogoutURL(currentURI));
        } else {
            // 未ログイン
            requestScope("login", false);
            requestScope("loginUrl", userService.createLoginURL(currentURI));
        }
        return forward("index.jsp");
    }
}
