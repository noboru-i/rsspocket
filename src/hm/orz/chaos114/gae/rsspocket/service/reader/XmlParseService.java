package hm.orz.chaos114.gae.rsspocket.service.reader;

import hm.orz.chaos114.gae.rsspocket.model.RssFeed;
import hm.orz.chaos114.gae.rsspocket.model.UserRss;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slim3.controller.upload.FileItem;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;


public class XmlParseService {

    public List<UserRss> parseXml(final FileItem fileItem) {
        final SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);

        SAXParser parser;
        try {
            parser = factory.newSAXParser();
        } catch (final Exception e) {
            return null;
        }

        final Handler handler = new Handler();
        try (InputStream is = new ByteArrayInputStream(fileItem.getData())){
            parser.parse(is, handler);
        } catch (final Exception e) {
        }

        return handler.userRssList;
    }

    private class Handler extends DefaultHandler2 {
        List<UserRss> userRssList = new ArrayList<>();

        private Set<String> parentTags = new LinkedHashSet<>();
        private int depth = 0;

        @Override
        public void startElement(final String uri, final String localName, final String qName,
                final Attributes attributes) throws SAXException {
            if (!qName.equalsIgnoreCase("outline")) {
                // outline以外は処理しない
                return;
            }
            depth++;

            final String xmlUrl = attributes.getValue("xmlUrl");
            if (xmlUrl == null) {
                // フォルダだと判断
                parentTags.addAll(getTagsFromAttributes(attributes));
                return;
            }
            final RssFeed feed = new RssFeed();
            final UserRss userRss = new UserRss();
            feed.setUrl(xmlUrl);
            userRss.getRssFeed().setModel(feed);
            final Set<String> tags = new LinkedHashSet<>();
            tags.addAll(parentTags);
            tags.addAll(getTagsFromAttributes(attributes));
            userRss.setTags(tags);
            userRssList.add(userRss);
        }

        @Override
        public void endElement(final String uri, final String localName, final String qName)
                throws SAXException {
            if (!qName.equalsIgnoreCase("outline")) {
                // outline以外は処理しない
                return;
            }
            depth--;
            if (depth == 0) {
                parentTags = new LinkedHashSet<>();
            }
        }

        private Set<String> getTagsFromAttributes(final Attributes attributes) {
            final Set<String> set = new LinkedHashSet<>();
            final String text = attributes.getValue("text");
            if (text != null) {
                set.add(text);
            }

            return set;
        }
    }
}
