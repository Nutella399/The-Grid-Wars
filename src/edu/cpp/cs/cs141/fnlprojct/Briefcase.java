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

public class Briefcase {
	
		/*
		 *  The row of the briefcase
		 */
		private int row;
		
		/*
		 * The column of the briefcase
		 */
		private int column; 
		
		/*
		 * Constructor
		 */
		public Briefcase() {
			
		}
		
		/**
		 * This sets the row the case is in
		 * @param row
		 */
		public void  setRow(int row){
			this.row = row; 
		}  
		
		/**
		 * This sets the column the case is in
		 * @param column
		 */
		public void setColumn(int column){
			this.column = column;
		}
		
		/*
		 * This helps us get the row the case is in
		 */
		public int getRow() {
			return row;
		}
		
		/*
		 * This helps up find the column the ase is in
		 */
		public int getColumn() {
			return column; 
		}
}
