package view;

import java.util.Scanner;

import beans.Manager;
import beans.User;

public class ManagerMenu {
	//************************ manager
	public void startMenu(User manager) {
		String in = "";
		Scanner sc = new Scanner(System.in);
		boolean notValid = true;
		Manager m = (Manager) manager;
		
		System.out.println("\n\t Manager: " + manager.getFstName());
		System.out.print("\n\t Do you want to visualize the weekly report? (Y/N): ");
		while(notValid) {
			in = sc.nextLine();
			if(!in.equals("") && (in.equalsIgnoreCase("Y") || in.equalsIgnoreCase("N"))) {
				notValid = false;
			} else System.out.print("\t Invalid option. Re-enter: ");
		}
		sc.close();
		
		if(in.equalsIgnoreCase("Y"))
			m.requestWeeklyReportsFile();
		else
			System.out.println("\n\t Good bye!");
		System.exit(0);	
	}
}
