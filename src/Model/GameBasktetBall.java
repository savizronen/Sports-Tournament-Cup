package Model;

import View.Effects;
import javafx.scene.control.Label;

public class GameBasktetBall extends Game implements Playable {

	public GameBasktetBall(Participant playerA, Participant playerB, Participant gameWinner) {
		super(playerA, playerB, gameWinner);
		
		this.gameName = new Label("BasketBall");
		this.numOfRounds = numOfRounds();

		this.playerA.initGameArray(numOfRounds);
		this.playerB.initGameArray(numOfRounds);

		fillGameGrid(playersScoresGrid, playerA, playerB);
		
		calculateButton_setOnAction(this, gameWinner);
		close_playGame_setOnActions(this, gameWinner);
	}
	
	@Override
	public int numOfRounds() {
		return 4;
	}	
	
//---------------------------------------------------------------------------------

	public Participant calculateWinner() {
		for (int i = 0; i < numOfRounds(); i++) {
			int roundAScore = Integer.parseInt(playerA.getPlayerGameArr().get(i).getText().toString().replaceAll(" ", ""));
			int roundBScore = Integer.parseInt(playerB.getPlayerGameArr().get(i).getText().toString().replaceAll(" ", ""));

			Effects.setRoundWinnerEffect(i, roundAScore, roundBScore, this);

			playerA.setScore(roundAScore + playerA.getScore());
			playerB.setScore(roundBScore + playerB.getScore());
		}
		return pickWinnerAndFinalScore(this, playerA.getScore(), playerB.getScore());
	}
	
}
