package egovframework.deposit.service.impl;

import java.util.HashMap;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;

@Mapper
public interface DepositMapper {
	
	/**
	 * 서비스 사용자 회원가입 시 적립금 지급
	 * @param Long
	 * @return int
	 * */
	int membershipSignupBonus(Long userSeq) throws Exception;
	
	/**
	 * 서비스 사용자 적립금 내역 조회
	 * @param HashMap
	 * @return List
	 * */
	List<EgovMap> selectByPaging(HashMap<String, Object> map) throws Exception;

	/**
	 * 서비스 사용자 적립금 내역 개수 조회
	 * @param HashMap
	 * @return int
	 * */
	int selectByPagingCount(HashMap<String, Object> map) throws Exception;
}
