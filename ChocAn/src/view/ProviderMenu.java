package view;

import java.util.Scanner;

import beans.User;

public class ProviderMenu {
	
	Scanner sc = new Scanner(System.in);
	
	//************************ provider
	public void startMenu(User provider) {
		System.out.println("\n\t Provider: " + provider.getFstName());
		System.out.println("\n\t Choose the type of operation: ");
		System.out.println("\t 1. Check member's status"
					   + "\n\t 2. Register a service for a member"
					   + "\n\t 3. Check fee's total"
					   + "\n\t 4. Request Provider Directory"
					   + "\n\t 5. Logoff\n");
		printMenu(provider);
	}
	
	public void printMenu(User provider) {
		String in = "";
		int inInt = 0;
		boolean notValid = true;
		
		System.out.print("\t Option: ");
		while(notValid) {
			in = sc.nextLine();
			if(Menu.isNumeric(in) && (in.equals("1") || in.equals("2") || in.equals("3")) || in.equals("4") || in.equals("5")) {
				notValid = false;
			} else
				System.out.print("\t Invalid option. Re-enter: ");
		}
		
		inInt = Integer.valueOf(in);
		if(inInt == 1)
			openMenuCheckStatus(provider);
		else if(inInt == 2)
			openMenuCheckStatus(provider); // openMenuCheckStatus is used to start a service because the method gets first the ID and then sends it to startMenuRegisterService()
		else if(inInt == 3)
			openMenuCheckFees(provider);
		else if(inInt == 4)
			openMenuProviderDirectory(provider, 0);
		else if(inInt == 5) {
			System.out.println("\n\t Good bye!");
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
			if(Menu.isNumeric(in) || !in.equals("")) {
				notValid = false;
			} else 
				System.out.print("\t Invalid login. Re-enter: ");
		}
		
		inInt = Integer.valueOf(in);
		
		// verificar status do membro e salvar na variavel "status"
		status = 0; // verificar status pra executar a funcao correta
		System.out.println("\t Status of the Member: " + " === NAME === ");
		if(status == 1) {
			System.out.println("\t Member Validated!"); // if validated
			System.out.print("\n\t Do you want to start a service with this member? (Y/N): ");
			while(notValid) {
				in = sc.nextLine();
				if(!in.equals("") || in.equalsIgnoreCase("Y") || in.equalsIgnoreCase("N")) {
					notValid = false;
				} else System.out.print("\t Invalid option. Re-enter: ");
			}
			
			if(in.equalsIgnoreCase("Y"))
				openMenuRegisterService(provider, inInt);
			else
				startMenu(provider);
		}			
		else if(status == 2)
			System.out.println("\t Invalid number!"); // if not valid
		else if(status == 3)
			System.out.println("\t Member Suspended!"); // if suspended
		System.out.print("\n\t Press ENTER to return to menu ");
		sc.nextLine();
		startMenu(provider);
		// complete the method
	}
	
	public void openMenuRegisterService(User provider, int ID) {
		String in = "";
		boolean notValid = true;
		boolean startOver = true;
		
		System.out.println("\n\t Enter the date the service was provided." 
						 + "\n\t (Format: MM/DD/YYYY): ");
		// get the date
		while(startOver) {
			openMenuProviderDirectory(provider, 1);
			System.out.print("\n\t Which service do you want to register? ");
			// get the service code
			System.out.println("\n\t The service keyed was: " + " === SERVICE NAME === ");
			System.out.print("\t Confirm? (Y/N): ");
			while(notValid) {
				in = sc.nextLine();
				if(!in.equals("") || in.equalsIgnoreCase("Y") || in.equalsIgnoreCase("N")) {
					notValid = false;
				} else System.out.print("\t Invalid option. Re-enter: ");
			}
			
			if(in.equalsIgnoreCase("Y"))
				startOver = false;
			else {
				System.out.println("\n\t 1. Return to menu" 
								 + "\n\t 2. Look up the Provider Directory again");
				notValid = true;
				while(notValid) {
					in = sc.nextLine();
					if(!in.equals("") || in.equalsIgnoreCase("1") || in.equalsIgnoreCase("2")) {
						notValid = false;
					} else System.out.print("\t Invalid option. Re-enter: ");
				}
				
				if(in.equalsIgnoreCase("1"))
					startOver = false;
			}
		}
		if(in.equalsIgnoreCase("1"))
			startMenu(provider);
			
		System.out.println("\n\t Comments: ");
		// get the comments
		System.out.println("\n\t Current date and time === SHOW === ");
		System.out.println("\t Date service was provided === SHOW === ");
		System.out.println("\t Provider number === SHOW === ");
		System.out.println("\t Member number === SHOW === ");
		System.out.println("\t Service code === SHOW === ");
		System.out.println("\t Comments === SHOW === ");
		System.out.println("\n\t Confirm registration of this service? (Y/N): ");
		while(notValid) {
			in = sc.nextLine();
			if(!in.equals("") || in.equalsIgnoreCase("Y") || in.equalsIgnoreCase("N")) {
				notValid = false;
			} else System.out.print("\t Invalid option. Re-enter: ");
		}
		
		if(in.equalsIgnoreCase("Y")) {
			// register service in database
			// if success print
			System.out.println("\t Registered successfully!");
			System.out.println("\n\t The fee to be paid for this service is === SHOW FEE RECEIVED BY PROVIDER === ");
		}
		else
			startMenu(provider);
		
	}
	
	public void openVerificationForm(User provider) {
		System.out.println("\n\t Please, fill the verification form: ");
		System.out.println("\t (Once filled, it cannot be changed)");
		System.out.print("\n\t Current date (MM/DD/YYYY): ");
		System.out.print("\t Current time (HH:MM am or pm): ");
		System.out.print("\t Date service was provided: ");
		System.out.print("\t Member name: ");
		System.out.print("\t Member number: ");
		System.out.print("\t Service code: ");
		System.out.print("\t Fee: ");
		System.out.print("\n\t Confirm? (Y/N): ");
		
	}
	
	public void openMenuCheckFees(User provider) {
		System.out.println("\n\t Fee's total of current week");
		// get the report of the current week for that provider passed as parameter
		System.out.println("\n\t Provider name === SHOW === ");
		System.out.println("\t Provider number === SHOW === ");
		System.out.println("\t Provider street address === SHOW === ");
		System.out.println("\t Provider city === SHOW === ");
		System.out.println("\t Provider state === SHOW === ");
		System.out.println("\t Provider ZIP code === SHOW === ");
		// make a loop between all the services of the current week
		System.out.println("\t   Date of service === SHOW === ");
		System.out.println("\t   Date and time data were receive by the computer === SHOW === ");
		System.out.println("\t   Member name === SHOW === ");
		System.out.println("\t   Member number === SHOW === ");
		System.out.println("\t   Service code === SHOW === ");
		System.out.println("\t   Fee to be paid === SHOW === ");
		System.out.println("\n\t Total number of consultations with members === SHOW === ");
		System.out.println("\t Total fee for week === SHOW === ");
	}
	
	public void openMenuProviderDirectory(User provider, int status) { // status: se o provider solicitou o directory do menu principal, status = 0
																	   //         se o provider iniciou um registro de servico, status = 1
		System.out.println("\n\t Provider Directory");
		System.out.println(" === SHOWS SERVICES AVAILABLES === ");     // solicitar do banco
		if(status == 0) {
			System.out.print("\n\t Press ENTER to return to menu ");
			sc.nextLine();
			startMenu(provider);
		} else
			return;
	}
}
