package util;

public class Utility {
	public static boolean isNumeric(String str) { // verifies the input  
		try {  
			Integer.parseInt(str);
		} catch(NumberFormatException nfe) {  
			return false;  
		}  
		return true;  
	}
	public static boolean isValidStr(String in){
		if(in.equals("")){
			return false;
		}
		else return true;
	}
}
