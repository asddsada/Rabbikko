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
	private DungeonScene scene;
	//constructor
	//listener
	//loop -> update draw call RenderableHolder
	
	public DungeonCanvas(DungeonScene scene) {
		super(SceneManeger.WIDGTH, SceneManeger.HEIGHT);
		this.scene = scene;
		gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, SceneManeger.WIDGTH, SceneManeger.HEIGHT);
		
		InputUtility.bindMouseOnListeners(this);
	}
	
	//public static 
	
	public void canvasUpdate() {
		gc.setFill(Color.BLACK);
		//if(InputUtility.isPrevPressed()&& mousex in &&mouseyin) scene.toDialog(1); <- how to open menu dialog
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity.isVisible() && !entity.isDestroyed()) {
				entity.draw(gc);
			}
		}
	}
}
