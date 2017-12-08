package model.attribute;

import javafx.scene.canvas.GraphicsContext;
import logic.ForceManeger;
import logic.GameLogic;
import model.GameObject;
import model.entity.DungeonableEntity;
import model.entity.Entity;
import model.entity.Hero;
import model.items.Weapons;
import sharedObj.RenderableHolder;
import utility.Pair;
import view.SceneManeger;

public class Intelligence extends Attribute{
	public Intelligence() {
		heroWeapon = (Weapons) Hero.inventory.getBag()[4];
		attackMultiply = 1.2;
		attackRange = new Pair(getHeroWeapon().getWidth(), getHeroWeapon().getHeight());
		attackSpeed = 0.5;
		hpMultiply = 1.2;
		hpRegen = 5;
		mpRegen = 1;
		attackObj = new GameObject(heroWeapon.getX() + 20, heroWeapon.getY(), 500) {

			@Override
			public void draw(GraphicsContext gc) {
				if (heroWeapon.getAttackTime() > 0) {
					gc.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
					if(GameLogic.hero.getDirection()==Entity.RIGHT) {	
						gc.drawImage(RenderableHolder.mEffect,pos.x-getWidth()/4,pos.y-getHeight()*2,RenderableHolder.mEffect.getWidth()/2,RenderableHolder.mEffect.getHeight()/2);
					}else if(GameLogic.hero.getDirection()==Entity.LEFT) {	
						gc.drawImage(RenderableHolder.mEffect,pos.x-getWidth()*4,pos.y-getHeight()*2,RenderableHolder.mEffect.getWidth()/2,RenderableHolder.mEffect.getHeight()/2);
					}else if(GameLogic.hero.getDirection()==Entity.BACK) {	
						gc.drawImage(RenderableHolder.mEffect,pos.x-getWidth(),pos.y-getHeight()*2,RenderableHolder.mEffect.getWidth()/2,RenderableHolder.mEffect.getHeight()/2);
					}else if(GameLogic.hero.getDirection()==Entity.FRONT) {	
						gc.drawImage(RenderableHolder.mEffect,pos.x-getWidth()*1.5,pos.y-getHeight()/2,RenderableHolder.mEffect.getWidth()/2,RenderableHolder.mEffect.getHeight()/2);
					}
				}
			}

			@Override
			public double getWidth() {
				return ((GameLogic.hero.getDirection() % 3) == SceneManeger.Y_AXIS) ? attackRange.y * 0.7
						: attackRange.x;
			}

			@Override
			public double getHeight() {
				return ((GameLogic.hero.getDirection() % 3) == SceneManeger.Y_AXIS) ? attackRange.x * 1.5
						: attackRange.y * 0.6;
			}
		};
	}

	@Override
	public <T1 extends Attribute, T2 extends Attribute> void attack(DungeonableEntity<T1> attacker,
			DungeonableEntity<T2> other) {
		// TODO Auto-generated method stub
		other.damage((int) (attacker.getBaseAtk() * attackMultiply),
				ForceManeger.calculateDirection(attacker.getDirection()));
	}

	@Override
	public void update(int direction, double x, double y) {
		// TODO Auto-generated method stub
		if (direction == Entity.RIGHT) {
			this.attackObj.setX(x + GameLogic.hero.getWidth() * 2 / 3);
			this.attackObj.setY(y + GameLogic.hero.getHeight() / 3);
		} else if (direction == Entity.LEFT) {
			this.attackObj.setX(x + GameLogic.hero.getWidth() / 3 - attackRange.x);
			this.attackObj.setY(y + GameLogic.hero.getHeight() / 3);
		} else if (direction == Entity.BACK) {
			this.attackObj.setX(x + GameLogic.hero.getWidth() / 6);
			this.attackObj.setY(y - attackRange.y + GameLogic.hero.getHeight() * 5 / 6);
		} else if (direction == Entity.FRONT) {
			this.attackObj.setX(x + GameLogic.hero.getWidth() / 6);
			this.attackObj.setY(y + GameLogic.hero.getHeight() * 2 / 3);
		}
	}

}
