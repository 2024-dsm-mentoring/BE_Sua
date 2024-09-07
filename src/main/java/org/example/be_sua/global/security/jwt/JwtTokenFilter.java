package org.example.be_sua.global.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor // final이 붙은 필드 생성자 자동 생성
@Component // 빈으로 등록
// 매 요청마다 JWT 토큰을 검사하고 인증을 수행, 주로 JWT 기반 인증을 처리하는 데 사용함
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        String parseToken = jwtTokenProvider.resolveToken(request); // 요청에서 JWT 토큰을 추출
        if (parseToken != null) {
            Authentication authentication = jwtTokenProvider.authentication(parseToken); // JWT 토큰을 기반으로 Authentication 객체를 생성
            SecurityContextHolder.getContext().setAuthentication(authentication); // SecurityContextHolder에 인증 정보를 설정
        }
        filterChain.doFilter(request, response);
    }
}
