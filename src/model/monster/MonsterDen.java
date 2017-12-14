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
import utility.Constant;
import utility.InputUtility;
import utility.Pair;
import utility.RandomUtility;
import utility.ResourceLoader;

public class MonsterDen {
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
					if (Main.isGameRunning && RenderableHolder.getInstance().size() < Constant.MAX_NUMBER_RENDERABLE_HOLD && GameLogic.hero.isAlive()) {
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
					.forEach(m -> m.setAlive(false));
			Dungeon.getEntitiesHolder().stream().filter(i -> i instanceof Monster).map(i -> (Monster) i)
					.forEach(Dungeon::destroyEntities);
		} else if (InputUtility.isKeyPressed(KeyCode.K)) { // instance buy staff
			((Weapons) (Inventory.getBag()[4])).setWeapon();
		} else if (InputUtility.isKeyPressed(KeyCode.J)) { // instance buy bow
			((Weapons) (Inventory.getBag()[3])).setWeapon();
		} else if (InputUtility.isKeyPressed(KeyCode.H)) { // instance buy sword
			((Weapons) (Inventory.getBag()[2])).setWeapon();

		} else if (InputUtility.isKeyPressed(KeyCode.M)) { // money hag
			GameLogic.hero.earnMoney(999999);
		}
	}

	private Image monsterImg(int i) {
		if (i < 1)
			i = 1;
		else if (i > 5)
			i = 5;
		return ResourceLoader.monsterImage[i - 1];
	}

	public void setDunLvl(int dunLvl) {
		this.dunLvl = dunLvl;
	}

	public void genMonster(int img, int row, int col, int size, int time) {
		if (time <= 0 || time > 4)
			time = 1;
		if(img<1 || img>5) return;
		if(row<0||row>1) return;
		if(col<0||col>3) return;
		int mass = (int) (Math.pow((float) ((4 - col) / (row + 1)), 2) * 27 + img * 23);
		int hp = (int) (Math.pow(11.0, (3 - img % 3)) + ((col % 2 + 1) * 23) / (row + 1)) + (size - 1) * 221;
		int idle = (int) Math.max(((img % 3 + 1) * (30 - (dunLvl / 100.0))) + (mass / 100.0), 70);
		int speed = (int) (9 - ((4 - col) / (row + 1)) - (mass / 2000.0));
		Attribute atr;
		if (row == 0) {
			atr = col % 2 == 0 ? new Agility() : new Strength();
		} else {
			atr = col > 1 ? new Intelligence() : new Strength();
		}
		for (int i = 0; i < time; i++) {
			Dungeon.addEntities(new Monster(monsterImg(img), row, col, speed, mass, hp * ((dunLvl / 20) + 1),
					Math.max((int) ((((2 - img % 3) / 3.0 * 300.0) + 13 * (col * img) + 6 * (row) + hp / 1377.0 * 400) / 2),15), atr,
					idle, (1 + img % 3), (3 - img % 3) * 300, speed * (3 - img % 3) * 40,
					(int) ((hp / 49.0 * dunLvl) + ( (9 - ((4 - col) / (row + 1)) - (mass / 2000.0)))*3+ (img-1)*50), size));
			monsterCount += size;
		}
	}

	public void genByLvl(int img, int rand) {
		if (rand < 0)
			return;
		for (int i = 0; i < rand; i++) {
			int col = RandomUtility.random() % 4;
			genMonster(img, (dunLvl % 10) > 5 ? 0 : col % 2, col, 1, i);
		}
	}

	private void addMonster() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SecurityException {
		if (RenderableHolder.getInstance().size() >= 35)
			return;
		int rand = RandomUtility.randomByLevel(dunLvl) % 3 + 1;
		if ((dunLvl % 7 == 0 && dunLvl != 0 && monsterCount > maxMonster - 10)
				|| (dunLvl > 25 && rand + (RandomUtility.random())%4 == 1)) { // gen boss
			if (dunLvl > 35) {
				if (rand % 3 == 1) {
					genMonster(3, 0, (rand % 2), 2, 1);
					genMonster(rand, 0, rand, 2, 1);
					genMonster(rand, 1, rand, 2, 1);
				} else {
					genMonster(3, 1, 2+(rand % 2), 2, (rand % 2)+2);
				}
				genMonster(4, 0, 2+rand%2, 1, 2);
				genMonster(4, 0,rand, 1, 2);
				genMonster(4, 1, 0, 1, 1);
				genMonster(5, 1, rand%2, 1, 1);
			} else if (dunLvl > 30) {
				if (rand % 3 == 0) {
					genMonster(1, 1, rand, 2, 3);
				} else if (rand % 3 == 1) {
					genMonster(3, 0, (rand % 2), 1, 1);
					genMonster(1, 0, rand, 2, 2);
				} else {
					genMonster(3, 0, 0, 1, 1);
					genMonster(3, 0, 1, 1, 1);
				}
			} else if (dunLvl > 20) {
				if (rand % 2 == 0)
					genMonster(1, 1, rand, 2, 2);
				else {
					genMonster(3, 0, (rand % 2), 1, 1);
					genMonster(2, 0, 1 + (rand % 2) * 2, 2, 1);
				}
			} else if (dunLvl > 10) {
				genMonster(3, 0, (rand % 2), 1, 1);
			} else if (dunLvl > 10) {
				if (rand % 2 == 0)
					genMonster(2, 0, 1 + (rand % 2) * 2, 2, 2);
				else
					genMonster(2, 1, 4, 2, 1);
			} else {
				genMonster(2, 0, 1, 2, 1);
			}
			monsterCount += 10;
			return;
		}
		if (dunLvl > 35) {
			genByLvl(3, 1);
			genByLvl(2, rand - 1);
		} else if (dunLvl > 30) {
			genByLvl(3, rand - 3);
			genByLvl(1, 2);
			genByLvl(2, 1);
		} else if (dunLvl > 20) {
			genByLvl(1, rand - 1);
			genByLvl(2, 1);
		} else if (dunLvl > 10) {
			genByLvl(1, rand % 2);
			genByLvl(2, rand - 1);
		} else if (dunLvl > 10) {
			genByLvl(1, rand - 1);
			genByLvl(2, 1);
		} else {
			genByLvl(2, rand);
		}
		if(dunLvl%13==0 &&(RandomUtility.random())%73 == 1) {
			genMonster(5, 1, rand%2, 1, 1);
		}
	}
}
