package kr.ac.hnu.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    // MemberRepository 를 자동으로 찾아서 주입시켜줌 (@AutoWired)
    private final MemberRepository repository;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return repository.save(member);
    }
//    public MemberService(MemberRepository repository) { // 생성자 주입 방식 -> @RequiredArgsConstructor
//        this.repository = repository;
//    }
    

    private void validateDuplicateMember(Member member) {
        Member findMember = repository.findByEmail(member.getEmail());
        if (findMember == null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
