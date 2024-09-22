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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.payload.ApiResponse;
import egovframework.user.service.ServiceUser;
import egovframework.user.service.impl.SignUpVO;
import egovframework.user.service.impl.UserVO;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private final ServiceUser service;
	
	public UserController(ServiceUser service) {
		this.service = service;
	}
	
	@Resource(name="propertyService")
	protected EgovPropertyService propertyService;
	
	
	/**
	 * 이메일, 닉네임 중복여부 조회
	 * @param  HashMap
	 * @return ResponseEntity
	 * @throws Exception
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
	 * @throws Exception
	 * */
	@PostMapping("/signUp")
	public ResponseEntity<?> signUp() throws Exception {
		ApiResponse response = new ApiResponse(200, true, "success");
		response.setResult(service.getCommCode());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * 서비스 사용자 회원가입
	 * @param SignUpVO
	 * @return ResponseEntity
	 * @throws Exception
	 * */
	@PostMapping("/signUpUser")
	public ResponseEntity<?> signUpUser(@RequestBody SignUpVO vo) throws Exception {
		ApiResponse response = new ApiResponse(200, true, "success");

		response.setResult(service.signUpUser(vo));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * 서비스 사용자 로그인
	 * @param UserVO
	 * @return ResponseEntity
	 * @throws Exception
	 * */
	@PostMapping("/signInUser")
	public ResponseEntity<?> signInUser(@RequestBody HashMap<String, Object> map) throws Exception {
		ApiResponse response = new ApiResponse(200, true, "success");

		HashMap<String, Object> resultMap = service.signInUser(map);
		
		if(resultMap.containsKey("msg")) {
			response.setStatus(201);
			response.setMessage(resultMap.get("msg").toString());
		}
		response.setResult(resultMap);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * 서비스 사용자 회원정보 변경
	 * @param HashMap
	 * @return ResponseEntity
	 * @throws Exception
	 * */
	@PostMapping(value = "/updateInfo", consumes = {"multipart/form-data"})
	public ResponseEntity<?> updateInfo(@RequestParam HashMap<String, Object> map, MultipartHttpServletRequest request) throws Exception {
		ApiResponse response = new ApiResponse(200, true, "success");
		
		HashMap<String, Object> resultMap = service.updateInfo(map, request);
		
		if(resultMap.containsKey("msg")) {
			response.setStatus(202);
			response.setMessage(resultMap.get("msg").toString());
		}
		response.setResult(resultMap);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * 서비스 사용자 비밀번호 변경
	 * @param HashMap
	 * @return ResponseEntity
	 * @throws Exception
	 * */
	@PostMapping("/updatePassword")
	public ResponseEntity<?> updatePassword(@RequestBody HashMap<String, Object> map) throws Exception {
		ApiResponse response = new ApiResponse(200, true, "success");
		
		HashMap<String, Object> resultMap = service.updatePassword(map);
		
		if(resultMap.containsKey("msg")) {
			response.setStatus(202);
			response.setMessage(resultMap.get("msg").toString());
		}
		response.setResult(resultMap);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * 서비스 사용자 적립금 내역 조회
	 * @param HashMap
	 * @return ResponseEntity
	 * @throws Exception
	 * */
	@PostMapping("/depositList")
	public ResponseEntity<?> depositList(@RequestBody HashMap<String, Object> map) throws Exception {
		ApiResponse response = new ApiResponse(200, true, "success");
		
		HashMap<String, Object> resultMap = service.depositList(map);
		response.setResult(resultMap);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	/**
	 * 서비스 사용자 이메일 찾기
	 * @param HashMap
	 * @return ResponseEntity
	 * @throws Exception
	 * */
	@PostMapping("/findUserEmail")
	public ResponseEntity<?> findUserEmail(@RequestBody HashMap<String, Object> map) throws Exception {
		ApiResponse response = new ApiResponse(200, true, "success");

		HashMap<String, Object> resultMap = service.findUserEmail(map);
		
		if(resultMap.containsKey("msg")) {
			response.setStatus(404);
			response.setMessage(resultMap.get("msg").toString());
		}
		response.setResult(resultMap);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	


}
