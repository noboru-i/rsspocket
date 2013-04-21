package pocket4j;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import pocket4j.action.Action;
import pocket4j.action.retrieve.RetrieveAction;
import pocket4j.auth.Authorization;
import pocket4j.conf.Configuration;
import pocket4j.util.HttpRequestUtil;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class Pocket implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String URL_V3_ADD = "https://getpocket.com/v3/add";
    public static final String URL_V3_GET = "https://getpocket.com/v3/get";
    public static final String URL_V3_SEND = "https://getpocket.com/v3/send";

    /**
     * TODO それぞれを別クラス（同一interfaceの実装）にするべき？ 通常アクション列挙体
     */
    public enum BasicAction {
        ADD("add"), // 追加
        ARCHIVE("archive"), // archiveに移動
        READD("readd"), // archiveから戻す
        FAVORITE("favorite"), // favoriteをマークする
        UNFAVORITE("unfavorite"), // favoriteから外す
        DELETE("delete"), // 削除する
        ;

        private String actionName;

        private BasicAction(final String actionName) {
            this.actionName = actionName;
        }

        public String getActionName() {
            return actionName;
        }
    }

    private Authorization authorization;
    private final Configuration configuration;

    public Pocket(final Authorization authorization,
            final Configuration configuration) {
        this.authorization = authorization;
        this.configuration = configuration;
    }

    private String postRequest(final String url, final Action action)
            throws IOException {

        final String consumerKey = configuration.getApiKey();
        final String accessToken = authorization.getAccessToken();

        final JSONObject params = new JSONObject();
        try {
            params.put("consumer_key", consumerKey);
            params.put("access_token", accessToken);
            final Map<String, String> map = action.getRequestParams();
            if (map != null) {
                for (final String key : map.keySet()) {
                    params.put(key, map.get(key));
                }
            }
        } catch (final JSONException e) {
            throw new RuntimeException(e);
        }

        return HttpRequestUtil.postJson(url, params);
    }

    public void add(final Action action) throws IOException {

        postRequest(URL_V3_ADD, action);

        // TODO 後処理
    }

    public List<Item> retrieve(final RetrieveAction action) throws IOException {
        final String response = postRequest(URL_V3_GET, action);

        JSONObject object;
        try {
            object = new JSONObject(response);
        } catch (final JSONException e) {
            // 空のリストを返す
            return new ArrayList<Item>();
        }
        final JSONObject list = object.optJSONObject("list");
        if (list == null) {
            // listが取得できなかったため、空のリストを返す
            return new ArrayList<Item>();
        }
        final List<Item> items = new ArrayList<Item>();
        final Iterator<?> ite = list.keys();
        while (ite.hasNext()) {
            final Item item = new Item(list.optJSONObject((String) ite.next()));
            items.add(item);
        }

        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(final Item lhs, final Item rhs) {
                return lhs.getSortId() - rhs.getSortId();
            }
        });

        return items;
    }

    public void modify(final Action action) throws IOException {

        getRequest(URL_V3_SEND, action.getRequestParams());

        // TODO 後処理
    }

    private String getRequest(final String url, final Map<String, String> map)
            throws IOException {

        final String consumerKey = configuration.getApiKey();
        final String accessToken = authorization.getAccessToken();

        final Map<String, String> params = new HashMap<String, String>();
        params.put("consumer_key", consumerKey);
        params.put("access_token", accessToken);
        if (map != null) {
            for (final String key : map.keySet()) {
                params.put(key, map.get(key));
            }
        }

        return HttpRequestUtil.get(url, params);
    }

    public void setAuthorization(final Authorization authorization) {
        this.authorization = authorization;
    }

    public String fetchRequestToken(final String callbackUrl)
            throws IOException {
        final String url = configuration.getUrlV3Request();
        final JSONObject param = new JSONObject();
        try {
            param.put("consumer_key", configuration.getApiKey());
            param.put("redirect_uri", callbackUrl);
            final String response = HttpRequestUtil.postJson(url, param);
            final JSONObject respJson = new JSONObject(response);
            final String requestToken = respJson.getString("code");
            return requestToken;
        } catch (final JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAuthorizeUrl(final String requestToken,
            final String callbackUrl) {

        final Map<String, String> params = new HashMap<>();
        params.put("request_token", requestToken);
        params.put("redirect_uri", callbackUrl);
        return configuration.getUrlAuthorize() + buildQuery(params);
    }

    private String buildQuery(final Map<String, String> params) {
        final StringBuilder query = new StringBuilder();
        for (final String key : params.keySet()) {
            if (query.length() == 0) {
                query.append("?");
            } else {
                query.append("&");
            }
            query.append(key).append("=").append(params.get(key));
        }
        return query.toString();
    }

    public String fetchAccessToken(final String requestToken) {
        final String url = configuration.getUrlV3Authorize();
        final JSONObject param = new JSONObject();
        try {
            param.put("consumer_key", configuration.getApiKey());
            param.put("code", requestToken);
            final String response = HttpRequestUtil.postJson(url, param);
            final JSONObject respJson = new JSONObject(response);
            final String accessToken = respJson.getString("access_token");
            //            final String username = respJson.getString("username"); // TODO どこかで利用する

            return accessToken;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
