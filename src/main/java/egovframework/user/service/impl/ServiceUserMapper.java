package egovframework.user.service.impl;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface ServiceUserMapper {
	
	int duplicatedEmailCheck(SignUpVO vo) throws Exception;
	
	int signUpUser(SignUpVO vo) throws Exception;
}
