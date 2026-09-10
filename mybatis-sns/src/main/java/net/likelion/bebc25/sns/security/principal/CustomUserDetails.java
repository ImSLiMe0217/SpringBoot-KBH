package net.likelion.bebc25.sns.security.principal;

import net.likelion.bebc25.sns.domain.Member;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final Member member;

    public CustomUserDetails(Member member) {
        this.member = member;
    }

    // 사용자 정보가 전부 들어있는 Member 객체를 반환
    public Member getMember() {
        return member;
    }

    public Long getId() {
        return this.member.getId();
    }

    // 권한 목록을 반환 (ROLE)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 권한 문자열을 Spring Security의 SimpleGrantedAuthority 타입으로 변환
        return List.of(new SimpleGrantedAuthority(member.getRole()));
    }

    // 비밀번호를 반환
    @Override
    public @Nullable String getPassword() {
        return member.getPassword();
    }

    // 사용자의 식별자를 반환
    @Override
    public String getUsername() {
        return member.getEmail();
    }

    // 계정 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired(); // 기본값: true
    }

    // 계정 잠김(비밀번호 오류 반복 등) 여부
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();  // 기본값: true
    }

    // 인증정보 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired(); // 기본값: true
    }

    // 계정 활성화(휴면상태 체크) 여부
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();   // 기본값: true
    }
}
