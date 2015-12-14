package beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dao.ProviderDAO;
import dao.ServiceProvidedDAO;

public class Manager extends User{
	
	private int fkIdManager;
	
	public Manager(){}
	
	public Manager
		(
			int fkIdManager
		)
	{
		this.fkIdManager = fkIdManager;
	}
	
	public String lastWeekServices(Provider provider) {
		
		ServiceProvidedDAO serviceProvidedDAO = new ServiceProvidedDAO();
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat dateTimeFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aaa");
		DecimalFormat decimalFormat = new DecimalFormat("US$##0.00");
		
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
			writeFile.printf("\n\t Date and Time of Registration: " + dateTimeFormat.format(serviceProvided.getCurrentDate()));
			writeFile.printf("\n\t Member Name: " + serviceProvided.getMember().getFstName() + " " + serviceProvided.getMember().getLstName());
			writeFile.printf("\n\t Member ID: " + serviceProvided.getMember().getFkIdMember());
			writeFile.printf("\n\t Service Name: " + serviceProvided.getService().getName());
			writeFile.printf("\n\t Service ID: " + serviceProvided.getService().getIdService());
			writeFile.printf("\n\t Fee: " + decimalFormat.format(serviceProvided.getService().getFee() * (double) Provider.PERCENTAGE));
			totalFee += serviceProvided.getService().getFee() * (double) Provider.PERCENTAGE;
			numberOfConsultations++;
		}
		
		writeFile.printf("\n\n\t Total number of consultations with members: " + numberOfConsultations);
		writeFile.printf("\n\n\t Total fee for week: " + decimalFormat.format(totalFee));
		
		// Closing the file pointer
		try {
			file.close();
		} catch (IOException e) {
			return null;
		}

		return fileName;

	}
	
	public String createWeeklyReport() {

		ProviderDAO providerDAO = new ProviderDAO();

		ArrayList<Provider> providerList = providerDAO.sAll();
		ArrayList<String> fileNameList = new ArrayList<String>();
		String reportFileName = "Manager_Weekly_Providers_Report.txt";
		
		for(Provider provider : providerList)
			fileNameList.add(lastWeekServices(provider));
		
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
					content += "\n" + line;
				
				bufferedReader.close();
			} 
			catch (IOException e) {
				return null;
			}
				
			
			File tempFile = new File(fileName);
			tempFile.delete();
			
			writeFile.println("\n\t ================= NEW PROVIDER ================= " + content);
		}
		
		writeFile.close();
		
		return reportFileName;

	}
	
	public String requestWeeklyReportsFile() {
		ChocAn chocAn = new ChocAn();
		String fileName = createWeeklyReport();
		
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
				content += "\n" + line;
			
			bufferedReader.close();
		} 
		catch (IOException e) {
			return null;
		}
		
		System.out.println(content);
		
		return fileName;
		
	}

	public int getFkIdManager() {
		return fkIdManager;
	}

	public void setFkIdManager(int fkIdManager) {
		this.fkIdManager = fkIdManager;
	}
	
}
