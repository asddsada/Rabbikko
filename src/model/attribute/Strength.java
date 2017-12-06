package model.attribute;

import javafx.scene.canvas.GraphicsContext;
import model.GameObject;
import model.entity.DungeonableEntity;
import model.entity.Hero;
import model.items.Weapons;

public class Strength extends Attribute {
	public Strength() {
		heroWeapon= (Weapons) Hero.inventory.getBag()[2];
		attackObj = new GameObject(0, 0, 0) {
			
			@Override
			public void draw(GraphicsContext gc) {
//				attack effect?
			}
			
			@Override
			public double getWidth() {
				return 0;
			}
			
			@Override
			public double getHeight() {
				return 0;
			}
		};
	}

	@Override
	public <T1 extends Attribute, T2 extends Attribute> void attack(DungeonableEntity<T1> dungeonableEntity,
			DungeonableEntity<T2> other) {
		System.out.println("attack");
	}

}
