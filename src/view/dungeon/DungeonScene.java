package view.dungeon;

import Main.DungeonMain;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import utility.InputUtility;
import view.SceneManeger;


public class DungeonScene extends Scene{	
	private StackPane root;
	private Canvas canvas;
	private DialogPane dialog;
	
	public DungeonScene(){
		super(new StackPane());
		root = (StackPane) this.getRoot();
		root.setAlignment(Pos.CENTER);
		
		canvas = new DungeonCanvas(this);
		
		root.getChildren().add(canvas);		
		
		dialog = new DialogPane(this);
		root.getChildren().add(dialog);
		toDialog(0);
		
		InputUtility.bindListeners(this);
	}
	
	public void toDungeon() {
//		dialog.getChildren().clear();
		dialog.setVisible(false);
		DungeonMain.start();
		canvas.requestFocus();
	}
	
	public void toDialog(int c) {
		dialog.getChildren().clear();
		dialog.setVisible(true);
		dialog.requestFocus();
		DungeonMain.stop();
		switch(c) {
		case 0:			
			dialog.opening();
			break;
		case 1:
			dialog.inventory();
			break;
		case 2:
			dialog.shop();
			break;
		case 3:
			dialog.setting();
			break;
		}
	}

	public Canvas getCanvas() {
		return canvas;
	}
}
