package model;

import sharedObj.IRenderable;
import sharedObj.RenderableHolder;
import utility.Pair;

public abstract class GameObject implements IRenderable {
	protected Pair pos;
	protected int z;
	protected boolean visible;

	public GameObject(double x, double y, int z) {
		super();
		this.pos = new Pair(x, y);
		this.z = z;
		this.visible = true;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void destroyed() {
		// TODO Auto-generated method stub
		visible = false;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return z;
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return visible == false;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return visible == true;
	}

	public double getX() {
		return pos.x;
	}

	public double getY() {
		return pos.y;
	}

	public void setX(double x) {
		pos.x = x;
	}

	public void setY(double y) {
		pos.y = y;
	}

	public boolean isCollide(GameObject other, double x, double y) {
		// System.out.println(other.pos.x+" "+ other.pos.y +" "+ x+" "+ y+"
		// "+(other.pos.y+getHeight()/3)+" "+y+" "+(other.pos.y+getHeight()/3));
		
		//here, there is bug!!!!
		if ((other.getX() - other.getWidth() * 4 / 6 <= x && x <= other.getX() + other.getWidth() * 4 / 6)
				&& ((other.getY() - other.getHeight() / 6 + 5 <= y && y <= other.getY() + other.getHeight()))) {
			return true;
		} else if ((other.getX() - other.getWidth() <= x && x <= other.getX() + other.getWidth())
				&& ((other.getY() - other.getHeight() / 8 + 5 <= y && y <= other.getY() + other.getHeight()))) {
			return true;
		} else if ((other.getX() - other.getWidth() / 2 <= x && x <= other.getX() + other.getWidth() / 2)
				&& (other.getY() - other.getHeight() < y && y < other.getY())) {
			return true;
		}
		return false;
	}

	public abstract double getWidth();

	public abstract double getHeight();
}
