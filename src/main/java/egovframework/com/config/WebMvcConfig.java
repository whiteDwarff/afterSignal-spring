package egovframework.com.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import egovframework.com.security.CustomAuthenticationPrincipalResolver;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
	
	// ---------------------------------------------------------
	// addResourceHandlers : 이미지 경로 설정
	
	// 이미지 저장되는 루트 경로
	@Value("${server.file.path}")
	private String filePath;
	
	// 서비스 사용자 이미지 저장 경로
	@Value("${server.dir.serviceUser}")
	private String serviceUserDir;
	
	// 스토어 이미지 저장 경로
	@Value("${server.dir.store}")
	private String storeDir;
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	// 서비스 사용자 프로필 이미지
   	    registry.addResourceHandler(serviceUserDir  + "/**")
   	    		.addResourceLocations("file://" + filePath + serviceUserDir + "/");
   	    
   	    // 스토어 사용자 파일경로 설정
	   	 registry.addResourceHandler(storeDir + "/**")
	   	 	.addResourceLocations("file://" + filePath + storeDir + "/");

  }
    
    // ---------------------------------------------------------
 	// addArgumentResolvers : 시큐리티 설정
    
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new CustomAuthenticationPrincipalResolver());
    }
    
}
