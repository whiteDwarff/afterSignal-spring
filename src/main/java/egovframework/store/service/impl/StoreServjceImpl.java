package egovframework.store.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.util.EgovFileUtil;
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
		
		if(count > 0)
			resultMap.put("msg", ExceptionEnum.JOIN_001.getMessage());
		
		resultMap.put("count", count);
		return resultMap;
	}
	
	/**
	 * 가입 신청
	 * @param  HashMap, MultipartHttpServletRequest
	 * @return EgovMap
	 * @throws Exception
	 * */
	public EgovMap apply(HashMap<String, Object> param, MultipartHttpServletRequest multiRequest) throws Exception {
		EgovMap resultMap = new EgovMap();
		
		LOGGER.info("param ----------->> " + param.toString());
		 Map<String, MultipartFile> fileMap = multiRequest.getFileMap();
		
		String defaultPath = propertyService.getString("STORE_FILE_PATH");
		String dir = "conceptImage";
		
		EgovFileUtil.dropzoneFileUpload(multiRequest, defaultPath + File.separator + dir);
		// 파일 목록 순회
	    for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
	        String fileName = entry.getKey();  // 파일의 이름
	        MultipartFile file = entry.getValue();  // MultipartFile 객체

	        // 파일 이름과 파일의 크기를 출력하거나 원하는 처리
	        System.out.println("File Name: " + fileName);
	        System.out.println("File Size: " + file.getSize());

	        // 추가적으로 원하는 로직 (예: 파일 저장, 검증 등)을 여기서 처리 가능
	    }
		
		
		
		return resultMap;
	}
}
