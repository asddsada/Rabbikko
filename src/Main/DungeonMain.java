package Main;

import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import logic.GameLogic;
import view.SceneManeger;
import view.dungeon.DungeonCanvas;

public class DungeonMain {
	private static DungeonCanvas canvas;
	private static GameLogic logic;

	public DungeonMain() {
		canvas = (DungeonCanvas) SceneManeger.dungeonScene.getCanvas();
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
