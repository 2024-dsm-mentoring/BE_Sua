package org.example.be_sua.global.security.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.be_sua.domain.user.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

// Spring Security가 사용자 인증 및 권한을 처리할 수 있게 함
// 계정의 만료, 잠금, 활성화 여부와 같은 정보를 통해 사용자의 상태를 관리

@Getter
@RequiredArgsConstructor
public class AuthDetails implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    } // 사용자의 권한을 반환하는 메서드

    @Override
    public String getPassword() {
        return user.getPassword();
    } //사용자 인증 암호 반환


    @Override
    public String getUsername() {
        return user.getAccountId();
    } //사용자 인증 이름 반환


    @Override
    public boolean isAccountNonExpired() {
        return true;
    } //사용자 계정 만료 여부

    @Override
    public boolean isAccountNonLocked() {
        return true;
    } //사용자 계정 잠금 여부

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    } //사용자 암호 만료 여부

    @Override
    public boolean isEnabled() {
        return true;
    } //사용자 활성화 여부
}
