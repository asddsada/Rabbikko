package model.field;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import sharedObj.IRenderable;
import sharedObj.RenderableHolder;
import utility.Pair;
import view.SceneManeger;

public abstract class Field implements IRenderable {
	private boolean visible;
	private WritableImage bg;
	private Pair topRight;
	private double width;
	private double height;

	public Field(Image bgImage, double width, double height, Pair topRight) {
		this.visible = true;
		this.width = width;
		this.height = height;
		this.bg = new WritableImage(bgImage.getPixelReader(), (int) width, (int) height);
		this.topRight = topRight;
	}

	@Override
	public int getZ() {
		return -9999;
	}

	@Override
	public boolean isDestroyed() {
		return visible == false;
	}

	@Override
	public boolean isVisible() {
		return visible == true;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(bg, topRight.x, topRight.y,SceneManeger.WIDGTH,SceneManeger.HEIGHT-100);
	}

	public abstract boolean isInBorder(Pair p);

}
