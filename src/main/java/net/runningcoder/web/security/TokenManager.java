package net.runningcoder.web.security;

import com.google.common.primitives.Longs;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.runningcoder.config.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by wangmaocheng on 2017/11/1.
 */
@Component
public class TokenManager {

    @Autowired
    private AppProperties properties;

    public UserContext parseUser(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(properties.getSecurity().getJwt().getSecret())
                .parseClaimsJws(token)
                .getBody();
        UserContext userContext = new UserContext(
                Longs.tryParse((String) claims.get("id")),
                (String) claims.get("username"),
                (String) claims.get("name"),
                (List<String>) claims.get("scopes")
        );
        return userContext;
    }

    public String createToken(UserContext userContext) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(nowMillis + properties.getSecurity().getJwt().getTtl()))
                .claim("id", userContext.getId() + "")
                .claim("username", userContext.getUsername())
                .claim("name", userContext.getName())
                .claim("scopes", userContext.getScopes())
                .signWith(SignatureAlgorithm.HS256, properties.getSecurity().getJwt().getSecret())
                .compact();
    }

}
