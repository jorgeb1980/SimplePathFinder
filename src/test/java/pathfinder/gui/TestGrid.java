package pathfinder.gui;

import org.junit.Test;

import pathfinder.logic.NodeGrid;
import pathfinder.logic.NodeGrid.ObstacleSize;

public class TestGrid {


	@Test
	public void testPaintGridSmallObstacles() {
		NodeGrid grid = new NodeGrid(20, 30);
		for (int i = 0; i < 5; i++) grid.addRandomObstacle(ObstacleSize.SMALL);
		System.out.println(grid.toString());
	}
	
	@Test
	public void testPaintGridMediumObstacles() {
		NodeGrid grid = new NodeGrid(20, 30);
		for (int i = 0; i < 5; i++) grid.addRandomObstacle(ObstacleSize.MEDIUM);
		System.out.println(grid.toString());
	}
	
	@Test
	public void testPaintGridLargeObstacles() {
		NodeGrid grid = new NodeGrid(20, 30);
		for (int i = 0; i < 5; i++) grid.addRandomObstacle(ObstacleSize.LARGE);
		System.out.println(grid.toString());
	}
}
