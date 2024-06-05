package kr.ac.hnu.shop.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // 쿼리문 - select * from item where name = '상품' and description = '테스트'
    // 쿼리문 - select * from item where name like = '%상품%' and description = '%테스트%'
    List<Item> findByName(String name);
    List<Item> findByNameIsContainingIgnoreCase(String name);
    // -> 쿼리 메서드 / 규칙 : find + (엔티티 이름) + By + 변수 이름

    // 상품(item)을 상품명(name)과 상품 상세 설명(description)을 or 조건을 이용해서 조회
    // -> 쿼리 : select * from item where name = ? of description = ?
    List<Item> findByNameOrDescription(String name, String description);

    // price 파라미터로 넘어온 값보다 작은 상품 데이터 조회
    // select * from item where price < ?
    List<Item> findByPriceLessThan(int price);

    // price 파라미터로 넘어온 값보다 큰 상품 데이터를 조회, 단 상품 가격이 높은 순으로 조회
    // 상품 가격이 높은 상품이 위에 배치되고 낮은 상품은 아래쪽에 배치 -> 내림차순(desc)
    // 오름차순일 경우 asc
    // select * from item where price > ? order by price
    List<Item> findByPriceGreaterThanOrderByPriceDesc(int price);

    // @Query을 이용한 JPQL(Java Persistence Query Language)
    // 파라미터로 넘어온 값이 상품 상세 설명에 포함되어 있는 상품을 조회, 단 상품 가격이 높은 순으로 조회
    // select * from item where description like '%?%' order by price desc
    @Query("select i from Item i where i.description like %:description% order by i.price desc")
    List<Item> findByDescription(@Param("description") String description);


}

