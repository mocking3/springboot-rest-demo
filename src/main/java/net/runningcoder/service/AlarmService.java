package net.runningcoder.service;

/**
 * Created by wangmaocheng on 2017/11/6.
 */
public interface AlarmService {
    boolean sendMessage(String to, String content, boolean isSync);
}
