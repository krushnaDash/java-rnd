package com.krushna.Java_rnd.snakeGame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SnakeTest {
	

	
	@Test
	void givenNewPoint_WhenMove_ThenRetrunTrue() {
		Snake snake = new Snake(new Point(0, 0));
		boolean expected=true;
		boolean actual=snake.move(new Point(0, 1), false);
		assertEquals(expected, actual);
	}
	
    @Test
	void givenExistingPoint_WhenMove_ThenRetrunFalse() {
    	Snake snake = new Snake(new Point(0, 0));
    	boolean expected=false;
    	snake.move(new Point(0, 1), true);
		snake.move(new Point(0, 2), true);// Up
		snake.move(new Point(0, 3), true);//Up
		snake.move(new Point(1, 3), true);//right
		boolean actual=snake.move(new Point(1, 3), true);//left
		assertEquals(expected, actual);
	}

}
