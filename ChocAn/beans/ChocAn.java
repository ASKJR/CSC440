package beans;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dao.ServiceProvidedDAO;
import util.EmailSender;

public class ChocAn {
	
	private final int ERR_CREATING_FILE = -1;
	private final int ERR_CLOSING_FILE = -2;
	private final int ERR_READING_FILE = -3;

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
		return 0;
	}

	public int validateMember() {
		return 0;
	}

}
