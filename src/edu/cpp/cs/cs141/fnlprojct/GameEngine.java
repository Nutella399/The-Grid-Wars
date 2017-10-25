/**
 * 
 */
package edu.cpp.cs.cs141.fnlprojct;

import java.util.Random;

import edu.cpp.cs.cs141.fnlprojct.Grid.gridAgents;

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

public class GameEngine {

	/*
	 * The Player of the game
	 */
	private Player player; 
	
	/*
	 * The array of enemies 
	 */
	private Enemy[] enemies;
	
	/*
	 * The briefcase object
	 */
	private Briefcase bcase;
	
	/*
	 * The inicibility object
	 */
	private PowerUps inv;
	
	/*
	 * The ammo object
	 */
	private PowerUps ammo;
	
	/*
	 * The radar object 
	 */
	private PowerUps radar;
	
	/*
	 * The grid Object 
	 */
	private Grid grid;
	
	/*
	 * The gun object
	 */
	private Gun gun;
	
	/*
	 * The boolean for when the game is over
	 */
	private boolean gameOver;
	
	/*
	 * Creates a player
	 * Creates a player
	 * Creates a grid
	 * creates enemies
	 * creates all agents
	 * constructor
	 */
	public GameEngine() {
		gun = new Gun();
		player = new Player(gun);
		grid = new Grid();
		enemies = new Enemy[6];
		for(int i = 0; i < enemies.length; i++){
			enemies[i] = new Enemy();
		}
		bcase = new Briefcase();
		inv = new PowerUps();
		ammo = new PowerUps();
		radar = new PowerUps();
		gameOver = false;
	}
	
	/*
	 * Generates grid and places all grid agents
	 */
	public void generateGrid() {
		grid.placeGridAgents(gridAgents.PLAYER, player.getRow(), player.getColumn());
		grid.placeGridAgents(gridAgents.ROOM, 1, 1);
		grid.placeGridAgents(gridAgents.ROOM, 1, 4);
		grid.placeGridAgents(gridAgents.ROOM, 1, 7);
		grid.placeGridAgents(gridAgents.ROOM, 4, 1);
		grid.placeGridAgents(gridAgents.ROOM, 4, 4);
		grid.placeGridAgents(gridAgents.ROOM, 4, 7);
		grid.placeGridAgents(gridAgents.ROOM, 7, 1);
		grid.placeGridAgents(gridAgents.ROOM, 7, 4);
		grid.placeGridAgents(gridAgents.ROOM, 7, 7);
		for(int i = 0; i < 6; i++) {
			grid.placeGridAgents(gridAgents.ENEMY);
		}
		grid.placeGridAgents(gridAgents.AMMO);
		grid.placeGridAgents(gridAgents.INVICIBILITY);
		grid.placeGridAgents(gridAgents.RADAR);
		grid.placeGridAgents(gridAgents.BRIEFCASE);
	}
	
	/**
	 * Controls where the player can move and how the player moves
	 **/
	public void playerMove(String movement) {
		int row = player.getRow();
		int column = player.getColumn();
		playerPutItBack(player.getRow(), player.getColumn());
		player.move(movement);
		if (playerEnterRoom() || exitRoom(row, column)) {
			if (canEnterRoom(row, column) || canExitRoom()) {
				grid.placeGridAgents(gridAgents.PLAYER, player.getRow(), player.getColumn());
			} else {
				player.setRow(row);
				player.setColumn(column);
				grid.placeGridAgents(gridAgents.PLAYER, row, column);
			}
		} else {
			grid.placeGridAgents(gridAgents.PLAYER, player.getRow(), player.getColumn());
		}
	}
	
	/**
	 * Puts the Room back once the player enters it
	 * @param row
	 * @param col
	 */
	public void playerPutItBack(int row, int col) {
		if((row == 1 || row == 4 || row  == 7) && (col == 1 || col == 4 || col == 7)) {
			grid.putNull(row, col);
			grid.placeGridAgents(gridAgents.ROOM, row, col);
		}else {
			grid.putNull(row, col);
		}
	}
	
