package hm.orz.chaos114.gae.rsspocket.controller.pocket;

import hm.orz.chaos114.gae.rsspocket.dao.UserInfoDao;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import pocket4j.Pocket;
import pocket4j.conf.Configuration;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class Auth_callbackController extends Controller {

    @Override
    public Navigation run() throws Exception {
        final String requestToken = sessionScope("requestToken");
        if (requestToken == null) {
            return redirect("/?error=no_session"); // TODO トップ画面でエラーを表示する必要がある？
        }

        // request tokenより、access tokenを取得
        final Configuration configuration = new Configuration();
        final Pocket pocket = new Pocket(null, configuration);
        final String accessToken = pocket.fetchAccessToken(requestToken);

        // access tokenを保存
        final UserService userService = UserServiceFactory.getUserService();
        final UserInfoDao dao = new UserInfoDao();
        dao.updatePocketAccessTokenAsync(userService.getCurrentUser(), accessToken);

        return redirect("/home");
    }
}
