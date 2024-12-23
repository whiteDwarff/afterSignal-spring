package egovframework.com.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class EgovFileUtil {
	
	/**
	 * 하나의 파일 저장
	 * @param MultipartFile file, String uploadPath
	 * @return HashMap
	 * */
	public static HashMap<String, Object>filieUpload(MultipartFile file, String uploadPath) {
		
		HashMap<String, Object> resultMap = new HashMap<>();
		
		// 실제 파일명
		String originalFileName = file.getOriginalFilename();
		// 파일의 확장자
	    String ext = extractExt(originalFileName);
	    // 서버에 저장되는 파일명
	    String saveFileName = EgovFormBasedUUID.randomUUID().toString().replaceAll("-", "") + ext;
	    // 저장경로
	    String uploadFilePath = uploadPath + File.separator + saveFileName;

	    File uploadFile = new File(uploadFilePath);

	    // 해당 경로에 폴더가 없다면 폴더 생성
	    if(!uploadFile.exists()) uploadFile.mkdirs();

	    Path path = Paths.get(uploadFilePath).toAbsolutePath();

	    try {
	    	 file.transferTo(path.toFile());
	    	 resultMap.put("filePath", uploadPath + File.separator);
	    	 resultMap.put("saveFileName", saveFileName);
	    	 resultMap.put("originalFileName", originalFileName);
	    	 resultMap.put("fileExt", ext);
	    	 resultMap.put("fileSize", file.getSize());
	    } catch(IOException e) {
	    	 e.printStackTrace();
	    }
	    
		return resultMap;
	}
	
	public static List<HashMap<String, Object>> dropzoneFileUpload(MultipartHttpServletRequest multiRequest, String uploadPath) {
		 
		List<HashMap<String, Object>> resultMap = new ArrayList<HashMap<String, Object>>();	
		
		 Map<String, MultipartFile> fileMap = multiRequest.getFileMap();
		 
		 int num = 0;
		 
		 for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
			  String fileName = entry.getKey();
			  if(fileName.equals("dzFile[" + String.valueOf(num) + "]")) {
				  MultipartFile file = entry.getValue();
				  HashMap<String, Object> map = filieUpload(file, uploadPath);
				  map.put("seq", num + 1);
				  resultMap.add(map);
				  num++;
			  } 
		  }
		return resultMap;
	}
	/**
	 * 파일경로와 파일명을 통해 물리적 파일 삭제
	 * @param String, String
	 * @return Boolean
	 * */
	public static Boolean fileDelete(String fullPath, String fileName) {
		
		Boolean result = false;
		File file = new File(fullPath + File.separator + fileName);
		
		if(file.exists()) {
			file.delete();
			result = true;
		}
		return result;
	}
	
	/**
	 * 파일 확장자 반환
	 * @param String
	 * @return String
	 * */
	public static String extractExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}


}
