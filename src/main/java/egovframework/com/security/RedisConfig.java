package egovframework.com.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
	
	 @Value("${spring.data.redis.host}")
	 private String host;

	 @Value("${spring.data.redis.port}")
	 private String port;

	 @Value("${spring.data.redis.password}")
	 private String password;
	 
	 // redis 연결을 위한 설정 
	 @Bean
	 public RedisConnectionFactory redisConnectionFactory() {
	      RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
	      redisStandaloneConfiguration.setHostName(host);
	      redisStandaloneConfiguration.setPort(Integer.parseInt(port));
	      redisStandaloneConfiguration.setPassword(password);
	      
	      LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
	      return lettuceConnectionFactory;
	 }

	 // key, value를 입력하고 사용하기 위해 template 생성, redis-cli를 통해 직접 데이터를 조회 가능
	 @Bean
	 public RedisTemplate<String, Object> redisTemplate() {
	      RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
	      
	      // Redis 연결
	      redisTemplate.setConnectionFactory(redisConnectionFactory());
	      // Key-Value 형태로 직렬화를 수행
	      redisTemplate.setKeySerializer(new StringRedisSerializer());
	      redisTemplate.setValueSerializer(new StringRedisSerializer());
	      // Hash Key-Value 형태로 직렬화를 수행
	      redisTemplate.setHashKeySerializer(new StringRedisSerializer());
	      redisTemplate.setHashValueSerializer(new StringRedisSerializer());
	      // 기본 직렬화 설정
	      redisTemplate.setDefaultSerializer(new StringRedisSerializer());
	      
	      return redisTemplate;
	 }
	 
	 
}
