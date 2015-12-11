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
			openMenuUpdate(option);
		else if(inInt == 3)
			openMenuDelete(option);
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
	
	public void openMenuUpdate(String user) {
		String in = "";
		boolean notValid  = true;
		Member member     = null;
		Provider provider = null;
		
		System.out.println("\n\t Operation: Update " + user);
		System.out.print("\t Enter the ID: ");
		while(notValid) {
			in = sc.nextLine();
			if(u.isNumeric(in)) { // valid ID
				if(user.equals("member")){
					member = memberCtrl.sOne(Integer.parseInt(in));
					if(member != null)
						if(member.getFkIdMember() != 0){
							System.out.println(member.getFkIdMember());
							notValid = false;
						}
				}
				//It's a provider
				else{
					provider = providerCtrl.sOne(Integer.parseInt(in));
					if(provider != null){
						if(provider.getFkIdProvider() != 0)
							notValid= false;
					}
				}
				if(notValid)
					System.out.print("\t Invalid ID. Re-enter: ");
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
		
		/*System.out.print("\t First name    : "); // fields to update data
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
		System.out.print("\n\t Do you want to update this " + user + "? (Y/N): ");*/
	}
	
	public void openMenuDelete(String user) {
		String in = "";
		boolean notValid = true;
		
		System.out.println("\n\t Operation: Delete " + user);
		System.out.print("\t Enter the ID: ");
		while(notValid) {
			in = sc.nextLine();
			if(u.isNumeric(in)) { // valid ID
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
	
}
