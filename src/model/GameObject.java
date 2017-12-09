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
	
	public void setZ(int z) {
		this.z = z;
	}

	public boolean isCollide(GameObject other, double x, double y) {
		 if ((other.getX() - getWidth() * 4 / 6 <= x && x <= other.getX() + getWidth()
		 * 4 / 6)
		 && ((other.getY() - getHeight() / 6 + 5 <= y && y <= other.getY() +
		 getHeight()))) {
		 this.z = 20;
		 
		 } else if ((other.getX() - getWidth() <= x && x <= other.getX() + getWidth())
		 && ((other.getY() - getHeight() / 8 + 5 <= y && y <= other.getY() +
		 getHeight()))) {
		 this.z = 1;
		 } else if ((other.getX() - getWidth() <= x && x <= other.getX() + getWidth())
		 && (other.getY() - getHeight() < y && y < other.getY())) {
		 this.z = -1;
		 }
		
		if ((((x - other.getWidth()) <= other.getX()) && (other.getX() <= (x + getWidth() + other.getWidth())))
				&& (((y - other.getHeight()) <= other.getY())
						&& (other.getY() <= (y + other.getHeight()/2 + getHeight())))
				&& (((x - other.getWidth()) <= (other.getX() + other.getWidth()))
						&& ((other.getX() + other.getWidth()) <= (x + getWidth() + other.getWidth())))
				&& (((y - other.getHeight()) <= (other.getY()+other.getHeight()))
						&& ((other.getY()+other.getHeight()) <= (y + other.getHeight()/2 + getHeight()))))
			return true;
		return false;
	}

	public abstract double getWidth();

	public abstract double getHeight();
}
