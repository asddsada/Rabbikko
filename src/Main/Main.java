package Main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.ForceManeger;
import logic.GameLogic;
import view.SceneManeger;

public class Main extends Application {
	public static boolean isGameRunning;
	@Override
	public void start(Stage primaryStage) {
		try {
			isGameRunning=true;
			SceneManeger.initialize(primaryStage);
			SceneManeger.gotoScene(SceneManeger.mainScene);
			
			primaryStage.setTitle("RabbikoRPG");
			primaryStage.centerOnScreen();
			primaryStage.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void stop() throws Exception {
		isGameRunning=false;
		System.out.println("called");
		DungeonMain.stop();
		GameLogic.dungeon.monsterDen.stop();
		Platform.exit();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
