package egovframework.user.service.impl;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface sericeUserMapper {
	
	int duplicatedEmailCheck(signUpVO vo) throws Exception;
}
