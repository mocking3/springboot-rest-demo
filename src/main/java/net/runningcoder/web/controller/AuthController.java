package net.runningcoder.web.controller;

import com.google.common.collect.Lists;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import net.runningcoder.web.RestException;
import net.runningcoder.web.RspCode;
import net.runningcoder.web.dto.req.CreateTokenDto;
import net.runningcoder.web.dto.req.RefreshTokenDto;
import net.runningcoder.web.dto.rsp.TokenInfoDto;
import net.runningcoder.web.security.Token;
import net.runningcoder.web.security.TokenManager;
import net.runningcoder.web.security.UserContext;
import org.apache.commons.lang3.StringUtils;
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
        Token token = tokenManager.createToken(userContext);
        TokenInfoDto tokenInfoDto = new TokenInfoDto(
                token.getAccessToken(),
                token.getExpiresIn(),
                token.getRefreshToken(),
                userContext);
        return tokenInfoDto;
    }

    @PutMapping
    @ResponseBody
    public TokenInfoDto refreshToken(@RequestBody RefreshTokenDto reqDto) {
        String username = null;
        try {
            username = tokenManager.getSubjectByRefreshToken(reqDto.getRefreshToken());
        } catch (ExpiredJwtException e) {
            throw new RestException(RspCode.TOKEN_AUTH_EXPIRED);
        } catch (SignatureException e) {
            throw new RestException(RspCode.TOKEN_AUTH_NOT_VALID);
        }
        if (StringUtils.isBlank(username))
            throw new RestException(RspCode.TOKEN_AUTH_NOT_VALID);

        UserContext userContext = new UserContext(1l, "wangmaocheng", "王茂成",
                Lists.newArrayList("ROLE_ADMIN", "ROLE_EDITOR"));
        Token token = tokenManager.createToken(userContext);
        TokenInfoDto tokenInfoDto = new TokenInfoDto(
                token.getAccessToken(),
                token.getExpiresIn(),
                token.getRefreshToken(),
                userContext);
        return tokenInfoDto;
    }

}
