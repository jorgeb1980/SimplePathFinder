package pathfinder.logic;

import lombok.Getter;

// Obstacles cannot be in a "diagonal" position for simplicity
public enum Orientation { 
	NORTH(0, -1), EAST(1, 0), WEST(-1, 0), SOUTH(0, 1);
	
	private Orientation(int horizontal, int vertical) { 
		vector = Pair.builder().x(horizontal).y(vertical).build();
	}
	
	@Getter private Pair vector;
}