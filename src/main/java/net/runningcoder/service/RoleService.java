package net.runningcoder.service;

import net.runningcoder.domain.Role;

import java.util.List;

/**
 * Created by wangmaocheng on 2017/11/8.
 */
public interface RoleService {

    Role getRole(String roleCode);

    List<String> getAuthorityCodes(String roleCode);
}
