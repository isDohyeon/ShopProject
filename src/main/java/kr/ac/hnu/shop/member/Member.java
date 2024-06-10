package kr.ac.hnu.shop.member;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Getter
@Entity // 데이터베이스와 연동하는 객체
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role; // admin, user, guest -> enumeration 으로 관리

    @CreatedDate
    private LocalDateTime registerDate;

    public static Member createMember(MemberFormDTO dto, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.name = dto.getName();
        member.address = dto.getAddress();
        member.password = passwordEncoder.encode(dto.getPassword()); // 패스워드 암호화
        member.email = dto.getEmail();
        member.role = Role.USER;
        return member;
    }
}
