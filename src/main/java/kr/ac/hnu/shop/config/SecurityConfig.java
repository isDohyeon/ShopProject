package kr.ac.hnu.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Spring Security : 필터 방식으로 작동 -
@Configuration
@EnableWebSecurity // security filter chain을 자동으로 등록
public class SecurityConfig { // 루트로 들어갔을 때 무조건 로그인 페이지로 넘어가지 않게 함
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 로그인, 로그아웃 인증(Authentication) 관련 설정
        // 인가(Authorization)
        return http.build();
    }

    // 패스워드 암호화 방법 결정
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

