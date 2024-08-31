package egovframework.config.security;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal( HttpServletRequest request, 
    								 HttpServletResponse response, 
    								 FilterChain chain
    							    )  throws ServletException, IOException {
    	// 요청에서 JWT 토큰 추출
        String token = jwtTokenProvider.resolveToken(request);
        logger.debug("@@@@@@@@ JWT실행 : " + token);
        
        
        // 토큰이 존재하고 validateToken을 통해 검증이 통과되면 검증 통과
        if (token != null && jwtTokenProvider.validateToken(token, jwtTokenProvider.extractUsername(token))) {
            // 토큰에서 사용자 이름 추출
        	String username = jwtTokenProvider.extractUsername(token);
            // 사용자 이름과 권한 정보 담기
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    						username, 
                    						null,
                    						Collections.emptyList());
            // Security의 SecurityContext에 인증 정보를 저장
            SecurityContextHolder.getContext().setAuthentication(auth);
            logger.debug("@@@@@@@@ 검증성공");

        } else {
        	logger.debug("@@@@@@@@@@@@@ 검증 실패 ");
        }
        chain.doFilter(request, response);
    }
}

