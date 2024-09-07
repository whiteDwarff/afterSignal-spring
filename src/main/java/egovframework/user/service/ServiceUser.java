package egovframework.user.service;

import java.util.HashMap;

import egovframework.user.service.impl.SignUpVO;

public interface ServiceUser {
	
	public HashMap<String, Object> signUpUser(SignUpVO vo) throws Exception;


}
