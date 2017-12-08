package model.entity;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import logic.ForceManeger;
import logic.GameLogic;
import model.attribute.Attribute;
import model.field.Dungeon;
import model.field.Navigation;
import model.field.Obstructable;
import model.items.Inventory;
import sharedObj.RenderableHolder;
import utility.InputUtility;
import view.SceneManeger;

public class Hero extends DungeonableEntity<Attribute> {
	private double maxMp;
	private double currentMp;
	private int money;
	public static Inventory inventory;
	private String name;

	public Hero(int direction, Attribute atkType) {
		super(SceneManeger.WIDGTH / 2, (SceneManeger.HEIGHT - 100) / 2, RenderableHolder.humanImage, 0, 3, direction, 7,
				50, 1000, 45, atkType);
		this.maxMp = 1000;
		this.currentMp = 0;
		this.money = 0;
		this.z = -1;
		this.race = DungeonableEntity.HUMANITY;
		Hero.inventory = new Inventory();
		setAtktype(atkType);
		name = Navigation.getName();
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (dmgTimer % 5 == 0)
			super.draw(gc);
	}

	@Override
	public boolean attack() {
		boolean r = super.attack();
		if (r)
			this.healMp(15);
		else
			this.atkType.getHeroWeapon().use();
		return r;
	}

	@Override
	protected boolean isBlock(double x, double y) {
		if (struct)
			return false;
		if (dmgTimer != 0)
			return false;
		ArrayList<DungeonableEntity<Attribute>> inArea = Dungeon.getEntityInArea(this, x, y);
		for (DungeonableEntity<Attribute> other : inArea) {
			// System.out.println(other.getClass().getSimpleName());
			if (this.race != other.race)
				this.damage(other.baseAtk, this.direction);
			if (other instanceof Obstructable)
				return true;
		}
		return false;
	}

	@Override
	public void update() {
		super.update();
		if (isAlive && dmgTimer == 0) {
			if (InputUtility.isKeyPressed(KeyCode.W))
				move(Entity.BACK);
			if (InputUtility.isKeyPressed(KeyCode.S))
				move(Entity.FRONT);
			if (InputUtility.isKeyPressed(KeyCode.A))
				move(Entity.LEFT);
			if (InputUtility.isKeyPressed(KeyCode.D))
				move(Entity.RIGHT);
			if (InputUtility.isKeyPressed(KeyCode.SPACE) && atkType.getAttackTime()==0)
				attack();

			if (isBlock(pos.x, pos.y))
				struct = true;
			else
				struct = false;
			if (currentHp != getMaxHp())
				healHp(0.1);
			if (currentMp != getMaxMp())
				healMp(0.2);

		}
		if (isAlive) {
			this.atkType.getHeroWeapon().update(direction, pos.x, pos.y);
			this.atkType.update(this.direction, this.pos.x, this.pos.y);
		}
		// System.out.println(atkType.getClass().getSimpleName());
	}

	public void healHp(double i) {
		if (getCurrentHp() + i >= getMaxHp()) {
			restoreHp();
		} else {
			currentHp += i * atkType.getHpRegen();
		}
	}

	public void healMp(double i) {
		if (getCurrentMp() + i >= getMaxMp()) {
			currentMp = getMaxMp();
		} else {
			currentMp += i * atkType.getMpRegen();
		}
	}

	public void useMp(int i) {
		currentMp = currentMp < i ? 0 : currentMp - i;
	}

	public void revive() {
		this.restoreHp();
		Dungeon.addEntities(this);
		setAtktype(atkType);
	}

	public void restoreHp() {
		currentHp = getMaxHp();
		setVisible(true);
		isAlive = true;
	}

	public void resetMp() {
		currentMp = 0;
	}

	public double getMaxMp() {
		return maxMp;
	}

	public double getCurrentMp() {
		return currentMp;
	}

	public <T extends Attribute> void setAtktype(T atkType) {
		if (this.atkType != null) {
			this.atkType.getHeroWeapon().destroyed();
			this.atkType.getAttackObj().destroyed();
		}
		this.atkType = atkType;
		this.atkType.setOwner(this);
		this.atkType.getHeroWeapon().held();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoney() {
		return money;
	}

	public void useMoney(int i) {
		money -= i;
		if (money < 0)
			money = 0;
	}

	public void earnMoney(int i) {
		money += i;
	}
}
