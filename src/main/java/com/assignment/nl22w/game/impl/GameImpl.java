package com.assignment.nl22w.game.impl;

import com.assignment.nl22w.game.Game;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Component
public class GameImpl implements Game {
	private static final char EMPTY_SPACE = ' ';
	private static final char TREE = '1';
	private static final char START_POSITION = 'X';

	private static final int[] ROW_OFFSETS = {-1, 0, 1, 0};
	private static final int[] COL_OFFSETS = {0, 1, 0, -1};

	private static final int MAX_MAP_SIZE = 11000;
	private static final int MIN_MAP_SIZE = 5;

	private char[][] map;
	private int rowCount;
	private int colCount;

	@Override
	public int escapeFromTheWoods(Resource resource) {
		if (!readMapFromResource(resource) || !isValidMap()) {
			return 0;
		}

		int[][] steps = new int[rowCount][colCount];
		int[] startPosition = findStartPosition();
		int minimumSteps = 0;

		if (startPosition != null) {
			minimumSteps = performBFS(startPosition, steps);
		}
		return minimumSteps;
	}

	private boolean readMapFromResource(Resource resource) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
			List<String> lines = reader.lines().toList();

			if (lines.isEmpty()) {
				return false;
			}

			rowCount = lines.size();
			colCount = lines.get(0).length();

			map = new char[rowCount][colCount];

			for (int i = 0; i < rowCount; i++) {
				String currentLine = lines.get(i);
				map[i] = currentLine.toCharArray();
			}

			return true;
		} catch (IOException e) {
			return false;
		}
	}

	private int[] findStartPosition() {
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				if (map[i][j] == START_POSITION) {
					return new int[]{i, j};
				}
			}
		}
		return null;
	}

	private boolean isValidMap() {
		return isValidMapShape() && isValidMapSize() && isValidMapSymbols();
	}

	private boolean isValidMapShape() {
		for (int i = 0; i < rowCount; i++) {
			if (map[i].length != colCount) {
				return false;
			}
		}
		return true;
	}

	private boolean isValidMapSize() {
		if (rowCount < MIN_MAP_SIZE || rowCount > MAX_MAP_SIZE || colCount < MIN_MAP_SIZE || colCount > MAX_MAP_SIZE) {
			return false;
		}
		return true;
	}

	private boolean isValidMapSymbols() {
		for (char[] row : map) {
			for (char symbol : row) {
				if (symbol != EMPTY_SPACE && symbol != TREE && symbol != START_POSITION) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean isExitPosition(int row, int col) {
		boolean isOnBorder = row == 0 || row == rowCount - 1 || col == 0 || col == colCount - 1;
		return isOnBorder && map[row][col] == EMPTY_SPACE;
	}

	private boolean isValidPosition(int row, int col) {
		boolean isWithinRows = row >= 0 && row < rowCount;
		boolean isWithinColumns = col >= 0 && col < colCount;
		return isWithinRows && isWithinColumns && map[row][col] == EMPTY_SPACE;
	}

	private int performBFS(int[] startPosition, int[][] steps) {
		Queue<int[]> queue = new ArrayDeque<>();
		int[][] visited = new int[rowCount][colCount];
		int minSteps = 0;
		int exitCount = 0;

		queue.offer(startPosition);
		steps[startPosition[0]][startPosition[1]] = 0;

		System.out.println();

		while (!queue.isEmpty()) {
			int[] position = queue.poll();
			System.out.println("position");
			System.out.println(position[1]);
			int row = position[0];
			int col = position[1];
			int currentSteps = steps[row][col];

			if (isExitPosition(row, col)) {
				exitCount++;
				if (exitCount > 1000) {
					return 0;
				}
				if (minSteps == 0 || currentSteps < minSteps) {
					minSteps = currentSteps;
				}
			}

			for (int i = 0; i < ROW_OFFSETS.length; i++) {
				int newRow = row + ROW_OFFSETS[i];
				int newCol = col + COL_OFFSETS[i];

				if (isValidPosition(newRow, newCol) && visited[newRow][newCol] == 0) {
					int[] newPosition = {newRow, newCol};
					queue.offer(newPosition);
					steps[newRow][newCol] = currentSteps + 1;
					visited[newRow][newCol] = 1;
				}
			}
		}
		return minSteps;
	}
}

