package hm.orz.chaos114.gae.rsspocket.service.reader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slim3.controller.upload.FileItem;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;


public class XmlParseService {

    public List<String> parseXml(final FileItem fileItem) {
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

        return handler.urlList;
    }

    private class Handler extends DefaultHandler2 {
        List<String> urlList = new ArrayList<>();

        @Override
        public void startElement(final String uri, final String localName, final String qName,
                final Attributes attributes) throws SAXException {
            final String url = attributes.getValue("xmlUrl");
            if (url != null) {
                urlList.add(url);
            }
        }

    }
}
