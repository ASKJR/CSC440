package beans;

public class Operator extends User {

	private int fkIdOperator;
	
	public Operator(){}
	
	public Operator(
			int fkIdOperator
			){
		this.fkIdOperator = fkIdOperator;
	}
	
	public int addMember(Member member) {
		return 0;
	}

	public int deleteMember(Member member) {
		return 0;
	}

	public int updateMember(Member member) {
		return 0;
	}

	public int addProvider(Provider provider) {
		return 0;
	}

	public int deleteProvider(Provider provider) {
		return 0;
	}

	public int updateProvider(Provider provider) {
		return 0;
	}

	public int getFkIdOperator() {
		return fkIdOperator;
	}

	public void setFkIdOperator(int fkIdOperator) {
		this.fkIdOperator = fkIdOperator;
	}
	
	
	
}