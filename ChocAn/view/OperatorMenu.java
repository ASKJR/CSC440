package view;

import java.util.Scanner;

import beans.*;
import util.*;
import controller.*;

public class OperatorMenu {
	
	Scanner sc =  new Scanner(System.in);
	Utility u  =  new Utility();
	MemberCtrl memberCtrl = new MemberCtrl();
	ProviderCtrl providerCtrl = new ProviderCtrl();
	
	//************************ operator
	public void startMenu(User operator) {
		System.out.println("\n\t Operator: " + operator.getFstName());
		System.out.println("\n\t Choose the type of user you want to operate: ");
		System.out.println("\t 1. Member"
					   + "\n\t 2. Provider"
					   + "\n\t 3. Logoff\n");
		printMenu(operator);
	}
	
	public void printMenu(User operator) {
		String in = "";
		String option = "";
		int inInt = 0;
		boolean notValid = true;
		
		System.out.print("\t Option: ");
		while(notValid) {
			in = sc.nextLine();
			if(u.isNumeric(in) && (in.equals("1") || in.equals("2") || in.equals("3"))) {
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
			System.out.println("\n\t Good bye!");
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
			if(u.isNumeric(in) && (in.equals("1") || in.equals("2") || in.equals("3") || in.equals("4"))) {
				notValid = false;
			} else
				System.out.print("\t Invalid option. Re-enter: ");
		}
		
		inInt = Integer.valueOf(in);
		if(inInt == 1)
			openMenuAdd(option);
		else if(inInt == 2)
			openMenuUpdate(option,operator);
		else if(inInt == 3)
			openMenuDelete(option,operator);
		else if(inInt == 4)
			startMenu(operator);
			
	}	
	
	public void openMenuAdd(String user) {
		String userData[] = new String[11];
		String in;
		System.out.println("\n\t Operation: Add " + user);
		
		while(true){
			System.out.print("\t First name    : ");
			in = sc.nextLine();
			if(u.isValidStr(in)){
				userData[6] = in;
				break;
			}
		}
		
		while(true){
			System.out.print("\t Last name     :");
			in = sc.nextLine();
			if(u.isValidStr(in)){
				userData[5] = in;
				break;
			}
		}
		
		while(true){
			System.out.print("\t Email         : ");
			in = sc.nextLine();
			if(u.isValidStr(in)){
				userData[10] = in;
				break;
			}
		}
		while(true){
			System.out.print("\t Cell phone    : ");
			in = sc.nextLine();
			if(u.isValidStr(in)){
				userData[7] = in;
				break;
			}
		}
		while(true){
			System.out.print("\t Home phone    : ");
			in = sc.nextLine();
			if(u.isValidStr(in)){
				userData[8] = in;
				break;
			}
		}
		while(true){
			System.out.print("\t Work phone    : ");
			in = sc.nextLine();
			if(u.isValidStr(in)){
				userData[9] = in;
				break;
			}
		}
		while(true){
			System.out.print("\t Address       : ");
			in = sc.nextLine();
			if(u.isValidStr(in)){
				userData[0] = in;
				break;
			}
		}
		while(true){
			System.out.print("\t Address line 2: ");
			in = sc.nextLine();
			if(u.isValidStr(in)){
				userData[1] = in;
				break;
			}
		}
		while(true){
			System.out.print("\t City          : ");
			in = sc.nextLine();
			if(u.isValidStr(in)){
				userData[2] = in;
				break;
			}
		}
		while(true){
			System.out.print("\t State         : ");
			in = sc.nextLine();
			if(u.isValidStr(in)){
				userData[3] = in;
				break;
			}
		}
		while(true){
			System.out.print("\t Zip code      : ");
			in = sc.nextLine();
			if(u.isValidStr(in)){
				userData[4] = in;
				break;
			}
		}
		if(user.equals("member")){
			if(memberCtrl.iOne(1,userData[0],userData[1],userData[2],userData[3],
						userData[4],userData[5],userData[6],userData[7],
						userData[8],userData[9],userData[10]) == User.SUCCESSFUL_SQL_QUERY){
				System.out.println("Member successfully inserted!");
			}
			else{
				System.out.println("Error sql:");
			}
			
		}
		else{
			if(providerCtrl.iOne(1,userData[0],userData[1],userData[2],userData[3],
					userData[4],userData[5],userData[6],userData[7],
					userData[8],userData[9],userData[10]) == User.SUCCESSFUL_SQL_QUERY){
				System.out.println("Provider successfully inserted!");
			}
			else{
				System.out.println("Error sql:");
			}
		}
		
		System.out.print("\n\t Do you want to add this " + user + "? (Y/N): ");
	}
	
	
	public void openMenuUpdate(String user,User operator) {
		String in = "";
		String ID = "";
		boolean notValid  = true;
		Member member     = null;
		Provider provider = null;
		String userData[] = new String[11];
		
		
		String AddrComp  ;
		String CellPhone ;
		String HomePhone ;
		String WorkPhone ;
		String Email     ;
		String City      ;
		String ZipCode   ;
		String State     ;
		String StAddr    ;
		String FstName   ;
		String LstName   ;
		
		
		
		
		System.out.println("\n\t Operation: Update " + user);
		System.out.print("\t Enter the ID: ");
		
		//-----------------ID validation-----------------
		while(notValid) {
			ID = sc.nextLine();
			if(u.isNumeric(ID)) { // valid ID
				if(user.equals("member")){
					member = memberCtrl.sOne(Integer.parseInt(ID));
					if(member != null){
						notValid = false;
					}
				}
				//It's a provider
				else{
					provider = providerCtrl.sOne(Integer.parseInt(ID));
					if(provider != null){
						notValid= false;
					}
				}
				if(notValid)
					System.out.print("\t Invalid ID. Re-enter: ");
			} else
				System.out.print("\t Invalid ID. Re-enter: ");
		}
		//-----------------END validation----------------
		
		
		//-----------------DISPLAY INFORMATION (member or provider)-----------------
		
		 // Get the data of the member
		if(member != null){
			
			//Check possible null fields
			 AddrComp  = (member.getAddrComp()  == null) ? "" : member.getAddrComp(); 
			 CellPhone = (member.getCellPhone() == null) ? "" : member.getCellPhone();
			 HomePhone = (member.getHomePhone() == null) ? "" : member.getHomePhone();
			 WorkPhone = (member.getWorkPhone() == null) ? "" : member.getWorkPhone();
			 Email     = (member.getEmail()     == null) ? "" : member.getEmail();
			 City      = (member.getCity()      == null) ? "" : member.getCity();
			 ZipCode   = (member.getZipCode()   == null) ? "" : member.getZipCode();
			 State     = (member.getState()     == null) ? "" : member.getState();
			 StAddr    = (member.getStAddr()    == null) ? "" : member.getStAddr();
			 FstName   = (member.getFstName()   == null) ? "" : member.getFstName();
			 LstName   = (member.getLstName()   == null) ? "" : member.getLstName();
			 
			 System.out.println("\n\t--- Member Info ---\n");
		}
		 // Get the data of the provider
		else{
			
			//Check possible null fields
			 AddrComp  = (provider.getAddrComp()  == null) ? "" : provider.getAddrComp(); 
			 CellPhone = (provider.getCellPhone() == null) ? "" : provider.getCellPhone();
			 HomePhone = (provider.getHomePhone() == null) ? "" : provider.getHomePhone();
			 WorkPhone = (provider.getWorkPhone() == null) ? "" : provider.getWorkPhone();
			 Email     = (provider.getEmail()     == null) ? "" : provider.getEmail();
			 City      = (provider.getCity()      == null) ? "" : provider.getCity();
			 ZipCode   = (provider.getZipCode()   == null) ? "" : provider.getZipCode();
			 State     = (provider.getState()     == null) ? "" : provider.getState();
			 StAddr    = (provider.getStAddr()    == null) ? "" : provider.getStAddr();
			 FstName   = (provider.getFstName()   == null) ? "" : provider.getFstName();
			 LstName   = (provider.getLstName()   == null) ? "" : provider.getLstName();

			 System.out.println("\n\t--- Provider Info ---\n");
		}
		//Display data
		System.out.println("\t First name    : "    + FstName);
		System.out.println("\t Last name     : "    + LstName);
		System.out.println("\t Email         : "    + Email);
		System.out.println("\t Cell phone    : "    + CellPhone);
		System.out.println("\t Home phone    : "    + HomePhone);
		System.out.println("\t Work phone    : "    + WorkPhone);
		System.out.println("\t Address       : "    + StAddr);
		System.out.println("\t Address line 2: "    + AddrComp);
		System.out.println("\t City          : "    + City);
		System.out.println("\t State         : "    + State);
		System.out.println("\t Zip code      : "    + ZipCode);
		System.out.println("\n\t-------------------\n");
		
		//-----------------END DISPLAY INFORMATION (member or provider)-------------
		
		

		//---------------------------START UPDATE-----------------------------------
		
		System.out.print("Do you want to update this "+ user + " ? Y-N: ");
		if(u.validInput().equalsIgnoreCase("Y")){
			System.out.print("\t First name    : ");
			in = sc.nextLine();
			
			if(in.equals("")){
				userData[6] = FstName;
			}
			else{
				userData[6] = in;
			}
		
			System.out.print("\t Last name     : ");
			in = sc.nextLine();
			
			if(in.equals("")){
				userData[5] = LstName;
			}
			else{
				userData[5] = in;
			}
			
			
			System.out.print("\t Email         : ");
			in = sc.nextLine();
			
			if(in.equals("")){
				userData[10] = Email;
			}
			else{
				userData[10] = in;
			}
			
			System.out.print("\t Cell phone    : ");
			in = sc.nextLine();
			
			if(in.equals("")){
				userData[7] = CellPhone;
			}
			else{
				userData[7] = in;
			}
			
			System.out.print("\t Home phone    : ");
			in = sc.nextLine();
			
			if(in.equals("")){
				userData[8] = HomePhone;
			}
			else{
				userData[8] = in;
			}
			
			System.out.print("\t Work phone    : ");
			in = sc.nextLine();
			
			if(in.equals("")){
				userData[9] = WorkPhone;
			}
			else{
				userData[9] = in;
			}
			
			System.out.print("\t Address       : ");
			in = sc.nextLine();
			
			if(in.equals("")){
				userData[0] = StAddr;
			}
			else{
				userData[0] = in;
			}
			
			
			System.out.print("\t Address line 2: ");
			in = sc.nextLine();
			
			if(in.equals("")){
				userData[1] = AddrComp;
			}
			else{
				userData[1] = in;
			}
			
			System.out.print("\t City          : ");
			in = sc.nextLine();
			
			if(in.equals("")){
				userData[2] = City;
			}
			else{
				userData[2] = in;
			}
			
			System.out.print("\t State         : ");
			in = sc.nextLine();
			
			if(in.equals("")){
				userData[3] = State;
			}
			else{
				userData[3] = in;
			}
			
			System.out.print("\t Zip code      : ");
			in = sc.nextLine();
			
			if(in.equals("")){
				userData[4] = ZipCode;
			}
			else{
				userData[4] = in;
			}
		if(user.equals("member")){
			if(memberCtrl.uOne(Integer.parseInt(ID),1,userData[0],userData[1],userData[2],userData[3],
					userData[4],userData[5],userData[6],userData[7],
					userData[8],userData[9],userData[10]) == User.SUCCESSFUL_SQL_QUERY){
				u.clearScreen();
				System.out.println("Member Updated sucessfully");
				startMenu(operator);
			}
			else{
				System.out.println("SQL Error:");
			}
		}
		else{
			if(providerCtrl.uOne(Integer.parseInt(ID),1,userData[0],userData[1],userData[2],userData[3],
					userData[4],userData[5],userData[6],userData[7],
					userData[8],userData[9],userData[10]) == User.SUCCESSFUL_SQL_QUERY){
				u.clearScreen();
				System.out.println("Provider Updated sucessfully");
				startMenu(operator);
			}
			else{
				System.out.println("SQL Error:");
			}
		}
	}else{
		// Answer NO to update
		u.clearScreen();
		startMenu(operator);
		
	}
		//----------------------------END UPDATE-----------------------------------
	}
	public void openMenuDelete(String user,User operator) {
		String in = "";
		String ID = "";
		boolean notValid  = true;
		Member member     = null;
		Provider provider = null;
		
		
		String AddrComp  ;
		String CellPhone ;
		String HomePhone ;
		String WorkPhone ;
		String Email     ;
		String City      ;
		String ZipCode   ;
		String State     ;
		String StAddr    ;
		String FstName   ;
		String LstName   ;
		
		
		
		
		System.out.println("\n\t Operation: Delete " + user);
		System.out.print("\t Enter the ID: ");
		
		//-----------------ID validation-----------------
		while(notValid) {
			ID = sc.nextLine();
			if(u.isNumeric(ID)) { // valid ID
				if(user.equals("member")){
					member = memberCtrl.sOne(Integer.parseInt(ID));
					if(member != null){
						notValid = false;
					}
				}
				//It's a provider
				else{
					provider = providerCtrl.sOne(Integer.parseInt(ID));
					if(provider != null){
						notValid= false;
					}
				}
				if(notValid)
					System.out.print("\t Invalid ID. Re-enter: ");
			} else
				System.out.print("\t Invalid ID. Re-enter: ");
		}
		//-----------------END validation----------------
		
		
		//-----------------DISPLAY INFORMATION (member or provider)-----------------
		
		 // Get the data of the member
		if(member != null){
			
			//Check possible null fields
			 AddrComp  = (member.getAddrComp()  == null) ? "" : member.getAddrComp(); 
			 CellPhone = (member.getCellPhone() == null) ? "" : member.getCellPhone();
			 HomePhone = (member.getHomePhone() == null) ? "" : member.getHomePhone();
			 WorkPhone = (member.getWorkPhone() == null) ? "" : member.getWorkPhone();
			 Email     = (member.getEmail()     == null) ? "" : member.getEmail();
			 City      = (member.getCity()      == null) ? "" : member.getCity();
			 ZipCode   = (member.getZipCode()   == null) ? "" : member.getZipCode();
			 State     = (member.getState()     == null) ? "" : member.getState();
			 StAddr    = (member.getStAddr()    == null) ? "" : member.getStAddr();
			 FstName   = (member.getFstName()   == null) ? "" : member.getFstName();
			 LstName   = (member.getLstName()   == null) ? "" : member.getLstName();
			 
			 System.out.println("\n\t--- Member Info ---\n");
		}
		 // Get the data of the provider
		else{
			
			//Check possible null fields
			 AddrComp  = (provider.getAddrComp()  == null) ? "" : provider.getAddrComp(); 
			 CellPhone = (provider.getCellPhone() == null) ? "" : provider.getCellPhone();
			 HomePhone = (provider.getHomePhone() == null) ? "" : provider.getHomePhone();
			 WorkPhone = (provider.getWorkPhone() == null) ? "" : provider.getWorkPhone();
			 Email     = (provider.getEmail()     == null) ? "" : provider.getEmail();
			 City      = (provider.getCity()      == null) ? "" : provider.getCity();
			 ZipCode   = (provider.getZipCode()   == null) ? "" : provider.getZipCode();
			 State     = (provider.getState()     == null) ? "" : provider.getState();
			 StAddr    = (provider.getStAddr()    == null) ? "" : provider.getStAddr();
			 FstName   = (provider.getFstName()   == null) ? "" : provider.getFstName();
			 LstName   = (provider.getLstName()   == null) ? "" : provider.getLstName();

			 System.out.println("\n\t--- Provider Info ---\n");
		}
		//Display data
		System.out.println("\t First name    : "    + FstName);
		System.out.println("\t Last name     : "    + LstName);
		System.out.println("\t Email         : "    + Email);
		System.out.println("\t Cell phone    : "    + CellPhone);
		System.out.println("\t Home phone    : "    + HomePhone);
		System.out.println("\t Work phone    : "    + WorkPhone);
		System.out.println("\t Address       : "    + StAddr);
		System.out.println("\t Address line 2: "    + AddrComp);
		System.out.println("\t City          : "    + City);
		System.out.println("\t State         : "    + State);
		System.out.println("\t Zip code      : "    + ZipCode);
		System.out.println("\n\t-------------------\n");
		
		//-----------------END DISPLAY INFORMATION (member or provider)-------------
		
		//---------------------------START DELETE-----------------------------------
		
		System.out.print("Do you want to delete this "+ user + " ? Y-N: ");
		if(u.validInput().equalsIgnoreCase("Y")){
			if(user.equals("member")){
				if(memberCtrl.alterStatus(Integer.parseInt(ID),User.STATUS_DELETED)
						== User.SUCCESSFUL_SQL_QUERY){
					u.clearScreen();
					System.out.println("Member sucessfully deleted.");
					startMenu(operator);
				}
				else{
					System.out.println("SQL error:");
				}
			}
			else{
				if(providerCtrl.alterStatus(Integer.parseInt(ID),User.STATUS_DELETED)
						== User.SUCCESSFUL_SQL_QUERY){
					u.clearScreen();
					System.out.println("Provider successfully deleted.");
					startMenu(operator);
				}
				else{
					System.out.println("SQL error:");
				}
			}
		}
		else{
			u.clearScreen();
			startMenu(operator);
		}
		//---------------------------END DELETE-------------------------------------
	}
	
}
