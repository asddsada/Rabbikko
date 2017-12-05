package model.monster;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import model.attribute.Strength;
import model.entity.Entity;
import sharedObj.RenderableHolder;

public class MonsterDen {
//	method to generate monster
//	call random to generate monster here
	private static final Set<Monster> MONSTER_HOLDER = new HashSet<>();
	private static Thread monsterThread;
	private static Random rand;
	
	public MonsterDen() {
		rand = new Random();
		
		addMonster(new Monster(50, 50, RenderableHolder.monsterImage02, 0, 1, Entity.FRONT, 5, 1000,new Strength() ));
		
		monsterThread = new Thread(() ->  {
			
		});
	}
	
	public void addMonster(Monster monster) {
		RenderableHolder.getInstance().add(monster);
		MONSTER_HOLDER.add(monster);
	}
}
