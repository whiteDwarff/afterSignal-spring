package egovframework.store.service.impl;

import java.util.HashMap;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface StoreMapper {

	/**
	 * 이메일 중복여부 조회
	 * @param  HashMap
	 * @return int
	 * @throws Exception
	 * */
	int duplicatedEmailCheck(HashMap<String, Object> param) throws Exception;
}
