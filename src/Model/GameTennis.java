package Model;

import View.Effects;
import javafx.scene.control.Label;

public class GameTennis extends Game implements Playable {
	
	private boolean tieOccurred = false;

	public GameTennis(Participant playerA, Participant playerB, Participant gameWinner) {
		super(playerA, playerB, gameWinner);
		
		this.gameName = new Label("Tennis");
		this.numOfRounds = numOfRounds();

		this.playerA.initGameArray(numOfRounds);
		this.playerB.initGameArray(numOfRounds);
		
		fillGameGrid(playersScoresGrid, playerA, playerB);
		
		visibleOverTimeTextFields(false);
		
		calculateButton_setOnAction(this, gameWinner);
		close_playGame_setOnActions(this, gameWinner);
	}

	@Override
	public int numOfRounds() {
		return 5;
	}

//---------------------------------------------------------------------------------

	private void visibleOverTimeTextFields(boolean state) {  // hides 2 lasts rounds of game
		for (int i = 3; i < numOfRounds() ; i++) {
			playerA.getPlayerGameArr().get(i).setVisible(state);
			playerB.getPlayerGameArr().get(i).setVisible(state);
		}
	}
	
//---------------------------------------------------------------------------------

	@Override
	public Participant calculateWinner() throws IllegalArgumentException {
		int playerAwins = 0;
		int playerBwins = 0;
		
		if(!tieOccurred) // checks only 3 first rounds
			numOfRounds = 3;
		else
			numOfRounds = numOfRounds();
		
		for (int i = 0; i < numOfRounds; i++) {
			int roundAScore = Integer.parseInt(playerA.getPlayerGameArr().get(i).getText().toString().replaceAll(" ", ""));
			int	roundBScore = Integer.parseInt(playerB.getPlayerGameArr().get(i).getText().toString().replaceAll(" ", ""));

			Effects.setRoundWinnerEffect(i, roundAScore, roundBScore, this);

			if (roundAScore > roundBScore)
				playerAwins++;
			if (roundAScore < roundBScore)
				playerBwins++;
		}
		
		playerA.getScoreLabel().setText(String.valueOf(playerAwins));
		playerB.getScoreLabel().setText(String.valueOf(playerBwins));
		
		if(Math.abs(playerAwins - playerBwins) < 3) { // this block allows only legit scores (4-1 , 3-0 , 3-2)
			if(!tieOccurred) {
				visibleOverTimeTextFields(true);
				tieOccurred = true;
				throw new IllegalArgumentException();	
			} else if(playerAwins + playerBwins < 5) {
				throw new IllegalArgumentException();
			}
		}	

		return pickWinnerAndFinalScore(this, playerAwins, playerBwins);
	}
		
}
