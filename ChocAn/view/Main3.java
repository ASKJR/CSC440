package view;

import java.util.Scanner;
import util.Utility;

public class Main3 {

	public static void main(String[] args) {
		//Menu menu = new Menu();	
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();
		while(!Utility.validateDate(str)) {
			System.out.print("Invalid! Re-enter: ");
			str = in.nextLine();
		}
		in.close();
		System.out.println("Validated!");
		//menu.startSystem();
	}

}
