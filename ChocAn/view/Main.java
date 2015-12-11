package view;

import beans.ChocAn;
import beans.Member;
import controller.MemberCtrl;

public class Main {

	public static void main(String[] args) {
		
		ChocAn chocAn = new ChocAn();
		Member member = new Member();
		MemberCtrl memberCtrl = new MemberCtrl();

		member = memberCtrl.sOne(2);
		if(chocAn.sendListOfServices(member) == 0){
			System.out.println("Report Sent Successfully");
		}
	}

}
