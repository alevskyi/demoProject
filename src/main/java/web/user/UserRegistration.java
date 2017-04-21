package web.user;

import userutils.User;

public class UserRegistration extends User{
	
	private String confirmPasswd; 
	
	public String getConfirmPasswd() {
		return confirmPasswd;
	}

	public void setConfirmPasswd(String confirmPasswd) {
		this.confirmPasswd = confirmPasswd;
	}
	
	public boolean isPasswordsMatch(){
		return passwd.equals(confirmPasswd);
	}
	
	@Override
	public String toString(){
		return super.toString() + "\n" + "Passwords match: " + isPasswordsMatch();
	}
}