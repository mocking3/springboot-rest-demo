package net.runningcoder.web.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by wangmaocheng on 2017/11/1.
 */
@ApiModel
@Data
public class CreateTokenDto {
    @ApiModelProperty(value="用户名", required = true)
    @NotBlank
    private String username;
    @ApiModelProperty(value="密码", required = true, notes = "md5加密后的值")
    @NotBlank
    private String password;
}
