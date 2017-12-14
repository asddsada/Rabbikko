package view.dungeon;

import java.util.ConcurrentModificationException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sharedObj.IRenderable;
import sharedObj.RenderableHolder;
import utility.Constant;
import utility.InputUtility;
import utility.ResourceLoader;

public class DungeonCanvas extends Canvas {
	// constructor
	// listener
	// loop -> update draw call RenderableHolder
	private GraphicsContext gc;
	private DungeonScene scene;

	public DungeonCanvas(DungeonScene scene) {
		super(Constant.SCENE_WIDTH, Constant.SCENE_HEIGHT);
		this.scene = scene;
		gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, Constant.SCENE_WIDTH, Constant.SCENE_HEIGHT);

		InputUtility.bindMouseOnListeners(this);
	}

	public void canvasUpdate() throws ConcurrentModificationException {
		if (!ResourceLoader.isLoadFinish()) {
			gc.setFill(Color.WHITE);
			gc.fillText("NOWLOADING",
					Constant.SCENE_WIDTH / 2
							- ResourceLoader.fontLoader.computeStringWidth("NOWLOADING", gc.getFont()) / 2,
					Constant.SCENE_HEIGHT / 2
							- ResourceLoader.fontLoader.getFontMetrics(gc.getFont()).getLineHeight() / 2);
		} else {
			for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
				if (entity.isVisible()) {
					entity.draw(gc);
				}
			}
		}
	}
}
