package model.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import logic.GameLogic;
import model.GameObject;
import model.attribute.Attribute;
import model.field.Dungeon;
import sharedObj.RenderableHolder;
import utility.Constant;
import utility.InputUtility;
import utility.Pair;
import view.SceneManeger;

public abstract class Entity extends GameObject {

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
		this.walktick = 1;
		this.img = new WritableImage(img.getPixelReader(), (int) w * 3 * column, (int) h * 4 * row, (int) w * 3,
				(int) h * 4);
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(new WritableImage(img.getPixelReader(), (int) w * walktick, (int) h * direction, (int) w, (int) h),
				pos.x, pos.y,getWidth(), getHeight());
//		gc.strokeRect(pos.x, pos.y, getWidth(), getHeight());
//		gc.strokeRect(pos.x + getWidth() / 6, pos.y, getWidth() / 1.5, getHeight());
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
		if (direction == Constant.ENTITY_BACK || direction ==  Constant.ENTITY_FRONT)
			setEntityY(dPos);
		else
			setEntityX(dPos);

	}

	private void addWalkTick() {
		if (counter == Constant.ENTITY_WALK_TICK_DELAY) {
			this.walktick = (this.walktick + 1) % Constant.ENTITY_WALK_STAGE;
			counter = 0;
		}
		counter++;
	}
	
	@Override
	public boolean isCollide(GameObject other, double x, double y) {
		if ((other.getX() - getWidth() * 4 / 6 <= x && x <= other.getX() + getWidth() * 4 / 6)
				&& ((other.getY() - getHeight() / 6 + 5 <= y && y <= other.getY() + getHeight()))) {
			this.z = 20;
		} else if ((other.getX() - getWidth() <= x && x <= other.getX() + getWidth())
				&& ((other.getY() - getHeight() / 8 + 5 <= y && y <= other.getY() + getHeight()))) {
			this.z = 1;
		} else if ((other.getX() - getWidth() <= x && x <= other.getX() + getWidth())
				&& (other.getY() - getHeight() < y && y < other.getY())) {
			this.z = -1;
		}
		 
		 if ((((x+getWidth()/3 - other.getWidth()) <= other.getX()) && (other.getX() <= (x + getWidth()*2/3 + other.getWidth())))
					&& (((y+getHeight()*5/6 - other.getHeight()) <= other.getY())
							&& (other.getY() <= (y + other.getHeight()/4 + getHeight())))
					&& (((x +getWidth()/3 - other.getWidth()) <= (other.getX() + other.getWidth()))
							&& ((other.getX() + other.getWidth()) <= (x + getWidth() + other.getWidth()*2/3)))
					&& (((y+getHeight()*5/6 - other.getHeight()) <= (other.getY()+other.getHeight()))
							&& ((other.getY()+other.getHeight()) <= (y + other.getHeight()/4 + getHeight()))))
				return true;
		 return false;
	}

	protected void move(int direction) {
		if ((this.direction != direction) && ((this.direction+direction)%Constant.ENTITY_WALK_STAGE==0)) {
			this.counter = 0;
			this.walktick = 1;
		} else if (this.direction == direction || ((this.direction+direction)%Constant.ENTITY_WALK_STAGE!=0)) {
			addWalkTick();
		}

		if (direction ==  Constant.ENTITY_FRONT)
			setPos((movespeed / 10.0) * Constant.SCENE_HEIGHT / 150,  Constant.ENTITY_FRONT);
		if (direction ==  Constant.ENTITY_BACK)
			setPos((-1) * (movespeed / 10.0) * Constant.SCENE_HEIGHT / 150,  Constant.ENTITY_BACK);
		if (direction ==  Constant.ENTITY_RIGHT)
			setPos((movespeed / 10.0) * Constant.SCENE_WIDTH / 200,  Constant.ENTITY_RIGHT);
		if (direction ==  Constant.ENTITY_LEFT)
			setPos((-1) * (movespeed / 10.0) * Constant.SCENE_WIDTH / 200,  Constant.ENTITY_LEFT);

		this.direction = direction;
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
		if (direction ==  Constant.ENTITY_BACK || direction ==  Constant.ENTITY_FRONT)
			return Constant.SCENE_Y_AXIS;
		return Constant.SCENE_X_AXIS;
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
