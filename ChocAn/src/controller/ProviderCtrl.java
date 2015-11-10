package controller;

import beans.Provider;
import dao.ProviderDAO;

public class ProviderCtrl {
	
	public int iOne(
		int fkIdProvider
			){
		
		ProviderDAO providerDAO = new ProviderDAO();
		Provider provider = new Provider();
		
		provider.setFkIdProvider(fkIdProvider);
		
		return providerDAO.iOne(provider);
	}
}