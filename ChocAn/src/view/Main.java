package view;

import java.util.Scanner;

import beans.*;
import controller.LoginCtrl;
import controller.UserCtrl;
import dao.UserDAO;

public class Main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int userId = 0;
		String password = "";
		
		System.out.println("ID: ");
		userId = Integer.valueOf(scan.nextLine());
		
		System.out.println("Password: ");
		password = scan.nextLine();
		
		LoginCtrl loginCtrl = new LoginCtrl();
		Login login = new Login();
		
		login.setFkIdUser(userId);
		login.setPassword(password);
		
		User user = null;
		
		if(loginCtrl.verifyLogin(userId, password) == 1){
			user = loginCtrl.retrieveUserType(login);
			
			if(user instanceof Manager){
				System.out.println("It is a Manager!");
			}else if (user instanceof Member){
				System.out.println("It is a Member!");
			}else if (user instanceof Operator){
				System.out.println("It is a Operator!");
			}else if (user instanceof Provider){
				System.out.println("It is a Provider!");
			}else {
				System.out.println("It is a not defined user!");
			}
			
			System.out.println("ID: " + user.getIdUser());
			System.out.println("ST_ADDR: " + user.getStAddr());
			System.out.println("ADDR_COMP: " + user.getAddrComp());
			System.out.println("CITY: " + user.getCity());
			System.out.println("STATE: " + user.getState());
			System.out.println("ZIP_CODE: " + user.getZipCode());
			System.out.println("LST_NAME: " + user.getLstName());
			System.out.println("FST_NAME: " + user.getFstName());
			System.out.println("CELL_PHONE: " + user.getCellPhone());
			System.out.println("HOME_PHONE: " + user.getHomePhone());
			System.out.println("WORK_PHONE: " + user.getWorkPhone());
			System.out.println("EMAIL: " + user.getEmail());
			
		}
		
		scan.close();
		
	}

}
