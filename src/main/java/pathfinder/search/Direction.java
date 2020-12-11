package pathfinder.search;

import lombok.Getter;
import pathfinder.logic.Pair;

// Possible movement directions (all 8)
public enum Direction { 
	NORTH(0, -1), EAST(1, 0), WEST(-1, 0), SOUTH(0, 1),
	NORTH_EAST(1, -1), NORTH_WEST(-1, -1), SOUTH_EAST(1, 1), SOUTH_WEST(-1, 1);
	
	private Direction(int horizontal, int vertical) { 
		vector = Pair.builder().x(horizontal).y(vertical).build();
	}
	
	@Getter private Pair vector;
}