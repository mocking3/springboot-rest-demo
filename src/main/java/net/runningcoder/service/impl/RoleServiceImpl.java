package net.runningcoder.service.impl;

import com.google.common.collect.Lists;
import net.runningcoder.domain.Role;
import net.runningcoder.persistence.RoleRepository;
import net.runningcoder.service.RoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangmaocheng on 2017/11/8.
 */
@Transactional
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRole(String roleCode) {
        return roleRepository.findByCode(roleCode);
    }

    @Override
    public List<String> getAuthorityCodes(String roleCode) {
        Role role = roleRepository.findByCode(roleCode);
        if (role != null && CollectionUtils.isNotEmpty(role.getAuthorityCodes())) {
            return role.getAuthorityCodes();
        } else {
            return Lists.newArrayList();
        }
    }
}
