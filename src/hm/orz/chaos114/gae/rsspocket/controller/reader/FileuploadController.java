package hm.orz.chaos114.gae.rsspocket.controller.reader;

import hm.orz.chaos114.gae.rsspocket.model.reader.RssFeed;
import hm.orz.chaos114.gae.rsspocket.service.reader.XmlParseService;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.upload.FileItem;

public class FileuploadController extends Controller {

    private final XmlParseService xmlParseService = new XmlParseService();

    @Override
    public Navigation run() throws Exception {
        final FileItem fileItem = requestScope("xmlfile");
        if (fileItem != null) {
            final List<RssFeed> parseXml = xmlParseService.parseXml(fileItem);
            requestScope("rssFeedList", parseXml);
        }
        return forward("fileupload.jsp");
    }
}
