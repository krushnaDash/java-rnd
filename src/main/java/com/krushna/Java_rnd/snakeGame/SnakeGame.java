package com.krushna.Java_rnd.snakeGame;

import java.util.Arrays;
import java.util.List;

public class SnakeGame {

	Snake snake;
	Board board;
	Direction currDirection;

	public  SnakeGame(int gridWidth, int gridHeight, List<Point> foodPositions) {
		this.snake = new Snake(new Point(0, 0));
		this.board = new Board(gridWidth, gridHeight, foodPositions);
	}

	public boolean moveSnake(Direction direction) {
		Point newHead=getNextPoint(snake.getHead(), direction);
		
		// check if the new head hit wall, then game over return false
		if(!board.isInside(newHead)) {
			return false;
		}
		// Move the snake
		return snake.move(newHead, board.canGrow(newHead));
		

	}

	Point getNextPoint(Point currentPoint, Direction driection) {
		switch (driection) {
		case UP:
			return new Point(currentPoint.x - 1, currentPoint.y);
		case DOWN:
			return new Point(currentPoint.x + 1, currentPoint.y);
		case LEFT:
			return new Point(currentPoint.x, currentPoint.y - 1);
		case RIGHT:
			return new Point(currentPoint.x, currentPoint.y + 1);
		default:
			return currentPoint;
		}
	}
	
	public void pirntBoard() {
		char [][] grid= new char [board.height][board.width];
		for(char[] row: grid) {
			Arrays.fill(row, '.');
		}
		// Draw the snake
		List<Point> body=snake.getBody();
		for(Point point : body) {
			grid[point.x][point.y]='s';
		}
		// Draw the food
		List<Point> foods=board.foods;
		for(Point point : foods) {
			grid[point.x][point.y]='f';
		}
		for (char[] row : grid) {
            System.out.println(new String(row));
        }
	}

}
