package model.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import model.attribute.Attribute;
import sharedObj.RenderableHolder;

public abstract class DungeonableEntity<T extends Attribute> extends Entity {
	public static final int HUMANITY = 0;
	public static final int MONSTER = 1;
	protected int maxHp;
	protected int currentHp;
	protected int baseAtk;
	protected int race;
	protected T atkType;

	public  DungeonableEntity(double x,double y,Image img,int row,int column, int direction,int movespeed,int maxHp, T atkType) {
		super(x,y, img, row, column, direction,movespeed);
		this.atkType=atkType;
		this.maxHp=maxHp;
		this.currentHp=this.maxHp;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		super.draw(gc);
	}

	public abstract void attack();

	public void damage(int dmg) {
		// decrease hp
		// force back;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void collide(DungeonableEntity enemy) {

	}
}
