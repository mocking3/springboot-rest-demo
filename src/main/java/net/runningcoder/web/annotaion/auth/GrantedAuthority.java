package net.runningcoder.web.annotaion.auth;

import java.util.Collection;
import java.util.List;

/**
 * Created by wangmaocheng on 2017/11/2.
 */
public interface GrantedAuthority {
    Collection<String> getAuthorities(List<String> scopes);
}
