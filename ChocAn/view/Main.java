package view;

import beans.*;

public class Main {

	public static void main(String[] args) {
		
		ChocAn chocAn = new ChocAn();
		
		String fileName = chocAn.accountsPayableSummaryFile();
		
		chocAn.sendReportViaEmail(fileName, "rso_oliver@hotmail.com", "Accounts Payable Summary Report");

	}
}
