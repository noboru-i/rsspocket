package hm.orz.chaos114.gae.rsspocket.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class AuthFilter implements Filter {

    private Pattern includePattern;

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        final String include = filterConfig.getInitParameter("include");
        includePattern = Pattern.compile(include);
    }

    @Override
    public void doFilter(final ServletRequest request,
            final ServletResponse response, final FilterChain chain)
                    throws IOException, ServletException {

        final UserService userService = UserServiceFactory.getUserService();
        final String currentUrl =
                ((HttpServletRequest) request).getRequestURI();
        if (!userService.isUserLoggedIn() && isIncludePath(currentUrl)) {
            // 指定されたURLへのアクセスの場合、ログイン画面にリダイレクトする
            ((HttpServletResponse) response).sendRedirect("/?error=no_login");
            return;
        }

        request.setAttribute("login", userService.isUserLoggedIn());
        request.setAttribute("user", userService.getCurrentUser());
        request.setAttribute("loginUrl", userService.createLoginURL("/rss"));
        request.setAttribute("logoutUrl", userService.createLogoutURL("/"));
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // no-op
    }

    /**
     * リクエストされたURLが除外対象か判断する。
     * 
     * @param currentUrl
     *            リクエストされたURL
     * @return 除外対象の場合true
     */
    private boolean isIncludePath(final String currentUrl) {
        final Matcher matcher = includePattern.matcher(currentUrl);
        return matcher.find();
    }
}
