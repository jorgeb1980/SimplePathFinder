package pathfinder.search;

import java.util.List;

import pathfinder.logic.NodeGrid;

interface IStrategy {

	/**
	 * The search strategy applies a new found search node to the remains of the
	 * list, leaving the list in a particular order
	 * @param childrenNodes Just generated new search nodes
	 * @param nodes Current master list of search nodes remaining
	 * @param grid Grid for context (certain strategies may require it)
	 */
	void apply(List<SearchNode> childrenNodes, List<SearchNode> nodes, NodeGrid grid);
}
