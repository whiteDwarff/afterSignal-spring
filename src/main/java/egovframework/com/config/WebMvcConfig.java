package egovframework.com.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	// 이미지 저장되는 루트 경로
	@Value("${server.file.path}")
	private String filePath;
	
	// 서비스 사용자 이미지 저장 경로
	@Value("${server.dir.serviceUser}")
	private String serviceUserDir;
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    
    // 서비스 사용자 프로필 이미지
    registry.addResourceHandler(serviceUserDir + File.separator + "**")
            .addResourceLocations("file://" +  filePath + serviceUserDir + File.separator);
  }
    
}
