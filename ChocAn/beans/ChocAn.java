package beans;

import java.io.BufferedReader;
import java.io.File;
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
			
			int totalFee = 0;
			
			ArrayList<ServiceProvided> serviceProvidedList = serviceProvidedDAO.sAll(provider);
			
			for(ServiceProvided serviceProvided : serviceProvidedList){
				totalFee += serviceProvided.getService().getFee();
			}
			
			writeFile.print("\n");
			writeFile.print(provider.getLstName() + ";");
			writeFile.print(provider.getFkIdProvider() + ";");
			writeFile.print(totalFee + ";");
			
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

	public String createAccountPayableReportFile() {

		ProviderDAO providerDAO = new ProviderDAO();

		ArrayList<Provider> providerList = providerDAO.sAll();
		ArrayList<String> fileNameList = new ArrayList();
		String reportFileName = "Manager_Accounts_Payable_Report.txt";
		
		for(Provider provider : providerList)
			fileNameList.add(createListOfServicesReportFile(provider));
		
		// Creating the file pointer
		FileWriter file;
		try {
			file = new FileWriter(reportFileName);
		} catch (IOException e) {
			file = null;
			return null;
		} 
		PrintWriter writeFile = new PrintWriter(file);
		
		for(String fileName : fileNameList)
		{
			String content = "";
			// Reading the stored file and appending to a single String variable
			BufferedReader bufferedReader;
			try 
			{
				bufferedReader = new BufferedReader
				(
					new FileReader(fileName)
				);
				
				String line = "";
				while ((line = bufferedReader.readLine()) != null)
					content += line;
				
				bufferedReader.close();
			} 
			catch (IOException e) {
				return null;
			}
				
			
			File tempFile = new File(fileName);
			tempFile.delete();
			
			writeFile.println("\n\n<BR><BR>========================== NEW PROVIDER ==========================<BR><BR>" + content);
		}
		
		writeFile.close();
		
		return reportFileName;
	}

	public String createListOfServicesReportFile(Member member) {

		ServiceProvidedDAO serviceProvidedDAO = new ServiceProvidedDAO();
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		ArrayList<ServiceProvided> serviceProvidedList = serviceProvidedDAO.sLastWeek(member);
		String fileName = "Member_" + member.getLstName() + "_" + member.getFkIdMember() + "_" + "_Services.txt";
		
		// Creating the file pointer
		FileWriter file;
		try {
			file = new FileWriter(fileName);
		} catch (IOException e) {
			file = null;
			return null;
		} 
		PrintWriter writeFile = new PrintWriter(file);
		
		// Filling the file with the requested information
		writeFile.printf("\n\t\t <br>Member Information");
		writeFile.printf("\n\t <br><br>ID: " + member.getFkIdMember());
		writeFile.printf("\n\t <br>First Name: " + member.getFstName()); 
		writeFile.printf("\n\t <br>Last Name: " + member.getLstName());
		writeFile.printf("\n\t <br>Street Address: " + member.getStAddr());
		writeFile.printf("\n\t <br>City: " + member.getCity());
		writeFile.printf("\n\t <br>State: " + member.getState());
		writeFile.printf("\n\t <br>ZIP Code: " + member.getZipCode());
		
		writeFile.printf("\n\n\t\t <br><br>Services used in the last week");
		
		for(ServiceProvided serviceProvided : serviceProvidedList){
			writeFile.printf("\n\n\t <br><br>Date of Service: " + dateFormat.format(serviceProvided.getOccurrenceDate()));
			writeFile.printf("\n\t <br>Provider Name: " + serviceProvided.getProvider().getFstName());
			writeFile.printf("\n\t <br>Service Name: " + serviceProvided.getService().getName());	
		}
		
		// Closing the file pointer
		try {
			file.close();
		} catch (IOException e) {
			return null;
		}

		return fileName;
		
	}
	
	public String createListOfServicesReportFile(Provider provider) {
		
		ServiceProvidedDAO serviceProvidedDAO = new ServiceProvidedDAO();
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		ArrayList<ServiceProvided> serviceProvidedList = serviceProvidedDAO.sLastWeek(provider);
		String fileName = "Provider_" + provider.getLstName() + "_" + provider.getFkIdProvider() + "_" + "_Services.txt";
		
		// Creating the file pointer
		FileWriter file;
		try {
			file = new FileWriter(fileName);
		} catch (IOException e) {
			file = null;
			return null;
		} 
		PrintWriter writeFile = new PrintWriter(file);
		
		// Filling the file with the requested information
		writeFile.printf("\n\t\t <br>Provider Information");
		writeFile.printf("\n\n\t <br><br>ID: " + provider.getFkIdProvider());
		writeFile.printf("\n\t <br>First Name: " + provider.getFstName() + " " + provider.getLstName());
		writeFile.printf("\n\t <br>Street Address: " + provider.getStAddr());
		writeFile.printf("\n\t <br>City: " + provider.getCity());
		writeFile.printf("\n\t <br>State: " + provider.getState());
		writeFile.printf("\n\t <br>ZIP Code: " + provider.getZipCode());
		
		writeFile.printf("\n\n\t\t <br><br>Services provided in the last week");
		
		double totalFee = 0;
		int numberOfConsultations = 0;
		for(ServiceProvided serviceProvided : serviceProvidedList){
			writeFile.printf("\n\n\t <br><br>Date of Service: " + dateFormat.format(serviceProvided.getOccurrenceDate()));
			writeFile.printf("\n\t <br>Date and Time of Registration: " + dateFormat.format(serviceProvided.getCurrentDate()));
			writeFile.printf("\n\t <br>Member Name: " + serviceProvided.getMember().getFstName() + " " + serviceProvided.getMember().getLstName());
			writeFile.printf("\n\t <br>Member ID: " + serviceProvided.getMember().getFkIdMember());
			writeFile.printf("\n\t <br>Service Name: " + serviceProvided.getService().getName());
			writeFile.printf("\n\t <br>Service ID: " + serviceProvided.getService().getIdService());
			writeFile.printf("\n\t <br>Fee: " + serviceProvided.getService().getFee());
			totalFee += serviceProvided.getService().getFee();
			numberOfConsultations++;
		}
		
		writeFile.printf("\n\n\t <br><br>Number of Consultations: " + numberOfConsultations);
		writeFile.printf("\n\n\t <br>Total fee: " + totalFee);
		
		// Closing the file pointer
		try {
			file.close();
		} catch (IOException e) {
			return null;
		}

		return fileName;

	}
	
	public int sendReportViaEmail(String fileName, String destinationAddress, String subject){
		
		destinationAddress = "rso_oliver@hotmail.com";
		
		String content = "";
		// Reading the stored file and appending to a single String variable
		BufferedReader bufferedReader;
		try 
		{
			bufferedReader = new BufferedReader
			(
				new FileReader(fileName)
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
			subject, 
			content,
			destinationAddress
		);
		
	}

}
