package model.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import model.attribute.Attribute;
import sharedObj.RenderableHolder;

public abstract class DungeonableEntity extends Entity {
	public static final int HUMANITY = 0;
	public static final int MONSTER = 1;
	protected int baseAtk;
	protected int maxHp;
	protected int currentHp;
	protected int maxMp;
	protected int currentMp;
	protected int race;
	protected Attribute atkType;

	public DungeonableEntity(double x,double y,Image img,int row,int column, int direction,int movespeed,Attribute atkType) {
		super(x,y, img, row, column, direction,movespeed);
		this.atkType=atkType;
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

	public void collide(DungeonableEntity enemy) {

	}
}
