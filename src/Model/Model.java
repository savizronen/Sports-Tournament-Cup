package Model;

import java.util.ArrayList;

import View.Effects;
import javafx.scene.paint.Color;

public class Model {
	
	public static final int MAX_PARTICIPANTS = 8;
	public static int indexOfPersonName = 0;

	private ArrayList<Participant> participants;
	private ArrayList<Participant> semiFinals;
	private ArrayList<Participant> finals;
	private ArrayList<Participant> winner;

//---------------------------------------------------------------------------------

	public Model() {
		this.participants = new ArrayList<Participant>(MAX_PARTICIPANTS);
		createParticipants(participants, MAX_PARTICIPANTS, Color.BLACK);
		
		this.semiFinals = new ArrayList<Participant>(MAX_PARTICIPANTS/2);
		createParticipants(semiFinals, MAX_PARTICIPANTS/2, Color.BLUEVIOLET);
		
		this.finals = new ArrayList<Participant>(MAX_PARTICIPANTS/4);
		createParticipants(finals, MAX_PARTICIPANTS/4, Color.DARKGREEN);
		
		this.winner = new ArrayList<Participant>(1);
		createParticipants(winner, 1, Color.RED);

	}

//---------------------------------------------------------------------------------

	private void createParticipants(ArrayList<Participant> particiArr, int numOfPlaces, Color color) {
		for(int i = 0 ; i < numOfPlaces; i++) {
			particiArr.add(new Participant());
			particiArr.get(i).getTextField().setEffect(Effects.getDropShadow(color));
		}
	}
	
//---------------------------------------------------------------------------------
	
	public ArrayList<Participant> getParticipants() {
		return participants;
	}

	public ArrayList<Participant> getSemiFinals() {
		return semiFinals;
	}

	public ArrayList<Participant> getFinals() {
		return finals;
	}

	public ArrayList<Participant> getWinner() {
		return winner;
	}

}
