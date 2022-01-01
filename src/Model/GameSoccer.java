package Model;

import java.util.ArrayList;

import View.AlertBox;
import View.Effects;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class GameSoccer extends Game implements Playable {

	// Penalties array
	private ArrayList<CheckBox> playerApenalties = new ArrayList<>();
	private ArrayList<CheckBox> playerBpenalties = new ArrayList<>();

	private GridPane penaltiesGrid;
	private boolean penaltiesOccurred = false;
	private boolean tieOccurred = false;

//---------------------------------------------------------------------------------

	public GameSoccer(Participant playerA, Participant playerB, Participant gameWinner) {
		super(playerA, playerB, gameWinner);

		this.gameName = new Label("Soccer");
		this.numOfRounds = numOfRounds();
		
		this.playerA.initGameArray(numOfRounds);
		this.playerB.initGameArray(numOfRounds);

		this.playerA.getPlayerGameArr().get(2).setVisible(false);
		this.playerB.getPlayerGameArr().get(2).setVisible(false);
	
		this.penaltiesGrid = new GridPane();
		this.penaltiesGrid.setVisible(false);
		this.penaltiesGrid.setHgap(23);
		this.penaltiesGrid.setVgap(15);
		
		fillGameGrid(playersScoresGrid, playerA, playerB);

		calculateButton_setOnAction(this, gameWinner);
		close_playGame_setOnActions(this, gameWinner);
	}

	@Override
	public int numOfRounds() {
		int numOfRounds = 0;
		if (gameName.getText().equals("Soccer"))
			numOfRounds = 3;
		if (gameName.getText().equals("Penalties"))
			numOfRounds = 5;
		return numOfRounds;
	}

//---------------------------------------------------------------------------------
// over-time results calculation 

	@Override
	public Participant calculateWinner() {
		if(!tieOccurred)
			numOfRounds = 2;
		else
			numOfRounds = 3;
		
		for (int i = 0; i < numOfRounds; i++) {
			int roundAScore = Integer.parseInt(playerA.getPlayerGameArr().get(i).getText().toString().replaceAll(" ", ""));
			int roundBScore = Integer.parseInt(playerB.getPlayerGameArr().get(i).getText().toString().replaceAll(" ", ""));

			Effects.setRoundWinnerEffect(i, roundAScore, roundBScore, this);

			playerA.setScore(roundAScore + playerA.getScore());
			playerB.setScore(roundBScore + playerB.getScore());
		}
		return pickWinnerAndFinalScore(this, playerA.getScore(), playerB.getScore());
	}
	
	private Participant penaltiesWinner() {
		int aScore = 0;
		int bScore = 0;
		for (int i = 0; i < numOfRounds(); i++) {
			if (playerApenalties.get(i).isSelected())
				aScore++;
			if (playerBpenalties.get(i).isSelected())
				bScore++;
		}
		return pickWinnerAndFinalScore(this, aScore, bScore);
	}
	
//---------------------------------------------------------------------------------

	// creates penalties and tie grid
	private void initPenalties() {
		gameName.setText("Penalties");
		penaltiesGrid.addColumn(0, Effects.initLabel(new Label(getPlayerA().getFullName()), 25, Color.GREEN),
									Effects.initLabel(new Label(getPlayerB().getFullName()), 25, Color.PURPLE));
		for (int i = 0; i < numOfRounds(); i++) {
			playerApenalties.add(new CheckBox());
			playerBpenalties.add(new CheckBox());
			
			penaltiesGrid.add(playerApenalties.get(i), i + 1, 0);
			penaltiesGrid.add(playerBpenalties.get(i), i + 1, 1);
		}
		playersScoresGrid.add(penaltiesGrid, 0, 2, 6, 6);
	}

//---------------------------------------------------------------------------------

	@Override
	public void calculateButton_setOnAction(Game game, Participant nextStageParticipant) {
		calculate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					
					Participant winner = null;
					if (!gameName.getText().equals("Penalties"))
						winner = calculateWinner();
					else
						winner = penaltiesWinner();
					
					game.endOfMatch(winner, nextStageParticipant);
					penaltiesGrid.setDisable(true);
				} catch (NumberFormatException e) {
					AlertBox.display("Error", "Please Enter Score (Numbers)");
				} catch (NullPointerException e) { // occurs when calculateWinner() is a tie
					AlertBox.display("Over-Time", "Its A Tie!");
					playerA.setScore(0);
					playerB.setScore(0);
					if (!tieOccurred) { // allows only 1 tie in match
						gameName.setText("Over-Time");
						playerA.getPlayerGameArr().get(2).setVisible(true);
						playerB.getPlayerGameArr().get(2).setVisible(true);
						tieOccurred = true;
					} else if (!penaltiesOccurred) {
						initPenalties(); // if soccer tie goes to penalties
						penaltiesGrid.setVisible(true);
						penaltiesOccurred = true; 
					}
				}
			}
		});
	}
	
//---------------------------------------------------------------------------------

}
