package Main;

import java.util.ConcurrentModificationException;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import logic.GameLogic;
import sharedObj.RenderableHolder;
import utility.ForceUtility;
import utility.InputUtility;
import utility.ResourceLoader;
import view.SceneManeger;
import view.dungeon.DungeonCanvas;

public class DungeonMain {
	private static DungeonCanvas canvas;
	private static GameLogic logic;
	private static ForceUtility forceManager;

	public DungeonMain() {
		logic = new GameLogic();
		canvas = (DungeonCanvas) SceneManeger.dungeonScene.getCanvas();
		ForceUtility.initilized();
	}

	private static AnimationTimer animation = new AnimationTimer() {
		public void handle(long now) {
			if (ResourceLoader.isLoadFinish()) {
				try {
					canvas.canvasUpdate();
					logic.logicUpdate();
					RenderableHolder.getInstance().update();
				} catch (IllegalArgumentException | ConcurrentModificationException e) {
					System.out.println("cannot update");
				}
			}else {
				canvas.canvasUpdate();
			}
		}
	};

	public static void start() {
		Main.isGameRunning = true;
		animation.start();
		ForceUtility.startForceRule();
	}

	public static void stop() {
		Main.isGameRunning = false;
		animation.stop();
		ForceUtility.pauseForceRule();
	}

	public static DungeonCanvas getCanvas() {
		return canvas;
	}

	public static GameLogic getLogic() {
		return logic;
	}

	public static ForceUtility getForceManager() {
		return forceManager;
	}
}
