package model.entity;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import logic.ForceManeger;
import logic.GameLogic;
import model.attribute.Attribute;
import model.field.Dungeon;
import sharedObj.RenderableHolder;
import utility.Constant;

public abstract class DungeonableEntity<T extends Attribute> extends Entity {

	protected double maxHp;
	protected double currentHp;
	protected int baseAtk;
	protected T atkType;
	protected int[] damageTake;
	protected boolean struct;
	protected int dmgTimer;
	private int hpBarTimer;
	private ArrayList<Integer> dmgTimerDelay;
	private int dmg;
	

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
		this.hpBarTimer = 0;
		dmgTimerDelay = new ArrayList<>();
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (!(this instanceof Hero) && (dmgTimer != 0 || hpBarTimer != 0)) {
			gc.setFill(Color.BLACK);
			gc.fillRect(pos.x + this.getWidth() / 6, pos.y - this.getHeight() / 6, this.getWidth() * 5 / 6, 8);
			gc.setFill(Color.DARKRED);
			gc.fillRect(pos.x + this.getWidth() / 6, pos.y - this.getHeight() / 6,
					(getCurrentHp() / getMaxHp()) * this.getWidth() * 5 / 6, 8);
			hpBarTimer--;
		}
		if (!dmgTimerDelay.isEmpty()) {
			gc.setFill(Color.RED);
			for (int i = 0; i < dmgTimerDelay.size(); i++) {
				gc.fillText("-" + Integer.toString(dmg), pos.x + getWidth() / 3,
						pos.y  + getHeight()/3 - (50 - dmgTimerDelay.get(i)));
				dmgTimerDelay.set(i, dmgTimerDelay.get(i)-1);
				if(dmgTimerDelay.get(i)==0) dmgTimerDelay.remove(i);
				else if(dmgTimerDelay.size()>=10&&dmgTimerDelay.get(i)<20) dmgTimerDelay.remove(i);
			}
		}
		if (dmgTimer % 5 == 0)
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
				}
			}

			return true;
		}
		return false;
	}

	public void damage(int dmg, int direction) {
		this.dmg = dmg;
		if(dmgTimerDelay.size()<=20) dmgTimerDelay.add(50);
		dmgTimer = Constant.DMG_TIME_MAX;
		this.damageTake[direction] += ForceManeger.<T>calculateForce(dmg, getAxis(direction), this);
		this.currentHp = this.currentHp - dmg >= 0 ? this.currentHp - dmg : 0;
		if (this.currentHp == 0)
			isAlive = false;
		ForceManeger.reactionEffect(this, direction);
		this.hpBarTimer = 180;
	}

	public double getMaxHp() {
		return maxHp * atkType.getHpMultiply() ;
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
		return baseAtk;
	}

	public T getAtkType() {
		return atkType;
	}

	@Override
	public void update() {
		if (!isAlive || this.currentHp == 0) {
			Dungeon.destroyEntities(this);
			this.atkType.getAttackObj().setVisible(false);
		} else if (isAlive) {
			dmgTimer = dmgTimer == 0 ? 0 : dmgTimer - 1;
		}
		this.atkType.update(this.direction, this.pos.x, this.pos.y);
	}
}
