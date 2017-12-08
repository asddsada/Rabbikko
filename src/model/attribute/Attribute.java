package model.attribute;

import javafx.scene.canvas.GraphicsContext;
import logic.GameLogic;
import model.GameObject;
import model.entity.DungeonableEntity;
import model.entity.Entity;
import model.items.Weapons;
import sharedObj.RenderableHolder;
import utility.Pair;

public abstract class Attribute {
	protected GameObject attackObj;
	protected double attackMultiply;
	protected Pair attackRange;
	protected double attackSpeed;
	protected double hpMultiply;
	protected int hpRegen;
	protected int mpRegen;
	protected Weapons heroWeapon;
	protected int attackTime;
	protected int atkTimeMax;
	protected DungeonableEntity<Attribute> owner;

	public Attribute() {
		attackTime = 0;
		this.atkTimeMax = 10;
	}
	public void update(int direction, double x, double y) {
		if(attackTime>0) attackTime--;
	};

	public abstract <T1 extends Attribute, T2 extends Attribute> void attack(DungeonableEntity<T1> dungeonableEntity,
			DungeonableEntity<T2> other);

	public Weapons getHeroWeapon() {
		return heroWeapon;
	}

	public GameObject getAttackObj() {
		return attackObj;
	}

	public void setAttackObj(GameObject attackObj) {
		this.attackObj = attackObj;
	}
	
	public int getAtkTimeMax() {
		return (int) (atkTimeMax / getAttackSpeed());
	}

	public double getAttackMultiply() {
		return attackMultiply;
	}

	public Pair getAttackRange() {
		return attackRange;
	}

	public double getAttackSpeed() {
		return attackSpeed;
	}

	public double getHpMultiply() {
		return hpMultiply;
	}

	public int getHpRegen() {
		return hpRegen;
	}

	public int getMpRegen() {
		return mpRegen;
	}
	
	public int getAttackTime() {
		return attackTime;
	}
	
	public void setOwner(DungeonableEntity<Attribute> owner) {
		this.owner = owner;
		attackObj.setVisible(true);
		RenderableHolder.getInstance().add(getAttackObj());
	}

	public void use() {
		if(attackTime==0)attackTime = getAtkTimeMax();
	}
}
