package model.monster;

import java.util.ArrayList;

import javafx.scene.image.Image;
import logic.GameLogic;
import model.Obstructable;
import model.attribute.Attribute;
import model.entity.DungeonableEntity;
import model.field.Dungeon;
import utility.Constant;
import utility.RandomUtility;

public class Monster extends DungeonableEntity<Attribute> implements Obstructable {
	private int idleParameter;
	private int timidParameter;
	private int persistentParameter;
	private int eyesight;
	private int bounty;
	private int rand;
	private double size;
	private int count;

	public Monster(Image img, int row, int column, int movespeed, int mass, int maxHp, int baseAtk, Attribute atkType,
			int idleParameter, int timidParaneter, int persistentParameter, int eyesight, int bounty, double size) {
		super(RandomUtility.randomInt((int) (Constant.SCENE_WIDTH * 0.01), (int) (Constant.SCENE_WIDTH * 0.9)),
				RandomUtility.randomInt((int) (Constant.SCENE_HEIGHT * 0.01),
						(int) ((Constant.SCENE_HEIGHT - Constant.NAVIG_HEIGHT) * 0.8)),
				img, row, column, Constant.ENTITY_FRONT, movespeed, mass, maxHp, baseAtk, atkType);
		this.idleParameter = idleParameter;
		this.timidParameter = timidParaneter;
		this.persistentParameter = persistentParameter;
		this.eyesight = eyesight;
		this.bounty = bounty;
		this.size = size;
		this.count = idleParameter;
		this.race = Constant.ENTITY_MONSTER;
	}

//	private int heroDirection() {
//		double dx = GameLogic.hero.getX();
//		double dy = GameLogic.hero.getY();
//		if (pos.diffX(dx) <= pos.diffY(dy)) {
//			return (pos.x - dx) >= 0 ? Constant.LEFT : Constant.RIGHT;
//		} else {
//			return (pos.y - dy) >= 0 ? Constant.BACK : Constant.FRONT;
//		}
//	}

	@Override
	public void update() {
		if (!isAlive || this.currentHp == 0) {
			Dungeon.destroyEntities(this);
			this.atkType.getAttackObj().setVisible(false);
			GameLogic.hero.earnMoney(bounty);
		} else if ((isAlive) && (dmgTimer == 0) && (count < idleParameter / 2)) {
			rand = RandomUtility.randomInt(0, 100);
//			if ((rand * timidParameter) % 100 < persistentParameter)
//				move(heroDirection());
//			else
				move(RandomUtility.randomByPercent(rand, this.direction, 98));
				attack();
			if (isBlock(pos.x, pos.y))
				struct = true;
			else
				struct = false;
		}
		if (isAlive) {
			this.atkType.update(this.direction, this.pos.x, this.pos.y);
			dmgTimer = dmgTimer == 0 ? 0 : dmgTimer - 1;
			count = count <= 1 ? idleParameter : count - 10 / timidParameter;
		}
	}

	@Override
	protected boolean isBlock(double x, double y) {
		if (struct)
			return false;
		ArrayList<DungeonableEntity<Attribute>> inArea = Dungeon.getEntityInArea(this, x, y);
		for (DungeonableEntity<Attribute> other : inArea) {
			if (this.race != other.getRace())
				other.damage(this.getBaseAtk(), other.getDirection());
			return true;
		}
		return false;
	}
}
