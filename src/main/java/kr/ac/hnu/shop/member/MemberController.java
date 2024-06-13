package kr.ac.hnu.shop.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.http.HttpRequest;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService service;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/members/new")
    public String newMember(Model model) {
        model.addAttribute("memberForm", new MemberFormDTO());
        return "member/memberForm";
    }

    @PostMapping("/members/new")
    public String saveMember(@Valid @ModelAttribute("memberForm") MemberFormDTO memberForm,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "member/memberForm";
        }
        Member member = Member.createMember(memberForm, passwordEncoder);
        service.saveMember(member);
        return "redirect:/";
    }
}