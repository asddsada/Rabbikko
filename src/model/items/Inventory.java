package model.items;

import sharedObj.RenderableHolder;

public class Inventory {
	private static final Useable[] BAG = {
			new Health(),new Mana(),
			new	Weapons(500, RenderableHolder.sword2, RenderableHolder.sword,1),
			new	Weapons(500, RenderableHolder.bow2, RenderableHolder.bow,2),
			new	Weapons(500, RenderableHolder.staff2, RenderableHolder.staff,3),
		};
	public int bagCapacity;
	
	public Inventory() {
		this.bagCapacity = 1;
		add(2); //need 1 sword
	}
	
	public void add(int i) {
		BAG[i].add();
		bagCapacity++;
	}
	
	public boolean isFull() {
		if (this.bagCapacity == 50) {
			return true;
		}
		return false;
	}

	public static Useable[] getBag() {
		return BAG;
	}
}
