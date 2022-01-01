package View;

import java.util.ArrayList;

import Model.Model;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ParticipantsPane {
	
	private ArrayList<TextField> particiTFarr;
	private GridPane textfieldsGrid;
	
//---------------------------------------------------------------------------------

	public ParticipantsPane(Model model) {
		particiTFarr = new ArrayList<TextField>(Model.MAX_PARTICIPANTS);	
		textfieldsGrid = new GridPane();
		initParticipantsTF(model, particiTFarr);
		particiTextFieldsGrid();
	}

//---------------------------------------------------------------------------------

	private void initParticipantsTF(Model model, ArrayList<TextField> particiTF) { 
		for (int i = 0; i < Model.MAX_PARTICIPANTS; i++) {
			particiTF.add(model.getParticipants().get(i).getTextField());
		}
	}
	
	private void particiTextFieldsGrid() { 
		for (int i = 0; i < Model.MAX_PARTICIPANTS; i++) {
			TextField tmpTF = particiTFarr.get(i);
			tmpTF.setPrefWidth(120);
			tmpTF.setEditable(false);
			tmpTF.setMouseTransparent(true);
			tmpTF.setFocusTraversable(false);

			textfieldsGrid.add(tmpTF, 0, i);
			textfieldsGrid.setVgap(28);
		}
	}
	
//---------------------------------------------------------------------------------
	
	public TextField getTextField(int index) {
		return particiTFarr.get(index);
	}

	public GridPane getTextfieldsGrid() {
		return textfieldsGrid;
	}

}
