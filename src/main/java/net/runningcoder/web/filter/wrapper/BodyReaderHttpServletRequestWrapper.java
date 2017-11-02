package net.runningcoder.web.filter.wrapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Created by wangmaocheng on 2017/2/28.
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private final byte[] rawData;

    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request)
            throws IOException {
        super(request);
        String string = IOUtils.toString(request.getInputStream());
        if (StringUtils.isNotBlank(string)) {
            try {
                JSONObject jsonObject = JSONObject.parseObject(string);
                for (Map.Entry<String, Object> objectEntry : jsonObject.entrySet()) {
                    if (objectEntry.getValue() != null && objectEntry.getValue() instanceof String) {
                        jsonObject.put(objectEntry.getKey(), StringEscapeUtils.escapeHtml4((String) objectEntry.getValue()));
                    }
                }
                string = jsonObject.toString();
            } catch (Exception e) {
                string = "{}";
            }
        }
        rawData = string.getBytes();
    }

    @Override
    public BufferedReader getReader() throws IOException {
        if (rawData == null)
            return null;

        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (rawData == null)
            return null;

        final ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }
}
