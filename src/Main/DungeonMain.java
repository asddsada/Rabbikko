package Main;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import logic.GameLogic;
import utility.InputUtility;
import view.SceneManeger;
import view.dungeon.DungeonCanvas;

public class DungeonMain {
	private static DungeonCanvas canvas;
	private static GameLogic logic;

	public DungeonMain() {
		logic = new GameLogic();
		canvas = (DungeonCanvas) SceneManeger.dungeonScene.getCanvas();
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
