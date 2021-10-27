package Main;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class AddMovieView extends ModalerDialog {
	private TextField tftitle;
	private TextField tfdirector;
	private TextField tfyear;
	private ComboBox<String> cbgenre;
	private TextArea txdescription;
	private FileChooser fcImage;
	
	public String getSelectedgenre() {
		return selectedgenre;
	}





	public void setSelectedgenre(String selectedgenre) {
		this.selectedgenre = selectedgenre;
	}





	private String selectedgenre;

	public TextField getTfImage() {
		return tfImage;
	}





	public void setTfImage(TextField tfImage) {
		this.tfImage = tfImage;
	}





	private TextField tfImage;
	
	
	
	
	public TextField getTftitle() {
		return tftitle;
	}





	public void setTftitle(TextField tftitle) {
		this.tftitle = tftitle;
	}





	public TextField getTfdirector() {
		return tfdirector;
	}





	public void setTfdirector(TextField tfdirector) {
		this.tfdirector = tfdirector;
	}





	public TextField getTfyear() {
		return tfyear;
	}





	public void setTfyear(TextField tfyear) {
		this.tfyear = tfyear;
	}





	public ComboBox getCbgenre() {
		return cbgenre;
	}





	public void setCbgenre(ComboBox cbgenre) {
		this.cbgenre = cbgenre;
	}





	public TextArea getTxdescription() {
		return txdescription;
	}





	public void setTxdescription(TextArea txdescription) {
		this.txdescription = txdescription;
	}





	public AddMovieView() {
		
		super();
	Label lbltitle = new Label("Title");
	tftitle = new TextField();
	HBox hbtitle = new HBox(lbltitle, tftitle);
	
	Label lbldirector = new Label("Director");
	 tfdirector = new TextField();
	HBox hbdirector = new HBox(lbldirector, tfdirector);
	
	Label lblyear = new Label("Year");
	tfyear = new TextField();
	HBox hbyear = new HBox(lblyear, tfyear);
	
	Label lblgenre = new Label("Genre");
	cbgenre = new ComboBox();
	
	ArrayList<String> genre = DataBase.loadAllGenre();
	cbgenre.setItems(FXCollections.observableArrayList(genre));
	HBox hbgenre = new HBox(lblgenre, cbgenre);
	cbgenre.getSelectionModel().selectedItemProperty().addListener((options,oldvalue,newvalue)->{
		selectedgenre = newvalue;
		
	});
	
	Label lbldescription = new Label("Description");
	txdescription = new TextArea();
	HBox hbdescription = new HBox(lbldescription,txdescription);
	
	Label lblPicture = new Label("Picture");
	fcImage = new FileChooser();
	fcImage.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Png Files","*.png"),new FileChooser.ExtensionFilter("Jpeg Files","*.Jpg"));
	tfImage = new TextField();
	Button image = new Button("Select File");
	
	image.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
		File selectedFile = fcImage.showOpenDialog(getOwner());
		if(!(selectedFile == null)) {
			tfImage.setText(selectedFile.getPath());

		}
				}
	});
	
	HBox hbImage = new HBox(lblPicture, tfImage,image);
	VBox vbox = new VBox();
	vbox.getChildren().addAll(hbtitle,hbdirector,hbgenre,hbyear,hbdescription,hbImage);
	getDialogPane().setContent(vbox);
	}

}
