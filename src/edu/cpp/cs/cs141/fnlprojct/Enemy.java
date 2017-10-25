/**
 * 
 */
package edu.cpp.cs.cs141.fnlprojct;

/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment Final
 *
 * Grid Wars: The Grid Awakens
 *
 * Team Vicious N' Delicious 
 *   @author Ruth
 * 	 @author Tarik
 * 	 @author Dylan
 * 	 @author Kenneth
 * 	 @author Ronald
 */

public class Enemy {
	
	/*
	 * The row for the enemy 
	 */
	private int row; 
	
	/*
	 * The column for the enemy
	 */
	private int column; 
	
	/*
	 * Constructor
	 */
	public Enemy() {
		
	}
	
	/*
	 * Tells the enemy to move up
	 */
	public void moveUp() {
		if(row > 0) {
			row--;
		} 
	}
	
	/*
	 * Tells the enemy to move down
	 */
	public void moveDown() {
		if(row < 8) {
			row++;
		} 
	}
	
	/*
	 * Tells the enemy to move right
	 */
	public void moveRight() {
		if(column < 8) {
			column++; 
		}
	}
	
	/*
	 * Tells the enemy to move left
	 */
	public void moveLeft() {
		if(column > 0) {
			column --; 
		}
	}
	
	/*
	 * Returns the row of the enemy
	 */
	public int getRow() {
		return row; 
	}
	
	/*
	 * Returns the column of the enemy
	 */
	public int getColumn() {
		return column; 
	}
	
	/**
	 * Keeps track of the enemy row
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row; 
	}
	
	/**
	 * Keeps track of the enemy column
	 * @param column
	 */
	public void setColumn(int column) {
		this.column = column;
	}
}
