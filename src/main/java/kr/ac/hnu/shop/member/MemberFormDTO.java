package kr.ac.hnu.shop.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor // Builder 패턴과 함께 사용
@AllArgsConstructor // NoArgsConstructor 와 함께 사용
public class MemberFormDTO { // shift + F6 -> 이름 변경
    private String name;
    private String email;
    private String password;
    private String address;
}
