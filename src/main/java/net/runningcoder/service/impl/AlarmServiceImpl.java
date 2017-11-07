package net.runningcoder.service.impl;

import net.runningcoder.service.AlarmService;
import org.springframework.stereotype.Service;

/**
 * Created by wangmaocheng on 2017/11/6.
 */
@Service
public class AlarmServiceImpl implements AlarmService {
    public boolean sendMessage(String to, String content, boolean isSync) {
        return false;
    }
}
