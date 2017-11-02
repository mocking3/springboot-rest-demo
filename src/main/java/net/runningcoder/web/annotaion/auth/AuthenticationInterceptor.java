package net.runningcoder.web.annotaion.auth;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import net.runningcoder.config.AppProperties;
import net.runningcoder.web.RestException;
import net.runningcoder.web.RspCode;
import net.runningcoder.web.security.TokenManager;
import net.runningcoder.web.security.UserContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by wangmaocheng on 2017/11/1.
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AppProperties properties;

    @Autowired
    private TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //如果认证token失败，并且方法注明了@Authentication，抛出异常
        if (method.getAnnotation(Authentication.class) != null) {
            String token = request.getHeader(properties.getSecurity().getAuthHeader());
            boolean verify = method.getAnnotation(Authentication.class).verify();
            if (verify && StringUtils.isBlank(token)) {
                throw new RestException(RspCode.PARAMS_LOST);
            }
            verifyToken(token, request);
        }
        //如果认证token失败，并且方法注明了@Authorization，抛出异常
        else if (method.getAnnotation(Authorization.class) != null) {
            String token = request.getHeader(properties.getSecurity().getAuthHeader());
            if (StringUtils.isBlank(token)) {
                throw new RestException(RspCode.PARAMS_LOST);
            }
            verifyToken(token, request);
        }
        return true;
    }

    private void verifyToken(String token, HttpServletRequest request) {
        if (StringUtils.isBlank(token))
            return;

        token = token.substring(properties.getSecurity().getAuthPrefix().length(), token.length());
        UserContext userContext = null;
        try {
            userContext = tokenManager.parseUser(token);
        } catch (ExpiredJwtException e) {
            throw new RestException(RspCode.TOKEN_AUTH_EXPIRED);
        } catch (SignatureException e) {
            throw new RestException(RspCode.TOKEN_AUTH_NOT_VALID);
        }
        if (userContext != null) {
            request.setAttribute("userContext", userContext);
        } else {
            throw new RestException(RspCode.TOKEN_AUTH_NOT_VALID);
        }
    }
}
