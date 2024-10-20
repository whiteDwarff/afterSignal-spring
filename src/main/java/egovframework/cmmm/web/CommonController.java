package egovframework.cmmm.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.LoginVO;
import egovframework.com.jwt.EgovJwtTokenUtil;
import egovframework.payload.ApiResponse;

@RestController
@RequestMapping("/api/common")
public class CommonController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	EgovJwtTokenUtil jwtUtil;
	
	@PostMapping("/getAccessToken")
	public ResponseEntity<?> getAccessToken(@RequestBody LoginVO vo, HttpServletResponse res) throws Exception {
		ApiResponse response = new ApiResponse(200, true, "success");
		
		jwtUtil.setHeaderAccessToken(res, "access-token", jwtUtil.generateToken(vo));
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/dropzoneSaveFiles")
	public ResponseEntity<?> dropzoneSaveFiles(
			@RequestParam HashMap<String, Object> map, 
			//@RequestParam("businessRegistration") MultipartFile businessRegistration,
			MultipartHttpServletRequest request ) throws Exception {
		ApiResponse response = new ApiResponse(200, true, "success");

		LOGGER.info("@@@@ PARAM : " + map.toString());
		
		//LOGGER.info("@@@@ FILE : " + businessRegistration);
		LOGGER.info("@@@@ FILE : " + request.getFile("businessRegistration"));
		
		
	    Map<String, MultipartFile> fileMap = request.getFileMap();
		
		// 파일 목록 순회
	    for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
	        String fileName = entry.getKey();  // 파일의 이름
	        MultipartFile file = entry.getValue();  // MultipartFile 객체

	        // 파일 이름과 파일의 크기를 출력하거나 원하는 처리
	        System.out.println("File Name: " + fileName);
	        System.out.println("File Size: " + file.getSize());

	        // 추가적으로 원하는 로직 (예: 파일 저장, 검증 등)을 여기서 처리 가능
	    }
		
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}
