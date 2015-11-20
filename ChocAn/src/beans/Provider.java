package beans;

import java.util.ArrayList;

public class Provider extends User {
	
	private int fkIdProvider;
	private ArrayList<Service> serviceList;

	public Provider(){}
	
	public Provider(
			int fkIdProvider,
			ArrayList<Service> serviceList
			){
		this.serviceList = serviceList;
		this.fkIdProvider = fkIdProvider;
	}
	
	public double retrieveWeeklyFees() {
		return 0;
	}

	public ArrayList<Service> requestServiceDirectory() {
		return null;
	}

	public int logIn() {
		return 0;
	}

	public int getFkIdProvider() {
		return fkIdProvider;
	}

	public void setFkIdProvider(int fkIdProvider) {
		this.fkIdProvider = fkIdProvider;
	}

	public ArrayList<Service> getServiceList() {
		return serviceList;
	}

	public void setServiceList(ArrayList<Service> serviceList) {
		this.serviceList = serviceList;
	}
	
	
	
}
