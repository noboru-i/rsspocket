package hm.orz.chaos114.gae.rsspocket.util.servlet;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class BufferedServletRequestWrapper extends HttpServletRequestWrapper {
    private final byte[] buffer;

    public BufferedServletRequestWrapper(final HttpServletRequest request)
            throws IOException {
        super(request);

        final InputStream is = request.getInputStream();
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final byte buff[] = new byte[1024];
        int read;
        while ((read = is.read(buff)) > 0) {
            baos.write(buff, 0, read);
        }

        buffer = baos.toByteArray();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new BufferedServletInputStream(buffer);
    }
}
