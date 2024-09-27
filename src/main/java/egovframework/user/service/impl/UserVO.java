package egovframework.user.service.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class UserVO {

	private Long seq;
	private String auth;
	private String authNm;
	private String nickname;
	private String email;
	private String password;
	private String name;
	private String gender;
	private String tel;
	private String city;
	private String district;
	private String grade;
	private String delYn;
	private String profileImage;
	private String pwdUpdDt;
	private String loginDt;
	private String joinDt;
	
	public UserVO(
			Long seq, 
			String auth,
			String authNm,
			String nickname, 
			String email,
			String password,
			String name,
			String gender,
			String tel,
			String city,
			String district,
			String grade,
			String delYn,
			String profileImage,
			String pwdUpdDt,
			String loginDt,
			String joinDt
			) {
		
		this.seq = seq;
		this.auth = auth;
		this.authNm = authNm;
		this.nickname =  nickname;
		this.email =  email;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.tel = tel;
		this.city = city;
		this.district =  district;
		this.grade =  grade;
		this.delYn = delYn;
		this.profileImage = profileImage;
		this.pwdUpdDt = pwdUpdDt;
		this.loginDt = loginDt;
		this.joinDt = joinDt;
	}
	
	static public class Builder {
		
		private Long seq;
		private String auth;
		private String authNm;
		private String nickname;
		private String email;
		private String password;
		private String name;
		private String gender;
		private String tel;
		private String city;
		private String district;
		private String grade;
		private String delYn;
		private String profileImage;
		private String pwdUpdDt;
		private String loginDt;
		private String joinDt;
		
		public Builder() {}
		
		public Builder(UserVO userVO) {
			this.seq = userVO.seq;
			this.auth = userVO.auth;
			this.authNm = userVO.authNm;
			this.nickname =  userVO.nickname;
			this.email =  userVO.email;
			this.password = userVO.password;
			this.name = userVO.name;
			this.gender = userVO.gender;
			this.tel = userVO.tel;
			this.city = userVO.city;
			this.district =  userVO.district;
			this.grade =  userVO.grade;
			this.delYn = userVO.delYn;
			this.profileImage = userVO.profileImage;
			this.pwdUpdDt = userVO.pwdUpdDt;
			this.loginDt = userVO.loginDt;
			this.joinDt = userVO.joinDt;
		}
		
		public Builder seq(Long seq) {
			this.seq = seq;
			return this;
		}
		
		public Builder auth(String auth) {
			this.auth = auth;
			return this;
		}
		
		public Builder authNm(String authNm) {
			this.authNm = authNm;
			return this;
		}
		
		public Builder nickname(String nickname) {
			this.nickname = nickname;
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
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder gender(String gender) {
			this.gender = gender;
			return this;
		}
		
		public Builder tel(String tel) {
			this.tel = tel;
			return this;
		}
		
		public Builder city(String city) {
			this.city = city;
			return this;
		}
		
		public Builder district(String district) {
			this.district = district;
			return this;
		}
		
		public Builder grade(String grade) {
			this.grade = grade;
			return this;
		}
		
		public Builder delYn(String delYn) {
			this.delYn = delYn;
			return this;
		}
		
		public Builder profileImage(String profileImage) {
			this.delYn = profileImage;
			return this;
		}
		
		public Builder pwdUpdDt(String pwdUpdDt) {
			this.pwdUpdDt = pwdUpdDt;
			return this;
		}
		
		public Builder loginDt(String loginDt) {
			this.loginDt = loginDt;
			return this;
		}
		
		public Builder joinDt(String joinDt) {
			this.joinDt = joinDt;
			return this;
		}
		
		
		public UserVO build() {
			return new UserVO(seq, auth, authNm, nickname, email, password, name, gender, tel, city, district, grade, delYn, profileImage, pwdUpdDt, loginDt, joinDt);
		}
		
	}
	
}
