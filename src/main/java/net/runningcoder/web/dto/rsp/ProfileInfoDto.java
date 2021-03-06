package net.runningcoder.web.dto.rsp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wangmaocheng on 2017/11/1.
 */
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfileInfoDto {
    @ApiModelProperty("姓名")
    private String name;
}
