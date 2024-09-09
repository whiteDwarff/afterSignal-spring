package egovframework.user.service.impl;

import java.util.HashMap;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

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
}
