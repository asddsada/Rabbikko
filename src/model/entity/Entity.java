package model.entity;

import com.sun.corba.se.spi.ior.Writeable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import model.gameObject;
import utility.Pair;

public abstract class Entity extends gameObject {
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public static final int UP = 4;

	protected double w;
	protected double h;
	protected Image img;
	protected int direction;
	private int walkTick;
	private int maxWalkTick;

	public Entity(Image cutImg, int direction) {
		super();
		this.w = 32;
		this.h = 32;
		this.img = cutImg;
		this.direction = direction;
		this.walkTick=2;
		this.maxWalkTick=3;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		new WritableImage(img.getPixelReader(), (int) w*walkTick, (int) h*direction);
	}

	public boolean isInArea(Pair pos) {
		return Math.abs(pos.x - pos.x) <= (w/2-5) && Math.abs(pos.y - pos.y) <= h;
	}
	
	public void resetWalkTick() {
		this.walkTick=2;
	}
	
	public void addWalkTick() {
		if(walkTick==maxWalkTick) walkTick=1;
		else walkTick++;
	}

}
