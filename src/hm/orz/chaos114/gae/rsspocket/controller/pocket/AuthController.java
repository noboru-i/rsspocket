package hm.orz.chaos114.gae.rsspocket.controller.pocket;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import pocket4j.Pocket;
import pocket4j.conf.Configuration;

public class AuthController extends Controller {

    @Override
    public Navigation run() throws Exception {

        final Configuration configuration = new Configuration();
        final Pocket pocket = new Pocket(null, configuration);
        final String requestToken =
                pocket.fetchRequestToken(request.getRequestURL() + "_callback");
        sessionScope("requestToken", requestToken);

        final String authorizeUrl = pocket.getAuthorizeUrl(requestToken, request.getRequestURL() + "_callback");

        return redirect(authorizeUrl);
    }

}
