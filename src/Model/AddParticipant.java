package Model;

import View.AlertBox;
import View.Effects;
import View.View;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class AddParticipant {
	
	private Label nameLabel = new Label("Participant Name:");
	private Button addPartici = new Button("Add Participant");
	private Button start = new Button("Start Championship");
	
	private TextField particiName = new TextField();
	private GridPane addParticiGrid = new GridPane();
	
	public AddParticipant() { // creates main menu center grid
		Effects.initLabel(nameLabel, 20, Color.BISQUE);
		
		addParticiGrid.add(nameLabel, 0, 0);
		addParticiGrid.add(particiName, 1, 0);
		addParticiGrid.add(addPartici, 0, 1);
		addParticiGrid.add(start, 1, 1);
		
		addParticiGrid.setHgap(20);
		addParticiGrid.setVgap(20);
	}

//---------------------------------------------------------------------------------

	// adds to participants array
	public void validateAndAdd(View view, Model model) {
		TextField particiName=view.getMainMenu().getAddGrid().getParticiName();
		try {
		 	validName(particiName.getText());
			if (!particiName.getText().trim().isEmpty()) {
				view.getMainMenu().getParticiPane().getTextField(Model.indexOfPersonName).setText(particiName.getText());
				model.getParticipants().get(Model.indexOfPersonName).setFullName(particiName.getText());
				Model.indexOfPersonName++;				
			}
		} catch (IndexOutOfBoundsException e) {
			AlertBox.display("Error", "Tournament List is FULL");
		} catch (IllegalArgumentException e) {
			AlertBox.display("Error", "Name Must Be Only Letters!");
		} 
		particiName.setText("");
	}
	
	private void validName(String name) throws IllegalArgumentException {
		for (int i = 0; i < name.length(); i++) {
			if (!((Character.isAlphabetic(name.charAt(i)) || name.charAt(i) == ' '))) 
				throw new IllegalArgumentException();
		}
	}
	
//---------------------------------------------------------------------------------
	
	public GridPane getAddParticiGrid() {
		return addParticiGrid;
	}

	public Button getAddPartici() {
		return addPartici;
	}

	public Button getStart() {
		return start;
	}

	public TextField getParticiName() {
		return particiName;
	}

}
