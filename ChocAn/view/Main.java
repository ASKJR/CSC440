package view;

import beans.ChocAn;
import beans.Provider;
import controller.ProviderCtrl;

public class Main {

	public static void main(String[] args) {
		
		ChocAn chocAn = new ChocAn();
		Provider provider = new Provider();
		ProviderCtrl providerCtrl = new ProviderCtrl();

		provider = providerCtrl.sOne(16);
		if(chocAn.sendListOfServices(provider) == 0){
			System.out.println("Report Sent Successfully");
		}
	}
}
