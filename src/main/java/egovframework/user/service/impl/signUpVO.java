package egovframework.user.service.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class signUpVO {

	private String email;
	private String password;
	private String nickname;
	private String name;
	private String tel;
	
	public signUpVO(String email, String password, String nickname, String name, String tel) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.name = name;
		this.tel = tel;
	}
	
	
	static public class Builder {
		
		private String email;
		private String password;
		private String nickname;
		private String name;
		private String tel;
		
		public Builder() {}
		
		public Builder(signUpVO signUpVO) {
			this.email = signUpVO.email;
			this.password = signUpVO.password;
			this.nickname = signUpVO.nickname;
			this.name = signUpVO.name;
			this.tel = signUpVO.tel;
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
		
		public signUpVO build() {
			return new signUpVO(email, password, nickname, name, tel);
		}
	}
	
}
