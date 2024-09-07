package org.example.be_sua.global.security.auth;

import lombok.RequiredArgsConstructor;
import org.example.be_sua.domain.user.domain.repository.UserRepository;
import org.example.be_sua.domain.user.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 사용자 인증을 처리하는 역할
// 로그인 과정에서 사용자의 세부 정보를 로드해 인증과 권한 검증을 도와줌

@RequiredArgsConstructor
@Service
public class AuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    // loadUserByUsername : 필수로 구현해야 하는 메서드, 사용자의 accountId를 이용해 사용자 정보를 로드
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        return userRepository.findByAccountId(accountId)
                .map(AuthDetails::new)
                // AuthDetails::new : 사용자가 존재할 경우, 해당 User 객체를 AuthDetails 객체로 반환
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
