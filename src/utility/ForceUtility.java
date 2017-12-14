package utility;

import model.attribute.Attribute;
import model.entity.DungeonableEntity;
import utility.Constant;
import utility.Pair;

public class ForceUtility {

	public static <T extends Attribute> void reactionEffect(DungeonableEntity<T> entity, int axis) {
		double d = entity.getMovespeed()*entity.getMovespeed()/2;
		Pair Ft =totalForce(entity);
		// bug able to push out of the field
		if (axis % 3 == Constant.SCENE_Y_AXIS)
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
		if (axis == Constant.SCENE_X_AXIS)
			return (int) (force * Constant.SCENE_WIDTH / 200);
		else
			return (int) (force * Constant.SCENE_HEIGHT / 150);
	}

	public static <T extends Attribute> int calculateDirection(int direction) {
		if (direction == 0)
			return 3;
		else
			return (direction * 2) % 3;
	}

}
