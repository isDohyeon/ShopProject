package kr.ac.hnu.shop.item;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

// Data Transfer Object -> ItemDto - Pascal Case : 첫 글자는 대문자로 시작, 연결되는 첫 문자는 대문자, 약어기때문 DTO
@Data // Getter, Setter, ToString
@Builder
public class ItemDTO {
    private Long id;
    private String name;
    private int price;
    private int quantity;
    private String description;
    private String status;
    private LocalDateTime registerDate;
    private LocalDateTime modifiedDate;
}
