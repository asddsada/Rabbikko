package view;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.dungeon.DungeonScene;
import view.title.TitleScene;

public class SceneManeger {

	private static Stage stage;
	// field for constantly use scene
	public static TitleScene mainScene = new TitleScene();
	public static DungeonScene dungeonScene = new DungeonScene();

	public static void initialize(Stage PrimaryStage) {
		stage = PrimaryStage;
		stage.setTitle("Rabbikko RPG");
		stage.show();
	}

	// go to scene
	public static void gotoScene(Scene scene) {
		stage.setScene(scene);
	}

	// skip to canvas
	public static void gotoCanvasScene(Canvas canvas) {
		StackPane root = new StackPane(canvas);
		Scene scene = new Scene(root);
		root.getChildren().add(canvas);
		canvas.requestFocus();
		stage.setScene(scene);
	}

}
