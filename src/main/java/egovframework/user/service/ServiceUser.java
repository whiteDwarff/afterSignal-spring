package egovframework.user.service;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.user.service.impl.SignUpVO;

public interface ServiceUser {
	
	/**
	 * 이메일, 닉네 중복여부 조회
	 * @param  SignUpVO
	 * @return HashMap
	 * @throws Exception 
	 * */
	HashMap<String, Object> duplicatedInfoCheck(HashMap<String, Object> map) throws Exception;

	/**
	 * 서비스 사용자 회원가입
	 * @param  SignUpVO
	 * @return HashMap
	 * @throws Exception 
	 * */
	HashMap<String, Object> signUpUser(SignUpVO vo) throws Exception;
	
	/**
	 * 공통코드 조회
	 * @return HashMap
	 * @throws Exception 
	 * */
	HashMap<String, Object> getCommCode() throws Exception;
	
	/**
	 * 서비스 사용자 로그인
	 * @param  UserVO
	 * @return HashMap
	 * @throws Exception 
	 * */
	HashMap<String, Object> signInUser(HashMap<String, Object> map) throws Exception;
	
	/**
	 * 서비스 샤용자 개인정보 변경 
	 * @param HashMap, MultipartHttpServletRequest
	 * @return HashMap
	 * @throws Exception 
	 * */
	HashMap<String, Object> updateInfo(HashMap<String, Object> map, MultipartHttpServletRequest request) throws Exception;

	/**
	 * 서비스 샤용자 비밀번호 변경 
	 * @param HashMap
	 * @return HashMap
	 * @throws Exception 
	 * */
	HashMap<String, Object> updatePassword(HashMap<String, Object> map) throws Exception;

	/**
	 * 서비스 사용자 적립금 내역 조회
	 * @param HashMap
	 * @return HashMap
	 * @throws Exception
	 * */
	HashMap<String, Object> depositList(HashMap<String, Object> map) throws Exception;

}
