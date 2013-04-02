package pocket4j.util;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
//import com.google.appengine.repackaged.com.google.api.client.http.HttpResponse;
//import com.google.appengine.repackaged.com.google.api.client.http.HttpResponseException;
/**
 * HTTP通信用のユーティリティクラス
 */
public final class HttpRequestUtil {

    public static String get(final String endpoint,
            final Map<String, String> params) throws IOException {

        //        // URLの組み立て
        //        final Uri.Builder builder = Uri.parse(endpoint).buildUpon();
        //        for (final String name : params.keySet()) {
        //            final String value = params.get(name);
        //            builder.appendQueryParameter(name, value);
        //        }
        //
        //        final DefaultHttpClient httpClient = new DefaultHttpClient();
        //        final HttpGet httpGet = new HttpGet(builder.build().toString());
        //        final HttpParams httpParams = httpClient.getParams();
        //        HttpConnectionParams.setConnectionTimeout(httpParams, 5000); // 接続のタイムアウトは5秒
        //        HttpConnectionParams.setSoTimeout(httpParams, 10000); // データ取得のタイムアウトは10秒
        //
        //        try {
        //            final String response = httpClient.execute(httpGet,
        //                new PocketHandler());
        //            return response;
        //        } finally {
        //            httpClient.getConnectionManager().shutdown();
        //        }
        return null;
    }

    /**
     * APサーバにPOSTリクエストを発行する。
     *
     * @param endpoint リクエストURL
     * @param params リクエストパラメータ
     * @return レスポンス文字列
     * @throws IOException 通信例外
     */
    public static String postJson(final String endpoint, final JSONObject params)
            throws IOException {
        final URLFetchService fetchService = URLFetchServiceFactory.getURLFetchService();
        final HTTPRequest request = new HTTPRequest(new URL(endpoint), HTTPMethod.POST);

        request.addHeader(new HTTPHeader("Content-Type", "application/json; charset=UTF-8"));
        request.addHeader(new HTTPHeader("X-Accept", "application/json"));
        request.setPayload(params.toString().getBytes("UTF-8"));

        final Future<HTTPResponse> future = fetchService.fetchAsync(request);

        HTTPResponse response;
        try {
            response = future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new IOException(e);
        }
        if (response.getResponseCode() != HttpServletResponse.SC_OK) {
            throw new IOException("Response code = " + response.getResponseCode());
        }

        final String responseBody = new String(response.getContent(), "UTF-8");
        return responseBody;
    }

    //    private static class PocketHandler implements ResponseHandler<String> {
    //        @Override
    //        public String handleResponse(final HttpResponse response)
    //                throws ClientProtocolException, IOException {
    //            // ステータスコードの確認
    //            final int statusCode = response.getStatusLine().getStatusCode();
    //            if (statusCode != 200) {
    //                throw new HttpResponseException(statusCode, "");
    //            }
    //
    //            // レスポンスを文字列にし、返す
    //            final String responseString = EntityUtils.toString(response
    //                .getEntity());
    //            Log.i(TAG, "response = " + responseString);
    //            return responseString;
    //        }
    //    }
}
