package pathfinder.search;

import java.util.LinkedList;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import pathfinder.logic.NodeGrid;
import pathfinder.logic.Pair;

@Data
@Builder
public class Searcher {

	private NodeGrid grid;
	private Pair start;
	private Pair end;
	
	// Basic validation: are start and end blocked by an obstacle?
	public boolean isReachable() {
		return !(grid.get(start.getX(), start.getY()).isObstacle()) && !(grid.get(end.getX(), end.getY()).isObstacle());
	}
	
	/**
	 * This method searches the state space looking for the shortest path between start and
	 * end, considering every existing obstacle in the grid
	 * @return List of nodes that make the shortest path.  If the algorithm has not been to find a path, the
	 * list is empty.
	 */
	public List<SearchNode> search(Strategy strategy) {
		List<SearchNode> nodes = new LinkedList<>();
		// Make sure the path is clean
		grid.clean();
		if (isReachable()) {
			nodes.add(new SearchNode(null, start));
			SearchNode result = null;
			while(!nodes.isEmpty()) {
				SearchNode current = nodes.remove(0);
				if (current.getCoordinates().equals(end)) {
					result = current;
				} else {
					List<SearchNode> children = getChildren(current);
					strategy.apply(children, nodes, grid);
				}
			}
			// Run in the opposite direction
			while (result != null) {
				nodes.add(0, result);
				result = result.getParent();
			}
		}
		return nodes;
	}

	private List<SearchNode> getChildren(SearchNode current) {
		// Apply all possible movements
		List<SearchNode> children = new LinkedList<>();
		for (Direction direction: Direction.values()) {
			SearchNode candidate = current.apply(direction);
			if (validate(candidate)) {
				children.add(candidate);
				grid.get(candidate.getCoordinates()).setAlreadyVisited(true);
			}
		}
		return children;
	}
	
	private boolean validate(SearchNode candidate) {		
		return grid.isInside(candidate.getCoordinates())
				&& !grid.get(candidate.getCoordinates()).isObstacle()
				&& !grid.get(candidate.getCoordinates()).isAlreadyVisited();
	}
	
}
