package org.example.be_sua.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //CORS 규칙을 정의
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로에 대해 CORS 설정을 적용
                .allowedMethods("POST", "PUT", "PATCH", "GET", "DELETE") // 서버에서 허용하는 HTTP 메서드를 정의
                .allowedHeaders("*") // 요청에서 모든 헤더를 허용
                .allowCredentials(true) // 클라가 쿠키와 인증 정보를 요청에 포함시킬 수 있도록 허용
                .allowedOriginPatterns("*"); // 모든 출처에서 요청을 허용
    }
}
