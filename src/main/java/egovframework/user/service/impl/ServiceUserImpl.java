package egovframework.user.service.impl;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Objects;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.cmmm.code.service.impl.CommCodeMapper;
import egovframework.com.util.EgovFileUtil;
import egovframework.com.util.QuasarPagingUtil;
import egovframework.deposit.service.impl.DepositMapper;
import egovframework.payload.ApiException;
import egovframework.payload.ExceptionEnum;
import egovframework.user.service.ServiceUser;



@Service
public class ServiceUserImpl implements ServiceUser{

	@Resource(name="propertyService")
	protected EgovPropertyService propertyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceUserImpl.class);

	private final ServiceUserMapper mapper;  // 사용자
	private final CommCodeMapper codeMapper; // 공통코드
	private final DepositMapper depositMapper;
	
	
	public ServiceUserImpl(ServiceUserMapper mapper, CommCodeMapper codeMapper, DepositMapper depositMapper) {
		this.mapper = mapper;
		this.codeMapper = codeMapper;
		this.depositMapper = depositMapper;
	}
	
	/**
	 * 이메일, 닉네임 중복여부 조회
	 * @param  SignUpVO
	 * @return HashMap
	 * @throws Exception 
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
	 * @throws Exception 
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
		
		int result = mapper.signUpUser(vo);
		if(result == 0) {
			throw new ApiException(ExceptionEnum.USER_001);
		} else {
			// 적립금 지급
			this.depositMapper.membershipSignupBonus(vo.getSeq());
		}
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		return resultMap;	
	}
	
	/**
	 * 공통코드 조회
	 * @return HashMap
	 * @throws Exception 
	 * */
	@Override
	public HashMap<String, Object> getCommCode() throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("city", codeMapper.selectAllKrnmById("2"));
		resultMap.put("COM0000006", codeMapper.selectAllKrnmById("3"));
		resultMap.put("COM0000008", codeMapper.selectAllKrnmById("4"));
		resultMap.put("COM0000007", codeMapper.selectAllKrnmById("5"));
		
		return resultMap;
	}
	
	/**
	 * 서비스 사용자 로그인
	 * @param  UserVO
	 * @return HashMap
	 * @throws Exception 
	 * */
	@Override
	public HashMap<String, Object> signInUser(HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		UserVO user = this.mapper.selectUserInfo(map);

		// 가입한 이메일이 없음
		if(Objects.isNull(user)) {
			throw new ApiException(ExceptionEnum.LOGIN_001);
		}
		
		byte[] salt = Base64.getDecoder().decode(user.getSalt());
		String securePassword = getSecurePassword(map.get("password").toString(), salt);
		
	
		// 비밀번호 불일치
		if(!securePassword.equals(user.getPassword())) {
			resultMap.put("msg", ExceptionEnum.LOGIN_002.getMessage());
		} else {
			// 로그인 날짜 업데이트
			this.mapper.updateLogindDt(user);
			user.setEmptyPassword();
			// 사용자 정보 추가
			resultMap.put("user", user);
		}
		
		return resultMap;
	}
	
	/**
	 * 서비스 샤용자 개인정보 변경 
	 * @param HashMap, MultipartHttpServletRequest
	 * @return HashMap
	 * @throws Exception 
	 * */
	@Override
	@Transactional
	public HashMap<String, Object> updateInfo(HashMap<String, Object> map, MultipartHttpServletRequest request) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		MultipartFile file = request.getFile("image");
		
		if(file != null) {
			EgovFileUtil fileUtil = new EgovFileUtil();
			// 이미지 저장경로 + 폴더
			String path = propertyService.getString("SERVICE_USER_FILE_PATH");
			String defaultPath = propertyService.getString("SERVICE_USER_PATH");
			String dir = map.get("dir").toString();
			String fullPath = path + File.separator + dir;
			
			// 프로필 이미지 저장
			HashMap<String, Object> fileMap = fileUtil.filieUpload(file, fullPath);
			
			String fileName = fileMap.get("saveFileName").toString();
			String fileExt = fileMap.get("fileExt").toString();
			
			String profileImage = defaultPath + File.separator + dir + File.separator +  fileName + fileExt;
			map.put("changedImage", profileImage);
			
			// 물리적 파일 삭제
			UserVO oriUserInfo =  this.mapper.selectUserInfo(map);
			fileUtil.fileDelete(propertyService.getString("FILE_DEFAULT_PATH"), oriUserInfo.getProfileImage());
		}
		try {
			int result = this.mapper.updateUserInfo(map);
			if(result == 0) {
				resultMap.put("msg", ExceptionEnum.USER_002.getMessage());
			} else {
				UserVO user = this.mapper.selectUserInfo(map);
				user.setEmptyPassword();
				resultMap.put("user", user);
			}
		} catch (Exception e){
			e.printStackTrace();
		} 
		return resultMap;
	}

	/**
	 * 서비스 사용자 비밀번호 변경 
	 * @param HashMap
	 * @return HashMap
	 * @throws Exception 
	 * */
	public HashMap<String, Object> updatePassword(HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<>();
		
		UserVO user = this.mapper.selectUserInfo(map);
		
		byte[] salt = Base64.getDecoder().decode(user.getSalt());
		String securePassword = getSecurePassword(map.get("password").toString(), salt);
		
		// 비밀번호 불일치
		if(!securePassword.equals(user.getPassword())) {
			resultMap.put("msg", ExceptionEnum.LOGIN_002.getMessage());
		} else {
			byte[] newSalt = this.getSalt();
			String newSecurePassword = this.getSecurePassword(map.get("newPassword").toString(), newSalt);
	        String saltString = Base64.getEncoder().encodeToString(newSalt);
	        
			map.put("salt", saltString);
			map.put("password", newSecurePassword);
			
			int result = this.mapper.updatePassword(map);
			
			// 비밀번호 변경 실패
			if(result == 0) 
				resultMap.put("msg", ExceptionEnum.USER_002.getMessage());
		}
		
		return resultMap;
	}
	
	/**
	 * 서비스 사용자 적립금 내역 조회
	 * @param HashMap
	 * @return HashMap
	 * @throws Exception
	 * */
	public HashMap<String, Object> depositList(HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<>();
		
		int count = this.depositMapper.selectByPagingCount(map);
		String maxPages = propertyService.getString("PAGELIST_VIEW_10");
		
		resultMap.put("maxPages", Integer.parseInt(maxPages));
		resultMap.put("page", new QuasarPagingUtil().setPagination(count, (int) map.get("current"), map));
		resultMap.put("list", this.depositMapper.selectByPaging(map));
		
		return resultMap;
	}
	
	
	
	
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
