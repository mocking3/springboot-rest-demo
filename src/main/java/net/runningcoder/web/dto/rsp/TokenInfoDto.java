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
public class TokenInfoDto {
    @ApiModelProperty("访问token")
    private String accessToken;
    @ApiModelProperty("失效时间，单位秒")
    private Long expiresIn;
    @ApiModelProperty("刷新token")
    private String refreshToken;
    @ApiModelProperty("用户信息")
    private Map<String, Object> userInfo;
}
