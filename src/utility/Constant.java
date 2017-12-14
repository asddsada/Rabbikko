package utility;

import model.items.Inventory;
import model.items.Weapons;

public abstract class Constant {
	public static final double SCENE_WIDTH = 1000;
	public static final double SCENE_HEIGHT = 700;
	public static final int SCENE_X_AXIS = 1;
	public static final int SCENE_Y_AXIS = 0;
	public static final int ENTITY_FRONT = 0;
	public static final int ENTITY_LEFT = 1;
	public static final int ENTITY_RIGHT = 2;
	public static final int ENTITY_BACK = 3;
	public static final int ENTITY_HUMANITY = 1;
	public static final int ENTITY_MONSTER = 0;
	public static final int DUNGEON_CHANGE_TIME_MAX = 400;
	public static final double NAVIG_WIDTH = ResourceLoader.navigBar.getWidth();
	public static final double NAVIG_HEIGHT = ResourceLoader.navigBar.getHeight();
	public static final int NAVIG_BAR_HEIGHT = 30;
	public static final int NAVIG_BORDER_WIDTH = 200;
	public static final int ENTITY_WALK_TICK_DELAY = 8;
	public static final int ENTITY_WALK_STAGE = 3;
	public static final int DMG_TIME_MAX = 10;
	public static final double BASE_HEAL_AMOUNT = 0.3;
	public static final int BASE_ATTACK_TIMER_MAX = 30;
	public static final int SWORD = 2;
	public static final int BOW = 3;
	public static final int STAFF = 4;
	public static final int BOUNTY_MULTIPLYER = 49;
	public static final int BASE_MP_USE = 30;
	public static final double MAX_MAGIC_TIME = ((Weapons) Inventory.getBag()[STAFF]).getWidth() * 5;
	public static final int MAX_SPELL_ACTIVE = 6;;

}
