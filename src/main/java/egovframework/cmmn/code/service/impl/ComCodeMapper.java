package egovframework.cmmn.code.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

@Mapper
public interface ComCodeMapper {

	List<EgovMap> selectAll() throws Exception;
}
