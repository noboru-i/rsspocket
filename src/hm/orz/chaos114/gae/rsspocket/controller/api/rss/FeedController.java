package hm.orz.chaos114.gae.rsspocket.controller.api.rss;

import hm.orz.chaos114.gae.rsspocket.controller.BaseApiController;
import hm.orz.chaos114.gae.rsspocket.service.rss.RssService;

import javax.servlet.http.HttpServletResponse;

import org.slim3.controller.Navigation;

import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class FeedController extends BaseApiController {

    RssService rssService = new RssService();

    @Override
    public Navigation run() throws Exception {
        final String url = request.getParameter("url");

        final JSONObject object = rssService.getFeedsJson(url);

        responseWriter(HttpServletResponse.SC_OK, object.toString());

        return null;
    }
}
