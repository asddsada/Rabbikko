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
	protected double maxHp;
	protected double currentHp;
	protected int baseAtk;
	protected T atkType;
	protected int[] damageTake;
	protected int dmgTimer;
	public static final int DMG_TIME_MAX = 3000;

	public DungeonableEntity(double x, double y, Image img, int row, int column, int direction, int movespeed, int mass,
			int maxHp, int baseAtk, T atkType) {
		super(x, y, img, row, column, direction, movespeed, mass);
		this.atkType = atkType;
		this.atkType.setOwner((DungeonableEntity<Attribute>) this);
		this.baseAtk = baseAtk;
		this.mass = mass;
		this.maxHp = maxHp;
		this.damageTake = new int[4];
		this.currentHp = getMaxHp();
		this.dmgTimer = 0;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		super.draw(gc);
	}

	public boolean attack() {
		if (atkType.getAttackTime() == 0) {
			ArrayList<DungeonableEntity<Attribute>> inArea = Dungeon.getEntityInArea(atkType.getAttackObj(),
					atkType.getAttackObj().getX(), atkType.getAttackObj().getY());
			if (inArea == null || inArea.size() <= 1)
				return false;
			for (DungeonableEntity<Attribute> other : inArea) {
				if (other.hashCode() != this.hashCode() && this.race != other.race) {
					atkType.use();
					atkType.attack(this, other);
					other.direction = ForceManeger.calculateDirection(this.direction);
				}
			}
			return true;
		}
		return false;
	}

	public void damage(int dmg, int direction) {
		dmgTimer = DMG_TIME_MAX;
		this.damageTake[direction] += ForceManeger.<T>calculateForce(dmg, getAxis(direction), this);
		this.currentHp = this.currentHp - dmg >= 0 ? this.currentHp - dmg : 0;
		if (this.currentHp == 0)
			isAlive = false;
		ForceManeger.reactionEffect(this, direction);
	}

	public double getMaxHp() {
		return maxHp * atkType.getHpMultiply();
	}

	public double getCurrentHp() {
		return currentHp;
	}

	public int[] getDamageTake() {
		return damageTake;
	}

	public int getDmgTimer() {
		return dmgTimer;
	}

	public int getBaseAtk() {
		return baseAtk ;
	}

	public T getAtkType() {
		return atkType;
	}

	@Override
	public void update() {
		if (!isAlive || this.currentHp==0) {
			Dungeon.destroyEntities(this);	
			this.atkType.getAttackObj().setVisible(false);
		}
		this.atkType.update(this.direction, this.pos.x, this.pos.y);
	}
}
