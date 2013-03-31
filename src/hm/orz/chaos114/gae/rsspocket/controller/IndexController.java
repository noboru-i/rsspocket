package hm.orz.chaos114.gae.rsspocket.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gdata.client.http.AuthSubUtil;

public class IndexController extends Controller {

    private static final String READER_DOMAIN = "http://www.google.com/reader/subscriptions/export";

    @Override
    public Navigation run() throws Exception {
        final UserService userService = UserServiceFactory.getUserService();
        final String currentURI = request.getRequestURI();
        if (userService.isUserLoggedIn()) {
            // ログイン済み
            requestScope("login", true);
            requestScope("userName", userService.getCurrentUser().getEmail());
            requestScope("logoutUrl", userService.createLogoutURL(currentURI));

            final String nextUrl = request.getRequestURL().toString();
            final String authUrl =
                    AuthSubUtil.getRequestUrl(nextUrl, READER_DOMAIN, false, true);
            requestScope("authUrl", authUrl);
        } else {
            // 未ログイン
            requestScope("login", false);
            requestScope("loginUrl", userService.createLoginURL(currentURI));
        }
        return forward("index.jsp");
    }
}
