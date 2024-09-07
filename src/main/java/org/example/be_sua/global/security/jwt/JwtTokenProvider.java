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

@RequiredArgsConstructor // final이 붙은 필드에 대한 생성자 자동 생성
@Component // 이 클래스를 빈으로 등록
// JWT 토큰을 생성하고 관리하는 클래스
public class JwtTokenProvider {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperty jwtProperty;
    private final AuthDetailsService authDetailsService;

    // 액세스 토큰 생성
    public String generateAccessToken(String accountId) {
        return generateToken(accountId, "access", jwtProperty.getAccessExp());
    }

    // 리프레시 토큰 생성, 저장
    public String generateRefreshToken(String accountId) {
        String refreshToken = generateToken(accountId, "refresh", jwtProperty.getRefreshExp());
        refreshTokenRepository.save(RefreshToken.builder()
                .accountId(accountId)
                .refreshToken(refreshToken)
                .build());

        return refreshToken;
    }

    // 공통적으로 사용하는 토큰 생성 메서드 : JWT의 서명 방식, 발급자, 만료 시간 등을 설정하여 토큰 생성
    public String generateToken(String accountId, String type, Long exp) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtProperty.getSecretKey()) // 서명을 추가하는 부분
                .setSubject(accountId) // 토큰의 주체를 설정
                .claim("type", type) // 토큰 유형 저장
                .setIssuedAt(new Date()) // 발급된 시간 설정
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000)) // 만료 시간 설정
                .compact(); // 설정된 정보를 바탕으로 JWT 문자열 생성하여 반환
    }

    // HTTP 요청에서 JWT 토큰 추출 (해당 헤더에서 JWT를 가져와 토큰만 추출하여 반환)
    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(jwtProperty.getHeader()); //요청 해더에서 토큰 가져옴
        return parseToken(bearer); // 토큰에서 실제 값을 파싱하여 반환
    }

    // Authorization 헤더에서 "Bearer"를 제거하여 실제 JWT 토큰만 반환
    public String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(jwtProperty.getPrefix())) {
            return bearerToken.replace(jwtProperty.getPrefix(), "");
        }
        return null;
    }

    // 토큰을 기반으로 Authentication 객체를 생성
    public Authentication authentication(String token) {
        UserDetails userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 사용자 ID 추출
    public String getTokenSubject(String token) {
        return getTokenBody(token).getSubject();
    }

    // 토큰의 클레임을 반환(JWT를 파싱하고, 유효성 검사를 수행) : 예외가 발생하면 적절한 예외 던짐
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
