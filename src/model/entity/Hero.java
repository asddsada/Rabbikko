package model.entity;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import model.attribute.Attribute;
import model.field.Dungeon;
import model.field.Obstructable;
import model.items.Inventory;
import sharedObj.RenderableHolder;
import utility.InputUtility;
import view.SceneManeger;

public class Hero extends DungeonableEntity<Attribute> {
	private int maxMp;
	private int currentMp;
	private final int MAXMP = 500;
	private static int money;
	public static Inventory inventory;

	public Hero(int direction, Attribute atkType) {
		super(SceneManeger.WIDGTH / 2, (SceneManeger.HEIGHT - 100) / 2, RenderableHolder.humanImage, 0, 3, direction, 7,
				50, 1000, 30, atkType);
		this.maxMp = 1000;
		this.currentMp = 0;
		this.money = 0;
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
		if(r) this.healMp(1);
		atkType.getHeroWeapon().use();
		return r;
	}

	@Override
	protected boolean isBlock(double x, double y) {
		ArrayList<DungeonableEntity<Attribute>> inArea = Dungeon.getEntityInArea(this, x, y);
		for (DungeonableEntity<Attribute> other : inArea) {
//			System.out.println(other.getClass().getSimpleName());
			if (this.race != other.race)
				this.damage(other.baseAtk, this.direction);
			if(other instanceof Obstructable)
				return true;
		}
		return false;
	}

	@Override
	public void update() {
		if (isAlive) {
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
			

			this.atkType.getHeroWeapon().update(direction,pos.x,pos.y);
		}
		// for (int i = 0; i <= 3; i++) {
		// System.out.println(getDamageTake()[i]);
		// }
	}

	public void healHp(int i) {
		if (getCurrentHp() + i > getMaxMp()) {
			resetHp();
		} else {
			currentHp += i*atkType.getHpRegen();
		}
	}

	public void healMp(int i) {
		if (getCurrentMp() + i > getMaxMp()) {
			resetMp();
		} else {
			currentMp += i*atkType.getMpRegen();
		}
	}

	public void resetHp() {
		currentHp = getMaxHp();
		setVisible(true);
		isAlive=true;
	}

	public void resetMp() {
		currentMp = maxMp;
	}

	public int getMaxMp() {
		return (int) (maxMp*atkType.getHpMultiply());
	}

	public int getCurrentMp() {
		return currentMp;
	}

	public <T extends Attribute> void setAtktype(T atkType) {
		if(this.atkType!=null) {
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
	
	

}
