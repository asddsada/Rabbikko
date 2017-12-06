package view.dungeon;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.entity.Hero;
import sharedObj.IRenderable;
import sharedObj.RenderableHolder;
import utility.InputUtility;
import view.SceneManeger;

public class DungeonCanvas extends Canvas {
	// constructor
	// listener
	// loop -> update draw call RenderableHolder
	private GraphicsContext gc;
	private DungeonScene scene;

	public DungeonCanvas(DungeonScene scene) {
		super(SceneManeger.WIDGTH, SceneManeger.HEIGHT);
		this.scene = scene;
		gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, SceneManeger.WIDGTH, SceneManeger.HEIGHT);

		InputUtility.bindMouseOnListeners(this);
	}

	public void canvasUpdate() {
		// if(InputUtility.isPrevPressed()&& mousex in &&mouseyin) scene.toDialog(1); <-
		// how to open menu dialog
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity.isVisible() && !entity.isDestroyed()) {
				entity.draw(gc);
			}
		}
		RenderableHolder.getInstance().sort();
	}
}
