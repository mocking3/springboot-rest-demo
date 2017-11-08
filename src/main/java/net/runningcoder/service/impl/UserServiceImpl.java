package net.runningcoder.service.impl;

import net.runningcoder.domain.User;
import net.runningcoder.persistence.UserRepository;
import net.runningcoder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangmaocheng on 2017/11/8.
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }
}
