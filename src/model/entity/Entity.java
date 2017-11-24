package model.entity;

import com.sun.corba.se.spi.ior.Writeable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import model.gameObject;
import utility.Pair;
import view.SceneManeger;

public abstract class Entity extends gameObject {
	public static final int FRONT = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int BACK = 3;

	protected static double w;
	protected static double h;
	protected Image img;
	protected int direction;
	protected int movespeed;
	private double walkTick;
	private int currentWalktick;
	private int maxWalkTick;
	
	public Entity(double x,double y,Image img,int row,int column, int direction,int movespeed) {
		super(x,y,0);
		this.w = 32;
		this.h = 32;
		this.direction = direction;
		this.movespeed = movespeed;
		this.walkTick = 1;
		this.maxWalkTick = 2;
		this.img = new WritableImage(img.getPixelReader(), (int) w * 3 * column, (int) h * 4 * row, (int) w * 3,
				(int) h * 4);
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(new WritableImage(img.getPixelReader(), (int) w * currentWalktick, (int) h * direction, (int) w, (int) h),
				(int) pos.x - w / 2, (int) pos.y, w * 2.5, h * 2.5);
	}

	public boolean isInArea(Pair d) {
		return pos.diffX(d.x) <= (w / 2 - 5) && pos.diffY(d.y) <= h;
	}

	protected void resetWalkTick() {
		this.walkTick = 1;
		this.currentWalktick = 1;
	}

	private void addWalkTick() {
		this.walkTick+=0.4;
		this.currentWalktick=(int) (walkTick%this.maxWalkTick+1);
//		should use thread here
	}

	protected void move(int direction) {
//		should ask if the change will be in the scene before change
		if (this.direction != direction) {
			resetWalkTick();
			this.direction =direction;
		}else {
			addWalkTick();
			this.direction = direction;
		}
		if (direction == FRONT)
			this.pos.y += ((movespeed / 10.0) * SceneManeger.HEIGHT / 150);
		else if (direction == BACK)
			this.pos.y -= ((movespeed / 10.0) * SceneManeger.HEIGHT / 150);
		else if (direction == RIGHT)
			this.pos.x += ((movespeed / 10.0) * SceneManeger.WIDGTH / 200);
		else if (direction == LEFT)
			this.pos.x -= ((movespeed / 10.0) * SceneManeger.WIDGTH / 200);
	}

	public abstract void update();
}
