package model.attribute;

import javafx.scene.canvas.GraphicsContext;
import logic.ForceManeger;
import model.GameObject;
import model.entity.DungeonableEntity;
import model.entity.Hero;
import model.items.Weapons;
import utility.Pair;

public class Strength extends Attribute {
	public Strength() {
		heroWeapon = (Weapons) Hero.inventory.getBag()[2];
		attackMultiply = 1.2;
		attackRange = new Pair(getHeroWeapon().getWidth() + 20, getHeroWeapon().getHeight() + 10);
		attackSpeed = 0.5;
		hpMultiply = 1.2;
		hpRegen = 5;
		mpRegen = 1;
		attackObj = new GameObject(heroWeapon.getX() + 20, heroWeapon.getY(), 500) {

			@Override
			public void draw(GraphicsContext gc) {
				// attack effect?
				gc.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
				if (heroWeapon.getAttackTime() > 0)
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
	public <T1 extends Attribute, T2 extends Attribute> void attack(DungeonableEntity<T1> attacker,
			DungeonableEntity<T2> other) {
		other.damage((int) (attacker.getBaseAtk() * attackMultiply),
				ForceManeger.calculateDirection(attacker.getDirection()));
		// System.out.println("Attack! "+other.getClass().getSimpleName()+" hp :
		// "+other.getCurrentHp());
	}

}
