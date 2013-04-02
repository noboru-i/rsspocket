package hm.orz.chaos114.gae.rsspocket.controller.pocket;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import pocket4j.Pocket;
import pocket4j.conf.Configuration;

public class Auth_callbackController extends Controller {

    @Override
    public Navigation run() throws Exception {
        final String requestToken = sessionScope("requestToken");
        if (requestToken == null) {
            return redirect("/?error=no_session"); // トップ画面でエラーを表示する必要がある？
        }

        final Configuration configuration = new Configuration();
        final Pocket pocket = new Pocket(null, configuration);
        final String accessToken = pocket.fetchAccessToken(requestToken);

        return redirect("/?token=" + accessToken);
    }
}
