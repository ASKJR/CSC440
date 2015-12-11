package view;

import java.sql.Timestamp;
import java.util.Date;

public class Main3 {

	public static void main(String[] args) {
		Menu menu = new Menu();		
		Date date = new Date();
		System.out.println(new Timestamp(date.getTime()));
		menu.startSystem();
	}

}
