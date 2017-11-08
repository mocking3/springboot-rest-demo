package net.runningcoder.persistence;

import net.runningcoder.domain.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by wangmaocheng on 2016/4/1.
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findByUsername(String username);
}
