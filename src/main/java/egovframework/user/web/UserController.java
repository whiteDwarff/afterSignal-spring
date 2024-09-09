package egovframework.user.web;

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

import egovframework.cmmn.code.service.CommCodeService;
import egovframework.payload.ApiResponse;
import egovframework.user.service.ServiceUser;
import egovframework.user.service.impl.SignUpVO;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private final ServiceUser service;
	private final CommCodeService codeService;	// 공통코드 서비스
	
	public UserController(ServiceUser service, CommCodeService codeService) {
		this.service = service;
		this.codeService = codeService;
	}
	
	
	@Resource(name="propertyService")
	protected EgovPropertyService propertyService;
	
	
	/**
	 * 이메일, 닉네임 중복여부 조회
	 * @param  HashMap
	 * @return ResponseEntity
	 * */
	@PostMapping("/duplicatedInfoCheck")
	public ResponseEntity<?> duplicatedInfoCheck(@RequestBody HashMap<String, Object> map) throws Exception {
		ApiResponse response = new ApiResponse(200, true, "success");
		response.setResult(service.duplicatedInfoCheck(map));
	
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * 공통코드 조회
	 * @return ResponseEntity
	 * */
	@PostMapping("/signUp")
	public ResponseEntity<?> signUp() throws Exception {
		ApiResponse response = new ApiResponse(200, true, "success");
		response.setResult(service.getCommCode());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
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
