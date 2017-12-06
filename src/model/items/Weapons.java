package model.items;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameLogic;
import model.GameObject;
import model.entity.Entity;
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
		this.imgIcon=imgIcon;
		this.amount = 0;
		attackTime=0;
	}

	public boolean isBuyable() {
		return amount!=1;
	}
	
	public int getPrice() {
		return price;
	}

	public int getAmount() {
		return amount;
	}

	@Override
	public void use(int direction) {
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
		return 
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 
	}
	
	public void update() {
		if(direction==Entity.LEFT) this.pos.x = GameLogic.hero.getX()-(getWidth()+??) ;
		else this.pos.x = GameLogic.hero.getX()+(getWidth()+??) ;
		
		this.pos.y = GameLogic.hero.getY() +?? ;
		
	}
	
	public void buy() {
		if(this.amount>0) this.amount--;
	}
}
