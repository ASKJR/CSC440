package view;

import java.util.Scanner;

public class Menu {
	
	Scanner sc ;
	
	public Menu(){
		sc = new Scanner (System.in);
	}
	
	public void printMenuLogin(){
		System.out.print("\t Login: ");
		
	}
	
	public void printMenuPassword(){
		System.out.print("\t Password: ");
		
	}
	
	private void printMenuOperator(String operatorName){
		System.out.println("\n------------OPERATOR------------");
		System.out.println("\t Operator: " + operatorName);
		System.out.println("\n\t Choose an option:");
		System.out.println("\t 1. Member"
				+ "\n\t 2. Provider"
				+ "\n\t 3. Logoff\n");
	}
	public int InputMenuOperator(){
		printMenuOperator("Kato");
		String in = "";
		int out = 0;
		boolean notValid = true;
		while(notValid){
			System.out.print("Type your operarion: ");
			in = sc.nextLine();
			if(isNumeric(in) && (in.equals("1") || in.equals("2") || in.equals("3"))){
				notValid = false;
			}
		}
		out = Integer.valueOf(in);
		return out;
	}
	
	
	public String InputLogin(){
		String in = "";
		boolean notValid = true;
		while(notValid){
			printMenuLogin();
			in = sc.nextLine();
			if(in.equals("") || !(isNumeric(in))){
				notValid = true;
			}
			else notValid = false;
		}
		return in;
	}
	
	public String InputPassword(){
		String in = "";
		printMenuPassword();
		in = sc.nextLine();
		return in;
	}
	
	
	
	public static boolean isNumeric(String str) {  
		try {  
			Integer.parseInt(str);
		} catch(NumberFormatException nfe) {  
			return false;  
		}  
		return true;  
	}
}
