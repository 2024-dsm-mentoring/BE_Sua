package org.example.be_sua.global.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.be_sua.domain.auth.domain.RefreshToken;
import org.example.be_sua.domain.auth.domain.repository.RefreshTokenRepository;
import org.example.be_sua.global.exception.InvalidJwtException;
import org.example.be_sua.global.exception.ExpiredJwtException;
import org.example.be_sua.global.security.auth.AuthDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperty jwtProperty;
    private final AuthDetailsService authDetailsService;

    public String generateAccessToken(String accountId) {
        return generateToken(accountId, "access", jwtProperty.getAccessExp());
    }

    public String generateRefreshToken(String accountId) {
        String refreshToken = generateToken(accountId, "refresh", jwtProperty.getRefreshExp());
        refreshTokenRepository.save(RefreshToken.builder()
                .accountId(accountId)
                .refreshToken(refreshToken)
                .build());

        return refreshToken;
    }

    public String generateToken(String accountId, String type, Long exp) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtProperty.getSecretKey())
                .setSubject(accountId)
                .claim("type", type)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(jwtProperty.getHeader());
        return parseToken(bearer);
    }

    public String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(jwtProperty.getPrefix())) {
            return bearerToken.replace(jwtProperty.getPrefix(), "");
        }
        return null;
    }

    public Authentication authentication(String token) {
        UserDetails userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getTokenSubject(String token) {
        return getTokenBody(token).getSubject();
    }

    public Claims getTokenBody(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperty.getSecretKey())
                    .parseClaimsJws(token).getBody();
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            throw ExpiredJwtException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidJwtException.EXCEPTION;
        }
    }


}
