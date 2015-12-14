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
	
	public int verifyLogin
		(
			int fkIdUser,
			String password
		)
	{
		LoginDAO loginDAO = new LoginDAO();
		UserDAO userDAO = new UserDAO();
		Login login = new Login();
		
		login.setFkIdUser(fkIdUser);
		login.setPassword(password);
		
		User user = loginDAO.retrieveUserType(login);
		
		login.setUser(user);
		
		return loginDAO.verifyLogin(login);
	}
	
    public User retrieveUserType
    	(
    		Login login
    	)
    {
    	LoginDAO loginDAO = new LoginDAO();
    	return loginDAO.retrieveUserType(login);
    }
	
}
