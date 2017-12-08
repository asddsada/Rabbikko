package model.field;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import logic.GameLogic;
import model.GameObject;
import model.attribute.Attribute;
import model.attribute.Intelligence;
import model.entity.DungeonableEntity;
import model.entity.Entity;
import model.entity.Hero;
import model.monster.Monster;
import model.monster.MonsterDen;
import sharedObj.RenderableHolder;
import utility.Pair;
import view.SceneManeger;

public class Dungeon extends Field {
	private static final Set<DungeonableEntity<Attribute>> entities_holder = new HashSet<>();
	private static final Set<DungeonableEntity<Attribute>> graveyard = new HashSet<>();
	public static MonsterDen monsterDen;
	private int lvl;
	private static int lvlChangetimer;
	private static final int CHANGE_TIME_MAX = 400;

	public Dungeon() {
		super(RenderableHolder.dungeonBg, SceneManeger.WIDGTH, SceneManeger.HEIGHT - Navigation.NAVIG_HEIGHT,
				new Pair(0, 0));
		this.lvl = 0;
		this.z = -99999;
		monsterDen = new MonsterDen();
		lvlChangetimer=CHANGE_TIME_MAX;
	}

	public boolean isInBoarder(Entity e, double x, double y) {
		return (0 - e.getWidth() / 6 <= x && x <= this.width - e.getWidth() * 5 / 6)
				&& (0 <= y && y <= this.height - e.getHeight());
	}

	@Override
	public void draw(GraphicsContext gc) {
		super.draw(gc);
		if (lvlChangetimer != 0) {
			gc.setFill(Color.BLACK);
			gc.fillRect(0, height / 2 - 50, width, 100);
			gc.setFill(Color.WHITE);
			gc.fillText("Level "+lvl+" "+Double.toString(lvlChangetimer / 100), width /2.25, height / 2);
		}
	}

	public static void addEntities(DungeonableEntity<Attribute> e) {
		RenderableHolder.getInstance().add(e);
		getEntitiesHolder().add(e);
		if (lvlChangetimer != 0)
			lvlChangetimer = 0;
	}

	public static <T extends Attribute> void destroyEntities(DungeonableEntity<T> e) {
			e.setVisible(false);
		graveyard.add((DungeonableEntity<Attribute>) e);
	}

	public void update() {
		graveyard.clear();
		monsterDen.update();
		for (DungeonableEntity<Attribute> e : entities_holder) {
			e.update();
		}
		for (DungeonableEntity<Attribute> e : graveyard) {
			if (e instanceof Hero) {
				SceneManeger.dungeonScene.toDialog(4); // dead
			}else entities_holder.remove(e);
		}
		if (isLevelClear())
			upLevel();
		if(lvlChangetimer>0) lvlChangetimer--;
	}
	
	public boolean isLevelClear() {
		return (entities_holder.size() == 1 && entities_holder.contains(GameLogic.hero) && !monsterDen.isGenerate());
	}

	private void upLevel() {
		if (lvlChangetimer == 0) {
			lvl++;
			lvlChangetimer = CHANGE_TIME_MAX;
		}
		
	}

	public void restart() {
		lvl = 0;
		lvlChangetimer=0;
		monsterDen.restart();
		RenderableHolder.getInstance().clear();
		entities_holder.clear();
		graveyard.clear();
	}

	public static Set<DungeonableEntity<Attribute>> getEntitiesHolder() {
		return entities_holder;
	}

	public static ArrayList<DungeonableEntity<Attribute>> getEntityInArea(GameObject object, double x, double y) {
		ArrayList<DungeonableEntity<Attribute>> result = new ArrayList<>();
		for (DungeonableEntity<Attribute> e : entities_holder) {
			if (e.hashCode() != object.hashCode() && object.isCollide(e, x, y)) {
				result.add(e);
				// System.out.println(e.getClass().getSimpleName());
			}
		}
		return result;
	}

	public int getLvl() {
		return lvl;
	}

	public static int getLvlChangetimer() {
		return lvlChangetimer;
	}

}
