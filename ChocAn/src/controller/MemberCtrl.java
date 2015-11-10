package controller;

import beans.Member;
import dao.MemberDAO;

public class MemberCtrl {
	
	public int iOne(
		int fkIdMember
		, int status
			){
		
		MemberDAO memberDAO = new MemberDAO();
		Member member = new Member();
		
		member.setFkIdMember(fkIdMember);
		member.setStatus(status);
		
		return memberDAO.iOne(member);
	}
}
