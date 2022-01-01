package Controller;

import Model.AddParticipant;
import Model.Model;
import View.AlertBox;
import View.ParticipantsPane;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

public class Controller {
	
	private Model model;
	private View view;
	
//---------------------------------------------------------------------------------

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		buttons_setOnAction();
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void setView(View view) {
		this.view = view;
	}
	
	public View getView() {
		return view;
	}

//---------------------------------------------------------------------------------
// changes background method
	
	private EventHandler<ActionEvent> handleBackgroundChange(int index){
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Background background2 = new Background(new BackgroundImage(view.getMainMenu().getGamePhotos().get(index),
						BackgroundRepeat.NO_REPEAT,	BackgroundRepeat.NO_REPEAT, 
						BackgroundPosition.CENTER,	view.getMainMenu().getBackgroundSize()));
				view.getMainMenu().getMainPane().setBackground(background2);
			}
		};
	}
	
//---------------------------------------------------------------------------------

	// checks if all participants places occupied
	private boolean checkEmptySlots() {
		try {
			if (Model.indexOfPersonName != 8) {
				throw new IllegalArgumentException();
			}
		} catch (IllegalArgumentException e) {
			AlertBox.display("Error", "Please Fill in all Participants Spots!");
			return true;
		}
		return false;
	}
	
//---------------------------------------------------------------------------------

	private void buttons_setOnAction() {
		
		
		// championship back button
		view.getChampionship().getClose().setOnAction(e -> view.getMainWindow().close());
		
		view.getChampionship().getReset().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				view.getMainWindow().close();
				model = new Model();
				Model.indexOfPersonName = 0;
				view = new View(model);
				buttons_setOnAction();
			}		
		});
		
		// changes background by sportToggle
		
		view.getMainMenu().getChooseSport().getTennis().setOnAction(handleBackgroundChange(0));
		
		view.getMainMenu().getChooseSport().getBasketBall().setOnAction(handleBackgroundChange(1));

		view.getMainMenu().getChooseSport().getSoccer().setOnAction(handleBackgroundChange(2));
		
		//---------------------------------------------------------------------------------

		AddParticipant addPartici = view.getMainMenu().getAddGrid();
		
		// addParticipant button 
		addPartici.getAddPartici().setOnAction(e -> addPartici.validateAndAdd(view,model));
		
		// ENTER key pressed 		
		addPartici.getParticiName().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode().equals(KeyCode.ENTER)) {
					addPartici.validateAndAdd(view,model);
				}
			}
		});

		// start button
		addPartici.getStart().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(!checkEmptySlots()) {
					view.getChampionship().startChampionship();
				    view.getMainMenu().getMainPane().setCenter(view.getChampionship().getChampionshipGames());
				    view.getMainMenu().getMainPane().getRight().setVisible(false);
				    view.getMainMenu().getMainPane().getBottom().setVisible(false);
				}
			}		
		});
		//---------------------------------------------------------------------------------
		// main menu reset and remove buttons 
		
		ParticipantsPane particiPane = view.getMainMenu().getParticiPane();

		// remove button
		view.getMainMenu().getRemove().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					TextField tmpParticiTF = particiPane.getTextField(Model.indexOfPersonName-1);
					if (!tmpParticiTF.getText().isEmpty()) {
						tmpParticiTF.setText("");
						Model.indexOfPersonName--;
					}
				} catch (IndexOutOfBoundsException e) {
					AlertBox.display("Error", "No Participants Listed");
				}
			}
			
		});
		
		// reset button		
		view.getMainMenu().getReset().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					if(particiPane.getTextField(0).getText().isEmpty())
						throw new IllegalArgumentException();
					for (int i = 0; i < Model.MAX_PARTICIPANTS; i++) {
						TextField tmpParticiTF = particiPane.getTextField(i);
						if(!tmpParticiTF.getText().isEmpty())
							tmpParticiTF.clear();
					}
					Model.indexOfPersonName = 0;
				} catch (IllegalArgumentException e) {
					AlertBox.display("Error", "No Participants Listed");
				}			
			}	
		});
		
	}
	
//---------------------------------------------------------------------------------

}
