package controller;

import beans.Manager;
import dao.ManagerDAO;

public class ManagerCtrl {

	public int iOne
		(
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
		)
	{
		ManagerDAO managerDAO = new ManagerDAO();
		Manager manager = new Manager();
		
		manager.setStAddr(stAddr);
		manager.setAddrComp(addrComp);
		manager.setCity(city);
		manager.setState(state);
		manager.setZipCode(zipCode);
		manager.setLstName(lstName);
		manager.setFstName(fstName);
		manager.setCellPhone(cellPhone);
		manager.setHomePhone(homePhone);
		manager.setWorkPhone(workPhone);
		manager.setEmail(email);
		
		return managerDAO.iOne(manager);
	}
	
}