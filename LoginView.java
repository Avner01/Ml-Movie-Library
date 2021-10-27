package Main;

import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

	public class LoginView extends Dialog<ButtonType> {
	
	private TextField tfUsername;
	private TextField tfPassword;

		
	public LoginView() {
		
		ButtonType log = new ButtonType("Login",ButtonData.OK_DONE);
		ButtonType register =  new ButtonType("Register",ButtonData.OTHER);
		this.getDialogPane().getButtonTypes().addAll(log, register);
		
		VBox vbox = new VBox();
		Label label = new Label("Login");
		
		
		
		tfUsername = new TextField();
	    tfUsername.setPromptText("Enter Name");

		tfPassword = new TextField();
	    PasswordField pf = new PasswordField();  
	    tfPassword.setPromptText("Enter Password");

	    Label lblUserN = new Label("User Name ");
		Label lblPassword = new Label("Password ");
		lblUserN.setPrefWidth(120);
		lblPassword.setPrefWidth(120);

		
		HBox username = new HBox(lblUserN,tfUsername);
		HBox password = new HBox(lblPassword,tfPassword);
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
