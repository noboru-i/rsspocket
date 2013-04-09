package hm.orz.chaos114.gae.rsspocket.controller.api.rss;

import hm.orz.chaos114.gae.rsspocket.controller.BaseApiController;
import hm.orz.chaos114.gae.rsspocket.service.rss.TagService;

import javax.servlet.http.HttpServletResponse;

import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;

public class EditController extends BaseApiController {

    private final TagService tagService = new TagService();

    @Override
    public Navigation run() throws Exception {
        if (!isPost()) {
            throw new UnsupportedOperationException();
        }

        final User user = requestScope("user");
        tagService.edit(user, getBody());

        responseWriter(HttpServletResponse.SC_OK, "{}");

        return null;
    }
}
