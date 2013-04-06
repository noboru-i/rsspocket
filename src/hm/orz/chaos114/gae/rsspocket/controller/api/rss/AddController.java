package hm.orz.chaos114.gae.rsspocket.controller.api.rss;

import hm.orz.chaos114.gae.rsspocket.controller.BaseApiController;
import hm.orz.chaos114.gae.rsspocket.dao.reader.RssFeedDao;
import hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed;

import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import net.arnx.jsonic.JSON;

import org.slim3.controller.Navigation;

public class AddController extends BaseApiController {

    @Override
    public Navigation run() throws Exception {
        if (!isPost()) {
            throw new UnsupportedOperationException();
        }

        final RssFeed[] rssFeeds = JSON.decode(getBody(), RssFeed[].class);
        final RssFeedDao dao = new RssFeedDao();
        dao.put(Arrays.asList(rssFeeds));

        responseWriter(HttpServletResponse.SC_OK, "{}");

        return null;
    }
}
