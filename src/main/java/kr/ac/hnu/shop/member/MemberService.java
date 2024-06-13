package kr.ac.hnu.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Spring Security에서 지원하는 로그인
/*
    1. 인 메모리 => user/서버 시작시 나오는 비밀번호 40글자
    2. 데이터베이스 => UserDetailsService를 커스터마이징
 */
@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository repository;

    // Transactional: 하나의 일 단위 => 실패시 원상복구
    @Transactional
    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return repository.save(member);
    }
    private void validateDuplicateMember(Member member) {
        Member findMember = repository.findByEmail(member.getEmail());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    // spring security는 username이라는 이름으로 로그인을 시도한다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = repository.findByEmail(username);
        if (member == null) {
            throw new UsernameNotFoundException(username);
        }
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().name())
                .build();
    }
}