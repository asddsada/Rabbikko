package view.title;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import view.SceneManeger;


public class TitleScene extends Scene {
	private StackPane root;
	private TitleCanvas canvas;
	
	public TitleScene(){
		super(new StackPane());
		root = (StackPane) this.getRoot();
		
		canvas = new TitleCanvas();
		
		root.getChildren().add(canvas);		
		canvas.requestFocus();
	}

	public TitleCanvas getCanvas() {
		return canvas;
	}
}
//this class as template code for more complex scene, 
//able to skip and call the canvas only.
