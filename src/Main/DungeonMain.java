package Main;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import logic.ForceManeger;
import logic.GameLogic;
import sharedObj.RenderableHolder;
import utility.InputUtility;
import view.SceneManeger;
import view.dungeon.DungeonCanvas;

public class DungeonMain {
	private static DungeonCanvas canvas;
	private static GameLogic logic;
	private static ForceManeger forceManager;

	public DungeonMain() {
		
		logic = new GameLogic();
		canvas = (DungeonCanvas) SceneManeger.dungeonScene.getCanvas();
		ForceManeger.initilized();
	}
	
	private static AnimationTimer animation = new AnimationTimer() {
		public void handle(long now) {
			try {
				canvas.canvasUpdate();
				logic.logicUpdate();
				RenderableHolder.getInstance().update();
			} catch (IllegalArgumentException e) {
				System.out.println("cannot update");
			}
		}
	};

	public static void start() {
		Main.isGameRunning = true;
		animation.start();
		ForceManeger.startForceRule();
	}

	public static void stop() {
		Main.isGameRunning = false;
		animation.stop();
		ForceManeger.pauseForceRule();
	}

	public static DungeonCanvas getCanvas() {
		return canvas;
	}

	public static GameLogic getLogic() {
		return logic;
	}

	public static ForceManeger getForceManager() {
		return forceManager;
	}
}
