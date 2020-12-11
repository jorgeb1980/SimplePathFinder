package pathfinder.search;

import lombok.Builder;
import lombok.Data;
import pathfinder.logic.Pair;

@Data
@Builder
public class SearchNode {

	private SearchNode parent;
	private Pair coordinates;
	
	public SearchNode apply(Direction direction) {
		return new SearchNode(this, coordinates.apply(direction.getVector()));
	}
}
