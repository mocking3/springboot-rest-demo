package net.runningcoder.web.dto.rsp.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Value;
import net.runningcoder.web.RspCode;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorRspDto extends RspDto {

    private int errorCode;

    private String errorMsg;

    public ErrorRspDto() {
        super(null);
        this.errorCode = RspCode.SYSTEM_ERROR.getCode();
        this.errorMsg = RspCode.SYSTEM_ERROR.getMessage();
    }

    public ErrorRspDto(RspCode rspCode) {
        super(null);
        this.errorCode = rspCode.getCode();
        this.errorMsg = rspCode.getMessage();
    }

    public ErrorRspDto(int errorCode, String errorMsg) {
        super(null);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }



}
