package controller;

import beans.Operator;
import dao.OperatorDAO;

public class OperatorCtrl {

	public int iOne(
		int fkIdOperator
			){
		
		OperatorDAO operatorDAO = new OperatorDAO();
		Operator operator = new Operator();
		
		operator.setFkIdOperator(fkIdOperator);
		
		return operatorDAO.iOne(operator);
	}
}
