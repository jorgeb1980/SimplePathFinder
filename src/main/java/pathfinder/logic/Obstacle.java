package pathfinder.logic;

import static pathfinder.logic.RandomGenerator.randomInt;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import lombok.Getter;

/*
 * We define an obstacle as a row of consecutive nodes of a certain length.
 * X,Y are the coordinates of the initial node.
 * It can have only orientation NORTH, SOUTH, WEST, EAST
 */
public class Obstacle {

	
	@Getter private int x;
	@Getter private int y;
	@Getter private int length;
	@Getter private Orientation orientation;
	
	public Obstacle(int x, int y, int length) {
		this(x, y, length, null);
	}
	
	public Obstacle(int x, int y, int length, Orientation orientation) {
		this.x = x;
		this.y = y;
		this.length = length;
		if (orientation != null) this.orientation = orientation;
		else this.orientation = Orientation.values()[randomInt(Orientation.values().length)];
	}

	public void flipObstacle() {
		int index = Arrays.asList(Orientation.values()).indexOf(orientation);
		index = (index + 1) % Orientation.values().length;
		orientation = Orientation.values()[index];
	}

	public List<Pair> generatePairs() {
		List<Pair> pairs = new LinkedList<>();
		for (int i = 0; i < length; i++) {
			if (i == 0) pairs.add(new Pair(x, y));
			else push(pairs, orientation);
		}
		return pairs;
	}
	
	private void push(List<Pair> pairs, Orientation orientation) {
		if (pairs.size() > 0) {
			Pair top = pairs.get(pairs.size() - 1);
			pairs.add(top.apply(orientation.getVector()));
		}
	}
}
