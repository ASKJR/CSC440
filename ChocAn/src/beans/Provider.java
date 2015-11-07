package beans;

import java.util.ArrayList;

public class Provider extends User {
	
	private int fkIdProvider;
	
	private ArrayList<Service> serviceList;

	private User user;

	private Service service;

	public Provider(){}
	
	public Provider(
			int fkIdProvider,
			ArrayList<Service> serviceList,
			User user,
			Service service
			){
		this.serviceList = serviceList;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
	
	
}
