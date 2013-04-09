package hm.orz.chaos114.gae.rsspocket.service.rss;

import java.util.Set;

public class RssTagParameter {
    private String url;

    private Set<String> tags;

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(final Set<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("AddParameter [url=");
        builder.append(url);
        builder.append(", tags=");
        builder.append(tags);
        builder.append("]");
        return builder.toString();
    }

}