package egovframework.config.security.impl;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AuthUser {
	
	private String email;
	private String password;

	public AuthUser(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	static public class Builder {	
		
		private String email;
		private String password;
		
		public Builder() {}
		
		public Builder(AuthUser user) {
			this.email = user.email;
			this.password = user.password;
		}
		
		public Builder email(String email) {
			this.email = email;
			return this;
		}
		
		public Builder password(String password) {
			this.password = password;
			return this;
		}
		
		public AuthUser build() {
			return new AuthUser(email, password);
		}
	}
}
