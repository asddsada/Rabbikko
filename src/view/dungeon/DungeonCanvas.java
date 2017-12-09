package view.dungeon;

import java.util.ConcurrentModificationException;

import com.sun.javafx.tk.FontLoader;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.entity.Hero;
import model.items.Weapons;
import sharedObj.IRenderable;
import sharedObj.RenderableHolder;
import utility.InputUtility;
import utility.ResourceLoader;
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

	public void canvasUpdate() throws ConcurrentModificationException {
		if (!ResourceLoader.isLoadFinish()) {
			gc.setFill(Color.WHITE);
			gc.fillText("NOWLOADING",
					SceneManeger.WIDGTH / 2
							- ResourceLoader.fontLoader.computeStringWidth("NOWLOADING", gc.getFont()) / 2,
					SceneManeger.HEIGHT / 2
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
