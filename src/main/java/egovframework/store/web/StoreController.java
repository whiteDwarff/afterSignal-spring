package egovframework.store.web;

import java.util.HashMap;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.payload.ApiResponse;
import egovframework.store.service.StoreService;

@RestController
@RequestMapping("/api/store")
public class StoreController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StoreController.class);

	@Resource(name="propertyService")
	protected EgovPropertyService propertyService;
	
	private final StoreService service;
	
	public StoreController(StoreService service) {
		this.service = service;
	}
	
	
	/**
	 * 이메일 중복여부 조회
	 * @param  HashMap
	 * @return ResponseEntity
	 * @throws Exception
	 * */
	@PostMapping("/duplicatedEmailCheck")
	public ResponseEntity<?> duplicatedInfoCheck(@RequestBody HashMap<String, Object> param) throws Exception {
		ApiResponse response = new ApiResponse(200, true, "success");
	
		EgovMap resultMap = service.duplicatedEmailCheck(param);
		if(resultMap.containsKey("msg")) {
			response.setStatus(400);
			response.setMessage(resultMap.get("msg").toString());
		}
		response.setResult(resultMap);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	
	
}
