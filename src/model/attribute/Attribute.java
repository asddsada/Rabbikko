package model.attribute;

import javafx.scene.canvas.GraphicsContext;
import model.GameObject;
import model.entity.DungeonableEntity;
import model.entity.Entity;
import model.items.Weapons;

public abstract class Attribute{
	protected GameObject attackObj;
	protected int damageMultiply;
	protected double attackRange;
	protected int attackSpped;
	protected int moveSpeedMultiply;
	protected int hpMultiply;
	protected int hpRegen;
	protected int mpRegen;	
	protected Weapons heroWeapon;
	
	public Attribute() {
		
	}
	public abstract void attack(DungeonableEntity<Attribute> other);
	public void useAttribute(DungeonableEntity<Attribute> entity) {
		
	}
	public Weapons getHeroWeapon() {
		return heroWeapon;
	}
	public GameObject getAttackObj() {
		return attackObj;
	}
	public void setAttackObj(GameObject attackObj) {
		this.attackObj = attackObj;
	}
	public int getDamageMultiply() {
		return damageMultiply;
	}
	public double getAttackRange() {
		return attackRange;
	}
	public int getAttackSpped() {
		return attackSpped;
	}
	public int getMoveSpeedMultiply() {
		return moveSpeedMultiply;
	}
	public int getHpMultiply() {
		return hpMultiply;
	}
	public int getHpRegen() {
		return hpRegen;
	}
	public int getMpRegen() {
		return mpRegen;
	}
	
	
}
