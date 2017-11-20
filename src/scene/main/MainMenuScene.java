package scene.main;

import drawing.SceneManeger;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;


public class MainMenuScene extends Scene {
	private StackPane root;
	private MainMenuCanvas canvas;
	
	public MainMenuScene(){
		super(new StackPane());
		root = (StackPane) this.getRoot();
		
		canvas = new MainMenuCanvas();
		
		root.getChildren().add(canvas);		
		canvas.requestFocus();
	}

	public MainMenuCanvas getCanvas() {
		return canvas;
	}
}
//this class as template code for more complex scene, 
//able to skip and call the canvas only.
