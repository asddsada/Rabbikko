package model.entity;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import logic.ForceManeger;
import logic.GameLogic;
import model.attribute.Attribute;
import model.field.Dungeon;
import sharedObj.RenderableHolder;

public abstract class DungeonableEntity<T extends Attribute> extends Entity {
	public static final int HUMANITY = 1;
	public static final int MONSTER = 0;
	protected int maxHp;
	protected int currentHp;
	protected int baseAtk;
	protected T atkType;
	protected int[] damageTake;
	protected boolean isAlive;

	public DungeonableEntity(double x, double y, Image img, int row, int column, int direction, int movespeed, int mass,
			int maxHp, int baseAtk, T atkType) {
		super(x, y, img, row, column, direction, movespeed, mass);
		this.atkType = atkType;
		this.baseAtk = baseAtk;
		this.mass = mass;
		this.maxHp = maxHp;
		this.damageTake = new int[4];
		this.currentHp = this.maxHp;
		this.isAlive=true;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		super.draw(gc);
	}

	public void attack() {
		ArrayList<DungeonableEntity<Attribute>> inArea = Dungeon.getEntityInArea(atkType.getAttackObj(), 
				atkType.getAttackObj().getX(), atkType.getAttackObj().getY());
		if(inArea==null) return;
		for (DungeonableEntity<Attribute> other : inArea) {
			System.out.println(other.getClass().getSimpleName());
			if (this.race != other.race)
				atkType.attack(other);
		}
	}

	public void damage(int dmg, int direction) {
		this.damageTake[direction] += ForceManeger.<T>calculateForce(dmg, getAxis(direction), this);
//		System.out.println(dmg);
		this.currentHp = this.currentHp-dmg>=0?this.currentHp-dmg:0;
		if (direction == 0)
			this.direction = 3;
		else {
			this.direction = (direction * 2) % 3;
		}
		System.out.println("HP " + currentHp);
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public int[] getDamageTake() {
		return damageTake;
	}
	
	@Override
	public void update() {
		if(this.currentHp==0) {
			this.setVisible(false);
			this.isAlive=false;
		}
	}
}
