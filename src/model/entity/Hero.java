package model.entity;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import logic.ForceManeger;
import model.attribute.Attribute;
import model.field.Dungeon;
import model.field.Obstructable;
import model.items.Inventory;
import sharedObj.RenderableHolder;
import utility.InputUtility;
import view.SceneManeger;

public class Hero extends DungeonableEntity<Attribute> {
	private double maxMp;
	private double currentMp;
	private static int money;
	public static Inventory inventory;

	public Hero(int direction, Attribute atkType) {
		super(SceneManeger.WIDGTH / 2, (SceneManeger.HEIGHT - 100) / 2, RenderableHolder.humanImage, 0, 3, direction, 7,
				50, 1000, 30, atkType);
		this.maxMp = 1000;
		this.currentMp = 0;
		this.money = 10000;
		this.z = -1;
		this.race = DungeonableEntity.HUMANITY;
		this.inventory = new Inventory();
		setAtktype(atkType);
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		super.draw(gc);
	}

	@Override
	public boolean attack() {
		boolean r = super.attack();
		if (r)
			this.healMp(15);
		atkType.getHeroWeapon().use();
		return r;
	}

	@Override
	protected boolean isBlock(double x, double y) {
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
		if (isAlive && dmgTimer == 0) {
			super.update();
			if (InputUtility.isKeyPressed(KeyCode.W))
				move(Entity.BACK);
			if (InputUtility.isKeyPressed(KeyCode.S))
				move(Entity.FRONT);
			if (InputUtility.isKeyPressed(KeyCode.A))
				move(Entity.LEFT);
			if (InputUtility.isKeyPressed(KeyCode.D))
				move(Entity.RIGHT);
			if (InputUtility.isKeyPressed(KeyCode.SPACE))
				attack();

			if (currentHp != getMaxHp())
				healHp(0.15);
			if (currentMp != getMaxMp())
				healMp(0.3);
			this.atkType.getHeroWeapon().update(direction, pos.x, pos.y);
		} else if (isAlive) {
			dmgTimer = dmgTimer == 0 ? dmgTimer - 1 : 0;
		}

		// System.out.println(getDamageTake()[0]+" "+getDamageTake()[1]+"
		// "+getDamageTake()[2]+" "+getDamageTake()[3]+" ");

	}

	public void healHp(double i) {
		if (getCurrentHp() + i >= getMaxHp()) {
			resetHp();
		} else {
			currentHp += i * atkType.getHpRegen();
		}
	}

	public void healMp(double i) {
		if (getCurrentMp() + i >= getMaxMp()) {
			resetMp();
		} else {
			currentMp += i * atkType.getMpRegen();
		}
	}

	public void resetHp() {
		currentHp = getMaxHp();
		setVisible(true);
		isAlive = true;
	}

	public void resetMp() {
		currentMp = maxMp;
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
		this.atkType.getHeroWeapon().held();
		RenderableHolder.getInstance().add(atkType.getAttackObj());
	}

	public static int getMoney() {
		return money;
	}
	
	public static void useMoney(int i) {
		money -= i;
	}
}
