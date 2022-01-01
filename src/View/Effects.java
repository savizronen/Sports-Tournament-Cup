package View;

import Model.Game;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Effects {
	
	public static DropShadow getDropShadow(Color color) {
		DropShadow tmpDropShadow = new DropShadow();
		
		tmpDropShadow.setRadius(5.0);
		tmpDropShadow.setOffsetX(3.0);
		tmpDropShadow.setOffsetY(3.0);
		tmpDropShadow.setColor(color);
		
		return tmpDropShadow;
	}
	
//---------------------------------------------------------------------------------
	
	// sets effect to round winner textField
	public static void setRoundWinnerEffect(int i, int aScore, int bScore, Game game) {
		if (aScore > bScore)
			game.getPlayerA().getPlayerGameArr().get(i).setEffect(getDropShadow(Color.RED));
		if (aScore < bScore)
			game.getPlayerB().getPlayerGameArr().get(i).setEffect(getDropShadow(Color.RED));
	}

//-------------------------------------------------------------------------------------------
	
	public static Label initLabel(Label label, int fontSize, Color color) {
		label.setEffect(Effects.getDropShadow(color));
		label.setFont(new Font("Cambria",fontSize));
		label.setAlignment(Pos.BASELINE_CENTER);
		
		return label;
	}

	public static void initText(RadioButton sport, int fontSize, Color color) {
		sport.setTextFill(color);
		sport.setFont(new Font("Cambria",fontSize));
	}
	
//---------------------------------------------------------------------------------
	
}
