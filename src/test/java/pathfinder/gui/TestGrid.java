package pathfinder.gui;

import static pathfinder.logic.NodeGrid.ObstacleSize.LARGE;
import static pathfinder.logic.NodeGrid.ObstacleSize.MEDIUM;
import static pathfinder.logic.NodeGrid.ObstacleSize.SMALL;

import org.junit.Test;

import pathfinder.logic.NodeGrid;
import pathfinder.logic.NodeGrid.ObstacleSize;

public class TestGrid {

	private void createGrid(int rows, int columns, ObstacleSize size) {
		System.out.printf("Creating grid with %s obstacles\n", size);
		NodeGrid grid = new NodeGrid(rows, columns);
		for (int i = 0; i < 5; i++) grid.addRandomObstacle(size);
		System.out.println(grid.toString());
	}

	@Test
	public void testPaintGridSmallObstacles() {
		createGrid(20, 30, SMALL);
	}
	@Test
	public void testPaintGridMediumObstacles() {
		createGrid(20, 30, MEDIUM);
	}
	
	@Test
	public void testPaintGridLargeObstacles() {
		createGrid(20, 30, LARGE);
	}
}
