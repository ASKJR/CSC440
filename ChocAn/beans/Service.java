package beans;

import java.util.ArrayList;

public class Service {

	private int idService;

	private String name;

	private double fee;

	private ArrayList<Provider> providerList;
	
	public Service(){}
	
	public Service(
			int idService,
			String name,
			double fee,
			ArrayList<Provider> providerList
			){
		this.idService = idService;
		this.name = name;
		this.fee = fee;
		this.providerList = providerList;
	}

	public int getIdService() {
		return idService;
	}

	public void setIdService(int idService) {
		this.idService = idService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public ArrayList<Provider> getProviderList() {
		return providerList;
	}

	public void setProviderList(ArrayList<Provider> providerList) {
		this.providerList = providerList;
	}
	
	

}
