package beans;

public class Login {

	public static final int UNSUCCESSFUL_INSERT = -1;
	public static final int SUCCESSFUL_INSERT = 0;
	
	private int fkIdUser;
	private String password;
	private User user;
	
	public Login(){}
	
	public Login(int fkIdUser, String password, User user){
		this.fkIdUser = fkIdUser;
		this.password = password;
		this.user = user;
	}
	
	public int getFkIdUser() {
		return fkIdUser;
	}
	public void setFkIdUser(int fkIdUser) {
		this.fkIdUser = fkIdUser;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
