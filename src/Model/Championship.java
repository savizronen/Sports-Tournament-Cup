package Model;

import java.util.ArrayList;

import View.GUIMethods;
import View.SportToggles;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Championship {

	private Group championshipGames;
	private RadioButton sportType;
	private Model model;

	private Button close = new Button("Close");
	private Button reset = new Button("Reset");

//---------------------------------------------------------------------------------

	public Championship(Model model) {
		this.model = model;
	}
	
	// used when pressing start button
	public void startChampionship() {
		this.sportType = (RadioButton) SportToggles.getRadiogroup().getSelectedToggle();
		this.championshipGames = championshipGames();	
	}

//---------------------------------------------------------------------------------
	
	// creates championship grid
	private Group championshipGames() {
		GridPane grid = new GridPane();
		HBox bottomGrid = new HBox(reset, close);
		
		close.setPrefWidth(59);
		bottomGrid.setSpacing(17);
		
		grid.add(tournamentPart("quarters", model.getParticipants(), model.getSemiFinals()), 1, 2);
		grid.add(tournamentPart("semis", model.getSemiFinals(), model.getFinals()), 2, 2);
		grid.add(tournamentPart("finals", model.getFinals(), model.getWinner()), 3, 2);		
		grid.add(bottomGrid, 3, 3);
				
		Group linesGrid = GUIMethods.createLines(grid);
		GUIMethods.setNodeInPlace(bottomGrid, 217, 114);
		GUIMethods.setNodeInPlace(linesGrid, 35, 20);
	
		return linesGrid;
	}

	// creates games by gamePart and sets playGame buttons grid to place
	private GridPane tournamentPart(String gamePart, ArrayList<Participant> participants,
														ArrayList<Participant> nextStageList) {
		GridPane grid = new GridPane();
		int numOfGames = 0;
		if (gamePart.equals("quarters"))
			numOfGames = GUIMethods.setGridLocation(4, grid, 85, 35, 40, 80);
		if (gamePart.equals("semis"))
			numOfGames = GUIMethods.setGridLocation(2, grid, 130, 60, 40, 190);
		if (gamePart.equals("finals"))
			numOfGames = GUIMethods.setGridLocation(1, grid, 235, 150, 40, 40);
		
		for (int i = 0; i < numOfGames; i++) {
			Game game = gameBySport(sportType, participants.get(i*2), participants.get(i*2+1), nextStageList.get(i));

			grid.add(GUIMethods.buttonGameGrid(game, nextStageList.get(i)), 0,i);			
		}
		return grid;
	}

	// creates correct game object
	private Game gameBySport(RadioButton sportType, Participant playerA, Participant playerB, Participant gameWinner) {
		if(sportType.getText().equalsIgnoreCase("Tennis"))
			return new GameTennis(playerA,playerB,gameWinner);
		if(sportType.getText().equalsIgnoreCase("BasketBall"))
			return new GameBasktetBall(playerA,playerB,gameWinner);
		if(sportType.getText().equalsIgnoreCase("Soccer"))
			return new GameSoccer(playerA,playerB,gameWinner);
		return null;

	}
	
//---------------------------------------------------------------------------------
	
	public Group getChampionshipGames() {
		return championshipGames;
	}
	
	public Button getClose() {
		return close;
	}
	
	public Button getReset() {
		return reset;
	}

}
