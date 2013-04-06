package hm.orz.chaos114.gae.rsspocket.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public abstract class BaseApiController extends Controller {

    private String body;

    @Override
    protected Navigation setUp() {

        try (final BufferedReader br =
                new BufferedReader(new InputStreamReader(
                        request.getInputStream(), "UTF-8"))) {
            String line;
            final StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            body = sb.toString();
        } catch (final IOException e) {
            // no-op
        }
        return super.setUp();
    }

    public String getBody() {
        return body;
    }

    /**
     * レスポンスにテキストを書き込みます。
     * 
     * @param status
     *            ステータスコード
     * @param text
     *            テキスト
     */
    public void responseWriter(final int status, final String text)
            throws IOException {
        response.setStatus(status);
        response.setContentType("application/json; charset=UTF-8");
        try (PrintWriter out =
                new PrintWriter(new OutputStreamWriter(
                        response.getOutputStream(), "UTF-8"))) {
            out.print(text);
        }
    }
}
