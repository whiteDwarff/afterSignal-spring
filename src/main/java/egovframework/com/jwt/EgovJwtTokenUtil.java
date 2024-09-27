package egovframework.com.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import egovframework.com.cmm.LoginVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EgovJwtTokenUtil implements Serializable {

	public static final long JWT_TOKEN_VALIDITY =  60 * 60; 					// accessToken의 유효시간   - 60분  
	public static final long JWT_REFRESH_TOKEN_VALIDITY = 14 * 24 * 60 * 60; 	// refreshToken의 유효시간  - 2주

	
	@Value("${jwt.secret.key}")   
	private String SECRET_KEY;
	
	/**
	 * 토큰에서 사용자 이메일 조회
	 * @param String
	 * @return String
	 * */
	public String getUserEmailFromToken(String token) {
		Claims claims = getClaimFromToken(token);
		return claims.get("email").toString();
	}

	/**
	 * 토큰에서 특정 key 조회
	 * @param String, String
	 * @return String
	 * */
	public String getInfoFromToken(String key, String token) {
		Claims claims = getClaimFromToken(token);
		return claims.get(key).toString();
	}

	/**
	 * 토큰에서 Claims 정보 조회
	 * @param String
	 * @return Claims
	 * */
	public Claims getClaimFromToken(String token) {
		final Claims claims = getAllClaimsFromToken(token);
		return claims;
	}

	/**
	 * 토큰에서 Claims 정보 조회
	 * @param String
	 * @return Claims
	 * */
	public Claims getAllClaimsFromToken(String token) {
		log.debug("===>>> secret = " + SECRET_KEY);
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
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
