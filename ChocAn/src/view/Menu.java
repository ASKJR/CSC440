package view;

import java.util.*;
import beans.*;
import controller.*;

public class Menu {
	
	Scanner sc ;
	
	public Menu() {
		sc = new Scanner (System.in);
	}
	
	//************************ login and password
	public void startSystem() {
		
		LoginCtrl loginctrl = new LoginCtrl();
		User user = null;
		Login lg = new Login();
		
		String login = "";
		String password = "";
		
		while(true){
			System.out.print("\t Login: ");
			while(true) {
				login = sc.nextLine();
				if(isNumeric(login) && !login.equals("")) {
					break;
				} else System.out.print("\t Invalid login. Re-enter: ");
			}
			System.out.print("\t Password: ");
			password = sc.nextLine();
			
			lg.setFkIdUser(Integer.parseInt(login));
			lg.setPassword(password);
			
			if(loginctrl.verifyLogin(lg.getFkIdUser(),lg.getPassword()) == 1) {
			    	user =  loginctrl.retrieveUserType(lg);
					break;
			}
			else{
				System.out.println("Fail to log in!");
			}
		}
		//END login--------------------------------------------
		
		
		//Define user type
		if(user instanceof Operator){
			OperatorMenu operatorMenu = new OperatorMenu();
			operatorMenu.startMenu(user);
		}else if (user instanceof Manager){
			ManagerMenu managerMenu = new ManagerMenu();
			managerMenu.startMenu(user);
		}else{
			ProviderMenu providerMenu = new ProviderMenu();
			providerMenu.startMenu(user);
	    }
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
