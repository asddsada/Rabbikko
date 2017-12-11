package model.attribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javafx.scene.canvas.GraphicsContext;
import logic.GameLogic;
import model.GameObject;
import model.entity.DungeonableEntity;
import model.entity.Entity;
import model.entity.Hero;
import model.field.Dungeon;
import model.items.Inventory;
import model.items.Weapons;
import utility.ResourceLoader;
import utility.Constant;
import utility.Pair;
import view.SceneManeger;

public class Intelligence extends Attribute {
	private Map<Pair, Pair> magicTime;
	private double maxMagicTime;
	private List<Pair> delTemp;
	private double magicW;
	private double magicH;

	public Intelligence() {
		super();
		heroWeapon = (Weapons) Inventory.getBag()[4];
		attackMultiply = 1.5;
		attackRange = new Pair(getHeroWeapon().getWidth() * 1.2, getHeroWeapon().getHeight());
		attackSpeed = 0.5;
		hpMultiply = 1;
		hpRegen = 1;
		mpRegen = 5;
		maxMagicTime = getHeroWeapon().getWidth() * 5;
		magicTime = new HashMap<>();
		delTemp = new LinkedList<>();
		magicW = attackRange.x*2;
		magicH = attackRange.y*0.7;
		img = ResourceLoader.intelligence;
		attackObj = new GameObject(heroWeapon.getX() + 20, heroWeapon.getY(), 500) {

			@Override
			public void draw(GraphicsContext gc) {
				if (owner.getAtkType().getAttackTime() > 0 ) {
//					gc.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
//					if (owner.getDirection() ==  Constant.RIGHT) {
//						gc.drawImage(Loader.mEffect, pos.x - getWidth() / 4, pos.y - getHeight() * 2,
//								Loader.mEffect.getWidth() / 2, Loader.mEffect.getHeight() / 2);
//					} else if (owner.getDirection() ==  Constant.LEFT) {
//						gc.drawImage(Loader.mEffect, pos.x - getWidth() * 3, pos.y - getHeight() * 2,
//								Loader.mEffect.getWidth() / 2, Loader.mEffect.getHeight() / 2);
//					} else if (owner.getDirection() ==  Constant.BACK) {
//						gc.drawImage(Loader.mEffect, pos.x - getWidth(), pos.y - getHeight() * 2,
//								Loader.mEffect.getWidth() / 2, Loader.mEffect.getHeight() / 2);
//					} else if (owner.getDirection() ==  Constant.FRONT) {
//						gc.drawImage(Loader.mEffect, pos.x - getWidth() * 1.5, pos.y - getHeight() / 2,
//								Loader.mEffect.getWidth() / 2, Loader.mEffect.getHeight() / 2);
//					}
				}
				for (Entry<Pair, Pair> e : magicTime.entrySet()) {
					double a = 0, b = 0;
					if (e.getValue().x ==  Constant.ENTITY_FRONT) {
						a = e.getKey().x + (owner.getWidth() - magicW) ;
						b = e.getKey().y + e.getValue().y + owner.getHeight() / 3;
					} else if (e.getValue().x ==  Constant.ENTITY_BACK) {
						a = e.getKey().x + (owner.getWidth() - magicW) ;
						b = e.getKey().y - e.getValue().y ;
					} else if (e.getValue().x ==  Constant.ENTITY_LEFT) {
						a = e.getKey().x - e.getValue().y-magicW/2;
						b = e.getKey().y ;
					} else if (e.getValue().x ==  Constant.ENTITY_RIGHT) {
						a = e.getKey().x + e.getValue().y;
						b = e.getKey().y ;
					}
//					gc.fillRect(a, b, magicW, magicH);
					gc.drawImage(ResourceLoader.mEffect, a- getWidth() *0.5, b - getHeight()/2 -owner.getHeight()*0.5,
							ResourceLoader.mEffect.getWidth() / 2.5, ResourceLoader.mEffect.getHeight() / 2.5);
				}
			}

			@Override
			public double getWidth() {
				return ((owner.getDirection() % 3) == Constant.SCENE_Y_AXIS) ? attackRange.y * 0.7 : attackRange.x;
			}

			@Override
			public double getHeight() {
				return ((owner.getDirection() % 3) == Constant.SCENE_Y_AXIS) ? attackRange.x * 1.5 : attackRange.y * 0.6;
			}

			@Override
			public boolean isCollide(GameObject other, double x, double y) {
				boolean isMagicHit = false;
				if (GameLogic.hero.getCurrentMp() > 5) {
					for (Entry<Pair, Pair> e : magicTime.entrySet()) {
						double a = 0, b = 0;
						if (e.getValue().x ==  Constant.ENTITY_FRONT) {
							a = e.getKey().x + (owner.getWidth() - magicW) ;
							b = e.getKey().y + e.getValue().y + owner.getHeight() / 3;
						} else if (e.getValue().x ==  Constant.ENTITY_BACK) {
							a = e.getKey().x + (owner.getWidth() - magicW) ;
							b = e.getKey().y - e.getValue().y ;
						} else if (e.getValue().x ==  Constant.ENTITY_LEFT) {
							a = e.getKey().x - e.getValue().y-magicW/2;
							b = e.getKey().y ;
						} else if (e.getValue().x ==  Constant.ENTITY_RIGHT) {
							a = e.getKey().x + e.getValue().y;
							b = e.getKey().y ;
						}
						if ((((a - other.getWidth()) <= other.getX())
								&& (other.getX() <= (a + magicW + other.getWidth())))
								&& (((b - other.getHeight()) <= other.getY())
										&& (other.getY() <= (b + other.getHeight() + magicH)))
								&& ((((a - other.getWidth()) <= (other.getX() + other.getWidth()))
										&& ((other.getX() + other.getWidth()) <= (a + magicW + other.getWidth())))
								&& (((b - other.getHeight()) <= (other.getY() + other.getHeight())) && ((other.getY()
										+ other.getHeight()) <= (b + other.getHeight()  + magicH)))))
							isMagicHit = true;						
					}
				}
//				if(owner.getAtkType().getAttackTime() ==0) return (super.isCollide(other, x, y)|| isMagicHit);
				return isMagicHit;
			}

		};
	}

