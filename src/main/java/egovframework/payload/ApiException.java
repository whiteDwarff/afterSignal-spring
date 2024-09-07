package egovframework.payload;

import lombok.Getter;
import lombok.ToString;

@Getter
public class ApiException  extends RuntimeException {
	private ExceptionEnum error;
	private String etc_msg;

	//정의된 에러메시지만
    public ApiException(ExceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    }

    //정의된 에러메시지 + 사용자 메시지
    public ApiException(ExceptionEnum e, String etc_msg) {
        super(e.getMessage() + " " + etc_msg);
        e.setMessage(e.getMessage() + " " + etc_msg);
        this.error = e;
    }
}
