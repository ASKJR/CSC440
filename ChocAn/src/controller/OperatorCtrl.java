package controller;

import beans.Operator;
import dao.OperatorDAO;

public class OperatorCtrl {

	public int iOne
		(
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
		OperatorDAO operatorDAO = new OperatorDAO();
		Operator operator = new Operator();

		operator.setStAddr(stAddr);
		operator.setAddrComp(addrComp);
		operator.setCity(city);
		operator.setState(state);
		operator.setZipCode(zipCode);
		operator.setLstName(lstName);
		operator.setFstName(fstName);
		operator.setCellPhone(cellPhone);
		operator.setHomePhone(homePhone);
		operator.setWorkPhone(workPhone);
		operator.setEmail(email);
		
		return operatorDAO.iOne(operator);
	}
}
