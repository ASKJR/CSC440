package view;

import java.util.Scanner;

import beans.ChocAn;
import beans.Member;
import beans.Provider;
import dao.MemberDAO;
import dao.ProviderDAO;

public class ReportView {


	
	public static void main(String[] args) {

		ChocAn chocAn = new ChocAn();
		
		Scanner scanner = new Scanner(System.in);
		
		String answer = "Y";
		String fileName = "";
		String destinationAddress = "rso_oliver@hotmail.com";
		
		while(!answer.equals("0")){
			
			System.out.println("\t\t ChocAn Reports\n\n\t Emails will be sent to: " + destinationAddress);
			System.out.println("\n\n\t 1. Create EFT");
			System.out.println("\n\t 2. List Of Services Provided by a Provider");
			System.out.println("\n\t 3. List Of Services Used by Client");
			System.out.println("\n\t 4. Accounts Payable Summary Report");
			System.out.println("\n\n\t 0. Exit");
			System.out.print("\n\n\t Choice: ");

			answer = scanner.nextLine();
			
			switch(answer){
			case "0":
				System.out.println("\n\n\t\t Software Closed!\n");
				System.exit(0);
				break;
			case "1":
				if(chocAn.createEFT()==0)
					System.out.println("\n\n\t\t EFT created succesfully!");
				else
					System.out.println("\n\n\t\t EFT was NOT created!");
				break;
			case "2":
				Provider provider = new Provider();
				provider.setIdUser(16);
				provider.setFkIdProvider(16);
				ProviderDAO providerDAO = new ProviderDAO();
				provider = providerDAO.sOne(provider);
				
				System.out.println("\n\n\t The provider named \"" + provider.getFstName() + "\" will be used as example.");
				
				fileName = chocAn.lastWeekServicesFile(provider);
				System.out.print("\n\n\t\t Sending Email! \n\n");
				chocAn.sendReportViaEmail(fileName, destinationAddress, "List Of Services Provided");
				break;
			case "3":
				Member member = new Member();
				member.setIdUser(2);
				member.setFkIdMember(2);
				MemberDAO memberDAO = new MemberDAO();
				member = memberDAO.sOne(member);
				
				System.out.println("\n\n\t The member named \"" + member.getFstName() + "\" will be used as example.");
				
				fileName = chocAn.lastWeekServicesFile(member);
				System.out.print("\n\n\t\t Sending Email! \n\n");
				chocAn.sendReportViaEmail(fileName, destinationAddress, "List Of Services Used By Client");
				break;
			case "4":
				fileName = chocAn.accountsPayableSummaryFile();
				chocAn.sendReportViaEmail(fileName, destinationAddress, "Accounts Payable Summary Report");
				break;
			default:
					
			}
			
		}
		
		scanner.close();

	}
	
}
