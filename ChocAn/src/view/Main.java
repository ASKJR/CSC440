package view;

import java.util.Scanner;

import beans.*;
import controller.*;
import dao.*;

public class Main {

	public static void main(String[] args) {
		
		LoginCtrl loginCtrl = new LoginCtrl();
		UserCtrl userCtrl = new UserCtrl();
		
		User user = new User();
		Login login = new Login();
		
		Scanner scan = new Scanner(System.in);
		
		int idUser = -1;
		String password = new String();
		
		System.out.println("Enter any User's ID:");
		idUser = scan.nextInt();

		user = loginCtrl.logIn(idUser);
		if(user != null){
			System.out.printf("User Found! ");
			switch(userCtrl.retrieveUserType(user))
			{
			case User.MANAGER:
				System.out.printf("It is a Manager!");
				break;
			case User.PROVIDER:
				System.out.printf("It is a Provider!");
				break;
			case User.OPERATOR:
				System.out.printf("It is an Operator!");
				break;
			case User.MEMBER:
				System.out.printf("It is a Member!");
				break;
			default:
				System.out.println("User Type Not Defined!");
			}
			
			scan.nextLine();
			System.out.println("\nEnter Your Password: ");
			password = scan.nextLine();
			
			login.setFkIdUser(idUser);
			login.setPassword(password);
			
			switch(loginCtrl.verifyPassword(login))
			{
			case 1:
				System.out.println("Password Correct!");
				break;
			case 0:
				System.out.println("Password Incorrect!");
				break;
			case -1:
				System.out.println("System Error!");
			}
			
		}
		else
		{
			System.out.println("User Not Found!");
		}
		
		scan.close();
		
	}

}
