package kr.ac.hnu.shop.common;

import kr.ac.hnu.shop.item.ItemDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;

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
                .registerDate(LocalDateTime.now()).build();
        model.addAttribute("item", item);
        return "thymeleaf/ex02";
    }
    @GetMapping("/ex03")
    public String ex03(Model model) {
        return "/thymeleaf/ex03";
    }
}
