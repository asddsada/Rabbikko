package model.attribute;

import javafx.scene.canvas.GraphicsContext;
import logic.GameLogic;
import model.GameObject;
import model.entity.Hero;
import model.items.Inventory;
import model.items.Weapons;
import utility.ResourceLoader;
import utility.Constant;
import utility.Pair;

public class Agility extends Attribute {
	public Agility() {
		super();
		heroWeapon = (Weapons) Inventory.getBag()[3];
		attackMultiply = 0.8;
		attackRange = new Pair(getHeroWeapon().getWidth() * 3, getHeroWeapon().getHeight());
		attackSpeed = 2;
		hpMultiply = 0.9;
		hpRegen = 2;
		mpRegen = 3;
		animationImg = ResourceLoader.agility;
		attackObj = new GameObject(heroWeapon.getX() + 20, heroWeapon.getY(), 500) {

			@Override
			public void draw(GraphicsContext gc) {
				if (owner.getAtkType().getAttackTime() > 0) {
					// gc.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
					if (owner.getDirection() == Constant.ENTITY_RIGHT) {
						gc.drawImage(ResourceLoader.aEffect2, pos.x - getWidth(), pos.y - getHeight() / 2);
					} else if (owner.getDirection() == Constant.ENTITY_LEFT) {
						gc.drawImage(ResourceLoader.aEffect, pos.x - getWidth(), pos.y - getHeight() / 2);
					} else if (owner.getDirection() == Constant.ENTITY_BACK) {
						gc.drawImage(ResourceLoader.aEffect4, pos.x - getWidth() / 2, pos.y - getHeight() * 1.5);
					} else if (owner.getDirection() == Constant.ENTITY_FRONT) {
						gc.drawImage(ResourceLoader.aEffect3, pos.x - getWidth() / 2, pos.y - getHeight());
					}
				}
			}

			@Override
			public double getWidth() {
				return ((owner.getDirection() % 3) == Constant.SCENE_Y_AXIS) ? attackRange.y * 0.7 : attackRange.x;
			}

			@Override
			public double getHeight() {
				return ((owner.getDirection() % 3) == Constant.SCENE_Y_AXIS) ? attackRange.x : attackRange.y * 0.7;
			}
		};
	}

	@Override
	public void update(int direction, double x, double y) {
		super.update(direction, x, y);
		if (direction == Constant.ENTITY_RIGHT) {
			this.attackObj.setX(x + owner.getWidth() * 2 / 3);
			this.attackObj.setY(y + owner.getHeight() / 3);
		} else if (direction == Constant.ENTITY_LEFT) {
			this.attackObj.setX(x + owner.getWidth() / 3 - attackRange.x);
			this.attackObj.setY(y + owner.getHeight() / 3);
		} else if (direction == Constant.ENTITY_BACK) {
			this.attackObj.setX(x + owner.getWidth() / 6);
			this.attackObj.setY(y - attackObj.getHeight() + owner.getHeight() * 5 / 6);
		} else if (direction == Constant.ENTITY_FRONT) {
			this.attackObj.setX(x + owner.getWidth() / 6);
			this.attackObj.setY(y + owner.getHeight() * 2 / 3);
		} else if (direction == Constant.ENTITY_BACK) {
			this.attackObj.setZ(owner.getZ() - 10);
		} else
			this.attackObj.setZ(owner.getZ() + 10);
	}

	@Override
	public void use() {
		super.use();
		if (owner instanceof Hero)
			GameLogic.hero.useMp(getManaUsed());
	}
	
	public int getManaUsed() {
		return Constant.BASE_MP_USE;
	}
}