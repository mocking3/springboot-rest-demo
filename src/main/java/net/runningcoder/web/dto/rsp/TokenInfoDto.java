package net.runningcoder.web.dto.rsp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.runningcoder.web.security.UserContext;

/**
 * Created by wangmaocheng on 2017/10/31.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TokenInfoDto {
    private String accessToken;
    private Long expiresIn;
    private String refreshToken;
    private UserContext userInfo;
}
