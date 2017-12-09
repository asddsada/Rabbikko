package model.monster;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import Main.DungeonMain;
import Main.Main;
import javafx.scene.image.Image;
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
import sharedObj.IRenderable;
import sharedObj.RenderableHolder;
import utility.InputUtility;
import utility.Pair;
import utility.RandomUtility;
import utility.ResourceLoader;

public class MonsterDen {
	// method to generate monster
	// call random to generate monster here
	private static Thread monsterThread;
	private int monsterCount;
	private int maxMonster;
	private int dunLvl;

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
					if (Main.isGameRunning && RenderableHolder.getInstance().size() < 35 && GameLogic.hero.isAlive()) {
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
		} else if (InputUtility.isKeyPressed(KeyCode.Y)) { // clear dungeon floor
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
					.forEach(m->m.setAlive(false));
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
	
	private Image monsterImg(int i) {
		if(i<1) i=1;
		else if(i>5) i=5;
		return ResourceLoader.monsterImage[i-1];
	}

	public void setDunLvl(int dunLvl) {
		this.dunLvl = dunLvl;
	}
	
	public void genMonster(int img,int row,int col,int mass,int size) {
		int hp = (int) (Math.pow(11.0,(3-img%3))+((col%2+1)*23)/(row+1));
		int idle = (int) Math.max( ((img%3+1)*(30-(dunLvl/100.0)))+(mass/100.0) ,70);
		int speed = (int) (9-((4-col)/(row+1))-(mass/2000.0));
		Attribute atr;
		if(row==0) {
			atr=col%2==0?new Agility():new Strength();
		}else {
			atr=col>1?new Intelligence():new Strength();
		}
		Dungeon.addEntities(new Monster(monsterImg(img), row, col, 
				speed, mass, 
				hp*((dunLvl/20)+1), (int) ((((2-img%3)/3.0*300.0)+13*(col*img)+6*(row)+hp/3775.0*350)/2), atr, 
				idle, (1+img%3), (3-img%3)*300, 
				speed*(3-img%3)*40, (int)(hp/50*dunLvl+speed), size));
	}

	private void addMonster() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SecurityException {
		if(RenderableHolder.getInstance().size()>=35) return;
		
		genMonster(1, 0, 2, 600	, 1);
//		Dungeon.addEntities(new Monster(monsterImg(2), 0, 1,
//				4, 70, 100, 30, new Strength(),
//				70, 2, 50, 30, 15, 1));
		monsterCount++;
	}
}
