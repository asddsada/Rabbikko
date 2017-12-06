package utility;

import model.attribute.Attribute;
import model.entity.DungeonableEntity;
import model.field.Dungeon;
import view.SceneManeger;

public class ForceManeger {
	private static Thread physicThread = null;
	private static boolean threadState;
	private static final float TIME_DIV =100;

	public static void initilized() {
		threadState=false;
		physicThread = new Thread(() -> {
			while (threadState) {
				for (DungeonableEntity<Attribute> entity : Dungeon.getENTITIES_HOLDER()) {
					int forceCount = 0;
					double dx = totalForce(entity).x / entity.getMass() / 200 /TIME_DIV ;
					double dy = totalForce(entity).y / entity.getMass() / 200 /TIME_DIV;
					while (!(dx==0&&dy==0) &&forceCount < TIME_DIV) {
						System.out.println(entity.getClass().getSimpleName()+" "+dx+" "+dy);
						entity.setPos(dx, SceneManeger.X_AXIS);
						entity.setPos(dy, SceneManeger.Y_AXIS);
						forceCount++;
//						try {
//							physicThread.sleep(100);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}catch(IllegalMonitorStateException e) {
//							e.printStackTrace();
//						}
					}
				}
			}
		});
	}
	// int totalCalForce;
	// int forceCount=0;
	// int displacement;

	// displacement = (totalCalForce/entity.getMass())/2;
	// while(forceCount!=totalCalForce) {
	// forceCount+=(totalCalForce/entity.getMass());
	// entity.setPos(displacement, axis);
	public static void startForceRule() {
		threadState = true;
		try {
			physicThread.start();
		} catch (IllegalThreadStateException e) {
			e.printStackTrace();
		}
	}

	public static void stopForceRule() {
		System.out.println("outtttttttttttttttttt");
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
