package net.runningcoder.web.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wangmaocheng on 2017/11/3.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    private String accessToken;
    private Long expiresIn;
    private String refreshToken;
}
