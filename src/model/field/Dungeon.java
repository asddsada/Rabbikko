package model.field;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
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
	private static Set<DungeonableEntity<Attribute>> ENTITIES_HOLDER = new HashSet<>();
	public static MonsterDen monsterDen;
	private int lvl;
	

	public Dungeon() {
		super(RenderableHolder.dungeonBg, SceneManeger.WIDGTH, SceneManeger.HEIGHT - Navigation.NAVIG_HEIGHT,
				new Pair(0, 0));
		this.lvl = 1;
		this.z = -99999;
		monsterDen = new MonsterDen();	
		addEntities(new Monster<Intelligence>(200,100, RenderableHolder.monsterImage02, 0, 1, Entity.FRONT, 5,100, 1000,20,new Intelligence() ));
	}

	public boolean isInBoarder(Entity e, double x, double y) {
		return (0 <= x && x <= this.width - e.getWidth() / 2 - 8) && (0 <= y && y <= this.height - e.getHeight());
	}
	
	public static void addEntities(DungeonableEntity<Attribute> e) {
		RenderableHolder.getInstance().add(e);
		getENTITIES_HOLDER().add(e);
	}

	public void update() {
		monsterDen.update();
		for(DungeonableEntity<Attribute> e:getENTITIES_HOLDER()) {
			e.update();
		}
	}

	public static Set<DungeonableEntity<Attribute>> getENTITIES_HOLDER() {
		return ENTITIES_HOLDER;
	}
	
	public static ArrayList<DungeonableEntity<Attribute>> getEntityInArea(GameObject object,double x,double y){
		ArrayList<DungeonableEntity<Attribute>> result = new ArrayList<>();
		for(DungeonableEntity<Attribute> e:ENTITIES_HOLDER) {
			if(e.hashCode()!=object.hashCode() && object.isCollide(e, x, y)){
				result.add(e);
//				System.out.println(e.getClass().getSimpleName());
			}			
		}
		return result;
	}
}
