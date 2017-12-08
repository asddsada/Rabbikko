package model.monster;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import Main.DungeonMain;
import Main.Main;
import javafx.scene.input.KeyCode;
import logic.GameLogic;
import model.GameObject;
import model.attribute.Agility;
import model.attribute.Attribute;
import model.attribute.Intelligence;
import model.attribute.Strength;
import model.entity.DungeonableEntity;
import model.entity.Entity;
import model.entity.Hero;
import model.field.Dungeon;
import model.items.Inventory;
import model.items.Weapons;
import sharedObj.RenderableHolder;
import utility.InputUtility;
import utility.RandomUtility;

public class MonsterDen {
	// method to generate monster
	// call random to generate monster here
	private static Thread monsterThread;
	private int monsterCount;
	private int maxMonster;

	public MonsterDen() {
		monsterCount = 0;
		maxMonster = 0;
		monsterThread = new Thread((new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(RandomUtility.randomTime(RenderableHolder.getInstance().size()));
					} catch (InterruptedException e) {
						System.out.println("monsterThread has been interrupted.");
						break;
					}
					if (Main.isGameRunning && RenderableHolder.getInstance().size() < 30 && GameLogic.hero.isAlive()) {
						if (monsterCount < maxMonster && (Dungeon.getLvlChangetimer() == 0)) {
							try {
								addMonster();
							} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
									| InvocationTargetException | SecurityException e) {
								System.out.println("cannot add monster");
							}
						} else if (GameLogic.dungeon.isLevelClear() && monsterCount >= maxMonster) {
							maxMonster = RandomUtility.randomByLevel(GameLogic.dungeon.getLvl());
							maxMonster = maxMonster > 50 ? 50 : maxMonster;
							monsterCount = 1;
						}
					}

				}
			}
		}));
		monsterThread.start();
	}

	public static void stop() {
		monsterThread.interrupt();
	}

	public boolean isGenerate() {
		return monsterCount < maxMonster;
	}

	public void restart() {
		maxMonster = 0;
		monsterCount = 0;
	}

	public void update() {
		// admin key
		if (InputUtility.isKeyPressed(KeyCode.P)) { // add monster
			try {
				addMonster();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | SecurityException e) {
				e.printStackTrace();
			}
		} else if (InputUtility.isKeyPressed(KeyCode.O)) { // restore hero hp
			GameLogic.hero.restoreHp();
		} else if (InputUtility.isKeyPressed(KeyCode.I)) { // clear dungeon floor
			for (DungeonableEntity<Attribute> e : Dungeon.getEntitiesHolder())
				if (!(e instanceof Hero))
					Dungeon.destroyEntities(e);
		} else if (InputUtility.isKeyPressed(KeyCode.U)) { // hero assassinate
			GameLogic.hero.healHp(-1000);
			GameLogic.hero.setAlive(false);
		} else if (InputUtility.isKeyPressed(KeyCode.L)) { // skip level
			try {
				addMonster();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | SecurityException e) {
				e.printStackTrace();
			}
			this.monsterCount = maxMonster;
			Dungeon.getEntitiesHolder().stream().filter(i -> i instanceof Monster).map(i -> (Monster) i)
					.forEach(GameObject::destroyed);
			Dungeon.getEntitiesHolder().stream().filter(i -> i instanceof Monster).map(i -> (Monster) i)
					.forEach(Dungeon::destroyEntities);
		}else if(InputUtility.isKeyPressed(KeyCode.K)) { //instance buy staff
			((Weapons) (Inventory.getBag()[4])).setWeapon(); 
		}else if(InputUtility.isKeyPressed(KeyCode.J)) { //instance buy bow
			((Weapons) (Inventory.getBag()[3])).setWeapon(); 
		}else if(InputUtility.isKeyPressed(KeyCode.H)) { //instance buy sword
			((Weapons) (Inventory.getBag()[2])).setWeapon(); 
		
		}else if(InputUtility.isKeyPressed(KeyCode.M)) { //money hag
			GameLogic.hero.earnMoney(999999);
		}
	}

	private void addMonster() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SecurityException {
		Dungeon.addEntities(new Monster(280 + 150, 100, RenderableHolder.monsterImage02, 0, 1, 50, 5, 50, 100,
				20, new Strength()));
		monsterCount++;
	}
}
