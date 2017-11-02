package net.runningcoder.web.security;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by wangmaocheng on 2017/11/1.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserContext {
    private Long id;
    private String username;
    private String name;
    private List<String> scopes = Lists.newArrayList();
}
