package org.example.be_sua.global.security.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.be_sua.domain.user.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@RequiredArgsConstructor
public class AuthDetails implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    } //사용자에게 부여된 권환 반환


    @Override
    public String getPassword() {
        return null;
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
    } //사용자 잠금 여부

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    } //사용자 암호 만료 여부

    @Override
    public boolean isEnabled() {
        return true;
    } //사용자 활성화 여부
}
