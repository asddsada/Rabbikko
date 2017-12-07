package utility;

import java.util.Random;

public class RandomUtility {
	private static final Random random = new Random();

	public static int randomInt(int from, int to) {
		return from + random.nextInt(to - from);
	}

	public static int dirctionRand(int rand,int direction) {
		return (direction + (rand > 95 ? rand % 4 : 0)) % 4;
	}

	public static int charateristicRand(Object o) {
		return 0;
	}
	
	public static int randomByLevel(int lvl) {
		if((lvl>20)&&(lvl<30)) return 30 +randomInt(0, lvl-18);
		if(lvl>30) return 40+randomInt(0, 5);
		return (int) (lvl*Math.PI/2+randomInt(0, lvl<10?lvl:10));
	}
	
	public static long randomTime(int sizeFactor) {
		if(sizeFactor<12) return 1000;
		else if(sizeFactor>20) return 7000;
		return randomInt(3, 7)*1000;
	}
}
