package Main;

import drawing.SceneManeger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			SceneManeger.initialize(primaryStage);
			SceneManeger.gotoScene(SceneManeger.getMainScene());
			
			primaryStage.setTitle("RabbikoRPG");
			primaryStage.centerOnScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
