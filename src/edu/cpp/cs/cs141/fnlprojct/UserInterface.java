/**
 * 
 */
package edu.cpp.cs.cs141.fnlprojct;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

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

public class UserInterface {

	private GameEngine ge; 
	
	private SaveLoadGame slg; 
	
	private Scanner keyboard; 
	
	boolean pickedUpInv; 
	
	boolean pickedUpAmmo;
	
	boolean pickedUpRadar; 
	
	
	/*
	 * constructor
	 */
	public UserInterface() {
		ge = new GameEngine();
		slg = new SaveLoadGame();
		keyboard = new Scanner(System.in);
		pickedUpInv = true;
		pickedUpAmmo = true;
		pickedUpRadar = true; 
		
		
	}
	
	/*
	 * Displays the main menu to start game or load game
	 */
	public void mainMenu() throws IOException, ClassNotFoundException{
		int option = getUserOption(); 
		switch(option){
			case 1: 
				storyLine();
				getDirections();
				key();
				runGame();
				break;
			case 2: 
				System.out.println("What is the name of the file you would like to load.");
				keyboard.nextLine();
				String fileName = keyboard.nextLine();
				try{
					slg.loadGame(fileName, ge);
				}catch(FileNotFoundException e) {
					System.out.println("That file doesn't exist");
					mainMenu();
				}finally{
					runOldGame();
				}
				break;
			case 3: 
				ge.setGameOver(true);
				break;
			case 4: 
				runDebugGame();
				break;
			default: 
				System.out.println("INVAILD OPTION");
				break; 
		}
	}
	
	/*
	 * Exits current game
	 */
	
	public void exitGame() throws IOException, ClassNotFoundException{
		mainMenu(); 
	}
	/*
	 * Runs the game the user is playing
	 */
	public void runGame() throws IOException, ClassNotFoundException {
		ge.generateGrid();
		ge.findThings();
		ge.findEnemy();
		while(!ge.gameOver()) {
			printVisibleGrid(ge.getGrid());
			printStats();
			pickedPowerUp();
			chooseAction();
			ge.takeATurn();
			ge.adjacentKill();
			ge.enemyMove();
			if (ge.gameOver() && ge.getPlayerLife() != 0) {
				System.out.println("CONGRAGULATIONS! YOU HAVE ESCAPED!");
			} else if (ge.gameOver() && ge.getPlayerLife() == 0) {
				System.out.println("You have no more lives!");
			}
		}
	}
	/*
	 * Runs the game but uses a debugged grid
	 */
	public void runDebugGame() throws ClassNotFoundException, IOException {
		ge.findThings();
		ge.findEnemy();
		while(!ge.gameOver()) {
			printDebugGrid(ge.getGrid());
			printStats();
			pickedPowerUp();
			chooseAction();
			ge.takeATurn();
			ge.adjacentKill();
			ge.enemyMove();
			if (ge.gameOver() && ge.getPlayerLife() != 0) {
				System.out.println("CONGRAGULATIONS! YOU HAVE ESCAPED!");
			} else if (ge.gameOver() && ge.getPlayerLife() == 0) {
				System.out.println("You have no more lives!");
			}
		}
	}
	/*
	 * Runs the old game after player has saved
	 */
	public void runOldGame() throws IOException, ClassNotFoundException {
		ge.findThings();
		System.out.println("It came here");
		ge.findEnemy();
		while(!ge.gameOver()) {
			printVisibleGrid(ge.getGrid());
			printStats();
			pickedPowerUp();
			chooseAction();
			ge.takeATurn();
			ge.adjacentKill();
			ge.enemyMove();
			if (ge.gameOver() && ge.getPlayerLife() != 0) {
				System.out.println("CONGRAGULATIONS! YOU HAVE ESCAPED!");
			} else if (ge.gameOver() && ge.getPlayerLife() == 0) {
				System.out.println("You have no more lives!");
			}
		}
	}
	/*
	 * Player can choose to move, look shoot, or save
	 */
	public void chooseAction() throws IOException, ClassNotFoundException {
		int actionChoice = getPlayerAction();
		
		switch(actionChoice) {
		case 1:
			playerLook();
			chooseAction();
			break;
		case 2:
			ge.playerMove(getMovement());
			break;
		case 3:
			shooting();
			break;
		case 4: 
			chooseIfYouSaveOrNot();
			break;
		default: 
			System.out.println("That isn't a valid option");
			chooseAction();
			break; 
		}
	}
	/*
	 * The Player choices if they would like to save or not
	 */
	public void chooseIfYouSaveOrNot() throws IOException, ClassNotFoundException {
		int choice; 
		
		System.out.println("Do you want to save? " + "\n 1. Yes" + "\n 2. No");
		choice = keyboard.nextInt();
		switch(choice) {
		case 1: 
			System.out.println("What would you like to title the save file.");
			keyboard.nextLine();
			String fileName = keyboard.nextLine();
			slg.saveGame(fileName, ge);
			exitGame();
			break; 
		case 2: 
			exitGame(); 
			break; 
		default: 
			System.out.println("That isn't a choice try again");
			chooseIfYouSaveOrNot();
		}
	}
	
