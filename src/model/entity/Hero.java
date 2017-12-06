package model.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import logic.GameLogic;
import model.attribute.Attribute;
import model.attribute.Intelligence;
import model.inventory.Inventory;
import sharedObj.RenderableHolder;
import utility.InputUtility;
import view.SceneManeger;

public class Hero extends DungeonableEntity<Attribute> {
	private int maxMp;
	private int currentMp;
	private static int money;
	private Inventory inventory;

	public Hero(int direction, Attribute atkType) {
		super(SceneManeger.WIDGTH / 2, (SceneManeger.HEIGHT - 100) / 2, RenderableHolder.humanImage, 0, 3, direction, 7,
				50, 1000, 30, atkType);
		this.maxMp = 1000;
		this.currentMp = 0;
		this.money = 0;
		this.z = -1;
		this.race = DungeonableEntity.HUMANITY;
		this.inventory = new Inventory();
		// this.atkType = new Intelligence();
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		super.draw(gc);
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isBlock(double x, double y) {
		for (DungeonableEntity<Attribute> other : GameLogic.dungeon.getENTITIES_HOLDER()) {
			if (other.hashCode() != this.hashCode() && super.isCollide(other, x, y)) {
				if (this.race == other.race)
					return true;
				else {
					this.damage(other.baseAtk, this.direction);
				}
			}
		}
		return false;
	}

	@Override
	public void update() {
		if (InputUtility.isKeyPressed(KeyCode.W))
			move(Entity.BACK);
		if (InputUtility.isKeyPressed(KeyCode.S))
			move(Entity.FRONT);
		if (InputUtility.isKeyPressed(KeyCode.A))
			move(Entity.LEFT);
		if (InputUtility.isKeyPressed(KeyCode.D))
			move(Entity.RIGHT);

		// for (int i = 0; i <= 3; i++) {
		// System.out.println(getDamageTake()[i]);
		// }
	}

	public void healHp(int i) {
		if (getCurrentHp() + i > getMaxMp()) {
			resetHp();
		} else {
			currentHp += i;
		}
	}

	public void healMp(int i) {
		if (getCurrentMp() + i > getMaxMp()) {
			resetMp();
		} else {
			currentMp += i;
		}
	}

	public void resetHp() {
		currentHp = maxHp;
	}

	public void resetMp() {
		currentMp = maxMp;
	}

	public int getMaxMp() {
		return maxMp;
	}

	public int getCurrentMp() {
		return currentMp;
	}

	public <T extends Attribute> void setAtktype(T atkType) {
		this.atkType = atkType;
	}

	public static int getMoney() {
		return money;
	}

}
