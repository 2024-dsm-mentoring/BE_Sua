package org.example.be_sua.global.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.be_sua.global.error.exception.CrudException;
import org.example.be_sua.global.error.exception.ErrorCode;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor // final이 붙은 필드 생성자 자동 생성
// Spring Security에서 발생할 수 있는 예외를 처리하는 필터
public class ExceptionFilter extends OncePerRequestFilter {
    // OncePerRequest : Spring Framework에서 제공하는 필터로 단 한번만 실행된다

    private final ObjectMapper objectMapper; // JSON 데이터와 Java 객체 간의 변화을 쉽게 해주는 라이브러리

    @Override
    protected void doFilterInternal( //필터 체인에서 요청과 예외를 처리하는 메서드
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        try {
            filterChain.doFilter(request, response);
            // 필터 체인을 계속 진행 즉, 다음 필터로 요청 전달
        } catch (CrudException e) {
            sendErrorMessage(response, e.getErrorCode());
            // CrudException이 발생하면 sendErrorMessage 메서드를 호출
        } catch (Exception e) {
            logger.error(e);
            sendErrorMessage(response, ErrorCode.INTERNAL_SERVER_ERROR);
            // 그 외의 일반 예러는 INTER_SERVER_ERROR로 처리
        }
    }

    private void sendErrorMessage(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .build();
        String errorResponseJson = objectMapper.writeValueAsString(errorResponse);

        // HTTP 응답 상태 코드를 설정하는 역할
        response.setStatus(errorCode.getStatus()); //errorCode.getStatus : 각 예외에 대응하는 상태 코드를 반환
        // 컨텐츠 타입을 JSON으로 설정
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); // MediaType.APPLICATION_JSON_VALUE : 클라이언트에게 응답 데이터가 JSON 형식임을 알려줌
        // 실제로 응답 본문에 데이터를 작성하는 과정
        response.getWriter().write(errorResponseJson); // response.getWriter() : 응답을 출력하기 위한 Writer 객체를 반환
    }
}