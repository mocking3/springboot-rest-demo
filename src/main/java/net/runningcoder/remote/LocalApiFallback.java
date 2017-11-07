package net.runningcoder.remote;

import net.runningcoder.web.dto.rsp.base.RspDto;

/**
 * Created by wangmaocheng on 2017/11/7.
 */
public class LocalApiFallback implements LocalApi {
    @Override
    public RspDto getErrorCodes() {
        return new RspDto(null);
    }
}
