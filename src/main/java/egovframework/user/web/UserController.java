package egovframework.user.web;

import java.util.HashMap;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.user.service.impl.sericeUserMapper;
import egovframework.user.service.impl.signUpVO;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private final sericeUserMapper mapper;
	
	public UserController(sericeUserMapper mapper) {
		this.mapper = mapper;
	}
	
	
	@Resource(name="propertyService")
	protected EgovPropertyService propertyService;
	
	@PostMapping("/signUp")
	public ResponseEntity<?> join(@RequestBody HashMap<String, Object> param) throws Exception {
		
		return ResponseEntity.ok(200);
	}
	
	
	
	@PostMapping("/duplicatedEmailCheck")
	public ResponseEntity<?> duplicateEmailCheck(@RequestBody signUpVO signUpVO) throws Exception {
		
		
		int result = mapper.duplicatedEmailCheck(signUpVO);
		
		LOGGER.info("@@@@ VO : " + signUpVO.toString());
		LOGGER.info("@@@@ result : " + String.valueOf(result));

	
		return ResponseEntity.ok(result);
	}
	
	


}
