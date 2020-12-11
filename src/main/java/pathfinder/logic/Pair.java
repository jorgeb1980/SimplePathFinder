package pathfinder.logic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pair {

	private int x;
	private int y;
	
	public Pair apply(Pair vector) {
		return new Pair(x + vector.x, y + vector.y);
	}

}
