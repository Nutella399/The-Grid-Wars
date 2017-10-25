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

public class PowerUps {
	/*
	 * the row of the column
	 */
	private int row; 
	
	/*
	 * the column of the powerup
	 */
	private int column; 
	
	/*
	 * Constructor
	 */
	public PowerUps() {
		
	}
	
	/**
	 * This keeps track of the row the power up is in
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row; 
	}
	/**
	 * This keeps track of the column the power up is in
	 * @param column
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	/*
	 * This find the row the power up is in
	 */
	public int getRow() {
		return row;
	}
	
	/*
	 * this find the column the power up is in
	 */
	public int getColumn() {
		return column;
	}
}