	/**
	 * Puts all agents back once enemy steps on it
	 * @param row
	 * @param col
	 */
	public void enemyPutItBack(int row, int col) {
		if(bcase.getRow() == row && bcase.getColumn() == col) {
			grid.putNull(row, col);
			grid.placeGridAgents(gridAgents.ROOM, row, col);
			grid.placeGridAgents(gridAgents.BRIEFCASE, row, col);
		}else if(inv.getRow() == row && inv.getColumn() == col && !pickedUpInv()) {
			grid.putNull(row, col);
			grid.placeGridAgents(gridAgents.INVICIBILITY, row, col);
		}else if(radar.getRow() == row && radar.getColumn() == col && !pickedUpRadar()) {
			grid.putNull(row, col);
			grid.placeGridAgents(gridAgents.RADAR, row, col);
		}else if(ammo.getRow() == row && ammo.getColumn() == col && !pickedUpAmmo()) {
			grid.putNull(row, col);
			grid.placeGridAgents(gridAgents.AMMO, row, col);
		}else if((row == 1 || row == 4 || row  == 7) && (col == 1 || col == 4 || col == 7)) {
			grid.putNull(row, col);
			grid.placeGridAgents(gridAgents.ROOM, row, col);
		}else{
			grid.putNull(row, col);
		}
	}
	
	/*
	 * Finds and sets enemies on the grid
	 */
	public void findEnemy(){
		String[][] Grid = grid.getGrid();
		for(int i = 0; i < enemies.length; i++) {
			for(int r = 0; r < 9; r++) {
				for(int c = 0; c < 9; c++){
					if(Grid[r][c] == "E" && (!AlreadyFound(enemies, r,c))) {
						enemies[i].setRow(r);
						enemies[i].setColumn(c);	
					}
				}
			}
		}
	}
	
