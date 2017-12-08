package model.monster;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import logic.GameLogic;
import model.attribute.Attribute;
import model.entity.DungeonableEntity;
import model.entity.Entity;
import model.field.Dungeon;
import model.field.Obstructable;
import utility.InputUtility;
import utility.RandomUtility;

public class Monster extends DungeonableEntity<Attribute> implements Obstructable {
	private int idleParameter;
	private int timidParaneter;
	private int persistentParameter;
	private int eyesight;
	private int bounty;
	private int rand;
	private boolean struct;

	public Monster(double x, double y, Image img, int row, int column, int direction, int movespeed, int mass,
			int maxHp, int baseAtk, Attribute atkType) {
		super(x, y, img, row, column, Entity.FRONT, movespeed, mass, maxHp, baseAtk, atkType);
		this.race = DungeonableEntity.MONSTER;
	}

	@Override
	public void update() {
		super.update();	
		if (!isAlive) GameLogic.hero.earnMoney(bounty);
		else if (isAlive && dmgTimer == 0) {
			rand = RandomUtility.randomInt(0, 100);
			
			move(RandomUtility.randomByPercent(rand,this.direction,95));
			if(isBlock(pos.x,pos.y)) struct=true;
			else struct =false;
			this.atkType.update(this.direction, this.pos.x, this.pos.y);
		} else if (isAlive) {
			dmgTimer = dmgTimer == 0 ? dmgTimer - 1 : 0;
		}
	}

	@Override
	protected boolean isBlock(double x, double y) {
		if(struct) return false;
		ArrayList<DungeonableEntity<Attribute>> inArea = Dungeon.getEntityInArea(this, x, y);
		for (DungeonableEntity<Attribute> other : inArea) {
			if (this.race != other.getRace())
				other.damage(this.getBaseAtk(), other.getDirection());
			return true;
		}
		return false;
	}
}
