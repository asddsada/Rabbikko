package Model;

import javafx.scene.canvas.GraphicsContext;
import sharedObj.IRenderable;
import view.SceneManeger;

public class DungeonField implements IRenderable{

	@Override
	public int getZ() {
		return -9999;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.clearRect(0, 0, SceneManeger.WIDGTH, SceneManeger.HEIGHT);
	}

	@Override
	public boolean isDestroyed() {
		return false;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

}
