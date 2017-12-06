package model.items;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.GameObject;
import sharedObj.IRenderable;

public class Weapons extends GameObject implements Useable {
	protected int price;
	protected Image img;
	protected int amount;
	
	public Weapons(double x,double y,int price, Image img) {
		super(x, y, 100);
		this.price = price;
		this.img = img;
		this.amount = 0;
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
	public void use() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.
		
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
		
	}
}
