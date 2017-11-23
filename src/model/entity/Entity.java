package model.entity;

import com.sun.corba.se.spi.ior.Writeable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import model.gameObject;
import utility.Pair;

public abstract class Entity extends gameObject {
	public static final int FRONT = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public static final int BACK = 4;

	protected static double w;
	protected static double h;
	protected Image img;
	protected int direction;
	private int walkTick;
	private int maxWalkTick;

	public Entity(double x,double y,Image img,int row,int column, int direction) {
		super(x,y,0);
		this.w = 32;
		this.h = 32;		
		this.direction = direction;
		this.walkTick=2;
		this.maxWalkTick=3;
		this.img = new WritableImage(img.getPixelReader(), (int) w*3*column*walkTick, (int) h*3*row*direction);
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(img, (int) pos.x, (int) pos.y);
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
	
	public abstract void update();

}
