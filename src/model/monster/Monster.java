package model.monster;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import logic.GameLogic;
import model.attribute.Attribute;
import model.entity.DungeonableEntity;
import model.entity.Entity;
import model.field.Dungeon;
import model.field.Navigation;
import model.field.Obstructable;
import utility.InputUtility;
import utility.RandomUtility;
import view.SceneManeger;

public class Monster extends DungeonableEntity<Attribute> implements Obstructable {
	private int idleParameter;
	private int timidParaneter;
	private int persistentParameter;
	private int eyesight;
	private int bounty;
	private int rand;
	private int size;

	public Monster(Image img, int row, int column, int movespeed, int mass,
			int maxHp, int baseAtk, Attribute atkType, int idleParameter, int timidParaneter, int persistentParameter,
			int eyesight, int bounty, int size) {
		super(RandomUtility.randomInt((int) (SceneManeger.WIDGTH*0.01), (int) (SceneManeger.WIDGTH*0.9)),
				RandomUtility.randomInt((int) (SceneManeger.HEIGHT*0.01), (int) ((SceneManeger.HEIGHT - Navigation.NAVIG_HEIGHT)*0.7)),
				img, row, column, Entity.FRONT, 
				movespeed, mass, maxHp, baseAtk, atkType);
		this.idleParameter = idleParameter;
		this.timidParaneter = timidParaneter;
		this.persistentParameter = persistentParameter;
		this.eyesight = eyesight;
		this.bounty = bounty;
		this.size=size;
	}

	@Override
	public void update() {
		super.update();	
		if (!isAlive) GameLogic.hero.earnMoney(bounty);
		else if (isAlive && dmgTimer == 0) {
			rand = RandomUtility.randomInt(0, 100);
			attack();
			move(RandomUtility.randomByPercent(rand,this.direction,95));
			if(isBlock(pos.x,pos.y)) {
				struct=true;
				setPos(5, this.direction);
			}
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
