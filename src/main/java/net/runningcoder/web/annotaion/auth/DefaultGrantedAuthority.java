package net.runningcoder.web.annotaion.auth;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangmaocheng on 2017/11/2.
 */
@Component
public class DefaultGrantedAuthority implements GrantedAuthority {
    @Override
    public List<String> getAuthorities(List<String> scopes) {
        return CollectionUtils.isEmpty(scopes) ? Lists.newArrayList() : scopes;
    }
}
