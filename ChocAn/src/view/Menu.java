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
	
	
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    int d = Integer.parseInt(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
}
