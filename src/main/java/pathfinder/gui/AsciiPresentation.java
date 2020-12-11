package pathfinder.gui;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import pathfinder.logic.Node;
import pathfinder.logic.NodeGrid;
import pathfinder.logic.Pair;
import pathfinder.search.SearchNode;

public class AsciiPresentation {

	public static String toAscii(NodeGrid grid, List<SearchNode> nodes) {
		List<Pair> pathCoordinates = new LinkedList<>();
		if (nodes != null) pathCoordinates = nodes.stream().map(n -> n.getCoordinates()).collect(Collectors.toList());
		StringBuilder sb = new StringBuilder();
		final int columns = grid.columns();
		final int rows = grid.rows();
		for (int i = 0; i < columns + 2; i++) sb.append("-");
		sb.append("\n");
		for (int row = 0; row < rows; row++) {
			sb.append("|");
			for(int column = 0; column < columns; column++) {
				Node node = grid.get(column, row);
				if (node.isObstacle()) sb.append("X");
				else if (pathCoordinates.contains(Pair.builder().x(column).y(row).build())) sb.append("O");
				else sb.append("_");
			}
			sb.append("|\n");
		}
		for (int i = 0; i < columns + 2; i++) sb.append("-");
		return sb.toString();
	}
}
