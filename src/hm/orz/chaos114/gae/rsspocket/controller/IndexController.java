package hm.orz.chaos114.gae.rsspocket.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        final UserService userService = UserServiceFactory.getUserService();
        if (userService.isUserLoggedIn()) {
            requestScope("loggedIn", Boolean.TRUE);
        }

        return forward("index.jsp");
    }
}
