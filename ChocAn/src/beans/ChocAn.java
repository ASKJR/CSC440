package beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dao.ServiceProvidedDAO;

public class ChocAn {

	public int createEFT() {
		return 0;
	}

	public int printWeeklyReports() {
		return 0;
	}

	public int sendAccountPayableReport(Manager manager) {
		return 0;
	}

	public int sendListOfServices(Member member) {

		ServiceProvidedDAO serviceProvidedDAO = new ServiceProvidedDAO();
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		ArrayList<ServiceProvided> serviceProvidedList = serviceProvidedDAO.sLastWeek(member);
		
		System.out.printf("\n\t\t Member Information");
		System.out.printf("\n\t ID: " + member.getFkIdMember());
		System.out.printf("\n\t First Name: " + member.getFstName()); 
		System.out.printf("\n\t Last Name: " + member.getLstName());
		System.out.printf("\n\t Street Address: " + member.getStAddr());
		System.out.printf("\n\t City: " + member.getCity());
		System.out.printf("\n\t State: " + member.getState());
		System.out.printf("\n\t ZIP Code: " + member.getZipCode());
		
		System.out.print("\n\n\t\t Services used in the last week");
		
		for(ServiceProvided serviceProvided : serviceProvidedList){
			System.out.printf("\n\n\t Date of Service: " + dateFormat.format(serviceProvided.getOccurrenceDate()));
			System.out.printf("\n\t Provider Name: " + serviceProvided.getProvider().getFstName());
			System.out.printf("\n\t Service Name: " + serviceProvided.getService().getName());	
		}
		
		
		
		return 0;
	}

	public int sendListOfServices(Provider provider) {
		return 0;
	}

	public int validateMember() {
		return 0;
	}

}
