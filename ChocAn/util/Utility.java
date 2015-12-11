package util;

import java.sql.Timestamp;
import java.util.Date;

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
	
	public static boolean validateDate(String str) { // finalizar
		String mm = "", dd = "", yyyy = "";
		int numM, numD, numY;
		boolean leapYear = false;
		Date currentDate;
		Timestamp timeStamp;
		
		if(str.length() != 10) return false;
		else {
			if(str.charAt(2) == '/' && str.charAt(5) == '/') {
				mm += str.charAt(0);
				mm += str.charAt(1);
				dd += str.charAt(3);
				dd += str.charAt(4);
				yyyy = str.substring(6);
				if(isNumeric(mm) && isNumeric(dd) && isNumeric(yyyy)) {
					numY = Integer.valueOf(yyyy);
					numD = Integer.valueOf(dd);
					numM = Integer.valueOf(mm);
					
					if((numY % 4 == 0) && (numY % 100 != 0))
						leapYear = true;
					else if(numY % 400 == 0)
						leapYear = true;
					for(int i = 1; i <= 12; i++) {
						
					}
				}				
			}
		}
		return false;
	}
}
