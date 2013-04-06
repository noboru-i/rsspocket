package hm.orz.chaos114.gae.rsspocket.controller.api.rss;

import hm.orz.chaos114.gae.rsspocket.controller.BaseApiController;
import hm.orz.chaos114.gae.rsspocket.service.rss.AddService;

import javax.servlet.http.HttpServletResponse;

import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;

public class AddController extends BaseApiController {

    private final AddService addService = new AddService();

    @Override
    public Navigation run() throws Exception {
        if (!isPost()) {
            throw new UnsupportedOperationException();
        }

        final User user = requestScope("user");
        addService.exec(user, getBody());

        responseWriter(HttpServletResponse.SC_OK, "{}");

        return null;
    }
}
