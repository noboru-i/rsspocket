package hm.orz.chaos114.gae.rsspocket.controller.oauth;

import hm.orz.chaos114.gae.rsspocket.dao.user.TokensDao;
import hm.orz.chaos114.gae.rsspocket.model.user.Tokens;

import java.security.PrivateKey;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gdata.client.http.AuthSubUtil;

public class CallbackController extends Controller {

    private static final String PASSWORD = "XXX";

    @Override
    public Navigation run() throws Exception {
        // tokenの永続化
        final String authToken =
                AuthSubUtil.getTokenFromReply(request.getQueryString());
        final PrivateKey privateKey = AuthSubUtil.getPrivateKeyFromKeystore("rsspocket", PASSWORD, "Rsspocket", PASSWORD);
        final String sessionToken = AuthSubUtil.exchangeForSessionToken(authToken, privateKey);

        if (sessionToken != null) {
            // tokenが取得できた場合

            // 認証済みのアカウントを取得する
            final UserService userService = UserServiceFactory.getUserService();
            if (userService.isUserLoggedIn()) {
                final String userId = userService.getCurrentUser().getUserId();
                final TokensDao dao = new TokensDao();
                Tokens tokens = dao.getByUserId(userId);
                if (tokens == null) {
                    tokens = new Tokens();
                    tokens.setUserId(userId);
                }
                tokens.setReaderToken(sessionToken);
                dao.put(tokens);
            }
        }
        return redirect("/");
    }
}
