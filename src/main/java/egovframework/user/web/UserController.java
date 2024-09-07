package egovframework.user.web;

import java.util.HashMap;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.payload.ApiResponse;
import egovframework.user.service.ServiceUser;
import egovframework.user.service.impl.ServiceUserMapper;
import egovframework.user.service.impl.SignUpVO;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private final ServiceUserMapper mapper;
	private final ServiceUser service;
	
	public UserController(ServiceUser service, ServiceUserMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}
	
	
	@Resource(name="propertyService")
	protected EgovPropertyService propertyService;
	
	
	/**
	 * 회원가입 > 중복된 닉네임 체크
	 * @param  SignUpVO
	 * @return ResponseEntity
	 * */
	@PostMapping("/duplicatedEmailCheck")
	public ResponseEntity<?> duplicateEmailCheck(@RequestBody SignUpVO vo) throws Exception {
		
		int result = mapper.duplicatedEmailCheck(vo);
		
		LOGGER.info("@@@@ result : " + String.valueOf(result));

	
		return ResponseEntity.ok(result);
	}
	
	/**
	 * 회원가입
	 * @param SignUpVO
	 * @return ResponseEntity
	 * */
	@PostMapping("/signUpUser")
	public ResponseEntity<?> signUpUser(@RequestBody SignUpVO vo) throws Exception {
		ApiResponse response =new ApiResponse(200, true, "success");

		response.setResult(service.signUpUser(vo));
//		service.signUpUser(vo);
//		return ResponseEntity.ok(200);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	


}
