package com.krushna.Java_rnd.snakeGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameDriver {
	public static void main(String[] args) {
		
		
		List<Point> foodPositions = new ArrayList<>();
		
		foodPositions.add(new Point(0, 2));
		foodPositions.add(new Point(1, 2));
        SnakeGame game = new SnakeGame(5, 5, foodPositions);
        
        
        game.pirntBoard();
        System.out.println();

        game.moveSnake(Direction.RIGHT);
        game.pirntBoard();
        System.out.println();

        game.moveSnake(Direction.RIGHT); // Eats food
        game.pirntBoard();
        System.out.println();

        game.moveSnake(Direction.DOWN);
        game.pirntBoard();
	}
}
