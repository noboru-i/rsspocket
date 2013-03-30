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
        if (request.getUserPrincipal() == null) {
            // 未ログイン
            requestScope("login", false);
            requestScope("loginUrl", userService.createLoginURL(currentURI));
        } else {
            // ログイン済み
            requestScope("login", true);
            requestScope("userName", request.getUserPrincipal().getName());
            requestScope("logoutUrl", userService.createLogoutURL(currentURI));
        }
        return forward("index.jsp");
    }
}
