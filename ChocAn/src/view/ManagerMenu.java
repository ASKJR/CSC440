package view;

import beans.User;

public class ManagerMenu {
	//************************ manager
	public void startMenu(User manager) {
		System.out.println("\n\t Manager: " + manager.getFstName());
		System.out.print("\n\t Do you want to visualize the weekly report? (Y/N): ");
		// shows the weekly report		
	}
}
