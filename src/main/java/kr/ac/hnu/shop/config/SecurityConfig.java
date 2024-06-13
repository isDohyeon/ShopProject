package kr.ac.hnu.shop.config;

import kr.ac.hnu.shop.member.Role;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Controller;

@Configuration
@EnableWebSecurity// security filter chain 을 자동으로 등록
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 로그인, 로그아웃 인증(Authentication)관련 설정
        // 인가, 허가(Authority)
        return http
                .formLogin(
                        c -> c
                                .loginPage("/login")
                                .defaultSuccessUrl("/")
                                .usernameParameter("email")
                                .failureUrl("/login")
                )
                .logout(
                        c -> c
                                .logoutRequestMatcher(
                                        AntPathRequestMatcher.antMatcher("/logout")
                                )
                                .logoutSuccessUrl("/")
                )
                .authorizeHttpRequests(
                        r -> r
                                .requestMatchers("/admin/**").hasRole(Role.ADMIN.name())
                                .requestMatchers(
                                        "/items/**", "/images/**", "/", "members/**",
                                        "/logout"
                                        ).hasRole(Role.USER.name())
                                .anyRequest().permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    // 패스워드 암호화 방법 결정
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer configureH2ConsoleEnable() {
        return s -> s.ignoring().requestMatchers(PathRequest.toH2Console());
    }
}