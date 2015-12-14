package beans;

public class Operator extends User {

	private int fkIdOperator;
	
	public Operator(){}
	
	public Operator(
			int fkIdOperator
			){
		this.fkIdOperator = fkIdOperator;
	}

	public int getFkIdOperator() {
		return fkIdOperator;
	}

	public void setFkIdOperator(int fkIdOperator) {
		this.fkIdOperator = fkIdOperator;
	}
	
	
	
}
