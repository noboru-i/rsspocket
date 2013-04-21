package pocket4j.action.add;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pocket4j.action.Action;

public class AddAction implements Action {
    private String url;
    private String title;
    private Set<String> tags;
    private String tweetId;

    public static AddAction createInstance(final Map<String, String> map) {
        final AddAction options = new AddAction();
        options.setUrl(map.get("url"));
        options.setTitle(map.get("title"));
        final String tagsStr = map.get("tags");
        if (tagsStr != null && tagsStr.length() != 0) {
            final List<String> tags = new ArrayList<>();
            tags.addAll(Arrays.asList(tagsStr.split(",")));
        }
        options.setTweetId(map.get("tweetId"));

        return options;
    }

    @Override
    public Method getMethod() {
        return Method.POST;
    }

    @Override
    public Map<String, String> getRequestParams() {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("url", url);
        params.put("title", title);
        params.put("tags", getTagsStr());
        params.put("tweet_id", tweetId);
        return params;
    }

    public String getTagsStr() {
        if (tags == null || tags.size() == 0) {
            return "";
        }
        final StringBuilder tagsStr = new StringBuilder();
        for (final String tag : tags) {
            if (tagsStr.length() != 0) {
                tagsStr.append(",");
            }
            tagsStr.append(tag);
        }
        return tagsStr.toString();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(final Set<String> tags) {
        this.tags = tags;
    }

    public String getTweetId() {
        return tweetId;
    }

    public void setTweetId(final String tweetId) {
        this.tweetId = tweetId;
    }

    @Override
    public String toString() {
        return "AddAction [url=" + url + ", title=" + title + ", tags=" + tags
                + ", tweetId=" + tweetId + "]";
    }

}
