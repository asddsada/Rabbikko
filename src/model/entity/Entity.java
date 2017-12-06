package model.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import logic.GameLogic;
import model.GameObject;
import model.attribute.Attribute;
import model.field.Dungeon;
import sharedObj.RenderableHolder;
import utility.InputUtility;
import utility.Pair;
import view.SceneManeger;

public abstract class Entity extends GameObject {
	public static final int FRONT = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int BACK = 3;

	protected static double w;
	protected static double h;
	protected Image img;
	protected int direction;
	protected int movespeed;
	protected int mass;
	protected int race;
	private int counter;
	private int walktick;
	protected boolean isAlive;

	public Entity(double x, double y, Image img, int row, int column, int direction, int movespeed, int mass) {
		super(x, y, 0);
		Entity.w = 32;
		Entity.h = 32;
		this.direction = direction;
		this.movespeed = movespeed;
		this.mass = mass;
		this.isAlive=true;
		this.counter = 0;
		this.img = new WritableImage(img.getPixelReader(), (int) w * 3 * column, (int) h * 4 * row, (int) w * 3,
				(int) h * 4);
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(new WritableImage(img.getPixelReader(), (int) w * walktick, (int) h * direction, (int) w, (int) h),
				pos.x, pos.y, w * 2.5, h * 2.5);
		gc.strokeRect(pos.x, pos.y, getWidth(), getHeight());
		gc.strokeRect(pos.x + getWidth() / 6, pos.y, getWidth() / 1.5, getHeight());
	}

	protected abstract boolean isBlock(double x, double y);

	private void setEntityX(double x) {
		if (GameLogic.dungeon.isInBoarder(this, this.pos.x + x, 0) && !this.isBlock(this.pos.x + x, this.pos.y))
			this.pos.x += x;
	}

	private void setEntityY(double y) {
		if (GameLogic.dungeon.isInBoarder(this, 0, this.pos.y + y) && !this.isBlock(this.pos.x, this.pos.y + y))
			this.pos.y += y;
	}

	public synchronized void setPos(double dPos, int direction) {
//		System.out.println(this.getClass().getSimpleName() + " sync");
		if (direction == BACK || direction == FRONT)
			setEntityY(dPos);
		else
			setEntityX(dPos);

	}

	private void addWalkTick() {
		if (counter == 8) {
			this.walktick = (this.walktick + 1) % 3;
			counter = 0;
		}
		counter++;
	}
	
	@Override
	public boolean isCollide(GameObject other, double x, double y) {
		// System.out.println(other.pos.x+" "+ other.pos.y +" "+ x+" "+ y+"
		// "+(other.pos.y+getHeight()/3)+" "+y+" "+(other.pos.y+getHeight()/3));
		 if ((other.getX() - getWidth() * 4 / 6 <= x && x <= other.getX() + getWidth()
		 * 4 / 6)
		 && ((other.getY() - getHeight() / 6 + 5 <= y && y <= other.getY() +
		 getHeight()))) {
		 this.z = 1;
		 } else if ((other.getX() - getWidth() <= x && x <= other.getX() + getWidth())
		 && ((other.getY() - getHeight() / 8 + 5 <= y && y <= other.getY() +
		 getHeight()))) {
		 this.z = 1;
		 } else if ((other.getX() - getWidth() <= x && x <= other.getX() + getWidth())
		 && (other.getY() - getHeight() < y && y < other.getY())) {
		 this.z = -1;
		 }
		
		 if ((other.getY() - getHeight() / 3 <= y && y <= other.getY() + getHeight() / 3)
					&& (other.getX() - getWidth() * 4 / 6 <= x && x <= other.getX() + getWidth() * 4 / 6))
				return true;
		 return false;
	}

	protected void move(int direction) {
		// should ask if the change will be in the scene before change
		this.direction = direction;
		if (this.direction != direction && (this.direction % 3) == (direction % 3)) {
			this.counter = 0;
			this.walktick = 1;
		} else if (InputUtility.isKeyTrig()) {
			this.walktick = (this.walktick + 1) % 3;
		} else if (this.direction == direction
				|| ((this.direction % 3) != (direction % 3) && (this.direction % 3) != 0 && (direction % 3) == 0)) {
			addWalkTick();
		}

		if (direction == FRONT)
			setPos((movespeed / 10.0) * SceneManeger.HEIGHT / 150, FRONT);
		if (direction == BACK)
			setPos((-1) * (movespeed / 10.0) * SceneManeger.HEIGHT / 150, BACK);
		if (direction == RIGHT)
			setPos((movespeed / 10.0) * SceneManeger.WIDGTH / 200, RIGHT);
		if (direction == LEFT)
			setPos((-1) * (movespeed / 10.0) * SceneManeger.WIDGTH / 200, LEFT);

		
	}

	public abstract void update();
	
	@Override
	public double getWidth() {
		return (w * 2.5);
	}
	
	@Override
	public double getHeight() {
		return (h * 2.5);
	}

	public int getAxis(int direction) {
		if (direction == Entity.BACK || direction == Entity.FRONT)
			return SceneManeger.Y_AXIS;
		return SceneManeger.Y_AXIS;
	}

	public int getDirection() {
		return direction;
	}

	public int getMovespeed() {
		return movespeed;
	}

	public int getMass() {
		return mass;
	}

	public int getRace() {
		return race;
	}
	
	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public void setMovespeed(int movespeed) {
		this.movespeed = movespeed;
	}
}
