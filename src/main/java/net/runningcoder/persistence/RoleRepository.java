package net.runningcoder.persistence;

import net.runningcoder.domain.Role;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by wangmaocheng on 2016/4/1.
 */
public interface RoleRepository extends PagingAndSortingRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    Role findByCode(String code);
}
