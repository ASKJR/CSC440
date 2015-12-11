package controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

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
	
	public ArrayList<ServiceProvided> sSome(int fkIdMember){
		
		Member member = new Member();
		ServiceProvidedDAO serviceProvidedDAO = new ServiceProvidedDAO();
		
		member.setFkIdMember(fkIdMember);
		ArrayList<ServiceProvided> serviceProvidedList = serviceProvidedDAO.sLastWeek(member);
		
		return serviceProvidedList;
	}
	
}
