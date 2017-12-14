package model.attribute;

import javafx.scene.image.Image;
import logic.ForceManeger;
import logic.GameLogic;
import model.GameObject;
import model.entity.DungeonableEntity;
import model.entity.Hero;
import model.items.Weapons;
import sharedObj.RenderableHolder;
import utility.Constant;
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
	protected DungeonableEntity<Attribute> owner;
	protected Image animationImg;

	public Attribute() {
		attackTime = 0;
	}
	public void update(int direction, double x, double y) {		
		if(attackTime>0) attackTime--;
		if(this.owner.isDestroyed()) this.attackObj.setVisible(false);
	};

	public <T1 extends Attribute, T2 extends Attribute> void attack(DungeonableEntity<T1> attacker,
			DungeonableEntity<T2> other) {
		if(other.getDmgTimer() < Constant.DMG_TIME_MAX/2) {
		other.damage((int) (attacker.getBaseAtk() * attackMultiply*((GameLogic.dungeon.getLvl()/20)+1)),
		ForceManeger.calculateDirection(attacker.getDirection()));
		}
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
	
	public int getAtkTimeMax() {
		return (int) (Constant.BASE_ATTACK_TIMER_MAX / getAttackSpeed());
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
	
	public void resetAttackTime() {
		if(getAttackTime()==0)attackTime = getAtkTimeMax();
	}
	
	public void setOwner(DungeonableEntity<Attribute> owner) {
		this.owner = owner;
		attackObj.setVisible(true);
		RenderableHolder.getInstance().add(getAttackObj());
	}

	public void use() {
		resetAttackTime();
	}
	
	public Image getImage() {
		return animationImg;
	}
}
