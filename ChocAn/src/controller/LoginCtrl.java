package controller;

import beans.*;
import dao.LoginDAO;
import dao.UserDAO;

public class LoginCtrl {

	public int iOne
		(
			int fkIdUser,
			String password
		)
	{
			
		LoginDAO loginDAO = new LoginDAO();
		Login login = new Login();
		
		login.setFkIdUser(fkIdUser);
		login.setPassword(password);
		
		return loginDAO.iOne(login);
	}
	
	public User logIn
		(
			int fkIdUser
		)
	{
		LoginDAO loginDAO = new LoginDAO();
		Login login = new Login();
		
		login.setFkIdUser(fkIdUser);
		
		return loginDAO.logIn(login);
	}
	
	public int verifyPassword
		(
			Login login
		)
	{
		LoginDAO loginDAO = new LoginDAO();
		return loginDAO.verifyPassword(login);
	}
	
}
