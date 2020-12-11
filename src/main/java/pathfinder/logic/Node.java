package pathfinder.logic;

import lombok.Getter;
import lombok.Setter;

public class Node {
	@Getter @Setter private boolean obstacle;
	@Getter @Setter private boolean alreadyVisited;
}
