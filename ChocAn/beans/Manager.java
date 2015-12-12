package beans;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
	
	public String requestWeeklyReportsFile() {
		ChocAn chocAn = new ChocAn();
		String fileName = chocAn.weeklyReportsFile();
		
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
