package egovframework.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtTokenProvider);

    	return http
            // 기본 인증 비활성화 (RESTful 사용)
            .httpBasic().disable()
            // Cross-Site Request Forgery 비활성화 (토큰 기반 인증)
            .csrf().disable()
            // 세션 사용 X
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            // 요청에 대한 권한을 설정
            .authorizeHttpRequests(authorize -> authorize
            //  .antMatchers("/test").authenticated()  // 인증된 사용자만 요청 허용
            .antMatchers("/api/test").authenticated()  
            //.antMatchers("/test").permitAll()  // 모든 요청 허용
            // .antMatchers("/members/test").hasRole("USER")  // USER 역할을 가진 사용자만 접근 허용
            //.anyRequest().authenticated()  // 나머지 모든 요청은 인증된 사용자만 접근 가능
            )
            // 사용자 인증 전에 JWT를 검증하도록 설정
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        	.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
