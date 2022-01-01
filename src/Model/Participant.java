package Model;

import java.util.ArrayList;

import View.Effects;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class Participant {
	
	private TextField fullName;
	private Label scoreLabel;
	private int score;
	private ArrayList<TextField> playerGameArr;

//---------------------------------------------------------------------------------

	public Participant() {
		this.fullName = new TextField();
		this.fullName.setPrefWidth(120);
		this.scoreLabel = new Label();
		this.score = 0;
		this.playerGameArr = new ArrayList<>();
		Effects.initLabel(getScoreLabel(),25, Color.DARKBLUE);

	}
	
	public void initGameArray(int numOfRounds) {
		for (int i = 0; i < numOfRounds; i++) {
			playerGameArr.add(new TextField());
			playerGameArr.get(i).setPrefWidth(40);
			playerGameArr.get(i).setEffect(Effects.getDropShadow(Color.DARKBLUE));
		}
	}
	
//---------------------------------------------------------------------------------

	public String getFullName() {
		return fullName.getText();
	}
	
	public void setFullName(String fullName) {
		this.fullName.setText(fullName);
	}
	
	public TextField getTextField() {
		return fullName;
	}

//---------------------------------------------------------------------------------

	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
//---------------------------------------------------------------------------------

	public Label getScoreLabel() {
		return scoreLabel;
	}

	public ArrayList<TextField> getPlayerGameArr() {
		return playerGameArr;
	}

}