	@Override
	public void update(int direction, double x, double y) {
		super.update(direction, x, y);
		if (direction ==  Constant.ENTITY_RIGHT) {
			this.attackObj.setX(x + owner.getWidth() * 2 / 3);
			this.attackObj.setY(y + owner.getHeight() / 3);
		} else if (direction ==  Constant.ENTITY_LEFT) {
			this.attackObj.setX(x + owner.getWidth() / 3 - attackRange.x);
			this.attackObj.setY(y + owner.getHeight() / 3);
		} else if (direction ==  Constant.ENTITY_BACK) {
			this.attackObj.setX(x + owner.getWidth() / 6);
			this.attackObj.setY(y - attackRange.y + owner.getHeight() * 5 / 6);
		} else if (direction ==  Constant.ENTITY_FRONT) {
			this.attackObj.setX(x + owner.getWidth() / 6);
			this.attackObj.setY(y + owner.getHeight() * 2 / 3);
		}
//		if (owner.getAtkType().getAttackTime() != 0) {
			ArrayList<DungeonableEntity<Attribute>> inArea = Dungeon.getEntityInArea(owner.getAtkType().getAttackObj(),
					owner.getAtkType().getAttackObj().getX(), owner.getAtkType().getAttackObj().getY());
			if (!(inArea == null || inArea.size() <= 1))
				for (DungeonableEntity<Attribute> other : inArea) {
					if (other.getAtkType().getAttackObj().hashCode() != owner.getAtkType().getAttackObj().hashCode() 
							&& owner.getRace() != other.getRace()) {
						owner.getAtkType().attack(owner, other);
					}
				}
//		}
		if (!(owner instanceof Hero)||GameLogic.hero.getCurrentMp() >= 30) {
			for (Entry<Pair, Pair> e : magicTime.entrySet()) {
				e.getValue().y += 2;
				if (e.getValue().y >= maxMagicTime)
					delTemp.add(e.getKey());
			}
			for (Pair e : delTemp) {
				magicTime.remove(e);
			}
			delTemp.clear();
		}
	}
	
	@Override
	public void use() {
		super.use();
		if (magicTime.size() <= 6 )
			magicTime.put(new Pair(attackObj.getX(), attackObj.getY()), new Pair(owner.getDirection(), 0));

		if(owner instanceof Hero) GameLogic.hero.useMp(30);
	}

}
