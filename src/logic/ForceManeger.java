package logic;

import com.sun.javafx.scene.traversal.Direction;

import Main.Main;
import model.attribute.Attribute;
import model.entity.DungeonableEntity;
import model.field.Dungeon;
import sharedObj.RenderableHolder;
import utility.Pair;
import view.SceneManeger;

public class ForceManeger {
	private static Thread physicThread = null;
	private static boolean threadState;
	private static final float TIME_DIV = 15;

	public static void initilized() {
		threadState = true;
	}

	public static void startForceRule() {
		// threadState = true;
	}

	public static void pauseForceRule() {
		// threadState = false;
	}

	public static <T extends Attribute> void reactionEffect(DungeonableEntity<T> entity, int axis) {
		double d = entity.getMovespeed()*entity.getMovespeed()/2;
		Pair Ft =totalForce(entity);
		// bug able to push out of the field
		if (axis % 3 == SceneManeger.Y_AXIS)
			d /= (Ft.y / entity.getMass());
		else
			d /= (Ft.x / entity.getMass());
		try {
			if(entity.isAlive())entity.setPos(d, axis);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static <T extends Attribute> Pair totalForce(DungeonableEntity<T> entity) {
		int x = 0, y = 0;
		y -= entity.getDamageTake()[0];
		y += entity.getDamageTake()[3];
		x += entity.getDamageTake()[1];
		x -= entity.getDamageTake()[2];
		for (int i = 0; i <= 3; i++) {
			entity.getDamageTake()[i] = 0;
		}
		return new Pair(x, y);
	}

	public static <T extends Attribute> int calculateForce(int force, int axis,
			DungeonableEntity<T> dungeonableEntity) {
		if (axis == SceneManeger.X_AXIS)
			return (int) (force * SceneManeger.WIDGTH / 200);
		else
			return (int) (force * SceneManeger.HEIGHT / 150);
	}

	public static <T extends Attribute> int calculateDirection(int direction) {
		if (direction == 0)
			return 3;
		else
			return (direction * 2) % 3;
	}

}
