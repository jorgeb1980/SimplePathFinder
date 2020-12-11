package pathfinder.logic;

import static pathfinder.logic.RandomGenerator.randomInt;

import java.util.List;

import pathfinder.logic.Obstacle.Orientation;

public class NodeGrid {
	
	//----------------------------------------------------------
	// Class constants
	
	private static final int MAX_TRIES = 15;
	
	//----------------------------------------------------------
	// Class properties
	
	private Node[][] nodes;
	
	public enum ObstacleSize { 
		SMALL(10), MEDIUM(5), LARGE(2);
		
		ObstacleSize(int fraction) { this.fraction = fraction; }
		private int fraction;
	}
	
	
	//----------------------------------------------------------
	// Class methods
		
	public NodeGrid(int rows, int columns) {
		nodes = new Node[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				nodes[i][j] = new Node();
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int columns = nodes[0].length;
		for (int i = 0; i < columns + 2; i++) sb.append("-");
		sb.append("\n");
		for (Node[] row: nodes) {
			sb.append("|");
			for(Node node: row) {
				if (node.isObstacle()) sb.append("X");
				else sb.append("_");
			}
			sb.append("|\n");
		}
		for (int i = 0; i < columns + 2; i++) sb.append("-");
		return sb.toString();
	}
	
	public void addRandomObstacle(ObstacleSize size) {
		int rows = nodes.length;
		int columns = nodes[0].length;
		int average = (rows + columns) / 2;
		int length = average / size.fraction;
		if (length == 0) length = 1;
		int counter = 0;
		boolean obstacleSet = false;
		// We will only try MAX_TRIES times to set a random obstacle
		while (!obstacleSet && counter < MAX_TRIES) {
			int x = randomInt(columns);
			int y = randomInt(rows);
			Obstacle obstacle = null;
			// Try to verify it in all the possible orientations
			int counterOrientation = 0;
			while (!obstacleSet && counterOrientation < Orientation.values().length) {
				if (obstacle == null) obstacle = new Obstacle(x, y, length);
				else obstacle.flipObstacle();
				obstacleSet = verifyAndApply(obstacle, rows, columns);
				counterOrientation++;
			}
			counter++;
		}
	}

	// This method checks if the 
	private boolean verifyAndApply(Obstacle obstacle, int rows, int columns) {
		// We must check:
		//	+ The position of the obstacle against the grid boundaries
		//	+ The position of the obstacle against the existing obstacles
		List<Pair> coordinates = obstacle.generatePairs();
		boolean check = true;
		check = coordinates.stream().noneMatch(p -> p.getX() < 0 || p.getX() >= columns || p.getY() < 0 || p.getY() >= rows)
				&& coordinates.stream().noneMatch(p -> nodes[p.getY()][p.getX()].isObstacle());
		if (check) for (Pair p: coordinates) nodes[p.getY()][p.getX()].setObstacle(true);
		return check;
	}
}
