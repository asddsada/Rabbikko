package model.monster;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import Main.DungeonMain;
import Main.Main;
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

						System.out.println(maxMonster);
					} catch (InterruptedException e) {
						System.out.println("monsterThread has been interrupted.");
						break;
					}
					if (Main.isGameRunning && GameLogic.hero.isAlive() && RenderableHolder.getInstance().size() < 40) {
						if (monsterCount < maxMonster && (Dungeon.getLvlChangetimer() ==0 ) ) {
							try {
								addMonster();
							} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
									| InvocationTargetException | SecurityException e) {
								e.printStackTrace();
							}
						} else if (GameLogic.dungeon.isLevelClear() && monsterCount == maxMonster) {
							maxMonster = RandomUtility.randomByLevel(GameLogic.dungeon.getLvl());
							maxMonster = maxMonster>30?30:maxMonster;
							monsterCount = 1;
							try {
								addMonster();
							} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
									| InvocationTargetException | SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
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
		return monsterCount!= maxMonster;
	}

	public void update() {
		// admin key
		if (InputUtility.isKeyPressed(KeyCode.P)) {
			try {
				addMonster();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (InputUtility.isKeyPressed(KeyCode.O)) {
			GameLogic.hero.restoreHp();
		} else if (InputUtility.isKeyPressed(KeyCode.I)) {
			for (DungeonableEntity<Attribute> e : GameLogic.dungeon.getEntitiesHolder())
				if (!(e instanceof Hero))
					Dungeon.destroyEntities(e);
		} else if (InputUtility.isKeyPressed(KeyCode.U)) {
			GameLogic.hero.healHp(-1000);
			GameLogic.hero.setAlive(false);
		}
	}

	private void addMonster() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SecurityException {
		Dungeon.addEntities(new Monster(280 + 150, 100, RenderableHolder.monsterImage02, 0, 1, Entity.FRONT, 5, 100, 10,
				20, new Strength()));
		monsterCount++;
	}
}
