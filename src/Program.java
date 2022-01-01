
import View.View;
import Model.Model;
import Controller.Controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage window) {
		Model theModel = new Model();
		View theView = new View(theModel);
		Controller controller = new Controller(theModel,theView);
	}

}
