package kr.ac.hnu.shop.item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//@Data - 대신 Getter와 NoArgsConstructor 사용 현업에서는 Setter를 잘 만들지 않는다 - 데이터 오염 방지
@Getter
@NoArgsConstructor
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
