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
import model.field.Dungeon;
import model.items.Inventory;
import model.items.Weapons;
import utility.ResourceLoader;
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
		attackSpeed = 0.6;
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
//					if (owner.getDirection() == Entity.RIGHT) {
//						gc.drawImage(Loader.mEffect, pos.x - getWidth() / 4, pos.y - getHeight() * 2,
//								Loader.mEffect.getWidth() / 2, Loader.mEffect.getHeight() / 2);
//					} else if (owner.getDirection() == Entity.LEFT) {
//						gc.drawImage(Loader.mEffect, pos.x - getWidth() * 3, pos.y - getHeight() * 2,
//								Loader.mEffect.getWidth() / 2, Loader.mEffect.getHeight() / 2);
//					} else if (owner.getDirection() == Entity.BACK) {
//						gc.drawImage(Loader.mEffect, pos.x - getWidth(), pos.y - getHeight() * 2,
//								Loader.mEffect.getWidth() / 2, Loader.mEffect.getHeight() / 2);
//					} else if (owner.getDirection() == Entity.FRONT) {
//						gc.drawImage(Loader.mEffect, pos.x - getWidth() * 1.5, pos.y - getHeight() / 2,
//								Loader.mEffect.getWidth() / 2, Loader.mEffect.getHeight() / 2);
//					}
				}
				for (Entry<Pair, Pair> e : magicTime.entrySet()) {
					double a = 0, b = 0;
					if (e.getValue().x == Entity.FRONT) {
						a = e.getKey().x + (owner.getWidth() - magicW) ;
						b = e.getKey().y + e.getValue().y + owner.getHeight() / 3;
					} else if (e.getValue().x == Entity.BACK) {
						a = e.getKey().x + (owner.getWidth() - magicW) ;
						b = e.getKey().y - e.getValue().y ;
					} else if (e.getValue().x == Entity.LEFT) {
						a = e.getKey().x - e.getValue().y-magicW/2;
						b = e.getKey().y ;
					} else if (e.getValue().x == Entity.RIGHT) {
						a = e.getKey().x + e.getValue().y;
						b = e.getKey().y ;
					}
//					gc.fillRect(a, b, magicW, magicH);
					gc.drawImage(ResourceLoader.mEffect, a- getWidth() *5/6, b - getHeight()/2 -owner.getHeight(),
							ResourceLoader.mEffect.getWidth() / 2, ResourceLoader.mEffect.getHeight() / 2);
				}
			}

			@Override
			public double getWidth() {
				return ((owner.getDirection() % 3) == SceneManeger.Y_AXIS) ? attackRange.y * 0.7 : attackRange.x;
			}

			@Override
			public double getHeight() {
				return ((owner.getDirection() % 3) == SceneManeger.Y_AXIS) ? attackRange.x * 1.5 : attackRange.y * 0.6;
			}

			@Override
			public boolean isCollide(GameObject other, double x, double y) {
				boolean isMagicHit = false;
				if (GameLogic.hero.getCurrentMp() > 5) {
					for (Entry<Pair, Pair> e : magicTime.entrySet()) {
						double a = 0, b = 0;
						if (e.getValue().x == Entity.FRONT) {
							a = e.getKey().x + (owner.getWidth() - magicW) ;
							b = e.getKey().y + e.getValue().y + owner.getHeight() / 3;
						} else if (e.getValue().x == Entity.BACK) {
							a = e.getKey().x + (owner.getWidth() - magicW) ;
							b = e.getKey().y - e.getValue().y ;
						} else if (e.getValue().x == Entity.LEFT) {
							a = e.getKey().x - e.getValue().y-magicW/2;
							b = e.getKey().y ;
						} else if (e.getValue().x == Entity.RIGHT) {
							a = e.getKey().x + e.getValue().y;
							b = e.getKey().y ;
						}
						if ((((a - other.getWidth()) <= other.getX())
								&& (other.getX() <= (a + magicW + other.getWidth())))
								&& (((b - other.getHeight()) <= other.getY())
										&& (other.getY() <= (b + other.getHeight() / 2 + magicH)))
								&& (((a - other.getWidth()) <= (other.getX() + other.getWidth()))
										&& ((other.getX() + other.getWidth()) <= (a + magicW + other.getWidth())))
								&& (((b - other.getHeight()) <= (other.getY() + other.getHeight())) && ((other.getY()
										+ other.getHeight()) <= (b + other.getHeight() / 2 + magicH))))
							isMagicHit = true;						
					}
				}
				if(owner.getAtkType().getAttackTime() ==0) return (super.isCollide(other, x, y)|| isMagicHit);
				return isMagicHit;
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
		if (owner.getAtkType().getAttackTime() != 0) {
			ArrayList<DungeonableEntity<Attribute>> inArea = Dungeon.getEntityInArea(owner.getAtkType().getAttackObj(),
					owner.getAtkType().getAttackObj().getX(), owner.getAtkType().getAttackObj().getY());
			if (!(inArea == null || inArea.size() <= 1))
				for (DungeonableEntity<Attribute> other : inArea) {
					if (other.hashCode() != owner.getAtkType().hashCode() && owner.getRace() != other.getRace()) {
						owner.getAtkType().use();
						owner.getAtkType().attack(owner, other);
					}
				}
		}
		if (GameLogic.hero.getCurrentMp() > 5) {
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
		if (magicTime.size() <= 12 )
			magicTime.put(new Pair(attackObj.getX(), attackObj.getY()), new Pair(owner.getDirection(), 0));

		GameLogic.hero.useMp(5);
	}

}
