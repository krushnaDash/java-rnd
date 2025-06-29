package com.krushna.Java_rnd.snakeGame;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Snake {
	
	Deque<Point> body= new LinkedList<>();
	
	
	/**
	 * return false if there is collision else return true
	 * @param newHead
	 * @param grow
	 * @return
	 */
	public boolean move(Point newHead, boolean grow) {
		// collision, the time complexity can be improved here by maintaining a HashSet
		if(body.contains(newHead))
			return false;
		
		body.addFirst(newHead);
		if(!grow) {
			body.removeLast();
		}
		return true;
	}
	
	public Snake(Point startPosition) {
		body.addFirst(startPosition);
	}
	
	public Point getHead() {
		return body.peek();
	}
	public List<Point> getBody(){
		return new ArrayList<>(body);
	}
	

}
