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

public class Gun implements Serializable{

	/**
	 * So we can serialize the object 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * The amount of ammo
	 */
	private int ammo; 
	
	/*
	 * Sets Ammo equal to the max ammo initially
	 * Constructor
	 */
	public Gun() {
		ammo = maxAmmo();
	}
	
	/*
	 * This is what happens when the player shoots the gun
	 */
	public int shot() {
		if (ammo == 1) {
			ammo = ammo - 1;
		}
		return ammo;
		
	}
	
	/*
	 * This indicates the maximum ammo a player can have
	 */
	public int maxAmmo() {
		int maxAmmo = 1;
		return maxAmmo;
	}
	
	/*
	 * This returns the amount of ammo the player has
	 */
	public int getAmmo() {
		return ammo;
	}
	
	/**
	 * This references the ammo object
	 * @param ammo
	 */
	public void setAmmo(int ammo){
		this.ammo = ammo;
	}
}
