package net.runningcoder.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangmaocheng on 2017/11/1.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private Security security;

    @Data
    public static class Security {
        private String authHeader = "Authorization";
        private String authPrefix = "Bearer ";
        private Jwt jwt;
    }

    @Data
    public static class Jwt {
        private Long accessTokenExpiresIn;
        private Long refreshTokenExpiresIn;
        private String secret;
    }
}


