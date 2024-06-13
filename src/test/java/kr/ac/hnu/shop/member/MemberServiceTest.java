package kr.ac.hnu.shop.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class MemberServiceTest {
    @Autowired
    private MemberService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Member createMember() {
        MemberFormDTO formDTO = MemberFormDTO.builder()
                .email("test@gmail.com")
                .name("홍길동")
                .address("대전광역시 대덕구")
                .password("1234").build();
        return Member.createMember(formDTO, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    void saveMember() {
        Member member = createMember();
        Member savedMember = service.saveMember(member);
        assertEquals(member.getEmail(), savedMember.getEmail());
        assertEquals(member.getName(), savedMember.getName());
        assertEquals(member.getRole(), savedMember.getRole());
    }

    @Test
    @DisplayName("중복 회원 가입 테스트")
    void saveDuplicateMemberTest() {
        Member member1 = createMember();
        Member member2 = createMember();
        service.saveMember(member1);

        Throwable e = assertThrows(IllegalStateException.class, () -> service.saveMember(member2));// 실행하면
        assertEquals("이미 가입된 회원입니다.", e.getMessage());
    }
}