package hm.orz.chaos114.gae.rsspocket.controller;

import hm.orz.chaos114.gae.rsspocket.dao.FeedsDao;
import hm.orz.chaos114.gae.rsspocket.dao.UserRssDao;
import hm.orz.chaos114.gae.rsspocket.model.Feeds;
import hm.orz.chaos114.gae.rsspocket.model.UserRss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.EntityNotFoundRuntimeException;

import com.google.appengine.api.users.User;

public class RssController extends Controller {

    UserRssDao dao = new UserRssDao();
    FeedsDao feedsDao = new FeedsDao();

    @Override
    public Navigation run() throws Exception {

        final User user = requestScope("user");
        final List<UserRss> userRssList = dao.getByUser(user);
        final Map<UserRss, List<Feeds>> map = new HashMap<>();
        final List<UserRss> userRssListNotNull = new ArrayList<>();
        for (final UserRss userRss : userRssList) {
            try {
                final List<Feeds> feedsList = feedsDao.getList(userRss.getRssFeed().getModel(), 0, 10);
                map.put(userRss, feedsList);
                userRssListNotNull.add(userRss);
            } catch(final EntityNotFoundRuntimeException e) {
                e.printStackTrace();
            }
        }
        requestScope("userRssList", userRssListNotNull);
        requestScope("feedsList", map);

        return forward("rss.jsp");
    }
}
