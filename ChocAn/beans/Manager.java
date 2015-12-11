package beans;

public class Manager extends User{
	
	private int fkIdManager;
	
	public Manager(){}
	
	public Manager
		(
			int fkIdManager
		)
	{
		this.fkIdManager = fkIdManager;
	}
	
	public int printWeeklyReports() {
		return 0;
	}

	public int getFkIdManager() {
		return fkIdManager;
	}

	public void setFkIdManager(int fkIdManager) {
		this.fkIdManager = fkIdManager;
	}
	
}
