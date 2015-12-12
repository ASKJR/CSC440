package view;

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
		
		String fileName = chocAn.createAccountPayableReportFile();
		chocAn.sendReportViaEmail(fileName, "rso_oliver@hotmail.com", "Accounts Payable Report");
		

	}
}
