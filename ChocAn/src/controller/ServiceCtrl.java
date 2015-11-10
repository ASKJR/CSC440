package controller;

import beans.Service;
import dao.ServiceDAO;

public class ServiceCtrl {

	public int iOne(
		String name,
		double fee
			){
		
		Service service = new Service();
		
		service.setFee(fee);
		service.setName(name);
		
		ServiceDAO serviceDAO = new ServiceDAO();
		
		return serviceDAO.iOne(service);
	}
	
}
