package controller;

import java.sql.Date;
import java.sql.Timestamp;

import beans.*;
import dao.ServiceProvidedDAO;

public class ServiceProvidedCtrl {

	public int iOne(
		Provider provider,
		Service service,
		Member member,
		Timestamp currentDate,
		Date occurrenceDate,
		String comment
			){
		
		ServiceProvided serviceProvided = new ServiceProvided();
		
		serviceProvided.setComment(comment);
		serviceProvided.setCurrentDate(currentDate);
		serviceProvided.setOccurrenceDate(occurrenceDate);
		serviceProvided.setMember(member);
		serviceProvided.setProvider(provider);
		serviceProvided.setService(service);
		
		ServiceProvidedDAO serviceProvidedDAO = new ServiceProvidedDAO();
		
		return serviceProvidedDAO.iOne(serviceProvided);
	}
	
}
