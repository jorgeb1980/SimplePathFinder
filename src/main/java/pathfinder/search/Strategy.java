package pathfinder.search;

import java.util.List;

import pathfinder.logic.NodeGrid;

public enum Strategy {

	DEPTH(new IStrategy() {
		@Override
		public void apply(List<SearchNode> childrenNodes, List<SearchNode> nodes, NodeGrid grid) {
			nodes.addAll(0, childrenNodes);			
		}
	}), 
	WIDTH(new IStrategy() {
		@Override
		public void apply(List<SearchNode> childrenNodes, List<SearchNode> nodes, NodeGrid grid) {
			nodes.addAll(childrenNodes);
		}
	});
	
	private IStrategy strategy;
	
	Strategy(IStrategy strategy) { this.strategy = strategy; }
	
	public void apply(List<SearchNode> childrenNodes, List<SearchNode> nodes, NodeGrid grid) {
		strategy.apply(childrenNodes, nodes, grid);
	}

}
