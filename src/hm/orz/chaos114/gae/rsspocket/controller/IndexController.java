package hm.orz.chaos114.gae.rsspocket.controller;

import hm.orz.chaos114.gae.rsspocket.dao.user.TokensDao;
import hm.orz.chaos114.gae.rsspocket.model.user.Tokens;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gdata.client.http.AuthSubUtil;

public class IndexController extends Controller {

    private static final String READER_DOMAIN =
            "http://www.google.com/reader/subscriptions/export";

    @Override
    public Navigation run() throws Exception {
        final UserService userService = UserServiceFactory.getUserService();
        final String currentURI = request.getRequestURI();
        if (userService.isUserLoggedIn()) {
            // ログイン済み
            requestScope("login", true);
            requestScope("userName", userService.getCurrentUser().getEmail());
            requestScope("logoutUrl", userService.createLogoutURL(currentURI));

            // Readerの認証チェック
            final TokensDao dao = new TokensDao();
            final Tokens tokens =
                    dao.getByUserId(userService.getCurrentUser().getUserId());
            if (tokens == null || tokens.getReaderToken() == null) {
                final String nextUrl =
                        request.getRequestURL().toString() + "oauth/callback";
                final String authUrl =
                        AuthSubUtil.getRequestUrl(
                            nextUrl,
                            READER_DOMAIN,
                            true,
                            true);
                requestScope("authUrl", authUrl);
            }
        } else {
            // 未ログイン
            requestScope("login", false);
            requestScope("loginUrl", userService.createLoginURL(currentURI));
        }
        return forward("index.jsp");
    }
}
