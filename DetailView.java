package Main;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class DetailView extends Dialog<ButtonType> {
	private BorderPane rootPane;
	private Movie movie;
	private Label lblTitle = new Label();
	private Label lbldirector = new Label();
	private Text txtdescription = new Text();
	private Label lblgenre = new Label();
	private ImageView imageview = new ImageView();
	private Media media;
	private Button button;

	public  DetailView() {
		super();
		ButtonType ok = ButtonType.OK;
		Button addToPList = new Button("Add To P List");
		this.getDialogPane().getButtonTypes().addAll(ok);

		rootPane = new BorderPane();
		Label topLabel = new Label(" Movie Detail ");
		rootPane.setTop(topLabel);
		GridPane gpCenter = new GridPane();
		rootPane.setCenter(gpCenter);
		gpCenter.add(lbldirector, 0,1);
		gpCenter.add(lblgenre, 0, 2);
		gpCenter.add(imageview, 0, 3);
		gpCenter.add(txtdescription, 0, 4);
		Button btnAdd = new Button ( "Add to Personal List");
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {


			@Override
			public void handle(ActionEvent e) {
				DataBase.addToPList(movie, Main.getUser());
				Alert alert = new Alert( AlertType.CONFIRMATION);
				alert.setContentText("Movie was added to list");
				alert.setHeaderText(null);
				alert.show();
			}
		});
		
		gpCenter.add(btnAdd, 0, 5);
		
		getDialogPane().setContent(rootPane);

	}
	public Pane getRoot() {
		return rootPane;
		
	}
	public void setMovie(Movie nextMovie) { 
		movie = nextMovie;
		lblTitle.setText(movie.getTitle());
		lblTitle.setFont(Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR,20));
		rootPane.setTop(lblTitle);
		
		lbldirector.setText(movie.getDirector());
		lbldirector.setFont(Font.font("verdana",FontWeight.BOLD,FontPosture.REGULAR,12));
		rootPane.setBottom(lbldirector);

		lblgenre.setText(movie.getGenre());
		lblgenre.setFont(Font.font("verdana",FontWeight.BOLD,FontPosture.REGULAR,14));
		
		txtdescription.setText(movie.getDescription());
		BorderPane.setAlignment(txtdescription, Pos.CENTER );
		txtdescription.setFont(Font.font("verdana",FontWeight.BOLD,FontPosture.REGULAR,11));
		rootPane.setRight(txtdescription);

		
		VBox vbox = new VBox(20);
		vbox.setPadding(new Insets(10));
		
		
		InputStream stream = null;
		try {
			stream = new FileInputStream(movie.getImagePath());
			Image image = new Image(stream); 
			imageview.setImage(image);
			imageview.setFitHeight(100); 
		    imageview.setFitWidth(200); 
		    imageview.setPreserveRatio(true);
			

		} catch (FileNotFoundException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Image could not be loaded: " + movie.getImagePath());
			alert.showAndWait();
		} 
	

		
	    
		
	}
	
}
