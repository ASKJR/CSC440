package controller;

import java.util.ArrayList;

import beans.Manager;
import beans.Member;
import beans.Operator;
import beans.Provider;
import beans.User;
import dao.UserDAO;

public class UserCtrl {

	public int iOne(
		String stAddr,
		String addrComp,
		String city,
		String state,
		String zipCode,
		String lstName,
		String fstName,
		String cellPhone,
		String homePhone,
		String workPhone,
		String email
			){
		
		UserDAO uDAO = new UserDAO();
		User user = new User();
		
		user.setAddrComp(addrComp);
		user.setStAddr(stAddr);
		user.setCellPhone(cellPhone);
		user.setCity(city);
		user.setEmail(email);
		user.setFstName(fstName);
		user.setHomePhone(homePhone);
		user.setLstName(lstName);
		user.setState(state);
		user.setWorkPhone(workPhone);
		user.setZipCode(zipCode);
		
		return uDAO.iOne(user);
	}
	
	public ArrayList<User> sAll(){
		
		UserDAO uDAO = new UserDAO();
		return uDAO.sAll();
	}
	
	public int retrieveUserType(User user){
		
		UserDAO userDAO = new UserDAO();
		
		User userTypeDefined = userDAO.retrieveUserType(user);
		
		if(userTypeDefined instanceof Provider)
			return User.PROVIDER;
		else if(userTypeDefined instanceof Manager)
			return User.MANAGER;
		else if(userTypeDefined instanceof Member) 
			return User.MEMBER;
		else if(userTypeDefined instanceof Operator)
			return User.OPERATOR;
		else
			return User.NOT_FOUND;
		
	}

	
}
