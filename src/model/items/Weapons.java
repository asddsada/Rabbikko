package model.items;

import java.util.ConcurrentModificationException;

import Main.DungeonMain;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameLogic;
import model.GameObject;
import model.attribute.Agility;
import model.attribute.Intelligence;
import model.attribute.Strength;
import sharedObj.RenderableHolder;
import utility.Constant;
import utility.ResourceLoader;

public class Weapons extends GameObject implements Useable {
	
	private int price;
	private Image imgWeapon;
	private int amount;
	private int type;

	public Weapons(int price, Image img, Image imgIcon, int type) {
		super(0, 0, 100);
		this.price = price;
		this.imgWeapon = img;
		this.amount = 0;
		this.visible = false;
		this.type = type;
	}

	public void held() {
		this.visible = true;
		RenderableHolder.getInstance().add(this);
	}

	public void setWeapon() {
		try {
			if (GameLogic.hero.getAtkType().getHeroWeapon().type != this.type)
				takeWeaponOff();
			if (type == Constant.SWORD && GameLogic.hero.getAtkType().getHeroWeapon().type != Constant.SWORD)
				GameLogic.hero.setAtktype(new Strength());
			else if (type == Constant.BOW && GameLogic.hero.getAtkType().getHeroWeapon().type != Constant.BOW)
				GameLogic.hero.setAtktype(new Agility());
			else if (type == Constant.STAFF && GameLogic.hero.getAtkType().getHeroWeapon().type != Constant.STAFF)
				GameLogic.hero.setAtktype(new Intelligence());
			this.update(GameLogic.hero.getDirection(), GameLogic.hero.getX(), GameLogic.hero.getY());
			DungeonMain.getCanvas().canvasUpdate();
		} catch (ConcurrentModificationException | NullPointerException e) {
			e.printStackTrace();
		}
	}

	public boolean isBuyable() {
		if (amount == 0 && GameLogic.hero.getMoney() >= this.price)
			return true;
		return false;
	}

	public int getPrice() {
		return price;
	}

	public int getAmount() {
		return amount;
	}

	public void add() {
		this.amount++;
	}

	@Override
	public void use() {
		GameLogic.hero.getAtkType().use();
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (GameLogic.hero.isAlive()) {
			if (GameLogic.hero.getAtkType().getAttackTime() == 0) {
				if (GameLogic.hero.getAtkType() instanceof Strength) {
					if (GameLogic.hero.getDirection() == Constant.ENTITY_RIGHT) {
						gc.drawImage(imgWeapon, pos.x - getWidth() * 2.5, pos.y - getHeight() / 2);
					} else if (GameLogic.hero.getDirection() == Constant.ENTITY_LEFT) {
						gc.drawImage(imgWeapon, pos.x + getWidth() * 1.75, pos.y - getHeight() / 2);
					} else if (GameLogic.hero.getDirection() == Constant.ENTITY_BACK) {
						gc.drawImage(imgWeapon, pos.x - getWidth() * 1.5, pos.y - getHeight() / 2);
					} else if (GameLogic.hero.getDirection() == Constant.ENTITY_FRONT) {
						gc.drawImage(imgWeapon, pos.x + getWidth(), pos.y - getHeight() / 2);
					}
				} else if (GameLogic.hero.getAtkType() instanceof Agility) {
					if (GameLogic.hero.getDirection() == Constant.ENTITY_RIGHT) {
						gc.drawImage(imgWeapon, pos.x - getWidth() * 2.7, pos.y - getHeight() / 3);
					} else if (GameLogic.hero.getDirection() == Constant.ENTITY_LEFT) {
						gc.drawImage(ResourceLoader.bow3, pos.x + getWidth() * 1.2, pos.y - getHeight() / 2);
					} else if (GameLogic.hero.getDirection() == Constant.ENTITY_BACK) {
						gc.drawImage(imgWeapon, pos.x - getWidth() * 1.5, pos.y - getHeight() / 4);
					} else if (GameLogic.hero.getDirection() == Constant.ENTITY_FRONT) {
						gc.drawImage(ResourceLoader.bow3, pos.x + getWidth() / 1.5, pos.y - getHeight() / 4);
					}
				}
			}
			if (GameLogic.hero.getAtkType() instanceof Intelligence) {
				if (GameLogic.hero.getDirection() == Constant.ENTITY_RIGHT) {
					gc.drawImage(imgWeapon, pos.x - getWidth() * 2.5, pos.y - getHeight() / 2);
				} else if (GameLogic.hero.getDirection() == Constant.ENTITY_LEFT) {
					gc.drawImage(imgWeapon, pos.x + getWidth() * 1.75, pos.y - getHeight() / 2);
				} else if (GameLogic.hero.getDirection() == Constant.ENTITY_BACK) {
					gc.drawImage(imgWeapon, pos.x - getWidth() * 1.5, pos.y - getHeight() / 2);
				} else if (GameLogic.hero.getDirection() == Constant.ENTITY_FRONT) {
					gc.drawImage(imgWeapon, pos.x + getWidth(), pos.y - getHeight() / 2);
				}
			}
		}
	}

	public Image getImage() {
		return imgWeapon;
	}

	@Override
	public double getWidth() {
		return 40;
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 80;
	}

	public void update(int direction, double x, double y) {
		if (direction == Constant.ENTITY_RIGHT) {
			this.pos.x = x + GameLogic.hero.getWidth() + getWidth();
			this.pos.y = (y + getHeight());
		} else if (direction == Constant.ENTITY_LEFT) {
			this.pos.x = (x - getWidth());
			this.pos.y = (y + getHeight());
		} else if (direction == Constant.ENTITY_BACK) {
			this.pos.x = (x + getWidth());
			this.pos.y = (y - getHeight());
		} else if (direction == Constant.ENTITY_FRONT) {
			this.pos.x = (x + getWidth());
			this.pos.y = (y + GameLogic.hero.getHeight() / 2 + getHeight());
		}
		if (GameLogic.hero.getDirection() == Constant.ENTITY_LEFT || GameLogic.hero.getDirection() == Constant.ENTITY_FRONT)
			this.pos.x = GameLogic.hero.getX() - (getWidth());
		else
			this.pos.x = GameLogic.hero.getX() + GameLogic.hero.getWidth() + (getWidth());

		this.pos.y = GameLogic.hero.getY() + 30;
		if (GameLogic.hero.getDirection() == Constant.ENTITY_LEFT)
			this.z = GameLogic.hero.getZ() - 1;
		else
			this.z = GameLogic.hero.getZ() + 1;
	}

	@Override
	public void reset() {
		this.amount = 0;
	}

	public void takeWeaponOff() {
		GameLogic.hero.getAtkType().getAttackObj().destroyed();
		GameLogic.hero.getAtkType().getHeroWeapon().destroyed();
	}
}
