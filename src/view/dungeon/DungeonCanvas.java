package view.dungeon;

import input.InputUtility;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import sharedObj.IRenderable;
import sharedObj.RenderableHolder;
import view.SceneManeger;

public class DungeonCanvas extends Canvas{
	private GraphicsContext gc;
	private StackPane root;
	//constructor
	//listener
	//loop -> update draw call RenderableHolder
	
	public DungeonCanvas(StackPane root) {
		super(SceneManeger.WIDGTH, SceneManeger.HEIGHT);
		this.root = root;
		gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, SceneManeger.WIDGTH, SceneManeger.HEIGHT);
	}
	
	//public static 
	
	public void canvasUpdate() {
		gc.setFill(Color.BLACK);
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity.isVisible() && !entity.isDestroyed()) {
				entity.draw(gc);
			}
		}
	}
}
