/**
 * 
 */
package edu.cpp.cs.cs141.fnlprojct;

import java.io.Serializable;
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

public class Player implements Serializable{
	/**
	 * This way we can serialize this object 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 *	The gun object we want to tie to the player 
	 */
	private Gun gun; 
	
	/**
	 * This is how we check to see if the player has ammo
	 */
	private boolean checkAmmo;
	
	/**
	 * This is how we check to see if the player picked up the radar
	 */
	private boolean hasRadar;
	
	/**
	 * This is how we check to see if the player picked up the invici
	 */
	private boolean hasInv;
	
	/*
	 * This is how we check to see if the player picked up the ammo
	 */
	private boolean hasAmmo;

	/**
	 * See how many lives the player has 
	 */
	private int lives;
	
	/**
	 * How many turns the player has for invicibility
	 */
	private int invTurns; 
	
	/**
	 * this is the row of the player
	 */
	private int row;
	
	/**
	 * The column of the player
	 */
	private int column; 
	
	/*
	 * Constructor for player class
	 */
	public Player(Gun gun) {
		hasRadar = false;
		hasInv = false; 
		hasAmmo = false;
		lives = 3; 
		row = 8;
		column = 0;  
		checkAmmo = true;
		this.gun = gun; 
	}
	
		/**
		 * This method uses a switch case to move the player in an indicated direction
		 * @param movement
		 */
		public void move(String movement) {
		String takeTurn = movement;
		switch(takeTurn) {
		
		case "w":
			if(row > 0) {
				row--;
			}
			break;
		case "a":
			if(column > 0) {
				column--;
			}
			break;
		case "s":
			if(row < 8){
				row++;
			}
			break;
		case "d":
			if(column < 8) {
				column++;
			}
			break;
		}
	}
	
		/*
		 * Calls the shoot method from the gun class to the player class
		 */
	public void shootGun() {
		gun.shot(); 
	}
	
	/*
	 * Checks to see if the player has ammo
	 */
	public boolean checkAmmo() {
		if (gun.getAmmo() == 0) {
			checkAmmo = false;
		}
		return checkAmmo;
	}
	
	/*
	 * This method adds ammo if the player picks up the ammo power up
	 */
	public void addAmmo() {
		if (gun.getAmmo() == 0) {
			setAmmo(1);
		} 	
	}
	
	/*
	 * Returns the row of the player
	 */
	public int getRow() {
		return row;
	}
	
	/*
	 * Returns the column of the player
	 */
	public int getColumn(){
		return column;
	}
	
	/*
	 * Returns the number of lives the player has left
	 */
	public int getLives() {
		return lives;
	}

	/*
	 * Removes a life if the player dies
	 */
	public void takeAwayALife() {
		lives --;
	}
	
	/**
	 * Initializes the number of turns the player has for invincibility
	 * @param ivTu
	 */
	public void setInvTurns(int invTurns) {
		this.invTurns = invTurns; 
	}
	
	/*
	 * Returns the number of turns the player has left for invincibility
	 */
	public int getInvTurns() {
		return invTurns;
	}
	
	/**
	 * Initializes the row the player is in
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
		
	}
	
	/**
	 * Initializes the column the player is in
	 * @param column
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	
	/*
	 * Subtracts a turn from invincibility
	 */
	public void takeATurn(){
		if(invTurns > 0){
			invTurns--;
		}
	}
	
	/**
	 * Tells the computer the player has a radar
	 * @param doesHasRadar
	 */
	public void setHasRadar(boolean hasRadar){
		this.hasRadar = hasRadar; 
	}
	
	/*
	 * Returns true id player has the radar
	 */
	public boolean getHasRadar(){
		return hasRadar; 
	}
	
	/**
	 * Saves if the player has invincibility
	 * @param hasInv
	 */
	public void setHasInv(boolean hasInv){
		this.hasInv = hasInv; 
	}
	
	/*
	 * Returns true if player has invinc
	 */
	public boolean getHasInv(){
		return hasInv; 
	}
	
	/**
	 * Keeps track if player has picked up ammo
	 * @param hasAmmo
	 */
	public void setHasAmmo(boolean hasAmmo){
		this.hasAmmo = hasAmmo;
	}
	
	/*
	 * Returns true if player has ammo
	 */
	public boolean getHasAmmo(){
		return hasAmmo; 
	}
	
	/*
	 * Returns amount of ammo
	 */
	public int getAmmo() {
		return gun.getAmmo(); 
	}
	
	/**
	 * Saves how much ammo player has
	 * @param ammo
	 */
	public void setAmmo(int ammo){
		gun.setAmmo(ammo);
	}
	
	/**
	 * Saves amount of lives player has
	 * @param lives
	 */
	public void setLives(int lives){
		this.lives = lives; 
	}
}
