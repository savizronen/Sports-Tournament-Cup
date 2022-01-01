
package View;

import Model.Championship;
import Model.Model;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View {
	
	private BorderPane mainLayout;
	private Stage mainWindow = new Stage();
	private MainMenu mainMenu;
	private Championship  championship;
	
//---------------------------------------------------------------------------------

	public View(Model model) {
		mainMenu = new MainMenu(model);
		mainLayout = mainMenu.getMainPane();
		championship=new Championship(model);

		Scene scene = new Scene(mainLayout, 1007, 557, Color.ALICEBLUE);
		mainWindow.setScene(scene);
			
		mainWindow.setResizable(false);
		mainWindow.show();
	}

//---------------------------------------------------------------------------------

	public Stage getMainWindow() {
		return mainWindow;
	}
	
	public Championship getChampionship() {
		return championship;
	}

	public void setChampionship(Championship championship) {
		this.championship = championship;
	}
	
	public MainMenu getMainMenu() {
		return mainMenu;
	}

}
