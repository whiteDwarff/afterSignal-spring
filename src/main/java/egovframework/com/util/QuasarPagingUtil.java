package egovframework.com.util;

import java.util.HashMap;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuasarPagingUtil {
	
	private int min;                  // 최소 페이지 (1)
	private int max;                  // 최대 페이지 (count 값)
	private int current;              // 현재 페이지
	private int offset;               // 불러올 개수, LIMIT 0, 20
	private final int maxPages = 10;  // 한 페이지에 보여줄 리스트 개수

	
	@Builder
	public QuasarPagingUtil(int min, int max, int current, int offset) {
	   this.min = min;
	   this.max = max;
	   this.current = current;
	   this.offset = offset;
	}
	
     public HashMap<String, Object> getPagination() {
	      HashMap<String, Object> map = new HashMap<>();

	      map.put("min", this.min);
	      map.put("max", this.max);
	      map.put("current", this.current);
	      map.put("maxPages", this.maxPages);
	      map.put("offset", this.offset);

	    return map;
	 }
     
     public HashMap<String, Object> setPagination(int count, int currentPage, HashMap<String, Object> param) {

	    if(count > 0) {
	      this.min = 1;
	      this.max = (int) Math.ceil((double) count / maxPages);
	      this.current = currentPage;
	      this.offset = (current * maxPages) - maxPages;
	    } else {
	      // 불러올 데이터가 없으면 초기값 셋팅
	      this.min = 1;
	      this.max = 1;
	      this.current = 1;
	      this.offset = 0;
	    }
	    param.put("offset", this.offset);
	
	    return this.getPagination();
	  }
    
}
