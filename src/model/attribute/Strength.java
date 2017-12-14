package model.attribute;

import javafx.scene.canvas.GraphicsContext;
import logic.GameLogic;
import model.GameObject;
import model.entity.DungeonableEntity;
import model.entity.Entity;
import model.entity.Hero;
import model.items.Inventory;
import model.items.Weapons;
import utility.ResourceLoader;
import utility.Constant;
import utility.ForceUtility;
import utility.Pair;
import view.SceneManeger;

public class Strength extends Attribute {
	public Strength() {
		super();
		heroWeapon = (Weapons) Inventory.getBag()[Constant.SWORD];
		attackMultiply = 1;
		attackRange = new Pair(getHeroWeapon().getWidth(), getHeroWeapon().getHeight());
		attackSpeed = 1;
		hpMultiply = 1.4;
		hpRegen = 5;
		mpRegen = 1;
		animationImg = ResourceLoader.strength;
		attackObj = new GameObject(0, 0, 500) {

			@Override
			public void draw(GraphicsContext gc) {
				try {
					if (owner instanceof Hero) {
						if (owner.getAtkType().getAttackTime() > 3) {
							// gc.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
							if (owner.getDirection() == Constant.ENTITY_RIGHT) {
								gc.drawImage(ResourceLoader.sEffect2, pos.x - getWidth() / 2, pos.y - getHeight() / 2,
										ResourceLoader.sEffect2.getWidth() * 0.8,
										ResourceLoader.sEffect2.getHeight() * 0.8);
							} else if (owner.getDirection() == Constant.ENTITY_LEFT) {
								gc.drawImage(ResourceLoader.sEffect, pos.x - getWidth() * 1.2,
										pos.y - getHeight() / 2, ResourceLoader.sEffect2.getWidth() * 0.8,
										ResourceLoader.sEffect2.getHeight() * 0.8);
							} else if (owner.getDirection() == Constant.ENTITY_BACK) {
								gc.drawImage(ResourceLoader.sEffect, pos.x - getWidth() / 6,
										pos.y - getHeight() * 0.6, ResourceLoader.sEffect2.getWidth() * 0.8,
										ResourceLoader.sEffect2.getHeight() * 0.8);
							} else if (owner.getDirection() == Constant.ENTITY_FRONT) {
								gc.drawImage(ResourceLoader.sEffect2, pos.x - getWidth() * 0.4,
										pos.y - getHeight() * 0.4, ResourceLoader.sEffect2.getWidth() * 0.8,
										ResourceLoader.sEffect2.getHeight() * 0.8);
							}
						}
					} else {
						if (owner.getAtkType().getAttackTime() > 0) {
							// gc.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
							if (owner.getDirection() == Constant.ENTITY_RIGHT) {
								gc.drawImage(ResourceLoader.monsterEffect, pos.x - getWidth() * 5 / 6,
										pos.y - getHeight());
							} else if (owner.getDirection() == Constant.ENTITY_LEFT) {
								gc.drawImage(ResourceLoader.monsterEffect, pos.x - getWidth() * 1.3,
										pos.y - getHeight());
							} else if (owner.getDirection() == Constant.ENTITY_BACK) {
								gc.drawImage(ResourceLoader.monsterEffect, pos.x - getWidth() / 2,
										pos.y - getHeight() * 5 / 6);
							} else if (owner.getDirection() == Constant.ENTITY_FRONT) {
								gc.drawImage(ResourceLoader.monsterEffect, pos.x - getWidth() / 2,
										pos.y - getHeight() * 5 / 6);
							}
						}
					}
				} catch (NullPointerException e) {
					System.out.println("cannot draw attack effect");
				}
			}

			@Override
			public double getWidth() {
				return ((GameLogic.hero.getDirection() % 3) == Constant.SCENE_Y_AXIS) ? attackRange.y
						: attackRange.x * 1.5;
			}

			@Override
			public double getHeight() {
				return ((GameLogic.hero.getDirection() % 3) == Constant.SCENE_Y_AXIS) ? attackRange.x * 2 : attackRange.y;
			}
		};
	}

	public void update(int direction, double x, double y) {
		try {
			super.update(direction, x, y);
			if (direction == Constant.ENTITY_RIGHT) {
				this.attackObj.setX(x + owner.getWidth() * 2 / 3);
				this.attackObj.setY(y + owner.getHeight() / 3);
			} else if (direction == Constant.ENTITY_LEFT) {
				this.attackObj.setX(x + owner.getWidth() / 3 - attackRange.x);
				this.attackObj.setY(y + owner.getHeight() / 3);
			} else if (direction == Constant.ENTITY_BACK) {
				this.attackObj.setX(x + (owner.getWidth() - this.attackObj.getWidth()) / 2);
				this.attackObj.setY(y - attackRange.y + owner.getHeight() * 5 / 6);
			} else if (direction == Constant.ENTITY_FRONT) {
				this.attackObj.setX(x + (owner.getWidth() - this.attackObj.getWidth()) / 2);
				this.attackObj.setY(y + owner.getHeight() * 2 / 3);
			}

			else if (direction == Constant.ENTITY_BACK) {
				this.attackObj.setZ(owner.getZ() - 10);
			} else
				this.attackObj.setZ(owner.getZ() + 10);
		} catch (NullPointerException e) {
			System.out.println("cannot update attack effect");
		}
	}
	
	public int getManaUsed() {
		return 0;
	}
}
