package view;

import java.util.Scanner;

import beans.User;

public class Menu {
	
	Scanner sc ;
	
	public Menu() {
		sc = new Scanner (System.in);
	}
	
	//************************ login and password
	public String InputLogin() {
		String in = "";
		boolean notValid = true;
		
		System.out.print("\t Login: ");
		while(notValid) {
			in = sc.nextLine();
			if(isNumeric(in) || !in.equals("")) {
				notValid = false;
			} else System.out.print("\t Invalid login. Re-enter: ");
		}
		return in; // return the input id -- WHAT TO DO NOW?
	}
	
	public String InputPassword() {
		String in = "";
		System.out.print("\t Password: ");
		in = sc.nextLine();
		return in; // return the input password -- WHAT TO DO NOW?
	}
	
	//************************ operator
	public void startMenuOperator(User operator) {
		System.out.println("\n\t Operator: " + operator.getFstName());
		System.out.println("\n\t Choose the type of user you want to operate: ");
		System.out.println("\t 1. Member"
					   + "\n\t 2. Provider"
					   + "\n\t 3. Logoff\n");
		printMenuOperator(operator);
	}
	
	public void printMenuOperator(User operator) {
		String in = "";
		String option = "";
		int inInt = 0;
		boolean notValid = true;
		
		System.out.print("\t Option: ");
		while(notValid) {
			in = sc.nextLine();
			if(isNumeric(in) && (in.equals("1") || in.equals("2") || in.equals("3"))) {
				notValid = false;
			} else
				System.out.print("\t Invalid option. Re-enter: ");
		}
		
		inInt = Integer.valueOf(in);
		if(inInt == 1)
			option = "member";
		else if(inInt == 2)
			option = "provider";
		else if(inInt == 3) {
			System.out.println("\n\t Good bye!."); // fix this. It should return to Login screen
			return;
		}
			
		System.out.println("\n\t Choose the type of operation: ");
		System.out.println("\t 1. Add " + option
				       + "\n\t 2. Update " + option
				       + "\n\t 3. Delete " + option
				       + "\n\t 4. Back\n");
		
		notValid = true;
		System.out.print("\t Option: ");
		while(notValid) {
			in = sc.nextLine();
			if(isNumeric(in) && (in.equals("1") || in.equals("2") || in.equals("3") || in.equals("4"))) {
				notValid = false;
			} else
				System.out.print("\t Invalid option. Re-enter: ");
		}
		
		inInt = Integer.valueOf(in);
		if(inInt == 1)
			openMenuAdd(option);
		else if(inInt == 2)
			openMenuUpdate(option);
		else if(inInt == 3)
			openMenuDelete(option);
		else if(inInt == 4)
			startMenuOperator(operator);
			
	}	
	
	public void openMenuAdd(String user) {
		System.out.println("\n\t Operation: Add " + user);
		System.out.print("\t First name    : ");
		System.out.print("\t Last name     : ");
		System.out.print("\t Email         : ");
		System.out.print("\t Cell phone    : ");
		System.out.print("\t Home phone    : ");
		System.out.print("\t Work phone    : ");
		System.out.print("\t Address       : ");
		System.out.print("\t Address line 2: ");
		System.out.print("\t City          : ");
		System.out.print("\t State         : ");
		System.out.print("\t Zip code      : ");
		System.out.print("\n\t Do you want to add this " + user + "? (Y/N): ");
	}
	
	public void openMenuUpdate(String user) {
		String in = "";
		boolean notValid = true;
		
		System.out.println("\n\t Operation: Update " + user);
		System.out.print("\t Enter the ID: ");
		while(notValid) {
			in = sc.nextLine();
			if(isNumeric(in)) { // valid ID
				notValid = false;
			} else
				System.out.print("\t Invalid ID. Re-enter: ");
		}
		System.out.print("\t First name    : "); // show the data of the user
		System.out.print("\t Last name     : ");
		System.out.print("\t Email         : ");
		System.out.print("\t Cell phone    : ");
		System.out.print("\t Home phone    : ");
		System.out.print("\t Work phone    : ");
		System.out.print("\t Address       : ");
		System.out.print("\t Address line 2: ");
		System.out.print("\t City          : ");
		System.out.print("\t State         : ");
		System.out.print("\t Zip code      : ");
		
		System.out.print("\t First name    : "); // fields to update data
		System.out.print("\t Last name     : ");
		System.out.print("\t Email         : ");
		System.out.print("\t Cell phone    : ");
		System.out.print("\t Home phone    : ");
		System.out.print("\t Work phone    : ");
		System.out.print("\t Address       : ");
		System.out.print("\t Address line 2: ");
		System.out.print("\t City          : ");
		System.out.print("\t State         : ");
		System.out.print("\t Zip code      : ");
		System.out.print("\n\t Do you want to update this " + user + "? (Y/N): ");
	}
	
