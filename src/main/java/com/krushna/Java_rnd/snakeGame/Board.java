package com.krushna.Java_rnd.snakeGame;

import java.util.List;

public class Board {
	int width, height;
	List<Point> foods;
	
	
	
	public Board(int width, int height, List<Point> foods) {
		super();
		this.width = width;
		this.height = height;
		this.foods = foods;
	}
	/**
	 * This will check if the point is inside the board
	 * true inside
	 * false cross
	 * @param p
	 * @return
	 */
	public boolean isInside(Point p) {
		return p.x>=0&& p.y >=0 && p.x < width && p.y < height;
			
	}
	public boolean canGrow(Point newHead) {
		// snake will grow
		if(foods.contains(newHead)) {
			// remove the food points and return true;
			foods.remove(newHead);
			return true;
		}
		return false;
	}


}
