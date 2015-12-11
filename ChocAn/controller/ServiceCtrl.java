package controller;

import java.util.ArrayList;

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
	
	public ArrayList<Service> sAll(){
		ServiceDAO serviceDAO = new ServiceDAO();
		return serviceDAO.sAll();
	}
	
	public Service sOne(int fkIdService){
		
		Service service = new Service();
		ServiceDAO serviceDAO = new ServiceDAO();
		
		service.setIdService(fkIdService);
		
		return serviceDAO.sOne(service);
	}
	
}
