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
		
		member.setIdUser(2);
		provider.setIdUser(16);
		
		member.setFkIdMember(2);
		provider.setFkIdProvider(16);
		
		member = memberDAO.sOne(member);
		provider = providerDAO.sOne(provider);
		
		chocAn.sendListOfServices(member);
		chocAn.sendListOfServices(provider);

	}
}
