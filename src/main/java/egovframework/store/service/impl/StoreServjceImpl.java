package egovframework.store.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.util.EgovFileUtil;
import egovframework.payload.ApiException;
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
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
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
	@Transactional
	public EgovMap apply(HashMap<String, Object> param, MultipartHttpServletRequest multiRequest) throws Exception {
		EgovMap resultMap = new EgovMap();
		
		try {
			// 시큐리티 패스워드 적용
			String securePassword = bCryptPasswordEncoder.encode(param.get("password").toString());
			param.put("password", securePassword);
			
			// 스토어 정보 저장
			int result = mapper.insertStoreInfo(param);
			
			if(result > 0) {
				String dir = "businessLicense";
				String path = propertyService.getString("STORE_FILE_PATH") + File.separator + dir;
				String defaultPath = propertyService.getString("STORE_PATH") + File.separator + dir;
				
				// 사업자 등록증 저장
				MultipartFile businessFile = multiRequest.getFile("businessRegistration");
			    
				HashMap<String, Object> businessFileMap = EgovFileUtil.filieUpload(businessFile, path);
			    businessFileMap.put("storeSeq", param.get("storeSeq"));
			    businessFileMap.put("type", "file");
			    businessFileMap.put("filePath", defaultPath);
			    
				mapper.insertStroeRefFiles(businessFileMap);
				
				// 스토어 이미지 저장
		    	dir = "conceptImage";
				path = propertyService.getString("STORE_FILE_PATH") + File.separator + dir;

				List<HashMap<String, Object>> dropzoneFileMap = EgovFileUtil.dropzoneFileUpload(multiRequest, path);
				
			    for(HashMap<String, Object> map : dropzoneFileMap) {
			    	map.put("storeSeq", param.get("storeSeq"));
			    	map.put("type", "image");
			    	map.put("filePath", propertyService.getString("STORE_PATH") + File.separator + dir);
			    	
			    	mapper.insertStroeRefFiles(map);
			    }
			    resultMap.put("seq", param.get("storeSeq"));
			}
		} catch(Exception e) {
			e.printStackTrace();
			resultMap.put("msg", ExceptionEnum.STORE_001.getMessage());
		}
		
		return resultMap;
	}
	
	/**
	 * 스토어 정보 조회
	 * @param  HashMap
	 * @return EgovMap
	 * @throws Exception
	 * */
	@Override
	public EgovMap getStoreInfo(HashMap<String, Object> param) throws Exception {		
		EgovMap resultMap = new EgovMap();
		
		resultMap.put("info", mapper.getStoreInfo(param));
		resultMap.put("fileList", mapper.getStoreRefFile(param));
		
		return resultMap;
	}
	
	/**
	 * 스토어 파일 다운로드
	 * @param  HashMap
	 * @return EgovMap
	 * @throws Exception
	 * */
	@Override
	public EgovMap storeFileDown(HashMap<String, Object> param) throws Exception {
		EgovMap resultMap = new EgovMap();
		List<EgovMap> list = mapper.getStoreRefFile(param);
		
		if(list.size() > 0) 
			//resultMap.put("file", list.get(0));
			return list.get(0);
		else 
			resultMap.put("msg", ExceptionEnum.STORE_002.getMessage());
		return resultMap;
	}
}
