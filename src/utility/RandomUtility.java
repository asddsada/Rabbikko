package utility;

import java.util.Random;

public abstract class RandomUtility {
	private static final Random random = new Random();

	public static int randomInt(int from, int to) {
		if(from>=to) return 0;
		return from + random.nextInt(to - from);
	}

	public static int randomByPercent(int rand,int base,int percent) { 
		return (base + (rand > percent ? rand % 4 : 0)) % 4;
	}

	public static int charateristicRand(Object o) {
		return 0;
	}
	
	public static int randomByLevel(int lvl) {
		if(lvl==0) return 0;
		if(lvl==1) return (int) (Math.PI/2+randomInt(0,3));
		if((lvl>15)&&(lvl<25)) return 30 +randomInt(0, lvl-14);
		if(lvl>30) return 40+randomInt(0, 5);
		return (int) (lvl*Math.PI/2+randomInt(0, lvl<10?lvl:10));
	}
	
	public static long randomTime(int sizeFactor) {
		if(sizeFactor<12) return 1000;
		else if(sizeFactor>20) return 7000;
		return randomInt(3, 7)*1000;
	}
	
	public static int random() {
		return (int) System.currentTimeMillis()%13;
	}
}
