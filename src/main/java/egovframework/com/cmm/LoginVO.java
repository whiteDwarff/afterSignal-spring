package egovframework.com.cmm;

import egovframework.user.service.impl.UserVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginVO {

	private String email;
	private String password;
	private String auth;
	
	public LoginVO() {}
	
	public LoginVO(String email, String password, String auth) {
		this.email = email;
		this.password = password;
		this.auth = auth;
	}
	
	public LoginVO(UserVO vo) {
		this.email = vo.getEmail();
		this.password = vo.getPassword();
		this.auth = vo.getAuthNm();
	}
}
