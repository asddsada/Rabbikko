package model.items;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameLogic;
import model.GameObject;
import model.attribute.Agility;
import model.attribute.Attribute;
import model.attribute.Intelligence;
import model.attribute.Strength;
import model.entity.Entity;
import model.entity.Hero;
import model.field.Dungeon;
import sharedObj.IRenderable;
import sharedObj.RenderableHolder;

public class Weapons extends GameObject implements Useable {
	private int price;
	private Image imgWeapon;
	private int amount;
	private int attackTime;
	private int atkTimeMax; // how many step swing should make

	public Weapons(int price, Image img, Image imgIcon) {
		super(0, 0, 100);
		this.price = price;
		this.imgWeapon = img;
		this.amount = 0;
		attackTime = 0;
		this.visible = false;
		this.atkTimeMax = 10;
	}

	public void held() {
		this.visible = true;
		RenderableHolder.getInstance().add(this);
	}

	public boolean isBuyable() {
		if (amount == 0 && GameLogic.hero.getMoney() >= this.price)
			return true;
		return false;
	}

	public int getAtkTimeMax() {
		return (int) (atkTimeMax / GameLogic.hero.getAtkType().getAttackSpeed());
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
		if(attackTime==0)attackTime = atkTimeMax;
		GameLogic.hero.setAtktype(GameLogic.hero.getAtkType());
		// System.out.println("use weapon");
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (GameLogic.hero.isAlive()) {
			if (attackTime == 0) {
				if (GameLogic.hero.getAtkType() instanceof Strength) {
					if(GameLogic.hero.getDirection()==Entity.RIGHT) {	
						gc.drawImage(imgWeapon,pos.x-getWidth()*2.5,pos.y-getHeight()/2);
					}else if(GameLogic.hero.getDirection()==Entity.LEFT) {	
						gc.drawImage(imgWeapon,pos.x+getWidth()*1.75,pos.y-getHeight()/2);
					}else if(GameLogic.hero.getDirection()==Entity.BACK) {	
						gc.drawImage(imgWeapon,pos.x-getWidth()*1.5,pos.y-getHeight()/2);
					}else if(GameLogic.hero.getDirection()==Entity.FRONT) {	
						gc.drawImage(imgWeapon,pos.x+getWidth(),pos.y-getHeight()/2);
					}
				}
				else if (GameLogic.hero.getAtkType() instanceof Agility) {
					if(GameLogic.hero.getDirection()==Entity.RIGHT) {	
						gc.drawImage(imgWeapon,pos.x-getWidth()*2.5,pos.y-getHeight()/4);
					}else if(GameLogic.hero.getDirection()==Entity.LEFT) {	
						gc.drawImage(RenderableHolder.bow3,pos.x+getWidth()*1.2,pos.y-getHeight()/4);
					}else if(GameLogic.hero.getDirection()==Entity.BACK) {	
						gc.drawImage(imgWeapon,pos.x-getWidth()*1.5,pos.y-getHeight()/4);
					}else if(GameLogic.hero.getDirection()==Entity.FRONT) {	
						gc.drawImage(RenderableHolder.bow3,pos.x+getWidth()/1.5,pos.y-getHeight()/4);
					}
				}
				else if (GameLogic.hero.getAtkType() instanceof Intelligence) {
					if(GameLogic.hero.getDirection()==Entity.RIGHT) {	
						gc.drawImage(imgWeapon,pos.x-getWidth()*2.5,pos.y-getHeight()/2);
					}else if(GameLogic.hero.getDirection()==Entity.LEFT) {	
						gc.drawImage(imgWeapon,pos.x+getWidth()*1.75,pos.y-getHeight()/2);
					}else if(GameLogic.hero.getDirection()==Entity.BACK) {	
						gc.drawImage(imgWeapon,pos.x-getWidth()*1.5,pos.y-getHeight()/2);
					}else if(GameLogic.hero.getDirection()==Entity.FRONT) {	
						gc.drawImage(imgWeapon,pos.x+getWidth(),pos.y-getHeight()/2);
					}
				}
			}
			else {
				// swing rotate
				// attack time == max/2 ,should go down
				// then go up and such
			}
		}
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

	public int getAttackTime() {
		return attackTime;
	}

	public void update(int direction, double x, double y) {
		if (direction == Entity.RIGHT) {
			this.pos.x = x + GameLogic.hero.getWidth() + getWidth();
			this.pos.y = (y + getHeight());
		} else if (direction == Entity.LEFT) {
			this.pos.x = (x - getWidth());
			this.pos.y = (y + getHeight());
		} else if (direction == Entity.BACK) {
			this.pos.x = (x + getWidth());
			this.pos.y = (y - getHeight());
		} else if (direction == Entity.FRONT) {
			this.pos.x = (x + getWidth());
			this.pos.y = (y + GameLogic.hero.getHeight() / 2 + getHeight());
		}
		if (GameLogic.hero.getDirection() == Entity.LEFT || GameLogic.hero.getDirection() == Entity.FRONT)
			this.pos.x = GameLogic.hero.getX() - (getWidth());
		else
			this.pos.x = GameLogic.hero.getX() + GameLogic.hero.getWidth() + (getWidth());

		this.pos.y = GameLogic.hero.getY() + 30;
		if(attackTime>0) attackTime--;
		if(GameLogic.hero.getDirection()==Entity.LEFT) this.z=GameLogic.hero.getZ()-1;
		else this.z=GameLogic.hero.getZ()+1;
	}
}
