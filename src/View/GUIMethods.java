package View;

import Model.Game;
import Model.Model;
import Model.Participant;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class GUIMethods {
	
	// creates playGame button node
	public static GridPane buttonGameGrid(Game game, Participant winnerSlot) {
		
		GridPane grid = new GridPane();
		grid.add(game.getPlayGame(), 0, 0);
		grid.add(winnerSlot.getTextField() , 1, 0);
		grid.setHgap(20);

		winnerSlot.getTextField().setEditable(false);

		return grid;
	}
	
//-------------------------------------------------------------------------------------------
// node aligment methods
		
	public static int setGridLocation(int numOfGames, GridPane grid, int translateY, int translateX, int Hgap, int Vgap) {
		grid.setTranslateY(translateY);
		grid.setTranslateX(translateX);
		grid.setHgap(Hgap);
		grid.setVgap(Vgap);
		
		return numOfGames;
	}
	
	public static void setNodeInPlace(Node node, int translateX, int translateY) {
		node.setTranslateX(translateX);
		node.setTranslateY(translateY);
		BorderPane.setMargin(node, new Insets(2, 2, 2, 2));
	}
	
//-------------------------------------------------------------------------------------------
// lines creatinon methods
	
	public static Group createLines(GridPane grid) {
		Pane linesArr = new Pane();//     numOfParts |  growth  sY1  sY2  wY  sX  eX  wEndX
		     					   // s - start	   \	    \	 \	  \   \    /   /    /
		// quarters 			   // e	- end	   /		 \	  \	   \   \   \   \    \
		createLinesHelper(linesArr, Model.MAX_PARTICIPANTS/2, 106, 67, 125, 97, 3, 70, 150);
		// semis 
		createLinesHelper(linesArr, Model.MAX_PARTICIPANTS/4, 212, 93, 201,145, 245, 314, 400);
		// finals
		createLinesHelper(linesArr, 1, 0, 145, 360, 249, 448, 620, 690);

		Group group = new Group(linesArr,grid);

		return group;
	}

	private static void createLinesHelper(Pane linesArr, int numOfParts, int growth, int line1StartY, int line2StartY, int winnerLineStartY, int startX, int endX, int winnerEndX) {
		for (int i = 0; i < numOfParts; i++) { 
			
			Line upperLine = new Line(startX, line1StartY, endX, line1StartY);
			Line lowerLine = new Line(startX, line2StartY, endX, line2StartY);
			Line connectLine = new Line(endX, line1StartY, endX, line2StartY);
			Line winnerLine = new Line(endX, winnerLineStartY, winnerEndX, winnerLineStartY);

			upperLine.setStrokeWidth(2);
			lowerLine.setStrokeWidth(2);
			connectLine.setStrokeWidth(2);
			winnerLine.setStrokeWidth(2);
			
			linesArr.getChildren().addAll(upperLine, lowerLine, connectLine, winnerLine);

			line1StartY += growth;
			line2StartY += growth;
			winnerLineStartY += growth;
		}
	}

//-------------------------------------------------------------------------------------------

}
