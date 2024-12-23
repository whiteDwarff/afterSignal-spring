package egovframework.store.service;

import java.util.HashMap;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface StoreService {
	
	/**
	 * 이메일 중복여부 조회
	 * @param  HashMap
	 * @return EgovMap
	 * @throws Exception
	 * */
	EgovMap duplicatedEmailCheck(HashMap<String, Object> param) throws Exception;

	/**
	 * 가입 신청
	 * @param  HashMap, MultipartHttpServletRequest
	 * @return EgovMap
	 * @throws Exception
	 * */
	EgovMap apply(HashMap<String, Object> param, MultipartHttpServletRequest multiRequest) throws Exception;

	/**
	 * 스토어 정보 조회
	 * @param  HashMap
	 * @return EgovMap
	 * @throws Exception
	 * */
	EgovMap getStoreInfo(HashMap<String, Object> param) throws Exception;
	
	/**
	 * 스토어 파일 다운로드
	 * @param  HashMap
	 * @return EgovMap
	 * @throws Exception
	 * */
	EgovMap storeFileDown(HashMap<String, Object> param) throws Exception;
}
