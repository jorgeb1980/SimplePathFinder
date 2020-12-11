package pathfinder.logic;

import lombok.Builder;
import lombok.Data;
import pathfinder.logic.Obstacle.Orientation;

@Data
@Builder
public class Pair {

	private int x;
	private int y;
	
	public Pair apply(Orientation orientation) {
		return new Pair(x + orientation.getHorizontal(), y + orientation.getVertical());
	}
}
