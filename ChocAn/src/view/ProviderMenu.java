package view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import beans.Member;
import beans.Service;
import beans.User;
import controller.MemberCtrl;
import controller.ServiceCtrl;
import util.Utility;

public class ProviderMenu {
	
	Scanner sc = new Scanner(System.in);
	Utility u = new Utility();
	
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
			if(Utility.isNumeric(in) && ((in.equals("1") || in.equals("2") || in.equals("3")) || in.equals("4") || in.equals("5"))) {
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
			System.exit(0);;
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
			if(Utility.isNumeric(in) && !in.equals("")) {
				notValid = false;
			} else 
				System.out.print("\t Invalid login. Re-enter: ");
		}
		inInt = Integer.valueOf(in);
		
		MemberCtrl memberCtrl = new MemberCtrl();
		Member member = new Member();
		member = memberCtrl.sOne(inInt);
		if(member != null)
			status = member.getStatus();
		else
			status = 2;
		
		if(status == 1) {
			System.out.println("\n\t Status of the Member: " + member.getFstName() + " " + member.getLstName());
			System.out.println("\t Member Validated!"); // if validated
			System.out.print("\n\t Do you want to start a service with this member? (Y/N): ");
			notValid = true;
			while(notValid) {
				in = sc.nextLine();
				if(!in.equals("") && (in.equalsIgnoreCase("Y") || in.equalsIgnoreCase("N"))) {
					notValid = false;
				} else System.out.print("\t Invalid option. Re-enter: ");
			}
			
			if(in.equalsIgnoreCase("Y"))
				openMenuRegisterService(provider, inInt);
			else
				startMenu(provider);
		}			
		else if(status == 3) {
			System.out.println("\n\t Status of the Member: " + member.getFstName() + " " + member.getLstName());
			System.out.println("\t Member Suspended!"); // if suspended
		}
		else
			System.out.println("\n\t Invalid Number!"); // if invalid
		System.out.print("\n\t Press ENTER to return to menu ");
		sc.nextLine();
		startMenu(provider);
	}
	
	public void openMenuRegisterService(User provider, int ID) {
		String in = "";
		int inInt = 0;
		boolean notValid = true;
		boolean startOver = true;
		ServiceCtrl serviceCtrl = new ServiceCtrl();
		Service service = null;
		
		System.out.println("\n\t Enter the date the service was provided." 
						 + "\n\t (Format: MM/DD/YYYY): ");
		// get the date
		while(startOver) {
			openMenuProviderDirectory(provider, 1);
			System.out.print("\n\t Which service do you want to register? ");
			notValid = true;
			while(notValid) {
				in = sc.nextLine();
				if(Utility.isNumeric(in)) {
					notValid = false;
				} else
					System.out.print("\t Invalid option. Re-enter: ");
			}
			
			inInt = Integer.valueOf(in);
			service = serviceCtrl.sOne(inInt);
			if(service != null) {
				System.out.println("\n\t The service keyed was: " + service.getName());
				System.out.print("\t Confirm? (Y/N): ");
				notValid = true;
				while(notValid) {
					in = sc.nextLine();
					if(!in.equals("") && (in.equalsIgnoreCase("Y") || in.equalsIgnoreCase("N"))) {
						notValid = false;
					} else System.out.print("\t Invalid option. Re-enter: ");
				}
				
				if(in.equalsIgnoreCase("Y"))
					startOver = false;
				else {
					System.out.println("\n\t 1. Return to menu" 
									 + "\n\t 2. Look up the Provider Directory again");
					System.out.print("\n\t Option: ");
					notValid = true;
					while(notValid) {
						in = sc.nextLine();
						if(!in.equals("") && (in.equalsIgnoreCase("1") || in.equalsIgnoreCase("2"))) {
							notValid = false;
						} else System.out.print("\t Invalid option. Re-enter: ");
					}
					
					if(in.equalsIgnoreCase("1"))
						startOver = false;
				}
			} else {
				System.out.println("\n\t Service number not found! ");
				System.out.print("\t Press ENTER to look up the Provider Directory again");
				sc.nextLine();
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
		notValid = true;
		while(notValid) {
			in = sc.nextLine();
			if(!in.equals("") && (in.equalsIgnoreCase("Y") || in.equalsIgnoreCase("N"))) {
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
	
	public void openMenuProviderDirectory(User provider, int status) { // status: if requested by menu, status == 0, if requested by start service, status != 0
		ServiceCtrl serviceCtrl = new ServiceCtrl();
		ArrayList<Service> serviceList = serviceCtrl.sAll();
		DecimalFormat money = new DecimalFormat("$##0.00");
		
		System.out.println("\n\t Provider Directory");
		if(serviceList != null) {
			for(Service i : serviceList) {
				System.out.println("\t "    + i.getIdService()
									+ " - " + i.getName()
									+ " "   + money.format(i.getFee()));
			}
		}
		else {
			System.out.println("\t There are no services to exhibit.");
			status = 0;
		}
		if(status == 0) {
			System.out.print("\n\t Press ENTER to return to menu ");
			sc.nextLine();
			startMenu(provider);
		} else
			return;
	}
}
