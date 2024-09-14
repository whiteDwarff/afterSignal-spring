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
	private String nickname;
	private String email;
	private String password;
	private String salt;
	private String name;
	private String gender;
	private String tel;
	private String city;
	private String district;
	private String grade;
	private int deposit;
	private int amount;
	private String delYn;
	private String profileImage;
	private String pwdUpdDt;
	private String loginDt;
	private String joinDt;
	
	public UserVO(
			Long seq, 
			String nickname, 
			String email,
			String password,
			String salt,
			String name,
			String gender,
			String tel,
			String city,
			String district,
			String grade,
			int deposit,
			int amount,
			String delYn,
			String profileImage,
			String pwdUpdDt,
			String loginDt,
			String joinDt
			) {
		
		this.seq = seq;
		this.nickname =  nickname;
		this.email =  email;
		this.password = password;
		this.salt = salt;
		this.name = name;
		this.gender = gender;
		this.tel = tel;
		this.city = city;
		this.district =  district;
		this.grade =  grade;
		this.deposit =  deposit;
		this.amount = amount;
		this.delYn = delYn;
		this.profileImage = profileImage;
		this.pwdUpdDt = pwdUpdDt;
		this.loginDt = loginDt;
		this.joinDt = joinDt;
	}
	
	static public class Builder {
		
		private Long seq;
		private String nickname;
		private String email;
		private String password;
		private String salt;
		private String name;
		private String gender;
		private String tel;
		private String city;
		private String district;
		private String grade;
		private int deposit;
		private int amount;
		private String delYn;
		private String profileImage;
		private String pwdUpdDt;
		private String loginDt;
		private String joinDt;
		
		public Builder() {}
		
		public Builder(UserVO userVO) {
			this.seq = userVO.seq;
			this.nickname =  userVO.nickname;
			this.email =  userVO.email;
			this.password = userVO.password;
			this.salt = userVO.salt;
			this.name = userVO.name;
			this.gender = userVO.gender;
			this.tel = userVO.tel;
			this.city = userVO.city;
			this.district =  userVO.district;
			this.grade =  userVO.grade;
			this.deposit =  userVO.deposit;
			this.amount = userVO.amount;
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
		
		public Builder salt(String salt) {
			this.salt = salt;
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
		
		public Builder deposit(int deposit) {
			this.deposit = deposit;
			return this;
		}
		
		public Builder amount(int amount) {
			this.amount = amount;
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
			return new UserVO(seq, nickname, email, password, salt, name, gender, tel, city, district, grade, deposit, amount, delYn, profileImage, pwdUpdDt, loginDt, joinDt);
		}
	}
	
}
