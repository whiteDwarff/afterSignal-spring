package egovframework.store.web;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	
	@Value("${server.file.path}")
	private String BASE_DIR;
	
	
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
	
	/**
	 * 스토어 가입 신청
	 * @param  HashMap, MultipartHttpServletRequest
	 * @return ResponseEntity
	 * @throws Exception
	 * */
	@PostMapping("apply")
	public ResponseEntity<?> apply(
			  @RequestParam HashMap<String, Object> param
			, MultipartHttpServletRequest multiRequest
		)  throws Exception { 
		ApiResponse response = new ApiResponse(200, true, "success");

		EgovMap resultMap = service.apply(param, multiRequest);
		
		response.setResult(resultMap);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * 스토어 정보 조회
	 * @param  HashMap
	 * @return ResponseEntity
	 * @throws Exception
	 * */
	@PostMapping("getStoreInfo")
	public ResponseEntity<?> getStoreInfo(@RequestBody HashMap<String, Object> param)  throws Exception { 
		ApiResponse response = new ApiResponse(200, true, "success");

		EgovMap resultMap = service.getStoreInfo(param);
		
		response.setResult(resultMap);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * 스토어 파일 다운로드
	 * @param  HashMap
	 * @return ResponseEntity
	 * @throws Exception
	 * */
	@PostMapping(value = "fileDown")
	public void fileDown(HttpServletResponse res, @RequestBody HashMap<String, Object> param) throws Exception {
	    EgovMap resultMap = service.storeFileDown(param);

	    if (resultMap.containsKey("msg")) {
	        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        res.setContentType("application/json");
	        res.getWriter().write("{\"status\":400,\"message\":\"" + resultMap.get("msg").toString() + "\"}");
	    } else {
	        String saveDir = resultMap.get("filePath").toString();
	        String fileName = resultMap.get("saveFileName").toString();
	        String fullPath = BASE_DIR + saveDir + File.separator + fileName;

	        try {
	            byte[] fileByte = FileUtils.readFileToByteArray(new File(fullPath));

	            res.setContentType("application/octet-stream");
	            res.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
	            res.setHeader("Content-Transfer-Encoding", "binary");

	            res.getOutputStream().write(fileByte);
	            res.getOutputStream().flush();
	            res.getOutputStream().close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

}
