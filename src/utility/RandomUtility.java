package utility;

import java.util.Random;

public class RandomUtility {
	private static final Random rand = new Random();

	public static int random(int from, int to) {
		return from + rand.nextInt(to - from);
	}

	public static int dirctionRand(int direction) {
		int add = rand.nextInt(100);
		return (direction + (add > 95 ? add % 4 : 0)) % 4;
	}

	public static int charateristicRand(Object o) {
		return 0;

	}
}