	/*
	 * Asks where the player wants to shoot
	 */
	public void shooting() {
		int trigger = shootDirection();
		
		switch(trigger) {
		
		case 1:
			ge.shootUp();
			break;
		case 2:
			ge.shootRight();
			break;
		case 3:
			ge.shootLeft();
			break;
		case 4:
			ge.shootDown();
			break;
		default:
			System.out.println("That isn't a valid option");
			shooting();
			break;
		}
	}
	
	/*
	 * Displays directions to shoot
	 */
	public int shootDirection() {
		int shot;
		
		System.out.println("1: Shoot Up");
		System.out.println("2: Shoot Right");
		System.out.println("3: Shoot Left");
		System.out.println("4: Shoot Down");
		shot = keyboard.nextInt();
		return shot;
	}

	/*
	 * Displays what the player wants to do
	 */
	public int getPlayerAction() {
		int action;
		System.out.println(" 1: Look" + "\n 2: Move" + "\n 3. Shoot" + "\n 4. Return to Main Menu");
		action = keyboard.nextInt();
		return action;
	}
	
	/*
	 * Displays where player wants to look
	 */
	public int getPlayerLook() {
		int look;
		System.out.println("1: Look Up");
		System.out.println("2: Look Right");
		System.out.println("3: Look Left");
		System.out.println("4: Look Down");
		look = keyboard.nextInt();
		return look;
	}
	
	/*
	 * Displays where player wants to move
	 */
	public String getMovement() {
		String takeTurn;
		System.out.println("w: Move Forward");
		System.out.println("a: Move Left");
		System.out.println("s: Move BackWards");
		System.out.println("d: Move Right"); 
		keyboard.nextLine();
		takeTurn = keyboard.nextLine();
		return takeTurn;
	}
	
	/*
	 * Displays main menu
	 */
	public int getUserOption(){
		System.out.println("***************************");
		System.out.println("Welcome to Grab The Briefcase 1.0!");
		System.out.println("\n Select from the following options: " + "\n 1. New Game" 
		+ "\n 2. Load Game" + "\n 3. Quit");
		int option = keyboard.nextInt();
		return option; 
	}
	
	/*
	 * Checks if there is an enemy if the same row or column
	 */
	public void playerLook() {
		int lookChoice = getPlayerLook();
		
		switch(lookChoice) {
		
		case 1:
			if (ge.checkUp()) {
				System.out.println("There is an enemy ahead!");
			} else {
				System.out.println("All Clear");
			}
			break;
		case 2:
			if (ge.checkRight()){
				System.out.println("There is an enemy to your right!");
			} else {
				System.out.println("All Clear");
			}
			break;
		case 3:
			if (ge.checkLeft()) {
				System.out.println("There is an enemy to your left!");
			} else {
				System.out.println("All Clear");
			}
			break;
		case 4:
			if (ge.checkDown()) {
				System.out.println("There is an enemy below you!");
			} else {
				System.out.println("All Clear");
			}
			break;
		default: 
			System.out.println("That isn't a valid option");
			playerLook();
			break;
		}
	}
	

	
	/**
	 * Displays debugged grid 
	 * @param grid 
	 */
	public void printDebugGrid(String[][] grid) {
		for(int r = 0; r < 9; r++) {		
			for(int c = 0; c < 9; c++) {
				printGrid(grid, r, c);
			}
			System.out.println();
		}
	}
	
