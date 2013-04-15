package hm.orz.chaos114.gae.rsspocket.controller.cron;

import hm.orz.chaos114.gae.rsspocket.service.rss.CrawlService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class CrawlController extends Controller {

    CrawlService service = new CrawlService();

    @Override
    public Navigation run() throws Exception {
        service.crawl();
        return null;
    }
}
