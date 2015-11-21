package view;

import java.util.Scanner;

import beans.*;
import controller.*;

public class Main {

	public static void main(String[] args) {
		
		// DECLARATIONS
		
		LoginCtrl loginCtrl = new LoginCtrl();						// Controller instances
		UserCtrl userCtrl = new UserCtrl();							
		
		User user = new User();										// Beans instances
		
		Scanner scan = new Scanner(System.in);						// Scanner instance
		
		int idUser = -1;											// Variables
		String password = new String();
		
		// CODING
		
		System.out.println("Enter any User's ID:");					// Reading ID (swiping ID card)
		idUser = Integer.valueOf(scan.nextLine());

		user = loginCtrl.logIn(idUser);								// Looking for the ID in the User table
		if(user != null){
			System.out.printf("User Found! ");
			switch(userCtrl.retrieveUserType(user))					// Retrieving type of user
			{
			case User.MANAGER:
				System.out.printf("It is a Manager!");					// MANAGER
				break;
			case User.PROVIDER:
				System.out.printf("It is a Provider!");					// PROVIDER
				break;
			case User.OPERATOR:
				System.out.printf("It is an Operator!");				// OPERATOR
				break;
			case User.MEMBER:
				System.out.printf("It is a Member!");					// MEMBER
				break;
			default:
				// User Type NOT DEFINED (The user exists in table User, but his ID is not a FK in any of the other tables (Provider, Manager, Operator, or Member))
				System.out.println("User Type Not Defined!"); 
			}
			 
			System.out.println("\nEnter Your Password: ");			// Requesting password
			password = scan.nextLine();
			
			user.setLogin(new Login(idUser, password, null));		// Filling information of the object user.login
			
			switch(loginCtrl.verifyPassword(user.getLogin()))		// Checking password
			{
			case 1:
				System.out.println("Password Correct!");				// CORRECT
				break;
			case 0:
				System.out.println("Password Incorrect!");				// INCORRECT
				break;
			case -1:
				System.out.println("System Error!");					// SYSTEM ERROR
				break;
			default:
				System.out.println("Unknown System Error!");			// UNKNOWN SYSTEM ERROR
			}
			
		}
		else
		{
			System.out.println("User Not Found!");
		}
		
		scan.close();
		
	}

}
