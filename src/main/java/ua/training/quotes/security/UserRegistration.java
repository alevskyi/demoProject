package ua.training.quotes.security;

import ua.training.quotes.model.User;

public class UserRegistration extends User {

	private String confirmPasswd;

	public String getConfirmPasswd() {
		return confirmPasswd;
	}

	public void setConfirmPasswd(String confirmPasswd) {
		this.confirmPasswd = confirmPasswd;
	}

//	public boolean isPasswordsMatch(){
//		return passwd.equals(confirmPasswd);
//	}

}