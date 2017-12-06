package model.attribute;

import javafx.scene.canvas.GraphicsContext;
import model.GameObject;
import model.entity.DungeonableEntity;
import model.entity.Hero;
import model.items.Weapons;
import utility.Pair;

public class Strength extends Attribute {
	public Strength() {
		heroWeapon= (Weapons) Hero.inventory.getBag()[0];
		attackMultiply=2;
		attackRange=new Pair(getHeroWeapon().getWidth()+20, getHeroWeapon().getHeight()+10);
		attackSpped=0.5;
		hpMultiply=1.2;
		hpRegen=5;
		mpRegen=1;
		attackObj = new GameObject(heroWeapon.getX()+20, heroWeapon.getY(), 500) {
			
			@Override
			public void draw(GraphicsContext gc) {
//				attack effect?
				gc.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
				if(heroWeapon.getAttackTime()>0)
					gc.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
			}
			
			@Override
			public double getWidth() {
				return attackRange.x;
			}
			
			@Override
			public double getHeight() {
				return attackRange.y;
			}
		};		
	}

	@Override
	public <T1 extends Attribute, T2 extends Attribute> void attack(DungeonableEntity<T1> dungeonableEntity,
			DungeonableEntity<T2> other) {
		System.out.println("attack");
	}

}
