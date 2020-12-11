package pathfinder.logic;

import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {

	/**
	 * Random integer generator 
	 * @param limit Upper limit for the random int
	 * @return Random integer in the range [0, limit)
	 */
	public static int randomInt(int limit) {
		return ThreadLocalRandom.current().nextInt(0, limit);
	}
}
