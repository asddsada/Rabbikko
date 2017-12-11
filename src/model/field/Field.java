package model.field;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import sharedObj.IRenderable;
import utility.ResourceLoader;
import utility.Constant;
import utility.Pair;
import view.SceneManeger;

public abstract class Field implements IRenderable {
	protected boolean visible;
	protected WritableImage bg;
	protected Pair topLeft;
	protected int z;
	protected double width;
	protected double height;

	public Field(Image bgImage, double width, double height, Pair topLeft) {
		this.visible = true;
		this.width = width;
		this.height = height;
		this.bg = new WritableImage(bgImage.getPixelReader(), (int) width, (int) height);
		this.topLeft = topLeft;
	}

	@Override
	public int getZ() {
		return z;
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
		gc.drawImage(bg, topLeft.x, topLeft.y,Constant.WIDTH,Constant.HEIGHT-ResourceLoader.navigBar.getHeight());
	}

	protected boolean isInBorderX(double x) {
		if (this.getTopRight().x < x && this.getTopRight().x + this.getWidth() > x)
			return true;
		return false;
	}

	protected boolean isInBorderY(double y) {
		if (this.getTopRight().y < y && this.getTopRight().y + this.getHeight() > y)
			return true;
		return false;
	}
	
	public Pair getTopRight() {
		return topLeft;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}
}
