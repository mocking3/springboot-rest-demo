package net.runningcoder.web.dto.rsp.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.commons.lang3.RandomUtils;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RspDto {

    protected Long requestId;

    private Object responseParams;

    public RspDto(Object responseParams) {
        this.requestId = System.currentTimeMillis() * 10000 + RandomUtils.nextInt(0, 10000);
        this.responseParams = responseParams;
    }
}
