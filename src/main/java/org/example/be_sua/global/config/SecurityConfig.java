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

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

   @Bean
    public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }

   @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http
               .csrf(CsrfConfigurer::disable)
               .cors(CorsConfigurer::disable)
               .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

               .authorizeHttpRequests(auth ->
                       auth
                               .requestMatchers("/users/signup", "/users/duplicate/", "/users/login", "/users/reissue", "admin/signuup", "admin/login").permitAll()
                               .requestMatchers("/users/haha").hasRole("USER")
                               .anyRequest().authenticated()
               )
               .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
               .addFilterBefore(new ExceptionFilter(objectMapper), JwtTokenFilter.class);

       return http.build();
   }
}
