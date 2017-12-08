package model.items;

import logic.GameLogic;
import utility.ResourceLoader;

public class Inventory {
	private static final Useable[] BAG = {
			new Health(),new Mana(),
			new	Weapons(500, ResourceLoader.sword2, ResourceLoader.sword,2),
			new	Weapons(500, ResourceLoader.bow2, ResourceLoader.bow,3),
			new	Weapons(500, ResourceLoader.staff2, ResourceLoader.staff,4),
		};
	public int bagCapacity;
	
	public Inventory() {
		this.bagCapacity = 1;
	}
	
	public void reset() {
		this.bagCapacity = 1;
		for(Useable i:BAG) {
			if(GameLogic.hero.getAtkType().getHeroWeapon().hashCode()!=i.hashCode()) i.reset();
		}
	}
	
	public void add(int i) {
		BAG[i].add();
		bagCapacity++;
	}
	
	public boolean isFull() {
		if (this.bagCapacity > 50) {
			return true;
		}
		return false;
	}

	public static Useable[] getBag() {
		return BAG;
	}
}
