package kr.ac.hnu.shop.common;

import kr.ac.hnu.shop.item.ItemDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/thymeleaf") // 각각의 메서드에서 사용하는 공통의 url 주소 추출
@Controller // 사용자가 요청하면 그 요청을 응답해줌
public class ThymeleafController {
    //@RequestMapping(value = "/thymeleaf/ex01", method = RequestMethod.GET)
    @GetMapping("/ex01")
    public String ex01(Model model) { // Model : 응답 페이지에 데이터를 전달하는 객체
        model.addAttribute("data", "타임리프 예제입니다.");
        return "/thymeleaf/ex01";
    }

    @GetMapping("/ex02")
    public String ex02(Model model) {
        // 데이터 전달 객체(Data Transfer Object)
        // Builder Pattern
        ItemDTO item = ItemDTO.builder()
                .name("테스트 상품")
                .price(10000)
                .description("테스트 상품 상세 설명")
                .registerDate(LocalDateTime.now())
                .build();
        model.addAttribute("item", item);
        return "thymeleaf/ex02";
    }

    @GetMapping("/ex03")
    public String ex03(Model model) {
        return "/thymeleaf/ex03";
    }

    @GetMapping("/ex04")
    public String ex04(Model model) {
        return "/thymeleaf/ex04";
    }

    @GetMapping("/ex05")
    public String ex05(Model model) {
        return "thymeleaf/ex05";
    }

    @GetMapping("/ex06")
    public String ex06(String name, int age, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "thymeleaf/ex06";
    }

    // @ModelAttribute
    // 부착 위치 : 파라미터, 메서드,
    // 내부적으로 @ModelAttribute("items", list); 의 작업을 함
    // ex3과 ex4의 중복코드를 제거함
    @ModelAttribute("items")
    public List<ItemDTO> items() {
        List<ItemDTO> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ItemDTO item = ItemDTO.builder()
                    .name("테스트 상품" + i)
                    .price(10000 * i)
                    .description("테스트 상품 상세 설명" + i)
                    .registerDate(LocalDateTime.now())
                    .build();
            list.add(item);
        }
        return list;
    }

    @GetMapping("/ex07")
    public String ex07() {
        return "thymeleaf/ex07";
    }
}
