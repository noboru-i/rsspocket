package hm.orz.chaos114.gae.rsspocket.util.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ServletInputStream;

public class BufferedServletInputStream extends ServletInputStream {

    private final ByteArrayInputStream inputStream;

    public BufferedServletInputStream(final byte[] buffer) {
        inputStream = new ByteArrayInputStream(buffer);
    }

    @Override
    public int available() throws IOException {
        return inputStream.available();
    }

    @Override
    public int read() throws IOException {
        return inputStream.read();
    }

    @Override
    public int read(final byte[] b, final int off, final int len)
            throws IOException {
        return inputStream.read(b, off, len);
    }

}
