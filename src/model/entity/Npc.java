package model.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.field.Obstructable;
import utility.Pair;

public class Npc extends Entity implements Obstructable {

	public Npc(double x,double y,Image img, int row, int column, int direction,int movespeed) {
		super(x,y,img, row, column, direction,movespeed);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}
