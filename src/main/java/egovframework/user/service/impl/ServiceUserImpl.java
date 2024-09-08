package egovframework.user.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.payload.ApiException;
import egovframework.payload.ExceptionEnum;
import egovframework.user.service.ServiceUser;
import egovframework.user.web.UserController;
import egovframework.user.service.impl.SignUpVO;



@Service
public class ServiceUserImpl implements ServiceUser{

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceUserImpl.class);

	private final ServiceUserMapper mapper;
	
	public ServiceUserImpl(ServiceUserMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public HashMap<String, Object> signUpUser(SignUpVO vo) throws Exception {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
//		try {
		byte[] salt = this.getSalt();
		String securePassword = this.getSecurePassword(vo.getPassword(), salt);
        String saltString = Base64.getEncoder().encodeToString(salt);
        
		vo.setSalt(saltString);
		vo.setPassword(securePassword);
		
		LOGGER.info("@@@@@@@@@@@@ VO" + vo.toString());
		
		int result = mapper.signUpUser(vo);
		if(result == 0) {
			throw new ApiException(ExceptionEnum.USER_001);
		}
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		return resultMap;	
	}
	
	// 로그인 
    // byte[] salt = Base64.getDecoder().decode(user.getSalt());
    // 입력된 비밀번호와 가져온 salt를 사용해 해싱된 비밀번호 생성
    // String securePassword = getSecurePassword(password, salt);
	
	/**
	 * SHA256 + salt로 암호화된 비밀번호 반환
	 * @param String, byte[]
	 * @return String
	 * */
	private String getSecurePassword(String password, byte[] salt) {

        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
	
	/**
	 * byte 배열 salt 반환
	 * @return byte[]
	 * */
    private byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

}