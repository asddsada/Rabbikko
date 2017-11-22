package Main;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import logic.GameLogic;
import model.GameModel;
import utility.InputUtility;
import view.SceneManeger;
import view.dungeon.DungeonCanvas;

public class DungeonMain {
	private static DungeonCanvas canvas;
	private static GameLogic logic;
	private static GameModel model;

	public DungeonMain() {
		canvas = (DungeonCanvas) SceneManeger.dungeonScene.getCanvas();
		model = new GameModel();
		logic = new GameLogic();
	}
	
	private static AnimationTimer animation = new AnimationTimer() {
		public void handle(long now) {
			canvas.canvasUpdate();
			logic.logicUpdate();
		}
	};


	public static void start() {
		animation.start();
	}

	public static void stop() {
		animation.stop();
	}

	
}
