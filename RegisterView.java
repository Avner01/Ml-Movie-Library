package Main;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RegisterView extends ModalerDialog {
	

	private TextField tfUsername;
	private TextField tfPassword;

	public RegisterView() {
		super();
		VBox vbox = new VBox();
		Label label = new Label("Register");
		tfUsername = new TextField(); 
		 tfPassword = new TextField(); 
//		TextField tfTelephone = new TextField(); 
//		TextField tfEmail = new TextField(); 
		
		Label lblUserN = new Label("User Name ");
//		Label lblEmail = new Label("Email ");
//		Label lblTelephone = new Label("Telephone ");
		Label lblPassword = new Label("Password ");
		HBox username = new HBox(lblUserN,tfUsername);
		HBox password = new HBox(lblPassword,tfPassword);
//		HBox email = new HBox(lblEmail,tfEmail);
//		HBox telephone = new HBox(lblTelephone,tfTelephone);

		vbox.getChildren().addAll(label,username,password);
		getDialogPane().setContent(vbox);

	}
	public TextField getTfUsername() {
		return tfUsername;
	}

	public TextField getTfPassword() {
		return tfPassword;
	}

}
