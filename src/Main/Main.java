package Main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.GameLogic;
import view.SceneManeger;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			SceneManeger.initialize(primaryStage);
			SceneManeger.gotoScene(SceneManeger.mainScene);
			
			primaryStage.setTitle("RabbikoRPG");
			primaryStage.centerOnScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void stop() throws Exception {
		DungeonMain.stop();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
