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

public class Intelligence extends Attribute{
	protected int magicTime;
	protected int magicTimeMax;
	public Intelligence() {
		super();
		heroWeapon = (Weapons) Inventory.getBag()[4];
		attackMultiply = 1.5;
		attackRange = new Pair(getHeroWeapon().getWidth()*1.2, getHeroWeapon().getHeight());
		attackSpeed = 0.8;
		hpMultiply = 1;
		hpRegen = 1;
		mpRegen = 5;
		attackObj = new GameObject(heroWeapon.getX() + 20, heroWeapon.getY(), 500) {

			@Override
			public void draw(GraphicsContext gc) {
				if (owner.getAtkType().getAttackTime()  > 0) {
					gc.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
					if(owner.getDirection()==Entity.RIGHT) {	
						gc.drawImage(RenderableHolder.mEffect,pos.x-getWidth()/4,pos.y-getHeight()*2,RenderableHolder.mEffect.getWidth()/2,RenderableHolder.mEffect.getHeight()/2);
					}else if(owner.getDirection()==Entity.LEFT) {	
						gc.drawImage(RenderableHolder.mEffect,pos.x-getWidth()*4,pos.y-getHeight()*2,RenderableHolder.mEffect.getWidth()/2,RenderableHolder.mEffect.getHeight()/2);
					}else if(owner.getDirection()==Entity.BACK) {	
						gc.drawImage(RenderableHolder.mEffect,pos.x-getWidth(),pos.y-getHeight()*2,RenderableHolder.mEffect.getWidth()/2,RenderableHolder.mEffect.getHeight()/2);
					}else if(owner.getDirection()==Entity.FRONT) {	
						gc.drawImage(RenderableHolder.mEffect,pos.x-getWidth()*1.5,pos.y-getHeight()/2,RenderableHolder.mEffect.getWidth()/2,RenderableHolder.mEffect.getHeight()/2);
					}
					
				}
			}

			@Override
			public double getWidth() {
				return ((owner.getDirection() % 3) == SceneManeger.Y_AXIS) ? attackRange.y * 0.7
						: attackRange.x;
			}

			@Override
			public double getHeight() {
				return ((owner.getDirection() % 3) == SceneManeger.Y_AXIS) ? attackRange.x * 1.5
						: attackRange.y * 0.6;
			}
			
			@Override
			public boolean isCollide(GameObject other, double x, double y) {
				boolean isMagicHit=false;
				if(GameLogic.hero.getCurrentMp()>5) {
										
					GameLogic.hero.useMp();
				}
				return super.isCollide(other, x, y)||isMagicHit;
			}
		};
	}

	@Override
	public void update(int direction, double x, double y) {
		super.update(direction, x, y);
		if (direction == Entity.RIGHT) {
			this.attackObj.setX(x + owner.getWidth() * 2 / 3);
			this.attackObj.setY(y + owner.getHeight() / 3);
		} else if (direction == Entity.LEFT) {
			this.attackObj.setX(x + owner.getWidth() / 3 - attackRange.x);
			this.attackObj.setY(y + owner.getHeight() / 3);
		} else if (direction == Entity.BACK) {
			this.attackObj.setX(x + owner.getWidth() / 6);
			this.attackObj.setY(y - attackRange.y + owner.getHeight() * 5 / 6);
		} else if (direction == Entity.FRONT) {
			this.attackObj.setX(x + owner.getWidth() / 6);
			this.attackObj.setY(y + owner.getHeight() * 2 / 3);
		}
	}

}
