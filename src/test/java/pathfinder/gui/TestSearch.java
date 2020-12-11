package pathfinder.gui;

import static pathfinder.search.Strategy.DEPTH;

import java.util.List;

import org.junit.Test;

import pathfinder.logic.NodeGrid;
import pathfinder.logic.Pair;
import pathfinder.search.SearchNode;
import pathfinder.search.Searcher;
import pathfinder.search.Strategy;

public class TestSearch {

	
	@Test
	public void testSearch1() {
		NodeGrid grid = new NodeGrid(10, 10);
		Pair start = Pair.builder().x(0).y(0).build();
		Pair end = Pair.builder().x(9).y(9).build();
		Searcher s = Searcher.builder().grid(grid).start(start).end(end).build();
		for (Strategy strategy: Strategy.values()) {
			System.out.printf("Applying %s strategy to small empty grid\n", strategy);
			List<SearchNode> path = s.search(strategy);
			System.out.println(AsciiPresentation.toAscii(grid, path));
		}
	}
}
