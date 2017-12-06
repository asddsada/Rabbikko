package model.attribute;

import javafx.scene.canvas.GraphicsContext;
import model.GameObject;
import model.entity.DungeonableEntity;
import model.entity.Entity;
import model.items.Weapons;
import utility.Pair;

public abstract class Attribute{
	protected GameObject attackObj;
	protected int damageMultiply;
	protected Pair attackRange;
	protected int attackSpped;
	protected int moveSpeedMultiply;
	protected int hpMultiply;
	protected int hpRegen;
	protected int mpRegen;	
	protected Weapons heroWeapon;
	
	public Attribute() {
		
	}
	public abstract <T1 extends Attribute,T2 extends Attribute> void attack(DungeonableEntity<T1> dungeonableEntity,DungeonableEntity<T2> other);
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
	public Pair getAttackRange() {
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
