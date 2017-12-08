package model.attribute;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.ForceManeger;
import logic.GameLogic;
import model.GameObject;
import model.entity.DungeonableEntity;
import model.entity.Entity;
import model.entity.Hero;
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
	protected Image img;

	public Attribute() {
		attackTime = 0;
		this.atkTimeMax = 30;
	}
	public void update(int direction, double x, double y) {		
		if(owner instanceof Hero)System.out.println(attackTime);
		if(attackTime>0) attackTime--;
		if(this.owner.isDestroyed()) this.attackObj.setVisible(false);
	};

	public <T1 extends Attribute, T2 extends Attribute> void attack(DungeonableEntity<T1> attacker,
			DungeonableEntity<T2> other) {
		other.damage((int) (attacker.getBaseAtk() * attackMultiply),
				ForceManeger.calculateDirection(attacker.getDirection()));
		
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
	
	public int resetAttackTime() {
		return attackTime;
	}
	
	public void setOwner(DungeonableEntity<Attribute> owner) {
		this.owner = owner;
		attackObj.setVisible(true);
		RenderableHolder.getInstance().add(getAttackObj());
	}

	public void use() {
		if(getAttackTime()==0)attackTime = getAtkTimeMax();
	}
	
	public Image getImage() {
		return img;
	}
}
