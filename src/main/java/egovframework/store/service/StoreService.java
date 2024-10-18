package egovframework.store.service;

import java.util.HashMap;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

public interface StoreService {
	
	/**
	 * 이메일 중복여부 조회
	 * @param  HashMap
	 * @return EgovMap
	 * @throws Exception
	 * */
	EgovMap duplicatedEmailCheck(HashMap<String, Object> param) throws Exception;

}
