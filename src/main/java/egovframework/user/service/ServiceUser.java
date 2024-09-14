package egovframework.user.service;

import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.user.service.impl.SignUpVO;
import egovframework.user.service.impl.UserVO;

public interface ServiceUser {
	
	/**
	 * 이메일, 닉네 중복여부 조회
	 * @param  SignUpVO
	 * @return HashMap
	 * */
	HashMap<String, Object> duplicatedInfoCheck(HashMap<String, Object> map) throws Exception;

	/**
	 * 서비스 사용자 회원가입
	 * @param  SignUpVO
	 * @return HashMap
	 * */
	HashMap<String, Object> signUpUser(SignUpVO vo) throws Exception;
	
	/**
	 * 공통코드 조회
	 * @return HashMap
	 * */
	HashMap<String, Object> getCommCode() throws Exception;
	
	/**
	 * 서비스 사용자 로그인
	 * @param  UserVO
	 * @return HashMap
	 * */
	HashMap<String, Object> signInUser(HashMap<String, Object> map) throws Exception;
	
	/**
	 * 서비스 샤용자 개인정보 변경 
	 * @param HashMap, MultipartHttpServletRequest
	 * @return HashMap
	 * @throws SQLException 
	 * */
	HashMap<String, Object> updateInfo(HashMap<String, Object> map, MultipartHttpServletRequest request) throws Exception ;

}
