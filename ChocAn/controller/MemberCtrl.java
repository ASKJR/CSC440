package controller;

import beans.Member;
import dao.MemberDAO;

public class MemberCtrl {
	
	public int iOne
		(
			int status,
			String stAddr,
			String addrComp,
			String city,
			String state,
			String zipCode,
			String lstName,
			String fstName,
			String cellPhone,
			String homePhone,
			String workPhone,
			String email
		)
	{
		MemberDAO memberDAO = new MemberDAO();
		Member member = new Member();

		member.setStatus(status);
		member.setStAddr(stAddr);
		member.setAddrComp(addrComp);
		member.setCity(city);
		member.setState(state);
		member.setZipCode(zipCode);
		member.setLstName(lstName);
		member.setFstName(fstName);
		member.setCellPhone(cellPhone);
		member.setHomePhone(homePhone);
		member.setWorkPhone(workPhone);
		member.setEmail(email);
		
		return memberDAO.iOne(member);
	}
	
	public int uOne
		(
			int fkIdMember,
			int status,
			String stAddr,
			String addrComp,
			String city,
			String state,
			String zipCode,
			String lstName,
			String fstName,
			String cellPhone,
			String homePhone,
			String workPhone,
			String email
		)
	{
			
		MemberDAO memberDAO = new MemberDAO();
		Member member = new Member();
		
		member.setFkIdMember(fkIdMember);
		member.setIdUser(fkIdMember);
		member.setStatus(status);
		member.setStAddr(stAddr);
		member.setAddrComp(addrComp);
		member.setCity(city);
		member.setState(state);
		member.setZipCode(zipCode);
		member.setLstName(lstName);
		member.setFstName(fstName);
		member.setCellPhone(cellPhone);
		member.setHomePhone(homePhone);
		member.setWorkPhone(workPhone);
		member.setEmail(email);
		
		return memberDAO.uOne(member);
	}
	
	public Member sOne
	(
		int fkIdMember
	)
	{
		MemberDAO memberDAO = new MemberDAO();
		Member member = new Member();
		
		member.setFkIdMember(fkIdMember);
		member.setIdUser(fkIdMember);
		
		return memberDAO.sOne(member);
	}
	
}