	/*
	 * Finds all non moving agents on grid
	 */
	public void findThings(){
		String[][] Grid = grid.getGrid();
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++){
				if(Grid[r][c] == "I") {
					inv.setRow(r);
					inv.setColumn(c);
				} else if(Grid[r][c] == "R") {
					radar.setRow(r);
					radar.setColumn(c);
				}else if(Grid[r][c] == "A") {
					ammo.setRow(r);
					ammo.setColumn(c);
				}else if(Grid[r][c] == "B") {
					bcase.setRow(r);
					bcase.setColumn(c);
				}
			}
		}
	}
	
	/*
	 * Checks ahead of the player for enemies
	 */
	public boolean checkUp() {
		boolean Up = false;
		for (int i = 0; i < enemies.length; i++){
			if (enemies[i] == null) {
				Up = false;
			} else {
			int row = player.getRow() - enemies[i].getRow();
		if (player.getColumn() == enemies[i].getColumn() && row > 0) {
			Up = true;
				}
			}
		}
		return Up;
	}
	
	/*
	 *Checks below player for enemies
	 */
	public boolean checkDown() {
			boolean down = false;
			for (int i = 0; i < enemies.length; i++) {
				if (enemies[i] == null) {
					down = false;
				} else {
				int row = player.getRow() - enemies[i].getRow();
				if (player.getColumn() == enemies[i].getColumn() && row < 0) {
					down = true;
					}
				}
			}
			return down;
		}
	
	/*
	 * Checks right of player for enemies
	 */
	public boolean checkRight() {
		boolean right = false;
		for (int i = 0; i < enemies.length; i++) {
			if (enemies[i] == null) {
				right = false;
			} else {
			int col = enemies[i].getColumn() - player.getColumn();
			if (player.getRow() == enemies[i].getRow() && col > 0) {
				right = true;
					}
				}
			}
		return right;
		}
	
	/*
	 * Checks left of player for enemies
	 */
	public boolean checkLeft() {
		boolean left = false;
		for (int i = 0; i < enemies.length; i++) {
			if (enemies[i] == null) {
				left = false;
			} else {
			int col = player.getColumn() - enemies[i].getColumn();
			if (player.getRow() == enemies[i].getRow() && col > 0) {
				left = true;
					}
				}
			}
		return left;
		}
	/*
	 * Shoots ahead of player
	 */
	public void shootUp() {
		player.shootGun();
		for (int i = 0; i < enemies.length; i++) {
			if (enemies[i] != null) {
			int row  = player.getRow() - enemies[i].getRow();
			if (player.getColumn() == enemies[i].getColumn() && row  > 0) {
				grid.putNull(enemies[i].getRow(), enemies[i].getColumn());
				nullEnemy(i);
				}
			}
		}
	
	}
	
	/*
	 * Shoots right of player
	 */
	public void shootRight() {
		player.shootGun();
		for (int i = 0; i < enemies.length; i++) {
			if (enemies[i] != null) {
			int col = enemies[i].getColumn() - player.getColumn();
			if (player.getRow() == enemies[i].getRow() && col > 0) {
				grid.putNull(enemies[i].getRow(), enemies[i].getColumn());
				nullEnemy(i);
				}
			}
		}
	}
	
	/*
	 * Shoots left of player
	 */
	public void shootLeft() {
		player.shootGun();
		for (int i = 0; i < enemies.length; i++) {
			if (enemies[i] != null) {
			int col = player.getColumn() - enemies[i].getColumn();
			if (player.getRow() == enemies[i].getRow() && col > 0) {
				grid.putNull(enemies[i].getRow(), enemies[i].getColumn());
				nullEnemy(i);
				}
			}
		}
	}
	
	/*
	 * Shoots below player
	 */
	public void shootDown() {
		player.shootGun();
		for (int i = 0; i < enemies.length; i++) {
			if (enemies[i] != null) {
			int row = player.getRow() - enemies[i].getRow();
			if (player.getColumn() == enemies[i].getColumn() && row > 0) {
				grid.putNull(enemies[i].getRow(), enemies[i].getColumn());
				nullEnemy(i);
				}
			}
		}
	}
	
	/**
	 * Enemies are set to null
	 * @param index
	 */
	public void nullEnemy(int index){
		enemies[index] = null; 
	}
	
	/*
	 * Returns true if enemy can kill player
	 */
	public boolean canKillAdjacentPlayer1 () {
		boolean adj = false;
		
		for(int i = 0; i < enemies.length; i++) {
			if (enemies[i] == null) {
				adj = false;
			} else {
			int diff = (player.getRow() - enemies[i].getRow()); 
			if ((player.getColumn() == enemies[i].getColumn()) && ((diff == 1 || diff == -1 || diff == 0))) {
				adj = true;
				break;
				}
			}
		}
		
		return adj;
	}
	
	/*
	 * Returns true if enemy can kill player on the sides
	 */
	public boolean canKillAdjacentPlayer2() {
		boolean adj2 = false;
		for(int i = 0; i < enemies.length; i++){
			if (enemies[i] == null) {
				adj2 = false;
			} else {
			int diff = (player.getColumn() - enemies[i].getColumn());
			if ((player.getRow() == enemies[i].getRow()) && ((diff == 1) || (diff == -1 || diff == 0))) {
				adj2 = true;
				break;
				}
			}
		}
		return adj2;
	}
	
	/*
	 * Returns true if spy has no more lives
	 */
	public boolean checkSpyLife() {
		boolean live = true;
		
		if (player.getLives() == 0) {
			live = false;
		}
		return live;
	}
	
	/*
	 * Checks to see if the player can enter the room
	 */
	public boolean playerEnterRoom() {
		boolean enter = false;
		if ((player.getRow() == 1 || player.getRow() == 4 || player.getRow() == 7) && (player.getColumn() == 1 || player.getColumn() == 4
				|| player.getColumn() == 7)) {
				enter = true;
		}
		return enter;
	}
	
	/**
	 * Checks to see if the enemy can enter a room
	 * @param index
	 * @return
	 */
	public boolean enemyEnterRoom(int index) {
		boolean enter = false;
		if ((enemies[index].getRow() == 1 || enemies[index].getRow() == 4 || enemies[index].getRow() == 7) && (enemies[index].getColumn() == 1 || enemies[index].getColumn() == 4
				|| enemies[index].getColumn() == 7)) {
				enter = true;
		}
		return enter;
	}
	
	/**
	 * Returns true if you can enter the room or not
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean canEnterRoom(int row, int column) {
		boolean ent = false;
		
		if ((row == 0 || row == 3 || row == 6) && (column == 1 || column == 4
				|| column == 7)) {
			ent = true;
		}
		return ent;
	}
	
	/**
	 * Returns true if you can exit the room
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean exitRoom(int row, int column) {
		boolean exit = false;
		
		if ((row == 1 || row == 4 || row == 7) && (column == 1 || column == 4 || column == 7)) {
			exit = true;
		}
		
		return exit;
	}
	
	/*
	 * Returns true if the player can exit the room
	 */
	public boolean canExitRoom() {
		boolean ex = false;
		
		if ((player.getRow() == 0 || player.getRow() == 3 || player.getRow() == 6) && (player.getColumn() == 1 || player.getColumn() == 4
				|| player.getColumn() == 7)) {
				ex = true;
		}
		return ex;
	}
	
	/*
	 * Kills the player if the enemy is adjacent or not if the player has invinc
	 */
	public void adjacentKill() {
		int row = player.getRow();
		int column = player.getColumn();
		haveInvicibility();
		if ((canKillAdjacentPlayer1() || canKillAdjacentPlayer2()) && !(haveInvicibility())) {
			player.takeAwayALife();
			playerPutItBack(row, column);
			grid.placeGridAgents(gridAgents.PLAYER, 8, 0);
			player.setRow(8);
			player.setColumn(0);
		}
	}
	
	/**
	 * Returns true if enemy is not null
	 * @param array
	 * @param row
	 * @param column
	 * @return the enemy is already found 
	 */
	public boolean AlreadyFound(Enemy[] array, int row, int column){
		boolean FoundAlready = false; 
		for(int i = 0; i < array.length; i++) {
			if(enemies[i] !=null) {
				if((enemies[i].getRow() == row) && (enemies[i].getColumn() == column)){
					FoundAlready = true; 
				}
			} 
		}
		return FoundAlready; 
	}
	
	/**
	 * Returns true if multiple enemies are in a spot
	 * @param index
	 * @return If there is an enemy there
	 */
	public boolean checkForEnemy(int i) {
		boolean heIsThere = false;
		int numOfEnemiesInSpot = 0; 
		for(int j = 0; j < 6; j++){
			if (enemies[j] == null) {
				heIsThere = false;
			} else {
			if(enemies[i].getRow() == enemies[j].getRow() && enemies[i].getColumn() == enemies[j].getColumn()){
				numOfEnemiesInSpot++;
				if(numOfEnemiesInSpot > 1) {
					heIsThere = true;
				}else {
					heIsThere = false;
					}
				}
			}
		}
		return heIsThere;
	}
	
	/**
	 * Moves the enemy randomly
	 * @param index
	 */
	public void enemyRandMove(int index){
		int rand = new Random().nextInt(100);
		enemyPutItBack(enemies[index].getRow(), enemies[index].getColumn());
		if(rand <= 25) {
			enemies[index].moveUp(); 
		}else if(rand <= 50) {
			enemies[index].moveDown();
		}else if(rand <= 75) {
			enemies[index].moveRight();
		}else {
			enemies[index].moveLeft();
		}
	}
	
	/**
	 * Places the enemy randomly
	 * @param index
	 */
	public void enemyPlacement(int index) {
		if (enemies[index] != null) {
			int prevEnemyRow = enemies[index].getRow(); 
			int prevEnemyCol = enemies[index].getColumn(); 
			enemyRandMove(index);
			if(!checkForEnemy(index) || (enemyEnterRoom(index) && canEnterRoom(prevEnemyRow, prevEnemyCol))){
				grid.placeGridAgents(gridAgents.ENEMY, enemies[index].getRow(), enemies[index].getColumn());
			}else {
				enemies[index].setRow(prevEnemyRow);
				enemies[index].setColumn(prevEnemyCol);
				enemyPlacement(index);
			
			}
		}
	}
	
	/*
	 * Places the enemy
	 */
	public void enemyMove() {
		for(int i = 0; i < 6; i++) {
			enemyPlacement(i);
		}
	}
	
	/*
	 * Ends the game if these cnsitions are met
	 */
	public boolean gameOver() {
		if (player.getRow() == bcase.getRow() && player.getColumn() == bcase.getColumn() || player.getLives() == 0) {
			gameOver = true;
		}
		return gameOver;
	}
		
	/*
	 * True if player picks up invinc
	 */
	public boolean pickedUpInv() {
		if((player.getRow() == inv.getRow()) && (player.getColumn() == inv.getColumn())) {
			player.setInvTurns(5);
			player.setHasInv(true);
		} 
		return player.getHasInv();
	} 
	
	/*
	 * True if player picks up ammo
	 */
	public boolean pickedUpAmmo() { 
		if((player.getRow() == ammo.getRow()) && (player.getColumn() == ammo.getColumn())) {
			player.addAmmo();
			player.setHasAmmo(true);
		}
		return player.getHasAmmo();
	}
	
	/*
	 * True if player picks up radar
	 */
	public boolean pickedUpRadar(){ 
		if((player.getRow() == radar.getRow()) && (player.getColumn() == radar.getColumn())) {
			player.setHasRadar(true);;
		}
		return player.getHasRadar();
	}
	
	/*
	 * True if player has invinc
	 */
	public boolean haveInvicibility(){
		boolean invi = false; 
		pickedUpInv();
		if(player.getInvTurns() > 0) {
			invi = true; 
		}
		return invi; 
	}
		
	/*
	 * Gets the grid from grid class
	 */
	public String[][] getGrid() {
		return grid.getGrid(); 
	}
	
	/*
	 * returns player row
	 */
	public int getPlayerRow() {
		return player.getRow(); 
	}
	
	/*
	 * returns player column
	 */
	public int getPlayerColumn() {
		return player.getColumn();
	}
	
	/**
	 * Saves player lives
	 * @param lives
	 */
	public void setPlayerLives(int lives) {
		player.setLives(lives);
	}
	
	/*
	 * gets case row
	 */
	public int getBriefcaseRow() {
		return bcase.getRow(); 
	}
	
	/*
	 * gets case column
	 */
	public int getBriefcaseColumn() {
		return bcase.getColumn();
	}
	
	/*
	 * gets player lives
	 */
	public int getPlayerLife() {
		return player.getLives();
	}
	
	/*
	 * Subtracts from invinc power up
	 */
	public void takeATurn() {
		player.takeATurn();
	}
	
	/*
	 * gets number of invince turns
	 */
	public int getInvTurns() {
		return player.getInvTurns();
	}
	
	/**
	 * saves if game is over or not
	 * @param isGameOver
	 */
	public void setGameOver(boolean isGameOver) {
		gameOver = isGameOver; 
	}
	
	/*
	 * gets player ammo
	 */
	public int getPlayerAmmo(){
		return player.getAmmo();
	}
	
	/*
	 * gets the gun object
	 */
	public Object getGunObject() {
		return gun; 
	}
	
	/*
	 * gets the player object
	 */
	public Object getPlayerObject() {
		return player; 
	}
	
	/*
	 * gets the grid object
	 */
	public Object getGridObject(){
		return grid; 
	}
	
	/**
	 * saves the player object
	 * @param player
	 */
	public void setPlayerObject(Player player) {
		this.player = player; 
	}
	
	/**
	 * saves gun object
	 * @param gun
	 */
	public void setGunObject(Gun gun){
		this.gun = gun;
	}
	
	/**
	 * saves grid object
	 * @param grid
	 */
	public void setGridObject(Grid grid){
		this.grid = grid; 
	}
}
