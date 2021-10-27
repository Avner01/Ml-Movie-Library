package Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Optional;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main extends Application {
	private DetailView detailview;
	private Scene scene;
	private final String imageFolder = "Pictures";
	private static int userID;
	private boolean showList = false;

	@Override
	public void start(Stage primaryStage) {
		try {
			DataBase.createUserTable();
			DataBase.createMovieTable();
			DataBase.createGenreTable();
			DataBase.createMovieListTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		menuview = new MainMenueView(this);
		detailview = new DetailView();
		
		DataBase.insertUser("admin", "admin1");	
		LoginView loginview = new LoginView();
		openLoginDialog(loginview, primaryStage);
		
	}
	private void openLoginDialog(LoginView loginview, Stage primaryStage) {
		Optional<ButtonType> result = loginview.showAndWait();
		if (result.get().getButtonData() == ButtonData.OK_DONE) {
			String username = loginview.getTfUsername().getText();
			String password = loginview.getTfPassword().getText();
			userID = DataBase.userExists(username, password);
			if(userID != -1) {
				openMainView(primaryStage);
			}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setContentText("Username or Password incorrect");
				alert.showAndWait();
				openLoginDialog(loginview, primaryStage);
			}
			
		} else {
			RegisterView registerview = new RegisterView();
			openRegisterView(registerview, primaryStage);
		}
	}
	private void openRegisterView(RegisterView registerview, Stage primaryStage) {
		Optional<ButtonType> registerresult = registerview.showAndWait();
		if (registerresult.get() == ButtonType.OK) {
		String username = registerview.getTfUsername().getText();
		if(DataBase.userNameExists(username)) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Username already in use");
			alert.showAndWait();
			openRegisterView(registerview, primaryStage);

		}else {
			String password = registerview.getTfPassword().getText();
			DataBase.insertUser(username, password);
			LoginView loginview = new LoginView();
			openLoginDialog(loginview, primaryStage);

			
		}

		}
	}
	
	private void openMainView(Stage primaryStage) {
	scene = new Scene(createMainView());
	primaryStage.setScene(scene);
	primaryStage.setTitle("Main Menu");  
	primaryStage.show();
	}

	private VBox createMainView() {
		TextField tfsearch = new TextField();
		Label label = new Label(" Main menu  ");
		StackPane root = new StackPane();
		Scene scene = new Scene(root,150,150);
		Label lblsearch = new Label("Search:");
		Button btnsearch = new Button("Find");
		Button btnadd = new Button("Add");
		Button btnShowList = new Button("Show Personal List");
//		Button btndelete = new Button("Delete");
		TableView<Movie> movietable = new TableView<>();

		root.getChildren().add(lblsearch);
		btnadd.setOnAction(new EventHandler<ActionEvent>() {


			@Override
			public void handle(ActionEvent e) {
			AddMovieView addMovie = new AddMovieView();
			openAddMovieView(movietable, addMovie);
			}
		});
		 

		btnsearch.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				movietable.setItems(search(tfsearch.getText()));
		}
	});
		btnShowList.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if(!showList) {
					movietable.setItems(loadList());
					btnShowList.setText("Show All Movies");
				showList = true;
				btnadd.setDisable(true);
				btnsearch.setDisable(true);
//				btndelete.setDisable(true);
				}else {
					movietable.setItems(loadMovies());
					btnShowList.setText("Show Personal List");
					showList = false;
					btnadd.setDisable(false);
					btnsearch.setDisable(false);
//					btndelete.setDisable(false);

				}
		}
	});
		
		// to do create column for the table view
		BorderPane rootPane = new BorderPane();
		rootPane.setTop(label);
		HBox hbsearch = new HBox(lblsearch, tfsearch, btnsearch, btnadd, btnShowList);
		rootPane.setBottom(hbsearch);
		BorderPane.setAlignment(btnsearch, Pos.BOTTOM_RIGHT);
		setupMovieTable(movietable);
		rootPane.setCenter(movietable);
		movietable.setItems(loadMovies());
		VBox vb = new VBox(movietable,rootPane);
		return vb;
		
	}
	
	private void openAddMovieView(TableView<Movie> movietable, AddMovieView addMovie) {
		Optional<ButtonType> result = addMovie.showAndWait();
		if(result.get()== ButtonType.OK) {
		Movie movie = new Movie();
		movie.setTitle(addMovie.getTftitle().getText());
		movie.setDirector(addMovie.getTfdirector().getText());
		movie.setGenre((String)addMovie.getSelectedgenre());
		movie.setDescription(addMovie.getTxdescription().getText());
		try {
			movie.setYear(Integer.parseInt(addMovie.getTfyear().getText()));
		
		String path = addMovie.getTfImage().getText();
		File imageFile = new File(path);
		
			Files.createDirectories(Paths.get(imageFolder)); //create folder if it doesnt exist
			Files.copy(Paths.get(path),Paths.get(imageFolder + "/" + imageFile.getName()));
		
		movie.setImagePath(imageFolder + "/" + imageFile.getName());

			DataBase.insertMovie(movie);
			movietable.setItems(loadMovies());
		} catch (NumberFormatException e1) {
			Alert error = new Alert(AlertType.ERROR);
			error.setContentText("Year not valid");
			error.showAndWait();
			openAddMovieView(movietable, addMovie);
		
		} catch (IOException e1) {
			Alert error = new Alert(AlertType.ERROR);
			error.setContentText("Picture not valid");
			error.showAndWait();
			openAddMovieView(movietable, addMovie);
			
		}
		}
	}

		
	private void setupMovieTable(TableView<Movie> movietable) {
		TableColumn<Movie, String> columnTitle = new TableColumn<>("Title");
		columnTitle.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
		movietable.getColumns().add(columnTitle);

		TableColumn<Movie, Integer> columnYear = new TableColumn<>("Year");
		columnYear.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("year"));
		movietable.getColumns().add(columnYear);

		TableColumn<Movie, String> columnDirector = new TableColumn<>("Director");
		columnDirector.setCellValueFactory(new PropertyValueFactory<Movie, String>("director"));
		movietable.getColumns().add(columnDirector);

		TableColumn<Movie, String> columnGenre = new TableColumn<>("Genre");
		columnGenre.setCellValueFactory(new PropertyValueFactory<Movie, String>("genre"));
		movietable.getColumns().add(columnGenre);

		movietable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			System.out.println("table select");
			if (newSelection != null) {
			detailview.setMovie(newSelection);
			Optional<ButtonType> result = detailview.showAndWait();
			
			}
		});
	}


			
		
	private ObservableList<Movie> search (String filter) {
		ObservableList<Movie> movieList = FXCollections.observableArrayList(DataBase.loadAllMoviesFilterd(filter));
		return movieList;			

	}
	
	
	private ObservableList <Movie> loadList() {
		ObservableList<Movie> movieList = FXCollections.observableArrayList(DataBase.loadMovieList(userID));
		return movieList;			
	}
	private ObservableList<Movie> loadMovies() {
		ObservableList<Movie> movieList = FXCollections.observableArrayList(DataBase.loadAllMovies());

		return movieList;

	}
	 public static int getUser() {
		 return userID;
	 }


	public static void main(String[] args) {
		
		launch(args);
	}
}
