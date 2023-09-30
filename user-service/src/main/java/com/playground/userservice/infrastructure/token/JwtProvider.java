package com.playground.userservice.infrastructure.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import static com.playground.core.constant.Constant.*;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperties jwtProperties;

    public String generateAccessToken(Long userId) {
        final Date issuedAt = new Date();
        final Date accessTokenExpiration = new Date(issuedAt.getTime() + jwtProperties.getAccessTokenValidity() * MILLISECOND_TO_SECOND);

        return buildAccessToken(userId, issuedAt, accessTokenExpiration);
    }

    public String generateRefreshToken(Long userId) {
        final Date issuedAt = new Date();
        final Date refreshTokenExpiration = new Date(issuedAt.getTime() + jwtProperties.getRefreshTokenValidity() * MILLISECOND_TO_SECOND);

        return buildRefreshToken(userId, issuedAt, refreshTokenExpiration);
    }

    private String buildAccessToken(Long userId, Date issuedAt, Date accessTokenExpiration) {
        return Jwts.builder()
            .setIssuer(TOKEN_ISSUER)
            .setIssuedAt(issuedAt)
            .setSubject(String.valueOf(userId))
            .claim(TOKEN_TYPE, ACCESS_TOKEN)
            .setExpiration(accessTokenExpiration)
            .signWith(getSecretKey())
            .compact();
    }

    private String buildRefreshToken(Long userId, Date issuedAt, Date accessTokenExpiration) {
        return Jwts.builder()
            .setIssuer(TOKEN_ISSUER)
            .setIssuedAt(issuedAt)
            .setSubject(String.valueOf(userId))
            .claim(TOKEN_TYPE, REFRESH_TOKEN)
            .setExpiration(accessTokenExpiration)
            .signWith(getSecretKey())
            .compact();
    }

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
    }

}
