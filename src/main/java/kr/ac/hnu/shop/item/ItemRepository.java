package kr.ac.hnu.shop.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // 쿼리문 - select * from item where name = '상품' and description = '테스트'
    // 쿼리문 - select * from item where name like = '%상품%' and description = '%테스트%'
    List<Item> findByName(String name);
    List<Item> findByNameIsContainingIgnoreCase(String name);
    // -> 쿼리 메서드 / 규칙 : find + (엔티티 이름) + By + 변수 이름
}
