package view;

import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import beans.Member;
import beans.Provider;
import beans.Service;
import beans.ServiceProvided;
import beans.User;
import controller.MemberCtrl;
import controller.ServiceCtrl;
import controller.ServiceProvidedCtrl;
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
		notValid = true;
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
		member = memberCtrl.checkStatus(inInt);
		if(member != null)
			status = member.getStatus();
		
		if(status == 1) {
			System.out.println("\n\t Status of the Member: " + member.getFstName() + " " + member.getLstName());
			System.out.println("\t Member Validated!"); // if validated
			System.out.print("\n\t Do you want to start a service with this member? (Y/N): ");
			in = Utility.validInput();
			
			if(in.equalsIgnoreCase("Y"))
				openMenuRegisterService(provider, member);
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
		Utility.clearScreen();															//************ add to the other lines that need ************//
		startMenu(provider);
	}
	
	public void openMenuRegisterService(User provider, Member member) {
		String in = "";
		int inInt = 0;
		int logStatus = 0;
		boolean notValid = true;
		boolean startOver = true;
		ServiceCtrl serviceCtrl = new ServiceCtrl();
		Service service = null;
		ServiceProvided serviceProvided = new ServiceProvided();
		Date occurrenceDate = null;
		ServiceProvidedCtrl serviceProvidedCtrl = new ServiceProvidedCtrl();
		DecimalFormat money = new DecimalFormat("US$##0.00");
		DateFormat dateTimeFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aaa");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		System.out.print("\n\t Enter the date the service was provided." 
						 + "\n\t (Format: MM/DD/YYYY): ");
		notValid = true;
		while(notValid) {
			in = sc.nextLine();
			if(Utility.validateDate(in)) {
				notValid = false;
			} else
				System.out.print("\t Invalid date. Re-enter: ");
		}
		occurrenceDate = Date.valueOf(Utility.convertDate(in));
		
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
				in = Utility.validInput();
				
				if(in.equalsIgnoreCase("Y")) {
					serviceProvided.setService(service);
					startOver = false;
				}
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
		}
		if(in.equalsIgnoreCase("1"))
			startMenu(provider);
			
		System.out.print("\n\t Comments (optional): ");
		serviceProvided.setComment(sc.nextLine());
		serviceProvided.setCurrentDate(Utility.currentDate());
		serviceProvided.setOccurrenceDate((java.sql.Date) occurrenceDate);
		serviceProvided.setProvider((Provider) provider);
		serviceProvided.setMember(member);
		
		Utility.clearScreen();
		System.out.println("\n\t     === Register of Service === ");
		System.out.println("\n\t Current date and time: " + dateTimeFormat.format(serviceProvided.getCurrentDate()));
		System.out.println("\t Date service was provided: " + dateFormat.format(serviceProvided.getOccurrenceDate()));
		System.out.println("\t Provider number: " + serviceProvided.getProvider().getIdUser());
		System.out.println("\t Provider name: " + serviceProvided.getProvider().getFstName() + " " + serviceProvided.getProvider().getLstName());
		System.out.println("\t Member number: " + serviceProvided.getMember().getIdUser());
		System.out.println("\t Member name: " + serviceProvided.getMember().getFstName() + " " + serviceProvided.getMember().getLstName());
		System.out.println("\t Service code: " + serviceProvided.getService().getIdService());
		System.out.println("\t Comments: " + serviceProvided.getComment());
		System.out.println("\t The fee to be paid for this service is: " + money.format(serviceProvided.getService().getFee()));
		System.out.print("\n\t Confirm registration of this service? (Y/N): ");
		in = Utility.validInput();
		
		if(in.equalsIgnoreCase("Y")) {
			logStatus = serviceProvidedCtrl.iOne(serviceProvided);
			if(logStatus == 0) {
				System.out.println("\t Registered successfully!");
			} else {
				System.out.println("\t There is an error in registering this service. Contact the support.");
			}
			System.out.print("\n\t Press ENTER to return to menu ");
			sc.nextLine();
		} else {
			System.out.println("\n\t Do you really want to cancel this service?"
					         + "\n\t All the work will be lost."
							 + "\n\t 1. Return to menu"
							 + "\n\t 2. Register the service");
			System.out.print("\n\t Option: ");
			notValid = true;
			while(notValid) {
				in = sc.nextLine();
				if(!in.equals("") && (in.equalsIgnoreCase("1") || in.equalsIgnoreCase("2"))) {
					notValid = false;
				} else System.out.print("\t Invalid option. Re-enter: ");
			}
			
			if(in.equalsIgnoreCase("2")) {
				logStatus = serviceProvidedCtrl.iOne(serviceProvided);
				if(logStatus == 0) {
					System.out.println("\t Registered successfully!");
				} else {
					System.out.println("\t There is an error in registering this service. Contact the support.");
				}
				System.out.print("\n\t Press ENTER to return to menu ");
				sc.nextLine();
			}
		}
		Utility.clearScreen();
		startMenu(provider);
	}
	
	public void openMenuCheckFees(User provider) {
		ServiceProvidedCtrl serviceProvidedCtrl = new ServiceProvidedCtrl();
		ArrayList<ServiceProvided> serviceProvidedList = null;
		DecimalFormat money = new DecimalFormat("US$##0.00");
		DateFormat dateTimeFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aaa");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		double totalFee = 0;
		int numberOfConsultations = 0;
		
		Utility.clearScreen();
		serviceProvidedList = serviceProvidedCtrl.sLastWeekProvider(provider.getIdUser());
		if(!serviceProvidedList.isEmpty()) {
			System.out.println("\n\t Fee's total of last week");
			System.out.println("\n\t Provider name: " + provider.getFstName() + " " + provider.getLstName());
			System.out.println("\t Provider number: " + provider.getIdUser());
			System.out.println("\t Provider street address: " + provider.getStAddr());
			System.out.println("\t Provider city: " + provider.getCity());
			System.out.println("\t Provider state: " + provider.getState());
			System.out.println("\t Provider ZIP code: " + provider.getZipCode());
			// make a loop between all the services of the current week
			for(ServiceProvided serviceProvided : serviceProvidedList) {
				System.out.println("\n\t   Date of service: " + dateFormat.format(serviceProvided.getOccurrenceDate()));
				System.out.println("\t   Date and time data were receive by the computer: " + dateTimeFormat.format(serviceProvided.getCurrentDate())); // it's printing the time 12:00:00 + today's date for every service. Why?
				System.out.println("\t   Member name: " + serviceProvided.getMember().getFstName() + " " + serviceProvided.getMember().getLstName());
				System.out.println("\t   Member number: " + serviceProvided.getMember().getFkIdMember());
				System.out.println("\t   Service code: " + serviceProvided.getService().getIdService());
				System.out.println("\t   Fee to be paid: " + money.format(serviceProvided.getService().getFee() * (double) Provider.PERCENTAGE));
				totalFee += serviceProvided.getService().getFee() * (double) Provider.PERCENTAGE;
				numberOfConsultations++;
				System.out.println("\n\t ----------------------------------------------------------------------------------- \n");
			}
			
			System.out.println("\t Total number of consultations with members: " + numberOfConsultations);
			System.out.println("\t Total fee for week: " + money.format(totalFee));
		} else {
			System.out.println("\n\t There are no services registered in the last week.");
		}
		System.out.print("\n\t Press ENTER to return to menu ");
		sc.nextLine();
		Utility.clearScreen();
		startMenu(provider);
	}
	
	public void openMenuProviderDirectory(User provider, int status) { // status: if requested by menu, status == 0, if requested by start service, status != 0
		ServiceCtrl serviceCtrl = new ServiceCtrl();
		ArrayList<Service> serviceList = serviceCtrl.sAll();
		DecimalFormat money = new DecimalFormat("US$##0.00");
		
		Utility.clearScreen();
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
