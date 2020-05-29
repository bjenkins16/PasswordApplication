package application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
	private static String PASSWORD_VALID = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])).{6,15}";
	private static String USERNAME_VALID = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])).{6,15}";
	
	public static boolean password(String pwd) {
		
		Pattern p = Pattern.compile(PASSWORD_VALID);
		Matcher m = p.matcher(pwd);
		
		return m.matches();
	}
	
	public static boolean username(String uname) {
		Pattern p = Pattern.compile(USERNAME_VALID);
		Matcher m = p.matcher(uname);
		
		return m.matches();
	}
}
