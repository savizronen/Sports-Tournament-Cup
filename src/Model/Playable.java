package Model;

import View.AlertBox;
import View.Effects;
import View.GUIMethods;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public interface Playable {
		
	int numOfRounds();
	
//---------------------------------------------------------------------------------
// Results calculation 
	
	Participant calculateWinner();
	
	// sets final scores GUI and returns game winner
	default Participant pickWinnerAndFinalScore(Game game, int aScore, int bScore) throws NullPointerException {
		
		game.playerA.getScoreLabel().setText(String.valueOf(aScore));
		game.playerB.getScoreLabel().setText(String.valueOf(bScore));
		
		if (aScore > bScore)
			return game.playerA;
		if (aScore < bScore)
			return game.playerB;
		else // if tie occurs
			throw new NullPointerException();
	}

//---------------------------------------------------------------------------------
// Game grid creation
	
	default Group createGameGrid(Game game) {
		Group group = new Group();

		Label title = Effects.initLabel(game.gameName, 40, Color.DARKBLUE);
		title.setTranslateX(178);
		title.setTranslateY(50);

		game.playersScoresGrid.getChildren().clear();
		game.playersScoresGrid.addColumn(0, Effects.initLabel(new Label(game.playerA.getFullName()), 25, Color.GREEN),
											Effects.initLabel(new Label(game.playerB.getFullName()), 25, Color.PURPLE));
		fillGameGrid(game.playersScoresGrid, game.playerA, game.playerB);

		HBox hbox = new HBox(game.calculate, new Text("   "), game.close);

		group.getChildren().addAll(title, game.playersScoresGrid, game.winnerMsg, hbox);

		group.getChildren().get(2).setTranslateX(160);
		group.getChildren().get(2).setTranslateY(330);
		group.getChildren().get(3).setTranslateX(178);
		group.getChildren().get(3).setTranslateY(400);

		GUIMethods.setGridLocation(0, game.playersScoresGrid, 130, 70, 20, 20);

		return group;
	}

	// add textFields to game grid
	default void fillGameGrid(GridPane grid, Participant playerA, Participant playerB) {
		for (int i = 0; i < numOfRounds(); i++) {
			grid.add(playerA.getPlayerGameArr().get(i), i + 1, 0);
			grid.add(playerB.getPlayerGameArr().get(i), i + 1, 1);
		}
		grid.add(playerA.getScoreLabel(), numOfRounds() + 1, 0);
		grid.add(playerB.getScoreLabel(), numOfRounds() + 1, 1);
	}
	
//---------------------------------------------------------------------------------
// game calculate button
	
	default void calculateButton_setOnAction(Game game , Participant nextStageParticipant) {
		game.calculate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					
					Participant winner = calculateWinner();
					game.endOfMatch(winner, nextStageParticipant);

				} catch (NumberFormatException e) {
					AlertBox.display("Error", "Please Enter Score (Numbers)");
				} catch (IllegalArgumentException e) { // occurs when tennis win difference smaller than 3
					AlertBox.display("Error", "3+ Wins Gain Needed!");
					game.gameName.setText("Over-Time");
				} catch (NullPointerException e) { // occurs when Game.calculateWinner is a tie
					AlertBox.display("Over-Time", "Its A Tie!");
					game.playerA.setScore(0);
					game.playerB.setScore(0);
				} 
			}
		});
	}
	
//---------------------------------------------------------------------------------
// game playGame and close buttons	
	
	default void close_playGame_setOnActions(Game game, Participant nextStageParticipant) {

		game.close.setOnAction(e -> game.gameWindow.close());
		
		game.playGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					if (game.playerA.getFullName().equals("") || game.playerB.getFullName().equals(""))
						throw new IllegalArgumentException(); // if game participants not chosen yet
					if(!game.gameStarted) {
						Scene scene = new Scene(createGameGrid(game), 500, 500, Color.ALICEBLUE);
						game.gameWindow.setScene(scene);
						game.gameStarted = true;
					}
					game.gameWindow.show();
				} catch (IllegalArgumentException e) {
					AlertBox.display("Error", "Player Not Assigned");
				}
			}
		});
	}
	
}
