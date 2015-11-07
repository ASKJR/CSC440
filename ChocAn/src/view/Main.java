package view;

import java.util.ArrayList;

import beans.User;
import dao.UserDAO;

public class Main {

	public static void main(String[] args) {
		
		UserDAO uDAO = new UserDAO();
		ArrayList<User> userList = uDAO.sAll();
		
		for(User user : userList){
			System.out.println(user.getFstName());
		}
		
	}

}
