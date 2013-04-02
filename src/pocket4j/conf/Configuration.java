package pocket4j.conf;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

public class Configuration implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String apiKey;

    private final String urlV3Request;
    private final String urlAuthorize;
    private final String urlV3Authorize;
    private final String urlV3Get;

    public Configuration() {

        final Properties properties = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("pocket.properties")){
            properties.load(is);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
        apiKey = properties.getProperty("consumer_key");
        urlV3Request = properties.getProperty("url_v3_request");
        urlAuthorize = properties.getProperty("url_authorize");
        urlV3Authorize = properties.getProperty("url_v3_authorize");
        urlV3Get = properties.getProperty("url_v3_get");
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getUrlV3Request() {
        return urlV3Request;
    }

    public String getUrlAuthorize() {
        return urlAuthorize;
    }

    public String getUrlV3Authorize() {
        return urlV3Authorize;
    }

    public String getUrlV3Get() {
        return urlV3Get;
    }

}
