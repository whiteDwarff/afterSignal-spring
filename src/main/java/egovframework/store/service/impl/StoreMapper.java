package egovframework.store.service.impl;

import java.util.HashMap;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

@Mapper
public interface StoreMapper {

	/**
	 * 이메일 중복여부 조회
	 * @param  HashMap
	 * @return int
	 * @throws Exception
	 * */
	int duplicatedEmailCheck(HashMap<String, Object> param) throws Exception;
	
	/**
	 * 스토어 가입신청
	 * @param  HashMap
	 * @return int
	 * @throws Exception
	 * */
	int insertStoreInfo(HashMap<String, Object> param) throws Exception;
	
	/**
	 * 스토어 첨부파일 저장
	 * @param  HashMap
	 * @throws Exception
	 * */
	void insertStroeRefFiles(HashMap<String, Object> param) throws Exception;

	/**
	 * 스토어 정보 조회
	 * @param  HashMap
	 * @return HashMap
	 * @throws Exception
	 * */
	EgovMap getStoreInfo(HashMap<String, Object> param) throws Exception;

	/**
	 * 스토어 첨부파일 조회
	 * @param  HashMap
	 * @return List
	 * @throws Exception
	 * */
	List<EgovMap> getStoreRefFile(HashMap<String, Object> param) throws Exception;
}
