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

	private Long seq;
	private String email;
	private String password;
	private String nickname;
	private String name;
	private String tel;
	private String salt;
	private String city;
	private String gender;


	
	public SignUpVO(Long seq, String email, String password, String nickname, String name, String tel, String salt, String city, String gender) {
		this.seq = seq;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.name = name;
		this.tel = tel;
		this.salt = salt;
		this.city = city;
		this.gender = gender;
	}
	
	
	static public class Builder {
		
		private Long seq;
		private String email;
		private String password;
		private String nickname;
		private String name;
		private String tel;
		private String salt;
		private String city;
		private String gender;

		
		public Builder() {}
		
		public Builder(SignUpVO signUpVO) {
			this.seq = signUpVO.seq;
			this.email = signUpVO.email;
			this.password = signUpVO.password;
			this.nickname = signUpVO.nickname;
			this.name = signUpVO.name;
			this.tel = signUpVO.tel;
			this.salt = signUpVO.salt;
			this.city = signUpVO.city;
			this.gender = signUpVO.gender;
		}
		
		public Builder seq(Long seq) {
			this.seq = seq;
			return this;
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
		
		public Builder city(String city) {
			this.city = city;
			return this;
		}
		
		public Builder gender(String gender) {
			this.gender = gender;
			return this;
		}
		
		public SignUpVO build() {
			return new SignUpVO(seq, email, password, nickname, name, tel, salt, city, gender);
		}
	}
	
}
