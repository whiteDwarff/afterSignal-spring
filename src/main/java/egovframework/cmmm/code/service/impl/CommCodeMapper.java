package egovframework.cmmm.code.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

@Mapper
public interface CommCodeMapper {

	/**
	 * 입력받은 아이디를 통해 하위목록 코드명 조회 (국문) 
	 * @param String
	 * @return List<EgovMap>
	 * @throws Exception;
	 * */
	List<EgovMap> selectAllKrnmById(String id) throws Exception;
}
