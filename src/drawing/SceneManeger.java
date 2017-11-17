package drawing;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import scene.main.MainMenuCanvas;
import scene.main.MainMenuScene;

public class SceneManeger {
//	public static final double WIDGTH = 000;
//	public static final double HEIGHT = 000;
	private static Stage stage;
	//field for constantly use scene
	private static MainMenuScene mainScene = new MainMenuScene();
	
	public static void initialize(Stage PrimaryStage) {
		stage = PrimaryStage;
		stage.show();
	}
	
	//go to scene
	public static void gotoScene(Scene scene) {
		stage.setScene(scene);
	}
	
	//skip to canvas
	public static void gotoCanvasScene(Canvas canvas) {
		StackPane root = new StackPane(canvas);
		Scene scene = new Scene(root);
		root.getChildren().add(canvas);	
		canvas.requestFocus();
		stage.setScene(scene);
	}

	public static MainMenuScene getMainScene() {
		return mainScene;
	}
	
	
}
