package egovframework.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import java.security.Key;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;
    // 30분
    private long expiration = 1800000;

    /**
     * JWT Key 생성
     * @param  String
     * @return String
     * --------------
     * @author 강문호
     * */
    private Key getSigningKey() {
    	 /**
    	 * 암호화 알고리즘 : 문자열 대신 바이트 배열을 입력으로 사용
    	 * HS256(HMAC SHA-256)을 사용 -> Java Cryptography Architecture(JCA)에 맞는 형식으로 반환
    	 * */
        byte[] keyBytes = secretKey.getBytes(); 
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    /**
     * JWT 생성
     * @param  String
     * @return String
     * */
    public String generateToken(String username) {
        /**
         * setIssuedAt()   : JWT가 발급된 시간 (현재시간)
         * setExpiration() : JWT의 만료 시
         * signWith()	   : JWT에 서명을 추가
         * compact()       : JWT 빌더에서 설정된 모든 정보(헤더, 페이로드, 서명)를 기반으로 최종적으로 JWT 문자열을 생성
         * */
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * JWT에서 사용자 이름 추출
     * @param  String
     * @return String
     * */
    public String extractUsername(String token) {
    	/**
    	 * getClaims() : JWT의 모든 클레임을 가져온다.
    	 * 사용자의 이름인 서브 클레임 반환
    	 * */
        return getClaims(token).getSubject();
    }

    /**
     * JWT에서 Claims 추출
     * @param  String
     * @return String
     * */
    private Claims getClaims(String token) {
    	/**
    	 * setSigningKey()  : 서명 검증을 위한 키 설정
    	 * parseClaimsJws() : JWT 토큰을 파싱, 유효성 검사 후 클레임 추출
    	 * getBody()        : 실제 클레임 데이터를 가져온다.
    	 * */
        return Jwts.parserBuilder()
                .setSigningKey(this.getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * JWT 유효성 검사
     * @param  String
     * @return boolean
     * */
    public boolean isTokenExpired(String token) {
    	/**
    	 * getExpiration() : 클레임의 만료시각(exp)를 가져 뒤 현재시간(new Date())와 비교
    	 * 토큰이 만료된 경우 true, 아니면 false 반환
    	 * */
        return this.getClaims(token).getExpiration().before(new Date());
    }

    /**
     * JWT 토큰이 주어진 사용자 이름에 대해 유효성 체크
     * @param  String
     * @return boolean
     * */
    public boolean validateToken(String token, String username) {
    	// 사용자의 이름 일치여부와 만료시간 체크 
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    // 요청에서 JWT 토큰 추출
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
