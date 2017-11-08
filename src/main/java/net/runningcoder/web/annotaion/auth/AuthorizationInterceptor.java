package net.runningcoder.web.annotaion.auth;

import net.runningcoder.web.RestException;
import net.runningcoder.web.RspCode;
import net.runningcoder.web.security.UserContext;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * Created by wangmaocheng on 2017/11/1.
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private GrantedAuthority grantedAuthority;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //如果认证token失败，并且方法注明了Authentication，抛出异常
        if (method.getAnnotation(Authorization.class) != null) {
            UserContext userContext = (UserContext) request.getAttribute("userContext");
            String[] operations = method.getAnnotation(Authorization.class).value();
            Collection<String> authorities = grantedAuthority.getAuthorities(userContext.getScopes());
            if (!ArrayUtils.isEmpty(operations)) {
                if (CollectionUtils.isEmpty(authorities))
                    throw new RestException(RspCode.PERMISSION_DENIED);

                boolean hasPermission = false;
                for (String operation : operations) {
                    if (authorities.contains(operation)) {
                        hasPermission = true;
                        break;
                    }
                }
                if (!hasPermission)
                    throw new RestException(RspCode.PERMISSION_DENIED);

            }
        }
        return true;
    }
}
