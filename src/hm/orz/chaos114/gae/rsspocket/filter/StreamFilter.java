package hm.orz.chaos114.gae.rsspocket.filter;

import hm.orz.chaos114.gae.rsspocket.util.servlet.BufferedServletRequestWrapper;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class StreamFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(final ServletRequest request,
            final ServletResponse response, final FilterChain chain)
                    throws IOException, ServletException {
        final String currentUrl =
                ((HttpServletRequest) request).getRequestURI();
        if (currentUrl.startsWith("/_ah")) {
            // "/_ah"から始まるURLを処理すると、500や404エラーとなるため処理しない
            chain.doFilter(request, response);
            return;
        }
        final ServletRequest newRequest =
                new BufferedServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(newRequest, response);
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    }

}
