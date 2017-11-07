package net.runningcoder.web.dto.rsp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Created by wangmaocheng on 2017/10/31.
 */
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorCodeInfoDto {
    @ApiModelProperty("错误码")
    private Integer code;
    @ApiModelProperty("错误描述")
    private String message;
}
