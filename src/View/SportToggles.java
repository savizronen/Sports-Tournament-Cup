package View;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SportToggles {
	
	private static final ToggleGroup radioGroup = new ToggleGroup();
	
	private RadioButton tennis = new RadioButton("Tennis");
	private RadioButton basketBall = new RadioButton("BasketBall");
	private RadioButton soccer = new RadioButton("Soccer");
	private VBox box;
	
//---------------------------------------------------------------------------------

	public SportToggles() {
		tennis.setSelected(true);
		tennis.setToggleGroup(radioGroup);
		basketBall.setToggleGroup(radioGroup);
		soccer.setToggleGroup(radioGroup);
		
		Effects.initText(tennis, 20, Color.ANTIQUEWHITE);
		Effects.initText(basketBall, 20, Color.ANTIQUEWHITE);
		Effects.initText(soccer, 20, Color.ANTIQUEWHITE);
		
		box = new VBox();
		box.setSpacing(10);
		box.getChildren().addAll(tennis,basketBall,soccer);
	}


//---------------------------------------------------------------------------------

	public VBox getBox() {
		return box;
	}

	public static ToggleGroup getRadiogroup() {
		return radioGroup;
	}
	
	public RadioButton getTennis() {
		return tennis;
	}

	public RadioButton getBasketBall() {
		return basketBall;
	}

	public RadioButton getSoccer() {
		return soccer;
	}
	
}
