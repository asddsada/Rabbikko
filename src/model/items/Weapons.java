package model.items;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameLogic;
import model.GameObject;
import model.entity.Entity;
import model.entity.Hero;
import sharedObj.IRenderable;

public class Weapons extends GameObject implements Useable {
	private int price;
	private Image imgWeapon;
	private Image imgIcon;
	private int amount;
	private int attackTime;
	private int ATK_TIME_MAX=30; //how many step swing should make
	
	public Weapons(int price, Image img,Image imgIcon) {
		super(0, 0, 100);
		this.price = price;
		this.imgWeapon = img;
		this.imgIcon = imgIcon;
		this.amount = 0;
		attackTime=0;
	}

	public boolean isBuyable() {
		if (amount == 0 && Hero.getMoney()>=this.price) return true;
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
		attackTime=ATK_TIME_MAX;
	}

	@Override
	public void draw(GraphicsContext gc) {
		if(attackTime==0) {
			//nomal draw
		}else {
			//swing rotate
			//attack time == max/2 ,should go down
			//then go up and such
			
			attackTime--;
		}
	}

	@Override
	public double getWidth() {
		return 32;
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 32;
	}
	
	public void update() {
		if(GameLogic.hero.getDirection()==Entity.LEFT||
				GameLogic.hero.getDirection()==Entity.FRONT) 
			this.pos.x = GameLogic.hero.getX()-(getWidth()) ;
		else this.pos.x = GameLogic.hero.getX()+GameLogic.hero.getWidth()+(getWidth()) ;
		
		this.pos.y = GameLogic.hero.getY()+30 ;
	}
}
