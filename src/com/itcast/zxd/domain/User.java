package com.itcast.zxd.domain;

import java.io.Serializable;

/**	
 * 	用户对应的Pojo对象
 * */
public class User implements Serializable{
	private int Userid;
	private String Username;
	private String Password;
	private int Gender;
	private String Activecode;
	private String Email;
	private int Status;
	private String Telephone;
	private String Regist_Time;
	private int Role;
		
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(int userid, String username, String password, int gender, String activecode, String email, int status,
			String telephone, String regist_Time, int role) {
		super();
		Userid = userid;
		Username = username;
		Password = password;
		Gender = gender;
		Activecode = activecode;
		Email = email;
		Status = status;
		Telephone = telephone;
		Regist_Time = regist_Time;
		Role = role;
	}




	@Override
	public String toString() {
		return "User [Userid=" + Userid + ", Username=" + Username + ", Password=" + Password + ", Gender=" + Gender
				+ ", Activecode=" + Activecode + ", Email=" + Email + ", Status=" + Status + ", Telephone=" + Telephone
				+ ", Regist_Time=" + Regist_Time + ", Role=" + Role + "]";
	}

	public int getUserid() {
		return Userid;
	}



	public void setUserid(int userid) {
		Userid = userid;
	}



	

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	
	
	public int getGender() {
		return Gender;
	}

	public void setGender(int gender) {
		Gender = gender;
	}

	public String getActivecode() {
		return Activecode;
	}

	public void setActivecode(String activecode) {
		Activecode = activecode;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public String getTelephone() {
		return Telephone;
	}

	public void setTelephone(String telephone) {
		Telephone = telephone;
	}

	public String getRegist_Time() {
		return Regist_Time;
	}

	public void setRegist_Time(String regist_Time) {
		Regist_Time = regist_Time;
	}

	public int getRole() {
		return Role;
	}

	public void setRole(int role) {
		Role = role;
	}

	
	
	
}
