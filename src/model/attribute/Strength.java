package model.attribute;

import javafx.scene.canvas.GraphicsContext;
import logic.ForceManeger;
import logic.GameLogic;
import model.GameObject;
import model.entity.DungeonableEntity;
import model.entity.Entity;
import model.items.Inventory;
import model.items.Weapons;
import sharedObj.RenderableHolder;
import utility.Pair;
import view.SceneManeger;

public class Strength extends Attribute {
	public Strength() {
		super();
		heroWeapon = (Weapons) Inventory.getBag()[2];
		attackMultiply = 1;
		attackRange = new Pair(getHeroWeapon().getWidth(), getHeroWeapon().getHeight());
		attackSpeed = 1;
		hpMultiply = 1.2;
		hpRegen = 5;
		mpRegen = 1;
		attackObj = new GameObject(0, 0, 500) {

			@Override
			public void draw(GraphicsContext gc) {
				try {
					if (owner.getAtkType().getAttackTime() > 0) {
//						gc.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
						if (owner.getDirection() == Entity.RIGHT) {
							gc.drawImage(RenderableHolder.sEffect2, pos.x - getWidth()*5/6, pos.y - getHeight());
						} else if (owner.getDirection() == Entity.LEFT) {
							gc.drawImage(RenderableHolder.sEffect, pos.x - getWidth()*1.3, pos.y - getHeight());
						} else if (owner.getDirection() == Entity.BACK) {
							gc.drawImage(RenderableHolder.sEffect, pos.x - getWidth() / 2, pos.y - getHeight()*5/6);
						} else if (owner.getDirection() == Entity.FRONT) {
							gc.drawImage(RenderableHolder.sEffect, pos.x - getWidth()/2, pos.y - getHeight()*5/6);
						}
					}
				} catch (NullPointerException e) {
					System.out.println("cannot draw attack effect");
				}
			}

			@Override
			public double getWidth() {
				return ((GameLogic.hero.getDirection() % 3) == SceneManeger.Y_AXIS) ? attackRange.y 
						: attackRange.x*1.4;
			}

			@Override
			public double getHeight() {
				return ((GameLogic.hero.getDirection() % 3) == SceneManeger.Y_AXIS) ? attackRange.x * 1.5
						: attackRange.y * 0.8;
			}
		};
	}

	public void update(int direction, double x, double y) {
		try {
			super.update(direction, x, y);
			if (direction == Entity.RIGHT) {
				this.attackObj.setX(x +owner.getWidth() * 2 / 3);
				this.attackObj.setY(y + owner.getHeight() / 3);
			} else if (direction == Entity.LEFT) {
				this.attackObj.setX(x + owner.getWidth() / 3 - attackRange.x);
				this.attackObj.setY(y + owner.getHeight() / 3);
			} else if (direction == Entity.BACK) {
				this.attackObj.setX(x + (owner.getWidth() - this.attackObj.getWidth()) / 2);
				this.attackObj.setY(y - attackRange.y + owner.getHeight() * 5 / 6);
			} else if (direction == Entity.FRONT) {
				this.attackObj.setX(x + (owner.getWidth() - this.attackObj.getWidth()) / 2);
				this.attackObj.setY(y +owner.getHeight() * 2 / 3);
			}
			
			else if (direction == Entity.BACK) {
				this.attackObj.setZ(owner.getZ()-10);
			}else this.attackObj.setZ(owner.getZ()+10);
		} catch (NullPointerException e) {
			System.out.println("cannot update attack effect");
		}
	}
}
