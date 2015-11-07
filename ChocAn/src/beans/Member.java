package beans;

public class Member extends User {

	private int fkIdMember;

	private boolean status;

	private User user;
	
	public Member(){}
	
	public Member(
			int fkIdMember,
			boolean status,
			User user
			){
		this.fkIdMember = fkIdMember;
		this.status = status;
		this.user = user;
	}

	public int getFkIdMember() {
		return fkIdMember;
	}

	public void setFkIdMember(int fkIdMember) {
		this.fkIdMember = fkIdMember;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
