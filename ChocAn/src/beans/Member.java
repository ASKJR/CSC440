package beans;

public class Member extends User {

	public static final int STATUS_VALID = 1;
	public static final int STATUS_INVALID = 2;
	public static final int STATUS_SUSPENDED = 3;
	public static final int STATUS_BLOCKED = 4;			// We need to work in pointing out all the possible status for a Member 
	
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