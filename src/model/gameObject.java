package model;

import sharedObj.IRenderable;
import utility.Pair;

public abstract class gameObject implements IRenderable {
	protected Pair pos;
	protected int z;
	protected boolean visible;

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
		return visible == false;
	}
}