	public void openMenuDelete(String user) {
		String in = "";
		boolean notValid = true;
		
		System.out.println("\n\t Operation: Delete " + user);
		System.out.print("\t Enter the ID: ");
		while(notValid) {
			in = sc.nextLine();
			if(isNumeric(in)) { // valid ID
				notValid = false;
			} else
				System.out.print("\t Invalid ID. Re-enter: ");
		}
		System.out.print("\t First name    : ");
		System.out.print("\t Last name     : ");
		System.out.print("\t Email         : ");
		System.out.print("\t Cell phone    : ");
		System.out.print("\t Home phone    : ");
		System.out.print("\t Work phone    : ");
		System.out.print("\t Address       : ");
		System.out.print("\t Address line 2: ");
		System.out.print("\t City          : ");
		System.out.print("\t State         : ");
		System.out.print("\t Zip code      : ");
		System.out.print("\n\t Do you want to delete this " + user + "? (Y/N): ");
	}
	
	//************************ manager
	public void startMenuManager(User manager) {
		System.out.println("\n\t Manager: " + manager.getFstName());
		System.out.print("\n\t Do you want to visualize the weekly report? (Y/N): ");
		// shows the weekly report		
	}
	
	//************************ provider
	public void startMenuProvider(User provider) {
		System.out.println("\n\t Provider: " + provider.getFstName());
		System.out.println("\n\t Choose the type of operation: ");
		System.out.println("\t 1. Check member's status"
					   + "\n\t 2. Register a service for a member"
					   + "\n\t 3. Check fee's total"
					   + "\n\t 4. Request Provider Directory"
					   + "\n\t 5. Logoff\n");
		printMenuProvider(provider);
	}
	
	public void printMenuProvider(User provider) {
		String in = "";
		String option = "";
		int inInt = 0;
		boolean notValid = true;
		
		System.out.print("\t Option: ");
		while(notValid) {
			in = sc.nextLine();
			if(isNumeric(in) && (in.equals("1") || in.equals("2") || in.equals("3")) || in.equals("4") || in.equals("5")) {
				notValid = false;
			} else
				System.out.print("\t Invalid option. Re-enter: ");
		}
		
		inInt = Integer.valueOf(in);
		if(inInt == 1)
			openMenuCheckStatus(provider);
		else if(inInt == 2)
			openMenuUpdate(option);
		else if(inInt == 3)
			openMenuDelete(option);
		else if(inInt == 4)
			startMenuProvider(provider);
		else if(inInt == 5) {
			System.out.println("\n\t Good bye!."); // fix this. It should return to Login screen
			return;
		}
			
	}
	
	public void openMenuCheckStatus(User provider) {
		String in = "";
		int inInt = 0;
		int status = 0;
		boolean notValid = true;
		
		System.out.println("\n\t Check member's status");
		System.out.print("\t Insert the ID: ");
		while(notValid) {
			in = sc.nextLine();
			if(isNumeric(in) || !in.equals("")) {
				notValid = false;
			} else System.out.print("\t Invalid login. Re-enter: ");
		}
		
		
		inInt = Integer.valueOf(in);
		// validar status do membro e salvar na variavel "status"
		status = 0; // verificar status pra executar a funcao correta
		System.out.println("\t Status of the Member: " + " === NAME === ");
		if(status == 1) {
			System.out.println("\t Member validated!"); // if validated
			System.out.print("\n\t Do you want to start a service with this member? (Y/N): ");
			while(notValid) {
				in = sc.nextLine();
				if(!in.equals("") || in.equalsIgnoreCase("Y") || in.equalsIgnoreCase("N")) {
					notValid = false;
				} else System.out.print("\t Invalid option. Re-enter: ");
			}
			
			if(in.equalsIgnoreCase("Y"))
				openMenuRegisterService(inInt);
			else
				startMenuProvider(provider);
		}			
		else if(status == 2)
			System.out.println("\t Invalid number!"); // if not valid
		else if(status == 3)
			System.out.println("\t Member suspended!"); // if suspended
		
		// complete the method
	}
	
	public void openMenuRegisterService(int ID) {
		System.out.print("\n\t Enter the date the service was provided: "
					   + "\n\t (Format: MM/DD/YYYY): ");
		
	}
	
	public void openMenuRegisterService() {
		String in = "";
		int inInt;
		boolean notValid = true;
		
		System.out.println("\n\t Register a service for a member");
		System.out.print("\t Insert the ID: ");
		while(notValid) {
			in = sc.nextLine();
			if(isNumeric(in)) { // valid ID
				notValid = false;
			} 
			else
				System.out.print("\t Invalid ID. Re-enter: ");
		}
		
		inInt = Integer.valueOf(in);
		// testar status do member (sem precisar dizer qual eh)
		// chama o metodo abaixo pra continuar o registro 
		openMenuRegisterService(inInt);
	}
	
	public void validInput() {
		String in = "";
		in = sc.nextLine();
		while(in.equals(""))
			in = sc.nextLine();
	}
	
	public static boolean isNumeric(String str) { // verifies the input  
		try {  
			Integer.parseInt(str);
		} catch(NumberFormatException nfe) {  
			return false;  
		}  
		return true;  
	}
}
