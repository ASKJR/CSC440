package view;

import java.util.Scanner;

public class Menu {
	
	Scanner sc ;
	
	public Menu(){
		sc = new Scanner (System.in);
	}
	
	public void printMenuLogin(){
		System.out.println("\t Login: ");
		
	}
	
	public void hello(){
		System.out.println("Oi");
	}
	
	
	
	public void logicMenuLogin(){
		String in;
		boolean notValid = true;
		while(notValid){
			printMenuLogin();
			in = sc.nextLine();
			if(in==""){
				notValid = true;
			}
			else notValid = false;
		}
	}
}
