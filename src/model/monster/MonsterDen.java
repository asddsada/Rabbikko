package model.monster;

import java.util.Random;
import logic.GameLogic;
import model.attribute.Intelligence;
import model.attribute.Strength;
import model.entity.Entity;
import model.field.Dungeon;
import sharedObj.RenderableHolder;

public class MonsterDen {
//	method to generate monster
//	call random to generate monster here	
	private static Thread monsterThread;
	private static Random rand;
	
	public MonsterDen() {
		rand = new Random();
		
		Dungeon.addEntities(new Monster<Intelligence>(200,100, RenderableHolder.monsterImage02, 0, 1, Entity.FRONT, 5,100, 1000,20,new Intelligence() ));
		Dungeon.addEntities(new Monster<Strength>(920/2+200,100, RenderableHolder.monsterImage02, 0, 1, Entity.FRONT, 5,100,1000,20,new Strength() ));
		Dungeon.addEntities(new Monster<Strength>(200,100+470/2, RenderableHolder.monsterImage02, 0, 1, Entity.FRONT, 5,100, 1000,20,new Strength() ));
		Dungeon.addEntities(new Monster<Strength>(920/2+100,100+470/2, RenderableHolder.monsterImage02, 0, 1, Entity.FRONT, 5,100, 1000,20,new Strength() ));
//		
		monsterThread = new Thread(() ->  {
			
		});
	}
	
	

	public void update() {
		
		
	}
}
