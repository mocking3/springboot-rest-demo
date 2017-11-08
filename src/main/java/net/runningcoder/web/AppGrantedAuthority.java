package net.runningcoder.web;

import com.google.common.collect.Sets;
import net.runningcoder.service.RoleService;
import net.runningcoder.web.annotaion.auth.GrantedAuthority;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by wangmaocheng on 2017/11/2.
 */
@Component
public class AppGrantedAuthority implements GrantedAuthority {

    @Autowired
    private RoleService roleService;

    @Override
    public Collection<String> getAuthorities(List<String> scopes) {
        Set<String> result = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(scopes)) {
            for (String scope : scopes) {
                result.addAll(roleService.getAuthorityCodes(scope));
            }
        }
        return result;
    }
}
