package hm.orz.chaos114.gae.rsspocket.controller.api.rss;

import hm.orz.chaos114.gae.rsspocket.controller.BaseApiController;
import hm.orz.chaos114.gae.rsspocket.service.rss.RssService;

import javax.servlet.http.HttpServletResponse;

import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;

public class AddController extends BaseApiController {

    private final RssService addService = new RssService();

    @Override
    public Navigation run() throws Exception {
        if (!isPost()) {
            response.setStatus(404);
            return null;
        }

        final User user = requestScope("user");
        addService.add(user, getBody());

        responseWriter(HttpServletResponse.SC_OK, "{}");

        return null;
    }
}
