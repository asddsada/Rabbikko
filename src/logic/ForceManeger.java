package logic;

import Main.Main;
import model.attribute.Attribute;
import model.entity.DungeonableEntity;
import model.field.Dungeon;
import utility.Pair;
import view.SceneManeger;

public class ForceManeger {
	private static Thread physicThread = null;
	private static boolean threadState;
	private static final float TIME_DIV = 100;

	public static void initilized() {
		threadState = true;
		physicThread = new Thread(() -> {
			while (Main.isGameRunning) {
				while (threadState) {
					// for (DungeonableEntity<Attribute> entity : Dungeon.getENTITIES_HOLDER()) {
					// int forceCount = 0;
					// double dx = totalForce(entity).x / entity.getMass() / 200 /TIME_DIV ;
					// double dy = totalForce(entity).y / entity.getMass() / 200 /TIME_DIV;
					// while (!(dx==0&&dy==0) &&forceCount < TIME_DIV) {
					// System.out.println(entity.getClass().getSimpleName()+" "+dx+" "+dy);
					// entity.setPos(dx, SceneManeger.X_AXIS);
					// entity.setPos(dy, SceneManeger.Y_AXIS);
					// forceCount++;
					// }
					// }
//					System.out.println(threadState);
				}
//				System.out.println("end");
			}			
		});
//		physicThread.start();
	}

	public static void startForceRule() {
		threadState = true;
	}

	public static void pauseForceRule() {
		threadState = false;
	}

	private static Pair totalForce(DungeonableEntity<Attribute> entity) {
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

}
