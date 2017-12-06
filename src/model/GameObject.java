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

	public boolean isCollide(GameObject other, double x, double y) {
		// System.out.println(other.pos.x+" "+ other.pos.y +" "+ x+" "+ y+"
		// "+(other.pos.y+getHeight()/3)+" "+y+" "+(other.pos.y+getHeight()/3));
		// if ((other.getX() - getWidth() * 4 / 6 <= x && x <= other.getX() + getWidth()
		// * 4 / 6)
		// && ((other.getY() - getHeight() / 6 + 5 <= y && y <= other.getY() +
		// getHeight()))) {
		// this.z = 1;
		// } else if ((other.getX() - getWidth() <= x && x <= other.getX() + getWidth())
		// && ((other.getY() - getHeight() / 8 + 5 <= y && y <= other.getY() +
		// getHeight()))) {
		// this.z = 1;
		// } else if ((other.getX() - getWidth() <= x && x <= other.getX() + getWidth())
		// && (other.getY() - getHeight() < y && y < other.getY())) {
		// this.z = -1;
		// }
		if ((other.getY() - getHeight() / 3 <= y && y <= other.getY() + getHeight() / 3)
				&& (other.getX() - getWidth() * 4 / 6 <= x && x <= other.getX() + getWidth() * 4 / 6))
			return true;

		return false;
	}

	public abstract double getWidth();

	public abstract double getHeight();
}
