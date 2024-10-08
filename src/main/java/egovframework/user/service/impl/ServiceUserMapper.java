package egovframework.user.service.impl;

import java.util.HashMap;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

@Mapper
public interface ServiceUserMapper {
	
	/**
	 * 서비스 사용자 이메일 중복검사
	 * @param  HashMap
	 * @return int
	 * */
	int duplicatedEmailCheck(HashMap<String, Object> map) throws Exception;
	
	/**
	 * 서비스 사용자 닉네임 중복검사
	 * @param  HashMap
	 * @return int
	 * */
	int duplicatedNicknameCheck(HashMap<String, Object> map) throws Exception;
	
	/**
	 * 서비스 사용자 회원가입
	 * @param  SignUpVO
	 * @return int
	 * */
	int signUpUser(SignUpVO vo) throws Exception;
	
	/**
	 * 서비스 사용자 로그인정보 조회
	 * @param  HashMap
	 * @return UserVO
	 * */
	UserVO selectUserInfo(HashMap<String, Object> map) throws Exception;
	
	/**
	 * 서비스 사용자 프로필 수정
	 * @param  HashMap
	 * @return int
	 * */
	int updateUserInfo(HashMap<String, Object> map) throws Exception;
	
	/**
	 * 서비스 사용자 비밀번호 수정
	 * @param  HashMap
	 * @return int
	 * */
	int updatePassword(HashMap<String, Object> map) throws Exception;
	
	/**
	 * 서비스 사용자 로그인 날짜 변경
	 * @param  UserVO
	 * @return int
	 * */
	int updateLogindDt(UserVO vo) throws Exception;
	
	/**
	 * 서비스 사용자 이메일 찾기
	 * @param HashMap
	 * @return EgovMap
	 * */
	EgovMap findUserEmail(HashMap<String, Object> map) throws Exception;
}
