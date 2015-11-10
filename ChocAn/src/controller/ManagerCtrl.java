package controller;

import beans.Manager;
import dao.ManagerDAO;

public class ManagerCtrl {

	public int iOne(
		int fkIdManager
			){
		
		ManagerDAO managerDAO = new ManagerDAO();
		Manager manager = new Manager();
		
		manager.setFkIdManager(fkIdManager);
		
		return managerDAO.iOne(manager);
	}
	
}