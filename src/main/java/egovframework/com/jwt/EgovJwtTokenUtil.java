package egovframework.com.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import egovframework.com.cmm.LoginVO;
import egovframework.com.util.EgovStringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class EgovJwtTokenUtil implements Serializable {

	// 1분 테스트
	public static final long JWT_TOKEN_VALIDITY =  60;// * 60; 					// accessToken의 유효시간   - 60분  
	public static final long JWT_REFRESH_TOKEN_VALIDITY = 14 * 24 * 60 * 60; 	// refreshToken의 유효시간  - 2주

	
	public static final String SECRET_KEY = "afterSignal_jwt_key";
	
	/**
	 * 토큰에서 특정 key 조회
	 * @param String, String
	 * @return String
	 * */
	public String getInfoFromToken(String key, String token) {
		Claims claims = getClaims(token);
		return claims.get(key).toString();
	}

	/**
	 * 토큰에서 Claims 정보 조회
	 * @param String
	 * @return Claims
	 * */
	public Claims getClaims(String token) {
		try {
			return Jwts.parser()
						.setSigningKey(SECRET_KEY)
						.parseClaimsJws(token)
						.getBody();
		} catch(ExpiredJwtException e) {
			return e.getClaims();
		}
	}

	/**
	 * access token 생성
	 * @param LoginVO
	 * @return String
	 * */
	public String generateToken(LoginVO loginVO) {
		return doGenerateToken(loginVO, "Authorization", JWT_TOKEN_VALIDITY);
	}
	
	/**
	 * access token 생성 (redis 저장용)
	 * @param LoginVO
	 * @return String
	 * */
	public String generateRefreshToken(LoginVO loginVO) {
		return doGenerateToken(loginVO, "Authorization", JWT_REFRESH_TOKEN_VALIDITY);
	}
	
	/**
	 * jwt 토큰 검증
	 * @param String
	 * @return boolean
	 * */
	public boolean validateToken(String jwtToken) {
	    if (EgovStringUtil.isEmpty(jwtToken)) {
	        return false;  // JWT가 없으면 바로 false 반환
	    }
	    
	    try {
	        Jws<Claims> claims = Jwts.parser()
	        						 .setSigningKey(SECRET_KEY)
	                                 .setAllowedClockSkewSeconds(300)  // 5분의 시간 차이 허용
	        						 .parseClaimsJws(jwtToken);
	        
	        return !claims.getBody().getExpiration().before(new Date());
	    } catch (ExpiredJwtException e) {
	    	System.out.println("@@@@ ERROR : " + e.getMessage());
	        return false;
	    }
	}
	
	/**
	 * Response Header에 jwt 토큰 저장 
	 * @param HttpServletResponse, String, String
	 * */
	public void setHeaderAccessToken(HttpServletResponse res, String value, String accessToken) {
		res.setHeader(value, accessToken);
	}

	// 1. 토큰의 Claims 정의
	// 2. HS512 알고리즘과 비밀 키를 사용하여 JWT에 서명
	// 3. JWS 컴팩트에 따라 직렬화, JWT를 안전 문자열로 압축
	private String doGenerateToken(LoginVO loginVO, String subject, long validity) {

		Map<String, Object> claims = new HashMap<>();
		claims.put("type", subject);
		claims.put("email", loginVO.getEmail());
		claims.put("auth", loginVO.getAuth());	//권한그룹으로 시프링시큐리티 사용
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + validity * 1000))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}
}