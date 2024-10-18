package egovframework.store.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.payload.ExceptionEnum;
import egovframework.store.service.StoreService;
import egovframework.store.web.StoreController;

@Service
public class StoreServjceImpl implements StoreService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StoreServjceImpl.class);

	private final StoreMapper mapper;
	
	public StoreServjceImpl(StoreMapper mapper) {
		this.mapper = mapper;
	}
	
	@Resource(name="propertyService")
	protected EgovPropertyService propertyService;
	
	/**
	 * 이메일 중복여부 조회
	 * @param  HashMap
	 * @return EgovMap
	 * @throws Exception
	 * */
	public EgovMap duplicatedEmailCheck(HashMap<String, Object> param) throws Exception {
		EgovMap resultMap = new EgovMap();
		
		int count = mapper.duplicatedEmailCheck(param);
		
		if(count == 0)
			resultMap.put("msg", ExceptionEnum.JOIN_001.getMessage());
		
		resultMap.put("count", count);
		return resultMap;
	}
}
