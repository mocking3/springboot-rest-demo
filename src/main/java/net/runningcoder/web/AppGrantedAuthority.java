package net.runningcoder.web;

import com.google.common.collect.Lists;
import net.runningcoder.web.annotaion.auth.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangmaocheng on 2017/11/2.
 */
@Component
public class AppGrantedAuthority implements GrantedAuthority {

    @Override
    public List<String> getAuthorities(List<String> scopes) {
        return Lists.newArrayList("OP_001");
    }
}
