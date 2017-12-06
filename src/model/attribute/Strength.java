package model.attribute;

import javafx.scene.canvas.GraphicsContext;
import model.GameObject;
import model.entity.DungeonableEntity;
import model.items.Sword;

public class Strength extends Attribute {
	public Strength() {
		heroWeapon= new Sword();
		attackObj = new GameObject(attackRange, attackRange, attackSpped) {
			
			@Override
			public void draw(GraphicsContext gc) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public double getWidth() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public double getHeight() {
				// TODO Auto-generated method stub
				return 0;
			}
		};
	}

	@Override
	public void attack(DungeonableEntity<Attribute> other) {
		System.out.println("atack!");
	}
}
