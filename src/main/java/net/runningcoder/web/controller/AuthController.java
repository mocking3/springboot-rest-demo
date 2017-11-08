package net.runningcoder.web.controller;

import com.google.common.collect.Lists;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import io.swagger.annotations.*;
import net.runningcoder.domain.User;
import net.runningcoder.domain.Status;
import net.runningcoder.service.UserService;
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

@Api(tags = "权限接口", description = "用来获取token凭证")
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取token", notes = "需要认证的接口，必须先获取token")
    @PostMapping
    @ResponseBody
    public TokenInfoDto createToken(@ApiParam(value = "请求dto", required = true) @RequestBody CreateTokenDto reqDto) {
        User user = userService.getUser(reqDto.getUsername());
        if (user == null || !user.getPassword().equals(reqDto.getPassword()) ||
                user.getStatus() == Status.DISABLED)
            throw new RestException(RspCode.GET_TOKEN_ERROR);

        UserContext userContext = new UserContext(user.getUsername(), Lists.newArrayList(user.getRole().getCode()));
        userContext.put("id", user.getId());
        userContext.put("name", user.getName());

        Token token = tokenManager.createToken(userContext);
        TokenInfoDto tokenInfoDto = new TokenInfoDto(
                token.getAccessToken(),
                token.getExpiresIn(),
                token.getRefreshToken(),
                userContext);
        return tokenInfoDto;
    }

    @ApiOperation(value = "刷新token", notes = "当token失效，调用此接口重新获取token")
    @PutMapping
    @ResponseBody
    public TokenInfoDto refreshToken(@ApiParam(value = "请求dto", required = true) @RequestBody RefreshTokenDto reqDto) {
        String username;
        try {
            username = tokenManager.getSubjectByRefreshToken(reqDto.getRefreshToken());
        } catch (ExpiredJwtException e) {
            throw new RestException(RspCode.TOKEN_AUTH_EXPIRED);
        } catch (SignatureException e) {
            throw new RestException(RspCode.TOKEN_AUTH_NOT_VALID);
        }
        if (StringUtils.isBlank(username))
            throw new RestException(RspCode.TOKEN_AUTH_NOT_VALID);

        User user = userService.getUser(username);
        if (user == null ||
                user.getStatus() == Status.DISABLED)
            throw new RestException(RspCode.GET_TOKEN_ERROR);

        UserContext userContext = new UserContext(user.getUsername(), Lists.newArrayList(user.getRole().getCode()));
        userContext.put("id", user.getId());
        userContext.put("name", user.getName());

        Token token = tokenManager.createToken(userContext);
        TokenInfoDto tokenInfoDto = new TokenInfoDto(
                token.getAccessToken(),
                token.getExpiresIn(),
                token.getRefreshToken(),
                userContext);
        return tokenInfoDto;
    }

}
