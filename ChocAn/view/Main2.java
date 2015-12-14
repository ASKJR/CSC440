package view;

import java.util.Scanner;

public class Main2 {
	public static void main(String[] args) {
		
		Menu menu = new Menu();
		String answer = "Y";
		Scanner scanner = new Scanner(System.in);
		while(answer.equals("Y")){
			menu.startSystem();
			System.out.println("\n\n\t Continue in the system? (Y/N)");
			answer = scanner.nextLine();
		}
		scanner.close();

	}

}
