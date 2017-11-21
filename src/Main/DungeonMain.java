package Main;

import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import logic.GameLogic;
import view.SceneManeger;
import view.dungeon.DungeonCanvas;

public class DungeonMain {
	private DungeonCanvas canvas;
	private GameLogic logic;

	// start game method
	// stop method
	public void start() {
		canvas = (DungeonCanvas) SceneManeger.dungeonScene.getCanvas();
		logic = new GameLogic();

		animation.start();
	}

	AnimationTimer animation=new AnimationTimer(){public void handle(long now){
		canvas.canvasUpdate();
		logic.logicUpdate();
	}};

}
