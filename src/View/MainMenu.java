package View;

import java.util.ArrayList;

import Model.AddParticipant;
import Model.Model;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class MainMenu {
	
	private Label labelChampionShip = new Label("Championship");
	private BorderPane mainPane;

	private Button reset = new Button("Reset");
	private Button remove = new Button("Remove");
	
	private GridPane particiGrid;
	private AddParticipant addGrid;
	private SportToggles chooseSport;
	private ParticipantsPane particiPane;

	private BackgroundSize backgroundSize;
	private ArrayList<Image> gamePhotos;
	
//---------------------------------------------------------------------------------
	
	public MainMenu(Model model) {
		mainPane = new BorderPane();
		initBackGround();
		
		// TOP
		Effects.initLabel(labelChampionShip, 30, Color.BISQUE);
		mainPane.setTop(labelChampionShip);
		GUIMethods.setNodeInPlace(labelChampionShip,400,50);
		
		// LEFT
		particiPane = new ParticipantsPane(model);
		particiGrid = particiPane.getTextfieldsGrid();
		GUIMethods.setNodeInPlace(particiGrid,40,80);
		mainPane.setLeft(particiGrid);
		
		// CENTER
		addGrid = new AddParticipant();
		GridPane AddGridPane = addGrid.getAddParticiGrid();
		GUIMethods.setNodeInPlace(AddGridPane,220,250);
		mainPane.setCenter(AddGridPane);
		
		// RIGHT
		chooseSport = new SportToggles();
		GUIMethods.setNodeInPlace(chooseSport.getBox(),-80,240);
		mainPane.setRight(chooseSport.getBox());
		
		// BOTTOM
		HBox bottomGrid = new HBox(reset,remove);
		bottomGrid.setSpacing(17);
		GUIMethods.setNodeInPlace(bottomGrid,800,-22);
		mainPane.setBottom(bottomGrid);	
		
	}

	private void initBackGround() {
		this.gamePhotos = new ArrayList<Image>();
		this.gamePhotos.add(new Image("file:GamePhotos/tennis.jpg"));
		this.gamePhotos.add(new Image("file:GamePhotos/basketball.jpg"));
		this.gamePhotos.add(new Image("file:GamePhotos/soccer.jpg"));
		this.backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);

		Background background = new Background(new BackgroundImage(gamePhotos.get(0), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, this.backgroundSize));
		mainPane.setBackground(background);

	}
	
//---------------------------------------------------------------------------------
// menu components
	
	public BorderPane getMainPane() {
		return mainPane;
	}

	public ParticipantsPane getParticiPane() {
		return particiPane;
	}
	
	public AddParticipant getAddGrid() {
		return addGrid;
	}
	
	public SportToggles getChooseSport() {
		return chooseSport;
	}
	
//---------------------------------------------------------------------------------
// buttons
	
	public Button getReset() {
		return reset;
	}

	public Button getRemove() {
		return remove;
	}
	
//---------------------------------------------------------------------------------
// Background
	
	public ArrayList<Image> getGamePhotos() {
		return gamePhotos;
	}

	public BackgroundSize getBackgroundSize() {
		return backgroundSize;
	}

}
