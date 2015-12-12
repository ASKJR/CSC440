package view;

import java.util.Scanner;

import beans.*;
import dao.*;

public class Main {

	public static void main(String[] args) {
		
		ChocAn chocAn = new ChocAn();
		
		Provider provider = new Provider();
		ProviderDAO providerDAO = new ProviderDAO();
		
		Member member = new Member();
		MemberDAO memberDAO = new MemberDAO();
		
		member.setFkIdMember(2);
		provider.setFkIdProvider(16);
		
		member = memberDAO.sOne(member);
		provider = providerDAO.sOne(provider);
		
		Manager manager = new Manager();
		String fileName = manager.requestWeeklyReportsFile();
		
		Scanner scanner = new Scanner(System.in);
		
		String yesNo = "";
		System.out.println("\n\n\t Do you want to receive this report via email (yes/no) ?");
		yesNo = scanner.nextLine();
		
		if(yesNo.equals("YES") || yesNo.equals("yes")){
			chocAn.sendReportViaEmail(fileName, "rso_oliver@hotmail.com", "Accounts Payable Report");
		}

	}
}
