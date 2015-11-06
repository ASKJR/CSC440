package view;

import java.util.ArrayList;

import dao.UserDAO;

public class Main {

	public static void main(String[] args) {
		
		UserDAO uDAO = new UserDAO();
		ArrayList<String> lstNames = uDAO.sFstNames();
		
		for(String str : lstNames){
			System.out.println(str);
		}
		
	}

}
