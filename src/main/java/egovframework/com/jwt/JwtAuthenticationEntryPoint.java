package egovframework.com.jwt;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.payload.ApiResponse;
import egovframework.payload.ExceptionEnum;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

	  @Override
	  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

		    ApiResponse result = new ApiResponse();
		    result.setStatus(403);
		    result.setMessage(ExceptionEnum.USER_004.getMessage());
	        ObjectMapper mapper = new ObjectMapper();

	        //Convert object to JSON string
	        String jsonInString = mapper.writeValueAsString(result);
	        
	        response.setStatus(HttpStatus.UNAUTHORIZED.value());
	        response.setContentType(MediaType.APPLICATION_JSON.toString());
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().println(jsonInString);
	  }
}
