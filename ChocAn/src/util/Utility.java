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
	
	public static boolean isValidStr(String str){
		if(str.equals("")){
			return false;
		}
		else return true;
	}
	
	public static void clearScreen() {
		for(int i = 0; i < 30; i++)
			System.out.println();
	}
}
