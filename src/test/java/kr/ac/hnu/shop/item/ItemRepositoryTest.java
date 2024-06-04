package kr.ac.hnu.shop.item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@DataJpaTest - 따로따로 테스트 하는 것 (주로 사용, 빠른 테스트 가능)
@SpringBootTest // 전체의 bean에 대해 테스트 (상대적으로 시간이 더 걸림)
// suite test - 종합 테스트
class ItemRepositoryTest {
    @Autowired
    private ItemRepository repository;

    // Unit test - 단위 테스트
    @Test
    void createItemTest() {
        Item item = new Item();
        item.setName("테스트 상품");
        item.setPrice(10000);
        item.setDescription("테스트 상품 상세 설명");
        item.setQuantity(100);
        item.setStatus("Selling");
        item.setRegisterDate(LocalDateTime.now());

        Item savedItem = repository.save(item);

        System.out.println("Saved Item : " + savedItem);
        assertEquals(1, savedItem.getId());
    }

    private void createItemList() {
        for(int i = 1; i <= 10; i++) {
            Item item = new Item();
            item.setName("테스트 상품" + i);
            item.setPrice(10000);
            item.setDescription("테스트 상품 상세 설명" + i);
            item.setQuantity(100 + i);
            item.setStatus("Selling");
            item.setRegisterDate(LocalDateTime.now());
            repository.save(item);
        }
    }
    @Test
    @DisplayName("상품명 조회 테스트")
    void findByNameTest() {
        createItemList();
        List<Item> itemList = repository.findByNameIsContainingIgnoreCase("상품1");
        assertEquals(2, itemList.size());
    }
}