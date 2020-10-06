package dad.javafx.adivinapp;

import java.util.ResourceBundle;

public class Main {

	public static void main(String[] args) {
		System.out.println(ResourceBundle.getBundle("properties/messages").getString("message"));
		AdivinApp.main(args);
	}

}
