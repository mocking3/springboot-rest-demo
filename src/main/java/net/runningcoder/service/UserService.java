package net.runningcoder.service;

import net.runningcoder.domain.User;

/**
 * Created by wangmaocheng on 2017/11/8.
 */
public interface UserService {

    User getUser(String username);
}
