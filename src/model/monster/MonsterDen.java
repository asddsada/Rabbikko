package model.monster;

import java.util.Random;

import javafx.scene.input.KeyCode;
import logic.GameLogic;
import model.attribute.Attribute;
import model.attribute.Intelligence;
import model.attribute.Strength;
import model.entity.DungeonableEntity;
import model.entity.Entity;
import model.entity.Hero;
import model.field.Dungeon;
import sharedObj.RenderableHolder;
import utility.InputUtility;

public class MonsterDen {
//	method to generate monster
//	call random to generate monster here	
	private static Thread monsterThread;
	private static Random rand;
	
	public MonsterDen() {
		rand = new Random();
		
		Dungeon.addEntities(new Monster(360,100, RenderableHolder.monsterImage02, 0, 1, Entity.FRONT, 5,100, 20,20,new Strength() ));
		Dungeon.addEntities(new Monster(440,100, RenderableHolder.monsterImage02, 0, 1, Entity.FRONT, 5,100,  50,25,new Strength() ));
		Dungeon.addEntities(new Monster(280,100, RenderableHolder.monsterImage02, 0, 1, Entity.FRONT, 5,100, 40,20,new Strength() ));
		Dungeon.addEntities(new Monster(200,100, RenderableHolder.monsterImage02, 0, 1, Entity.FRONT, 5,100, 40,28,new Strength() ));
		Dungeon.addEntities(new Monster(920/2+200,100, RenderableHolder.monsterImage02, 0, 1, Entity.FRONT, 5,200, 50,12,new Strength() ));
		Dungeon.addEntities(new Monster(200,100+470/2, RenderableHolder.monsterImage02, 0, 1, Entity.FRONT, 5,100,  50,12,new Strength() ));
		//Dungeon.addEntities(new Monster<Strength>(920/2+100,100+470/2, RenderableHolder.monsterImage02, 0, 1, Entity.FRONT, 5,100, 1000,20,new Strength() ));
//		
		monsterThread = new Thread(() ->  {
			
		});
	}
	
	

	public void update() {
		//admin key
		if(InputUtility.isKeyPressed(KeyCode.P)) {
			addMonster();
		}else if(InputUtility.isKeyPressed(KeyCode.O)) {
			GameLogic.hero.restoreHp();
		}else if(InputUtility.isKeyPressed(KeyCode.I)) {
			for(DungeonableEntity<Attribute> e: GameLogic.dungeon.getEntitiesHolder())
				if(!(e instanceof Hero) )Dungeon.destroyEntities(e);
		}else if(InputUtility.isKeyPressed(KeyCode.U)) {
			GameLogic.hero.healHp(-1000);
			GameLogic.hero.setAlive(false);
		}
		//
		
		if(Dungeon.getLvlChangetimer()==0) {
			
		}
		
		
	}
	
	private void addMonster() {
		Dungeon.addEntities(new Monster(280+150,100, RenderableHolder.monsterImage02, 0, 1, Entity.FRONT, 5,100, 1000,20,new Strength() ));
	}
}
