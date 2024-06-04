package kr.ac.hnu.shop.item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

//@Data - 대신 Getter와 NoArgsConstructor 사용 현업에서는 Setter를 잘 만들지 않는다 - 데이터 오염 방지
@Getter
@Setter
@ToString // 필드의 데이터들이 어떤 데이터가 들어가있는지 확인하기 용이
@NoArgsConstructor
// -> Data 어노테이션에 자동으로 포함되는 것들 - hashcode() equals()가 제외됨
@Entity
public class Item {
    // add in attribute - DB의 실제 데이터가 저장되는 곳, Create Table과 같음
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int price;
    private int quantity;
    private String description;
    private String status;
    private LocalDateTime registerDate;
    private LocalDateTime modifiedDate;

}
