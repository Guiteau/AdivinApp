package dad.javafx.adivinapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application {

	private Label introduceNumeroLabel;
	private TextField numeroText;
	private Button comprobarButton;
	private int numRandom, intentos;

	public AdivinApp() {

		super();
		setNumRandom(numRandom);

		System.out.println(numRandom);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		introduceNumeroLabel = new Label("Introduce un número del 1 al 100");

		numeroText = new TextField("0");
		numeroText.setPrefWidth(100);
		numeroText.setAlignment(Pos.CENTER);

		comprobarButton = new Button("Comprobar");

		comprobarButton.setOnAction(e -> lanzaAlert(e));

		HBox buttonHbox = new HBox(5, numeroText);
		buttonHbox.setAlignment(Pos.CENTER);

		VBox root = new VBox(5, introduceNumeroLabel, buttonHbox, comprobarButton);
		root.setAlignment(Pos.CENTER);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("AdivinApp");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void lanzaAlert(ActionEvent event) {

		Alert alert;
		ButtonType aceptarButton = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);

		intentos++;

		if (numeroText.getText().length() == 0 || !numeroText.getText().matches("[0-9]+") || Integer.parseInt(numeroText.getText()) > 100) {

			alert = new Alert(AlertType.ERROR, "El número introducido no es válido.", aceptarButton);
			alert.setTitle("AdivinApp");
			alert.setHeaderText("Error");

			alert.showAndWait();

		} else if (getNumRandom() == Integer.parseInt(numeroText.getText())) {

			alert = new Alert(AlertType.INFORMATION, "Sólo has necesitado " + intentos + " " + palabraIntentos(intentos)
					+ "\n\n" + "Vuelve a jugar y hazlo mejor.", aceptarButton);
			alert.setTitle("AdivinApp");
			alert.setHeaderText("¡Has ganado!");

			alert.showAndWait();

			setNumRandom(numRandom);
			setIntentos(0);
			numeroText.setText("0");
			System.out.println(numRandom);

		} else if (getNumRandom() != Integer.parseInt(numeroText.getText())) {

			alert = new Alert(AlertType.WARNING, "El número a adivinar es " + setMayorMenor() + " que "
					+ Integer.parseInt(numeroText.getText()) + "\n\n" + "Vuelve a intentarlo.", aceptarButton);
			alert.setTitle("AdivinApp");
			alert.setHeaderText("¡Has fallado!");

			alert.showAndWait();

		}

	}

	private String setMayorMenor() {

		String mayorMenor = "mayor";

		if (getNumRandom() < Integer.parseInt(numeroText.getText()))

			mayorMenor = "menor";

		return mayorMenor;

	}

	private String palabraIntentos(int intentos) {

		String palabraIntentos = "intento";

		if (intentos > 1)
			palabraIntentos = "intentos";

		return palabraIntentos;

	}

	public int getIntentos() {
		return intentos;
	}

	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}

	private int getNumRandom() {
		return numRandom;
	}

	private void setNumRandom(int numRandom) {

		int num1 = 0, num2 = 100;

		this.numRandom = (int) Math.floor(Math.random() * (num1 - (num2 + 1)) + (num2));
		
		while(this.numRandom < 0  || this.numRandom > 100) {
			
			this.numRandom = (int) Math.floor(Math.random() * (num1 - (num2 + 1)) + (num2));
			
		}
		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
