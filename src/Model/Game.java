package Model;

import View.Effects;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game {

	protected int numOfRounds;
	protected boolean gameStarted = false;
	
	protected Participant playerA;
	protected Participant playerB;
	
	// Scene components
	protected Label gameName;
	protected GridPane playersScoresGrid;
	protected Label winnerMsg;

	protected Button calculate = new Button("Calculate");
	protected Button close = new Button("Close");
	protected Button playGame = new Button("Play Game");
	
	protected Stage gameWindow;
	
//---------------------------------------------------------------------------------

	public Game(Participant playerA, Participant playerB, Participant gameWinner) {
		this.playerA = playerA;
		this.playerB = playerB;

		this.playersScoresGrid = new GridPane();
		this.winnerMsg = new Label("");

		Effects.initLabel(winnerMsg, 40, Color.GOLD);
		
		this.gameWindow = new Stage();
		this.gameWindow.setResizable(false);
	}	
	
// ---------------------------------------------------------------------------------
	
	// disables all editing after game ended
	public void endOfMatch(Participant winner, Participant nextStageParticipant) {
		gameWindow.setTitle("Match Over");
		calculate.setVisible(false);
		setGameArrayEditable(false);
		winnerMsg.setText(winner.getFullName() + " WON!");

		nextStageParticipant.setFullName(winner.getFullName());
	}
	
	private void setGameArrayEditable(boolean editable) {
		for (int i = 0; i < numOfRounds; i++) { // used after game ended to disable scores edit
			playerA.getPlayerGameArr().get(i).setEditable(editable);
			playerB.getPlayerGameArr().get(i).setEditable(editable);
		}
	}
	
// ---------------------------------------------------------------------------------

	public Participant getPlayerA() {
		return playerA;
	}

	public Participant getPlayerB() {
		return playerB;
	}

	public Button getPlayGame() {
		return playGame;
	}

}
