package org.example.be_sua.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.be_sua.global.error.ExceptionFilter;
import org.example.be_sua.global.security.jwt.JwtTokenFilter;
import org.example.be_sua.global.security.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // Spring에서 설정 클래스로 인식
@EnableWebSecurity // Spring Security 기능 활성화
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 생성
//Spring Security 설정을 관리하는 코드
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper; // Java에서 JSON데이터와 Java 객체 간의 변환을 쉽게 해주는 라이브러리

    //비밀번호를 암호화 하기 위한 PasswordEncoder 빈 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }

   //Spring Security의 필터 체인을 설정하는 부분
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http
               //csrf, cors 비활성화 : JWT 기반 인증에서는 주로 상태가 없는 요청을 다루기 때문
               .csrf(CsrfConfigurer::disable) // CsrfConfigurer : CSRF 공격을 방지하기 위한 설정을 구성하는 클래스
               .cors(CorsConfigurer::disable) // CorsConfigurer : CORS 설정을 구성하는 데 사용되는 클래스
               .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               //Spring Security가 세션을 사용하지 않도록 설정 : JWT를 사용할땐 서버가 클라이언트 상태를 저장하지 않기 때문

               //특정 요청 경로에 대해 인증을 허용하거나 요청
               .authorizeHttpRequests(auth ->
                       auth
                               //특정 경로에 대해 인증 없이 접근을 허용
                               .requestMatchers("/users/signup", "/users/login").permitAll()
                               //USER 권한이 있는 사용자만 접근 가능
                               .requestMatchers("/users/haha").hasRole("USER")
                               //그 외의 모든 요청은 인증이 필요함
                               .anyRequest().authenticated()
               )
               //JWT 토큰을 통해 인증을 처리하는 필터 추가
               .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
               //인증 및 인가 과정에서 발생하는 예외를 처리하는 필터
               .addFilterBefore(new ExceptionFilter(objectMapper), JwtTokenFilter.class);

       return http.build();
   }
}
