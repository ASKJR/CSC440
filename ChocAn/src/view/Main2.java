package view;

import controller.*;

public class Main2 {
	public static void main(String[] args) {
		Menu menu = new Menu();
		
		LoginCtrl loginctrl = new LoginCtrl(); 
		boolean notValid = true;
		
		while(notValid){
			int login        =  Integer.valueOf(menu.InputLogin());
		    String password  =  menu.InputPassword();
		    
		    if(loginctrl.verifyLogin(login,password) == 1) {
		    	notValid = false;
		    }
		}
		
		//menu.startMenuOperator();

	}

}
