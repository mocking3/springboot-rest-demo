package net.runningcoder.web.security;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wangmaocheng on 2017/11/1.
 */
public class UserContext extends HashMap<String, Object> {
    public static final String USERNAME = "username";
    public static final String SCOPES = "scopes";

    public UserContext(String username, List<String> scopes) {
        put(USERNAME, username);
        put(SCOPES, scopes);
    }

    public String getUsername() {
        return (String) get(USERNAME);
    }

    public List<String> getScopes() {
        return (List<String>) get(SCOPES);
    }
}
