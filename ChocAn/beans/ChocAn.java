package beans;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dao.ProviderDAO;
import dao.ServiceProvidedDAO;
import util.EmailSender;

public class ChocAn {
	
	private final int ERR_CREATING_FILE = -1;
	private final int ERR_CLOSING_FILE = -2;
	private final int ERR_READING_FILE = -3;

	public int createEFT() {
		
		// Creating the file pointer
		FileWriter file;
		try {
			file = new FileWriter("EFT.txt");
		} catch (IOException e) {
			file = null;
			return ERR_CREATING_FILE;
		} 
		PrintWriter writeFile = new PrintWriter(file);
		
		ProviderDAO providerDAO = new ProviderDAO();
		ServiceProvidedDAO serviceProvidedDAO = new ServiceProvidedDAO();
		ArrayList<Provider> providerList = providerDAO.sAll();
		
		for(Provider provider : providerList){
			System.out.println(provider.getStatus());
		}
		
		for(Provider provider : providerList){
			
			int totalFee = 0;
			
			ArrayList<ServiceProvided> serviceProvidedList = serviceProvidedDAO.sAll(provider);
			
			for(ServiceProvided serviceProvided : serviceProvidedList){
				totalFee += serviceProvided.getService().getFee();
			}
			
System.out.println(provider.getLstName());
			
			writeFile.println(provider.getLstName());
			writeFile.println(provider.getFkIdProvider());
			writeFile.println(totalFee);
			
		}
		
		writeFile.close();
		try {
			file.close();
		} catch (IOException e) {
			return ERR_CLOSING_FILE;
		}
		
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
		String content = "";
		
		// Creating the file pointer
		FileWriter file;
		try {
			file = new FileWriter("ListOfServices.txt");
		} catch (IOException e) {
			file = null;
			return ERR_CREATING_FILE;
		} 
		PrintWriter writeFile = new PrintWriter(file);
		
		// Filling the file with the requested information
		writeFile.printf("\n\t\t Member Information");
		writeFile.printf("\n\t ID: " + member.getFkIdMember());
		writeFile.printf("\n\t First Name: " + member.getFstName()); 
		writeFile.printf("\n\t Last Name: " + member.getLstName());
		writeFile.printf("\n\t Street Address: " + member.getStAddr());
		writeFile.printf("\n\t City: " + member.getCity());
		writeFile.printf("\n\t State: " + member.getState());
		writeFile.printf("\n\t ZIP Code: " + member.getZipCode());
		
		writeFile.printf("\n\n\t\t Services used in the last week");
		
		for(ServiceProvided serviceProvided : serviceProvidedList){
			writeFile.printf("\n\n\t Date of Service: " + dateFormat.format(serviceProvided.getOccurrenceDate()));
			writeFile.printf("\n\t Provider Name: " + serviceProvided.getProvider().getFstName());
			writeFile.printf("\n\t Service Name: " + serviceProvided.getService().getName());	
		}
		
		// Closing the file pointer
		try {
			file.close();
		} catch (IOException e) {
			return ERR_CLOSING_FILE;
		}
		
		// Reading the stored file and appending to a single String variable
		BufferedReader bufferedReader;
		try 
		{
			bufferedReader = new BufferedReader
					(
						new FileReader("ListOfServices.txt")
					);
			
			String line = "";
			while ((line = bufferedReader.readLine()) != null)
				content += line;
			
			bufferedReader.close();
		} 
		catch (IOException e) 
		{
			return ERR_READING_FILE;
		}

		// Sending the information via email
		EmailSender emailSender = new EmailSender();
		return emailSender.sendMessage
		(
			"List Of Services", 
			content,
			"rso_oliver@hotmail.com"
			//member.getEmail()
		);
	}
	
	public int sendListOfServices(Provider provider) {
		ServiceProvidedDAO serviceProvidedDAO = new ServiceProvidedDAO();
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		ArrayList<ServiceProvided> serviceProvidedList = serviceProvidedDAO.sLastWeek(provider);
		String content = "";
		
		// Creating the file pointer
		FileWriter file;
		try {
			file = new FileWriter("ListOfServices.txt");
		} catch (IOException e) {
			file = null;
			return ERR_CREATING_FILE;
		} 
		PrintWriter writeFile = new PrintWriter(file);
		
		// Filling the file with the requested information
		writeFile.printf("\n\t\t Provider Information");
		writeFile.printf("\n\n\t ID: " + provider.getFkIdProvider());
		writeFile.printf("\n\t First Name: " + provider.getFstName() + " " + provider.getLstName());
		writeFile.printf("\n\t Street Address: " + provider.getStAddr());
		writeFile.printf("\n\t City: " + provider.getCity());
		writeFile.printf("\n\t State: " + provider.getState());
		writeFile.printf("\n\t ZIP Code: " + provider.getZipCode());
		
		writeFile.printf("\n\n\t\t Services provided in the last week");
		
		double totalFee = 0;
		int numberOfConsultations = 0;
		for(ServiceProvided serviceProvided : serviceProvidedList){
			writeFile.printf("\n\n\t Date of Service: " + dateFormat.format(serviceProvided.getOccurrenceDate()));
			writeFile.printf("\n\t Date and Time of Registration: " + dateFormat.format(serviceProvided.getCurrentDate()));
			writeFile.printf("\n\t Member Name: " + serviceProvided.getMember().getFstName() + " " + serviceProvided.getMember().getLstName());
			writeFile.printf("\n\t Member ID: " + serviceProvided.getMember().getFkIdMember());
			writeFile.printf("\n\t Service Name: " + serviceProvided.getService().getName());
			writeFile.printf("\n\t Service ID: " + serviceProvided.getService().getIdService());
			writeFile.printf("\n\t Fee: " + serviceProvided.getService().getFee());
			totalFee += serviceProvided.getService().getFee();
			numberOfConsultations++;
		}
		
		writeFile.printf("\n\n\t Number of Consultations: " + numberOfConsultations);
		writeFile.printf("\n\n\t Total fee: " + totalFee);
		
		// Closing the file pointer
		try {
			file.close();
		} catch (IOException e) {
			return ERR_CLOSING_FILE;
		}
		
		// Reading the stored file and appending to a single String variable
		BufferedReader bufferedReader;
		try 
		{
			bufferedReader = new BufferedReader
					(
						new FileReader("ListOfServices.txt")
					);
			
			String line = "";
			while ((line = bufferedReader.readLine()) != null)
				content += line;
			
			bufferedReader.close();
		} 
		catch (IOException e) 
		{
			return ERR_READING_FILE;
		}

		// Sending the information via email
		EmailSender emailSender = new EmailSender();
		return emailSender.sendMessage
		(
			"List Of Services", 
			content,
			"rso_oliver@hotmail.com"
			//member.getEmail()
		);
	}

	
	public int validateMember() {
		return 0;
	}

}
