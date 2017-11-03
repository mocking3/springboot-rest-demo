package net.runningcoder.web.security;

import com.google.common.collect.Lists;
import com.google.common.primitives.Longs;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.runningcoder.config.AppProperties;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by wangmaocheng on 2017/11/1.
 */
@Component
public class TokenManager {

    public static final String SCOPE_REFRESH_TOKEN = "REFRESH_TOKEN";

    @Autowired
    private AppProperties properties;

    public Token createToken(UserContext  userContext) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long accessTokenExpiresIn = properties.getSecurity().getJwt().getAccessTokenExpiresIn();
        String accessToken = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(nowMillis + accessTokenExpiresIn * 1000))
                .claim("id", userContext.getId() + "")
                .claim("username", userContext.getUsername())
                .claim("name", userContext.getName())
                .claim("scopes", userContext.getScopes())
                .signWith(SignatureAlgorithm.HS256, generalKey())
                .compact();
        Long refreshTokenExpiresIn = properties.getSecurity().getJwt().getRefreshTokenExpiresIn();
        String refreshToken = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(nowMillis + refreshTokenExpiresIn * 1000))
                .setSubject(userContext.getUsername())
                .claim("scopes", Lists.newArrayList(SCOPE_REFRESH_TOKEN))
                .signWith(SignatureAlgorithm.HS256, generalKey())
                .compact();
        return new Token(accessToken, accessTokenExpiresIn, refreshToken);
    }

    public String getSubjectByRefreshToken(String refreshToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(properties.getSecurity().getJwt().getSecret())
                .parseClaimsJws(refreshToken)
                .getBody();
        return claims.getSubject();
    }

    public UserContext parseUserByAccessToken(String accessToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(properties.getSecurity().getJwt().getSecret())
                .parseClaimsJws(accessToken)
                .getBody();
        UserContext userContext = new UserContext(
                Longs.tryParse((String) claims.get("id")),
                (String) claims.get("username"),
                (String) claims.get("name"),
                (List<String>) claims.get("scopes")
        );
        return userContext;
    }

    private String generalKey() {
        String key = properties.getSecurity().getJwt().getSecret();
        return Base64.encodeBase64String(key.getBytes());
    }

}
