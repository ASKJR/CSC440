package util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Utility {
	static Scanner sc;
	public Utility(){
		sc = new Scanner(System.in);
	}
	
	public static String validInput(){
		String in = "";
		boolean notValid = true;
		
		while(notValid) {
			in = sc.nextLine();
			if(!in.equals("") && (in.equalsIgnoreCase("Y") || in.equalsIgnoreCase("N"))) {
				notValid = false;
			} else System.out.print("\t Invalid option. Re-enter: ");
		}
		return in;
	}
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
		boolean validDate = false;
		Timestamp currentDate;
		Date inputDate;
		
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
					
					if(numD > 31 || numM > 12)
						return false;
					
					if((numY % 4 == 0) && (numY % 100 != 0))
						leapYear = true;
					else if(numY % 400 == 0)
						leapYear = true;
					
					if(leapYear && numM == 2 && numD <= 29)
						validDate = true;
					else if(!leapYear && numM == 2 && numD <= 28)
						validDate = true;
					else if(numM != 2 && numM % 2 == 0 && numD <= 31)
						validDate = true;
					else if(numM % 2 != 0 && numD <= 30)
						validDate = true;
					
					if(validDate) {
						currentDate = currentDate();
						inputDate = null;
						SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
						try {
							inputDate = formatter.parse(str);
						} catch (ParseException e) {
							System.out.println("Error!");
						}
						return inputDate.before(currentDate);
					}
				} else return false;
			}
		}
		return validDate;
	}
	
	public static String convertDate(String str) {
		String toReturn = "";
		toReturn += str.substring(6);
		toReturn += "-";
		toReturn += str.substring(0, 2);
		toReturn += "-";
		toReturn += str.substring(3, 5);
		return toReturn;
	}
	
	public static Timestamp currentDate() {
		Date currDate = new Date();
		return new Timestamp(currDate.getTime());		
	}
}
