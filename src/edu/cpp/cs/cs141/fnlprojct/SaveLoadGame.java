/**
 * 
 */
package edu.cpp.cs.cs141.fnlprojct;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

public class SaveLoadGame {
	
	/*
	 * Constructor
	 */
	
	public SaveLoadGame() {
	}

	/**
	 * Serializes the contents of the game
	 * @param fileName
	 * @param Game Engine
	 * @throws IOException
	 */
	public void saveGame(String fileName, GameEngine ge) throws IOException {
		String file = fileName + ".dat";
		FileOutputStream fos = new FileOutputStream(new File (file));
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(ge.getGridObject());
		oos.writeObject(ge.getPlayerObject());
		oos.close();
	}

	/**
	 * Deserializes the contents of the game
	 * @param fileName
	 * @param Game Engine
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void loadGame(String fileName, GameEngine ge) throws IOException, ClassNotFoundException {
		String file = fileName + ".dat";
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		ge.setGridObject((Grid) ois.readObject());
		ge.setPlayerObject((Player) ois.readObject());
		ois.close();	 
	}
}
