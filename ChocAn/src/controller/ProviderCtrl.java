package controller;

import beans.Provider;
import dao.ProviderDAO;

public class ProviderCtrl {
	
	public int iOne
		(
			int fkIdProvider,
			int status,
			String stAddr,
			String addrComp,
			String city,
			String state,
			String zipCode,
			String lstName,
			String fstName,
			String cellPhone,
			String homePhone,
			String workPhone,
			String email
		)
	{
		
		ProviderDAO providerDAO = new ProviderDAO();
		Provider provider = new Provider();
		
		provider.setFkIdProvider(fkIdProvider);
		provider.setIdUser(fkIdProvider);
		provider.setStatus(status);
		provider.setStAddr(stAddr);
		provider.setAddrComp(addrComp);
		provider.setCity(city);
		provider.setState(state);
		provider.setZipCode(zipCode);
		provider.setLstName(lstName);
		provider.setFstName(fstName);
		provider.setCellPhone(cellPhone);
		provider.setHomePhone(homePhone);
		provider.setWorkPhone(workPhone);
		provider.setEmail(email);

		return providerDAO.iOne(provider);
	}
	
	public int uOne
		(
			int fkIdProvider,
			int status,
			String stAddr,
			String addrComp,
			String city,
			String state,
			String zipCode,
			String lstName,
			String fstName,
			String cellPhone,
			String homePhone,
			String workPhone,
			String email
		)
	{
			
		ProviderDAO providerDAO = new ProviderDAO();
		Provider provider = new Provider();
		
		provider.setFkIdProvider(fkIdProvider);
		provider.setIdUser(fkIdProvider);
		provider.setStatus(status);
		provider.setStAddr(stAddr);
		provider.setAddrComp(addrComp);
		provider.setCity(city);
		provider.setState(state);
		provider.setZipCode(zipCode);
		provider.setLstName(lstName);
		provider.setFstName(fstName);
		provider.setCellPhone(cellPhone);
		provider.setHomePhone(homePhone);
		provider.setWorkPhone(workPhone);
		provider.setEmail(email);
		
		return providerDAO.uOne(provider);
	}
	
	public int alterStatus
		(
			int fkIdProvider, 
			int status
		)
	{
		Provider provider = new Provider();
		ProviderDAO providerDAO = new ProviderDAO();
		
		provider.setStatus(status);
		provider.setFkIdProvider(fkIdProvider);

		provider.setFkIdProvider(fkIdProvider);
		provider.setStatus(status);
		
		return providerDAO.alterStatus(provider);
	}
	
    public Provider sOne
	    (
	    	int fkIdProvider
	    )
    {
		Provider provider = new Provider();
		ProviderDAO providerDAO = new ProviderDAO();
		
		provider.setFkIdProvider(fkIdProvider);
		
		return providerDAO.sOne(provider);
    }
	
}