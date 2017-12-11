package Main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.ForceManeger;
import logic.GameLogic;
import model.field.Dungeon;
import model.monster.MonsterDen;
import utility.ResourceLoader;
import view.SceneManeger;

public class Main extends Application {
	public static MediaPlayer m;
	public static boolean isGameRunning;

	@Override
	public void start(Stage primaryStage) {
		try {
			m = new MediaPlayer(ResourceLoader.titleBgm);
			m.setCycleCount(MediaPlayer.INDEFINITE);
			m.play();
			isGameRunning = true;
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
		try {
			DungeonMain.stop();
			MonsterDen.stop();
			m.stop();
		} catch (NullPointerException e) {
		} finally {
			isGameRunning = false;
			Platform.exit();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
