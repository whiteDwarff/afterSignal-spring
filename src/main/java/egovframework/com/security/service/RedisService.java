package egovframework.com.security.service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class RedisService {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;


	/**
	 * key와 value를 Redis에 저장/수정
	 * @param String key
	 * @param String value
	 * */
	public void setValues(String key, String value) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        values.set(key, value);
    }

	/**
	 * key와 value를 저장/수정, 메모리 상의 유효시간 설정
	 * @param String key
	 * @param String value
	 * @param Duration duration
	 * */
    public void setValues(String key, String data, int day) {
    	
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        values.set(key, data, Duration.ofDays(day));
    }
    
    /**
     * key을 기반으로 값을 조회
     * @param String key
     * @return String value
     * */
    public String getValues(String key) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        if (values.get(key) == null) return "";
        return (String) values.get(key);
    }

    /**
     * key를 기반으로 row 삭제
     * @param String key
     * */
    public void deleteValues(String key) {
        redisTemplate.delete(key);
    }
    

    public void expireValues(String key, int timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
    }

    public void setHashOps(String key, Map<String, String> data) {
        HashOperations<String, Object, Object> values = redisTemplate.opsForHash();
        values.putAll(key, data);
    }

    public String getHashOps(String key, String hashKey) {
        HashOperations<String, Object, Object> values = redisTemplate.opsForHash();
        return Boolean.TRUE.equals(values.hasKey(key, hashKey)) ? (String) redisTemplate.opsForHash().get(key, hashKey) : "";
    }

    public void deleteHashOps(String key, String hashKey) {
        HashOperations<String, Object, Object> values = redisTemplate.opsForHash();
        values.delete(key, hashKey);
    }

    public boolean checkExistsValue(String value) {
        return !value.equals("false");
    }
}
