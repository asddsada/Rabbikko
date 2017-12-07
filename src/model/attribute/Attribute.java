package model.attribute;

import javafx.scene.canvas.GraphicsContext;
import logic.GameLogic;
import model.GameObject;
import model.entity.DungeonableEntity;
import model.entity.Entity;
import model.items.Weapons;
import utility.Pair;

public abstract class Attribute{
	protected GameObject attackObj;
	protected double attackMultiply;
	protected Pair attackRange;
	protected double attackSpeed;
	protected double hpMultiply;
	protected int hpRegen;
	protected int mpRegen;	
	protected Weapons heroWeapon;
	
	public Attribute() {
		
	}
	
	public void update(int direction, double x,double y) {
		if(direction==Entity.RIGHT) {	
			this.attackObj.setX(x+GameLogic.hero.getWidth());
		this.attackObj.setY(y);
		}else if(direction==Entity.LEFT) {	
			this.attackObj.setX(x-attackRange.x);
		this.attackObj.setY(y);
		}else if(direction==Entity.BACK) {	
			this.attackObj.setX(x+GameLogic.hero.getWidth()/6);
		this.attackObj.setY(y-attackRange.y+GameLogic.hero.getHeight()/2);
		}else if(direction==Entity.FRONT) {	
			this.attackObj.setX(x+GameLogic.hero.getWidth()/6);
		this.attackObj.setY(y+GameLogic.hero.getHeight()/2);
		}
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
}
