package egovframework.user.service;

import java.util.HashMap;

import egovframework.user.service.impl.SignUpVO;

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


}
