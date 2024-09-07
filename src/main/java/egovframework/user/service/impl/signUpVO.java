package egovframework.user.service.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class SignUpVO {

	private String email;
	private String password;
	private String nickname;
	private String name;
	private String tel;
	private String salt;

	
	public SignUpVO(String email, String password, String nickname, String name, String tel, String salt) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.name = name;
		this.tel = tel;
		this.salt = salt;
	}
	
	
	static public class Builder {
		
		private String email;
		private String password;
		private String nickname;
		private String name;
		private String tel;
		private String salt;

		
		public Builder() {}
		
		public Builder(SignUpVO signUpVO) {
			this.email = signUpVO.email;
			this.password = signUpVO.password;
			this.nickname = signUpVO.nickname;
			this.name = signUpVO.name;
			this.tel = signUpVO.tel;
			this.salt = signUpVO.salt;
		}
		
		public Builder email(String email) {
			this.email = email;
			return this;
		}
		
		public Builder password(String password) {
			this.password = password;
			return this;
		}
		
		public Builder nickname(String nickname) {
			this.nickname = nickname;
			return this;
		}
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder tel(String tel) {
			this.tel = tel;
			return this;
		}
		
		public Builder salt(String salt) {
			this.salt = salt;
			return this;
		}
		
		public SignUpVO build() {
			return new SignUpVO(email, password, nickname, name, tel, salt);
		}
	}
	
}
