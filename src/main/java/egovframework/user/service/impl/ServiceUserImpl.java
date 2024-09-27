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
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.cmmm.code.service.impl.CommCodeMapper;
import egovframework.com.cmm.LoginVO;
import egovframework.com.jwt.EgovJwtTokenUtil;
import egovframework.com.util.EgovFileUtil;
import egovframework.com.util.QuasarPagingUtil;
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
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
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
		
		// 시큐리티 암호화 패스워드 적용
		vo.setPassword(bCryptPasswordEncoder.encode(vo.getPassword()));
		
		int result = mapper.signUpUser(vo);
		
		if(result == 0) {
			throw new ApiException(ExceptionEnum.USER_001);
		} else {
			// 적립금 지급
			this.depositMapper.membershipSignupBonus(vo.getSeq());
		}
		
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
		
		
		// 스프링 시큐리티로 암호화된 비밀번호와 입력값 비교
		Boolean isMatches = bCryptPasswordEncoder.matches(map.get("password").toString(), user.getPassword());

		// 비밀번호 불일치
		if(!isMatches) {
			resultMap.put("msg", ExceptionEnum.LOGIN_002.getMessage());
		} else {
			
			EgovJwtTokenUtil jwt = new EgovJwtTokenUtil();
			
			LoginVO loginInfo = new LoginVO(user);
			
			// 사용자에게 전송할 access token과 redis에 저장할 refresh token 생성
			String accessToken = jwt.generateToken(loginInfo);
			String refreshToken = jwt.generateRefreshToken(loginInfo);
			
			// 로그인 날짜 업데이트
			this.mapper.updateLogindDt(user);
			user.setPassword(null);
			// 사용자 정보 추가
			resultMap.put("user", user);
			resultMap.put("accessToken", accessToken);
			
			// redis 저장로직 추가
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
				user.setPassword(null);
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
		
		// 사용자가 입력한 비밀번호와 저장된 비밀번호가 일치한지 확인
		Boolean isMatches = bCryptPasswordEncoder.matches(map.get("password").toString(), user.getPassword());

		// 비밀번호 불일치
		if(!isMatches) {
			resultMap.put("msg", ExceptionEnum.LOGIN_002.getMessage());
		} else {
			map.put("password", bCryptPasswordEncoder.encode(map.get("newPassword").toString()));
			
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
	 * 서비스 사용자 이메일 찾기
	 * @param HashMap
	 * @return HashMap
	 * @throws Exception
	 * */
	public HashMap<String, Object> findUserEmail(HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<>();

		EgovMap result = this.mapper.findUserEmail(map);
		
		if(result != null) {
			resultMap.put("info", result);
		} else {
			resultMap.put("msg", ExceptionEnum.USER_003.getMessage());
		}
		return resultMap;
	}

	
	// ------------------------ 시큐리티 추가 후 미사용 로직 ------------------------ 
	
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
