package beans;

public class Member extends User { 
	
	private int fkIdMember;
	private int status;
	
	public Member(){}
	
	public Member(
			int fkIdMember,
			int status
			){
		this.fkIdMember = fkIdMember;
		this.status = status;
	}

	public int getFkIdMember() {
		return fkIdMember;
	}

	public void setFkIdMember(int fkIdMember) {
		this.fkIdMember = fkIdMember;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
