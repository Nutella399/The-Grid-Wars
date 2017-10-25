/**
 * 
 */
package edu.cpp.cs.cs141.fnlprojct;

import java.io.Serializable;
import java.util.Random;

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

public class Grid implements Serializable{
	
	/**
	 * The id for serialization  
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The grid itself
	 */
	private String grid[][];
	/*
	 * A Random number generator 
	 */
	private Random rand; 
	
	public static enum gridAgents{PLAYER, ENEMY, AMMO, RADAR, INVICIBILITY, ROOM, BRIEFCASE}
	
	/*
	 * Creates a new grid
	 * Constructor
	 */
	public Grid() {
		 grid = new String[9][9];
		 rand = new Random();
	}
	
	/**
	 * Return true if the position is filled
	 * @param gA
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean checkPosition(gridAgents gA,int row, int column) {
		boolean filledPosition = false;
		if(!(grid[row][column] == null) || (gridAgents.ENEMY == gA && row > 4 && column < 4)) {
			filledPosition = true;	
		}
		return filledPosition; 
	}
	
	/*
	 * Returns true if the position contains a room
	 */
	public boolean checkForRoom(int row, int column) {
		boolean room = false;
		if(grid[row][column] == "{ }") {
			room = true; 
		}
		return room; 
	}
	
	/*
	 * Returns a random row
	 */
	public int randomRow() {
		int row = rand.nextInt(8);
		return row; 
	}
	
	/*
	 * Returns a random column
	 */
	public int randomColumn() {
		int column = rand.nextInt(8);
		return column;
	}
	
	/**
	 * Puts null into the specified position
	 * @param row
	 * @param col
	 */
	public void putNull(int row, int col) {
		grid[row][col] = null; 
	}
	
	/*
	 * Returns the grid
	 */
	public String[][] getGrid() {
		return grid; 
	}
	
	/**
	 * Initializes the grid
	 * @param grid
	 */
	public void setGrid(String[][] grid) {
		this.grid = grid; 
	}
	
	/**
	 * Returns the letter for the grid agent
	 * @param gridAgents
	 * @return
	 */
	public String getGridAgentsLetter(gridAgents gridAgents) {
		String letter = ""; 
		switch(gridAgents){
		case PLAYER:
			letter = "P";
			break;
		case ENEMY: 
			letter = "E";
			break;
		case AMMO:
			letter = "A";
			break; 
		case RADAR: 
			letter = "R";
			break; 
		case INVICIBILITY: 
			letter = "I";
			break; 
		case ROOM:
			letter = "{ }";
			break; 
		case BRIEFCASE: 
			letter = "B";
			break; 
		} 
		return letter; 
			
	}
	
	/**
	 * Places the grid agent on to the board
	 * @param gridAgents
	 * @param row
	 * @param column
	 */
	public void placeGridAgents(gridAgents gridAgents, int row, int column) {
		String letter = getGridAgentsLetter(gridAgents);
		grid[row][column] = letter; 
		
	}
	
	/**
	 * Places briefcase into room
	 * @param gA
	 */
	public void placeGridAgents(gridAgents gA) {
		int row = randomRow(); 
		int column = randomColumn();
		switch(gA) {
		case BRIEFCASE: 
			if(checkForRoom(row, column)) {
				String letter = getGridAgentsLetter(gA);
				grid[row][column] = letter;
			}else {
				placeGridAgents(gA);
			}
			break;
		default: 
			if(!checkPosition(gA,row,column) ) {
				String letter = getGridAgentsLetter(gA);
				grid[row][column] = letter;
			}else {
				placeGridAgents(gA);
			}
			break;
		}
	}
}

