package net.runningcoder.web.controller;

import com.google.common.collect.Lists;
import net.runningcoder.web.dto.req.CreateTokenDto;
import net.runningcoder.web.dto.req.RefreshTokenDto;
import net.runningcoder.web.dto.rsp.TokenInfoDto;
import net.runningcoder.web.security.TokenManager;
import net.runningcoder.web.security.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private TokenManager tokenManager;

    @PostMapping
    @ResponseBody
    public TokenInfoDto createToken(@RequestBody CreateTokenDto reqDto) {
        UserContext userContext = new UserContext(1l, "wangmaocheng", "王茂成",
                Lists.newArrayList("ROLE_ADMIN", "ROLE_EDITOR"));
        String token = tokenManager.createToken(userContext);
        TokenInfoDto tokenInfoDto = new TokenInfoDto(token, userContext);
        return tokenInfoDto;
    }

    @PutMapping
    @ResponseBody
    public void refreshToken(@RequestBody RefreshTokenDto reqDto) {
    }

}
