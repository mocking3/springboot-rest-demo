package net.runningcoder.remote;

import feign.RequestLine;
import net.runningcoder.web.dto.rsp.base.RspDto;

/**
 * Created by wangmaocheng on 2017/11/7.
 */
public interface LocalApi {
    @RequestLine("GET /api/error-codes")
    RspDto getErrorCodes();
}