	/**
	 * Prints the grid the player sees 
	 * @param grid
	 */
	public void printVisibleGrid(String[][] grid) {
		int playRow = ge.getPlayerRow(); 
		int playColumn= ge.getPlayerColumn();
		for(int row = 0; row < 9; row++) {
			for(int column = 0;column < 9; column++ ) {
				if(aroundThePlayer(row, column, playRow, playColumn) || checkForRadar(row,column)) {
							printGrid(grid,row,column);
					}else{
						System.out.print("[*]");
					}
			}
			System.out.println();
		}
	}
	
	
	/*
	 * Prints the in game stats of the player
	 */
	public void printStats() {
		System.out.println("Player Lives: " + ge.getPlayerLife());
		System.out.println("Player Ammo: " + ge.getPlayerAmmo());
		if(ge.haveInvicibility()) {
			System.out.println("You have " + ge.getInvTurns() + " turns left of invicibility");
		}
	} 
	
	/**
	 * Allows the player to see ahead 2 spaces
	 * @param row 
	 * @param column
	 * @param playRow
	 * @param playColumn
	 * @return
	 */
	public boolean aroundThePlayer(int row, int column, int playRow, int playColumn){
		boolean blockAroundPlayer = false; 
		if(((row >= (playRow-2) && row <= (playRow+2)) && (column >= (playColumn-2) && column <= (playColumn+2)))){
			blockAroundPlayer = true;
		}
		return blockAroundPlayer;
	}
	
	/**
	 *  Return true if player has radar
	 * @param grid
	 * @param row
	 * @param column
	 */
	public boolean checkForRadar(int row, int column){
		boolean hasRadar = false;
		if((ge.pickedUpRadar() && row == ge.getBriefcaseRow() && column == ge.getBriefcaseColumn())) {
			hasRadar = true; 
		}
		return hasRadar; 
	}
	
	/**
	 * Prints the entire grid with all the agents
	 * @param grid
	 * @param row
	 * @param column
	 */
	public void printGrid(String[][] grid, int row, int column) {
		if(grid[row][column] == "P") {
			System.out.print("|P|");
		}else if(grid[row][column] == "E") {
			System.out.print("|E|");
		}else if(grid[row][column] == "A") {
			System.out.print("|A|");
		}else if(grid[row][column] == "{ }") {
			System.out.print("{ }");
		}else if(grid[row][column] == "I") {
			System.out.print("|I|");
		}else if(grid[row][column] == "R") {
			System.out.print("|R|");
		}else if(grid[row][column] == "B") {
			System.out.print("{B}");
		}else{
			System.out.print("| |");
		}
	}
	
	/*
	 * Prints the key to play the game
	 */
	public void key() {
		System.out.println("******************************");
		System.out.println("P - Player");
		System.out.println("E - Enemy");
		System.out.println("A - Ammo Refill");
		System.out.println("I - Invincibilty");
		System.out.println("R - Radar");
		System.out.println("B - Briefcase");
		System.out.println("{} - Room");
	}
	
	public void storyLine(){
		
	}
	
	/*
	 * Displays instructions to play game
	 */
	public void getDirections() {
		System.out.println("*******************************");
		System.out.println("1. Acquire the breifcase");
		System.out.println("2. Do not approach the enemies");
		System.out.println("3. Enter the rooms from the north");
		System.out.println("4. You may look in any direction around you");
		System.out.println("5. Shoot any enemies who are in your way if you have a bullet");
		System.out.println("6. Pick up power ups to aid in your journey");
		System.out.println(" - Additional Bullets: replenishs bullet if you do not have ammo");
		System.out.println(" - Invicibility: You are granted wiht invulnerability for 5 turns");
		System.out.println(" - Radar: Reveals the room in which the briefcase is located");
	}
	
	/*
	 * Displays message if power up is picked up
	 */
	public void pickedPowerUp(){
		if(ge.pickedUpInv() && pickedUpInv){
			System.out.println("You picked up Invincibility!");
			pickedUpInv(false);
		}else if(ge.pickedUpAmmo() && pickedUpAmmo){
			System.out.println("You picked up Ammo!");
			pickedUpAmmo(false);
		}else if(ge.pickedUpRadar() && pickedUpRadar){
			System.out.println("You picked up the Radar!");
			pickedUpRadar(false);
		}
	}
	
	/*
	 * True if invinc is picked up
	 */
	public boolean pickedUpInv(boolean pickedUp){
		pickedUpInv = pickedUp; 
		return pickedUpInv;
	}
	
	/*
	 * True if ammo is picked up
	 */
	public boolean pickedUpAmmo(boolean pickedUp){
		pickedUpAmmo = pickedUp; 
		return pickedUpAmmo;
	}
	
	/*
	 * True if Radar is picked up
	 */
	public boolean pickedUpRadar(boolean pickedUp){
		pickedUpRadar = pickedUp; 
		return pickedUpRadar;
	}
}
