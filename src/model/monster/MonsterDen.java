package model.monster;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import logic.GameLogic;
import model.attribute.Strength;
import model.entity.Entity;
import sharedObj.RenderableHolder;

public class MonsterDen {
//	method to generate monster
//	call random to generate monster here	
	private static Thread monsterThread;
	private static Random rand;
	
	public MonsterDen() {
		rand = new Random();
		
		GameLogic.dungeon.addEntities(new Monster(200,100, RenderableHolder.monsterImage02, 0, 1, Entity.FRONT, 5, 1000,new Strength() ));
//		GameLogic.dungeon.addEntities(new Monster(920/2+200,100, RenderableHolder.monsterImage02, 0, 1, Entity.FRONT, 5, 1000,new Strength() ));
//		GameLogic.dungeon.addEntities(new Monster(200,100+470/2, RenderableHolder.monsterImage02, 0, 1, Entity.FRONT, 5, 1000,new Strength() ));
		GameLogic.dungeon.addEntities(new Monster(920/2+100,200+470/2, RenderableHolder.monsterImage02, 0, 1, Entity.FRONT, 5, 1000,new Strength() ));
		
		monsterThread = new Thread(() ->  {
			
		});
	}
	
	

	public void update() {
		
		
	}
}
