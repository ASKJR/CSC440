package view;

import beans.ChocAn;
import beans.Provider;
import controller.ProviderCtrl;
import dao.ProviderDAO;

public class Main {

	public static void main(String[] args) {
		
		ChocAn chocAn = new ChocAn();
		Provider provider = new Provider();
		ProviderCtrl providerCtrl = new ProviderCtrl();

		chocAn.createEFT();
	}
}
