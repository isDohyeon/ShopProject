package kr.ac.hnu.shop;

import kr.ac.hnu.shop.item.ItemRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {

    private ItemRepository repository;
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

}
