package hm.orz.chaos114.gae.rsspocket.controller;

import hm.orz.chaos114.gae.rsspocket.dao.UserRssDao;
import hm.orz.chaos114.gae.rsspocket.model.UserRss;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.users.User;

public class RssController extends Controller {

    @Override
    public Navigation run() throws Exception {

        final User user = requestScope("user");
        final UserRssDao dao = new UserRssDao();
        final List<UserRss> userRssList = dao.getByUser(user);
        requestScope("userRssList", userRssList);

        return forward("rss.jsp");
    }
}
