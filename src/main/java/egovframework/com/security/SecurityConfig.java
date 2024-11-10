
package egovframework.com.security;

import egovframework.com.jwt.JwtAuthenticationEntryPoint;
import egovframework.com.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	 // 사용자 Get 요청 시 인증예외 List
    private String[] USER_GET_WHITELIST = {
    	"/serviceUser/profile/*"		// 사용자 프로필 이미지 경로
    };
    
    // 스토어 Get 요청 시 인증예외 List
    private String[] STORE_GET_WHITELIST = {
    		
    };
    

    // 사용자 인증 예외 List
    private String[] USER_AUTH_WHITELIST = {
    	"/api/common/*",
    	"/api/user/signUp",
    	"/api/user/signUpUser",
    	"/api/user/signInUser",
    	"/api/user/duplicatedInfoCheck",
    	"/api/user/findUserEmail",
    };
    // 스토어 인증 예외 List
    private String[] STORE_AUTH_WHITELIST = {
    	"/api/store/duplicatedEmailCheck",	// 이메일 중복 검사
    	"/api/store/apply",					// 가입신청 
    	"/api/store/login",
    	"/api/store/getStoreInfo",			// 이메일 중복 검사
    };
    
    
    // CORS 요청 목록
    private static final String[] ORIGINS_WHITELIST = {
            "http://localhost:9000",
    };

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }


    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // 쿠키 수신 여부 설정
        configuration.setAllowCredentials(true);
        // CORS 허용 port
        configuration.setAllowedOrigins(Arrays.asList(ORIGINS_WHITELIST));
        // configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        // 허용할 HTTP 메서드 설정
        configuration.setAllowedMethods(Arrays.asList("HEAD", "POST", "GET", "DELETE", "PUT", "PATCH"));
        // 요청 시 사용할 수 있는 헤더 목록을 설정 Authorization, Cache-Control, Content-Type...
        configuration.setAllowedHeaders(Arrays.asList("*"));

        // CORS 설정 완료
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                		 // hasRole()   : 특정 권한을 가진 사용자만 접근 가능 
                		.antMatchers("/admin/**").hasRole("ADMIN") //ROLE_생략=자동으로 입력됨
                         // permitAll() : 소스의 접근을 인증절차 없이 허용
                		.antMatchers(USER_AUTH_WHITELIST).permitAll()
                		.antMatchers(STORE_AUTH_WHITELIST).permitAll()
                		 // get 요청 시 인증절차 없이 허용
                        .antMatchers(HttpMethod.GET,USER_GET_WHITELIST).permitAll()
                        .antMatchers(HttpMethod.GET,STORE_GET_WHITELIST).permitAll()
                        .anyRequest().authenticated()
                ).sessionManagement((sessionManagement) ->
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .cors().and()
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandlingConfigurer ->
                        exceptionHandlingConfigurer
                                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                )
                .build();
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
