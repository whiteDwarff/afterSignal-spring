package egovframework.user.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.cmmn.code.service.impl.ComnCodeMapper;
import egovframework.payload.ApiException;
import egovframework.payload.ExceptionEnum;
import egovframework.user.service.ServiceUser;
import egovframework.user.web.UserController;
import egovframework.user.service.impl.SignUpVO;



@Service
public class ServiceUserImpl implements ServiceUser{

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceUserImpl.class);

	private final ServiceUserMapper mapper;  // 사용자
	private final ComnCodeMapper codeMapper; // 공통코드
	
	
	public ServiceUserImpl(ServiceUserMapper mapper, ComnCodeMapper codeMapper) {
		this.mapper = mapper;
		this.codeMapper = codeMapper;
	}
	
	/**
	 * 이메일, 닉네임 중복여부 조회
	 * @param  SignUpVO
	 * @return HashMap
	 * */
	public HashMap<String, Object> duplicatedInfoCheck(HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		int count;
		String flg = (String) map.get("flg");
		
		if(flg.equalsIgnoreCase("email"))
			count = this.mapper.duplicatedEmailCheck(map);
		else 
			count = this.mapper.duplicatedNicknameCheck(map);
		
		resultMap.put("count", count);
		
		return resultMap;
	}
	
	/**
	 * 서비스 사용자 회원가입
	 * @param  SignUpVO
	 * @return HashMap
	 * */
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
	
	
	/**
	 * 공통코드 조회
	 * @return HashMap
	 * */
	@Override
	public HashMap<String, Object> getCommCode() throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("city", codeMapper.selectLowerCommCdByKR("2"));
		resultMap.put("COM0000006", codeMapper.selectLowerCommCdByKR("3"));
		resultMap.put("COM0000008", codeMapper.selectLowerCommCdByKR("4"));
		resultMap.put("COM0000007", codeMapper.selectLowerCommCdByKR("5"));
		
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
