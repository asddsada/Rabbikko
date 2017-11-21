package view.dungeon;

import input.InputUtility;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import view.SceneManeger;


public class DungeonScene extends Scene{
	//dungeon canvas
	//navigation bar canvas
	
	private StackPane root;
	private Canvas canvas;
	private DialogPane dialog;
	
	public DungeonScene(){
		super(new StackPane());
		root = (StackPane) this.getRoot();
		root.setAlignment(Pos.CENTER);
		
		canvas = new DungeonCanvas(root);
		
		root.getChildren().add(canvas);		
		
		dialog = new DialogPane(this);
		root.getChildren().add(dialog);
		dialog.opening();
		dialog.requestFocus();
		
		InputUtility.bindListeners(this);
	}
	
	public void toDungeon() {
		canvas.requestFocus();
		root.getChildren().remove(dialog);
	}

	public Canvas getCanvas() {
		return canvas;
	}
}
