package Main;

import javafx.scene.layout.Pane;

public abstract class View  {
protected Main main;

public View(Main main) {
	super();
	this.main = main;
}
public abstract Pane getRoot(); // abtract method 

	
	

}
