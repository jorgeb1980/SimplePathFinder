package pathfinder.logic;

import static pathfinder.logic.RandomGenerator.randomInt;

import java.util.List;

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
	
	public boolean addObstacle(Obstacle obstacle) {
		return verifyAndApply(obstacle);
	}
	
	public boolean addRandomObstacle(ObstacleSize size) {
		int average = (rows() + columns()) / 2;
		int length = average / size.fraction;
		if (length == 0) length = 1;
		int counter = 0;
		boolean obstacleSet = false;
		// We will only try MAX_TRIES times to set a random obstacle
		while (!obstacleSet && counter < MAX_TRIES) {
			int x = randomInt(columns());
			int y = randomInt(rows());
			Obstacle obstacle = null;
			// Try to verify it in all the possible orientations
			int counterOrientation = 0;
			while (!obstacleSet && counterOrientation < Orientation.values().length) {
				if (obstacle == null) obstacle = new Obstacle(x, y, length);
				else obstacle.flipObstacle();
				obstacleSet = verifyAndApply(obstacle);
				counterOrientation++;
			}
			counter++;
		}
		return obstacleSet;
	}

	// This method checks if the 
	private boolean verifyAndApply(Obstacle obstacle) {
		// We must check:
		//	+ The position of the obstacle against the grid boundaries
		//	+ The position of the obstacle against the existing obstacles
		List<Pair> coordinates = obstacle.generatePairs();
		boolean check = true;
		check = coordinates.stream().allMatch(coord -> isInside(coord))
				&& coordinates.stream().noneMatch(coord -> get(coord).isObstacle());
		if (check) for (Pair coord: coordinates) get(coord).setObstacle(true);
		return check;
	}
	
	public int columns() {
		return nodes[0].length;
	}
	
	public int rows() {
		return nodes.length;
	}
	
	public Node get(int x, int y) {
		if (isInside(x, y)) {
			return nodes[y][x];
		} else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public Node get(Pair coordinates) {
		return get(coordinates.getX(), coordinates.getY());
	}
	
	public boolean isInside(int x, int y) {
		return x >= 0 && x < columns() && y >= 0 && y < rows();
	}
	
	public boolean isInside(Pair coordinates) {
		return isInside(coordinates.getX(), coordinates.getY());
	}

	// Reset all nodes
	public void clean() {
		for (Node[] row: nodes) {
			for (Node node: row) {
				node.setAlreadyVisited(false);
			}
		}
	}
}
