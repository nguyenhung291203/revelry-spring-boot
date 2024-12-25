package com.develop.revelryspringboot.security;

import com.develop.revelryspringboot.entity.Account;
import com.develop.revelryspringboot.entity.Token;
import com.develop.revelryspringboot.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class JwtUtil {
    @NonFinal
    @Value("${app.jwt-secret}")
    String SECRET_KEY;

    @NonFinal
    @Value("${app.jwt-expiration-milliseconds}")
    long EXPIRE_ACCESS_TOKEN_TIME;

    @NonFinal
    @Value("${app.jwt-expiration-refresh-token}")
    long EXPIRE_REFRESH_TOKEN_TIME;

    TokenRepository tokenRepository;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(Account account, long expireTime) {
        return Jwts.builder()
                .setSubject(account.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSigningKey())
                .compact();
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }

    public boolean validateAccessToken(String accessToken) {
        String username = extractUsername(accessToken);
        Token token = tokenRepository.findByAccessToken(accessToken).orElse(null);

        return token != null
                && username.equals(token.getAccount().getUsername())
                && token.getExpiresAccessTokenAt().isAfter(LocalDateTime.now());
    }

    public boolean validateRefreshToken(String refreshToken) {
        String username = extractUsername(refreshToken);
        Token token = tokenRepository.findByRefreshToken(refreshToken).orElse(null);
        return token != null && token.getExpiresRefreshTokenAt().isAfter(LocalDateTime.now()) && username.equals(token.getAccount().getUsername());
    }

    @Transactional
    public Token storeToken(Account account) {
        Token token = Token.builder()
                .accessToken(generateToken(account, EXPIRE_ACCESS_TOKEN_TIME))
                .refreshToken(generateToken(account, EXPIRE_REFRESH_TOKEN_TIME))
                .expiresAccessTokenAt(LocalDateTime.now().plusSeconds(EXPIRE_ACCESS_TOKEN_TIME / 1000))
                .expiresRefreshTokenAt(LocalDateTime.now().plusSeconds(EXPIRE_REFRESH_TOKEN_TIME / 1000))
                .account(account)
                .build();
        return tokenRepository.save(token);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
