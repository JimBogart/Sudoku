import java.io.IOException;
import java.util.Scanner;

/**
 * This is a class SudokuInteractive
 * It contains the method play(String file) that allows the user to play and complete a sudoku puzzle that has been read in from a file. 
 * @author James Bogart
 * @version 2016/11/27
 */


public class SudokuInteractive extends Sudoku {

	private int[][] array;
	private final int[][] master;


	public int[][] getArray() {
		return array;
	}

	public void setArray(int[][] array) {
		this.array = array;
	}

	public SudokuInteractive(int[][] array) {
		this.array = array;
		this.master = new int[9][9];
	}
	
	public SudokuInteractive() {
		this.array = new int[9][9];
		this.master = new int[9][9];
	}
	
	/**
	 * reset method - will reset the game board to be equal to the initial version of the array (i.e. deleting all user input)
	 */
	
	public void reset(){
		
			
			for (int i = 0; i < getArray().length; i++) {

				for (int j = 0; j < getArray()[i].length; j++) {

					array[i][j] = getMaster()[i][j];

				}

			}		
		}
	
	/**
	 * setMaster - method will set the "master" copy of the board
	 * @param a The array of values for the initial Sudoku board to be played
	 */
	
	public void setMaster(int[][]a){
		
		for (int i = 0; i < a.length; i++) {

			for (int j = 0; j < a[i].length; j++) {

				master[i][j] = a[i][j];

			}

		}		
	}
	
	/**
	 * @return master copy of the Sudoku puzzle
	 */

	public int[][] getMaster(){
		
		return master;
	}
	
	/** 
	 * static method play - plays the sudoku game
	 * @param file The file name of the Sudoku puzzle the user wants to play with
	 */

	public static void play(String file) {
		
		// Prints out the welcome message of the game, and explains the inputs the user can provide

		System.out.println(
				" Hello and welcome to the Sudoku Interactive! Below is the Sudoku you are working on.\n To enter the values, enter the Row and Column, then a semicolon then the input.\n For example, a3:5 to enter 5 to position a3. \n If you'd like to exit, type exit. If you wish to reset the board, type reset. ");

		// Creates a new SudokuInteractive object
		
		SudokuInteractive sudoku = new SudokuInteractive();
		
		// Uses SudokuRead makeNewArray(file) method to generate a new array to start the game

		try {
			sudoku.setArray(SudokuRead.makeNewArray(file));

		} catch (IOException e) {

			e.printStackTrace();
		}
		
		// Master copy is made by callint setMaster method on the new array

		sudoku.setMaster(sudoku.getArray());
		
		// While the new Sudoku object is not filled and not completed correctly, call the "changeValue" method


		while (sudoku.isFilled()==false || checkPlayerSudoku(sudoku)==false){
			

					changeValue(sudoku);
			}
		

		//If the user enters the values correctly - the puzzle is printed and then a congratulations method is shown
		
		System.out.println(sudoku);
		System.out.println("Hooray! You have completed your sudoku puzzle!");

		}
	
	/**
	 * checkPlayerSudoku - a method that checks if the sudoku has been filled correctly
	 * @param bob The SudokuInteractive object that has been made 
	 * @return true if it is filled in correctly
	 */
	
	
	public static boolean checkPlayerSudoku(SudokuInteractive sudoku) {
		
		//Intialises a new boolean array called sc1
		
		
		boolean[][] sc1 = new boolean[3][9];
		
		// sc1 is set to SudokuCheck.check() - which accepts the current SudokuInteractive array with user inputs
		
		sc1 = SudokuCheck.check(sudoku.getArray());
		
		// For loop will go through the boolean array made. If any of the values return false, then false returned

		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 9; j++) {

				if ( sc1[i][j] == false) {

					return false;
				}

			}

		}
		
		//If false not returned, this means every row, column and subsquare has been filled in correctly -true is returned

		return true;

	}
	
	/**
	 * changeValue - a method that allows the user to enter new values
	 * @param bob The sudoku Interactive being played
	 */

	public static void changeValue(SudokuInteractive sudoku) {
		
		// Prints the puzzle board (as is)

		System.out.println(sudoku);
		
		// Initalises a new scanner object

		Scanner s = new Scanner(System.in);
		
		// Asks the user for input

		System.out.println("Please enter your input:");
		
		// Intitialises a string, input to be equal to whatever the user types next

		String input = s.next();
		
		// If statement - the input MUST be either exit, reset, or match the correct input form, else it is ignored according to the worksheet rules

		if (input.equals("exit") || input.equals("reset") || input.matches("[a-i][1-9][:][1-9]")) {
			
			// If input is exit, then "Goodbye!" is printed and the system exits and the scanner closed

			if (input.equals("exit")) {
				System.out.println("Goodbye!");
				System.exit(0);
				s.close();
			}
			
			// If input is reset, the method "reset" is called and the game board is set back to the original version

			else if (input.equals("reset")) {
				sudoku.reset();
			}
			
			// This now checks if the input is valid (by matching it to the regex expression, and also checks if the action is allowed

			else if ((allowedAction(sudoku, rowIndex(input), columnIndex(input)) && input.matches("[a-i][1-9][:][1-9]"))) {
				
				// If the action is allowed, then the input is added to the array

				sudoku.getArray()[rowIndex(input)][columnIndex(input)] = addedValue(input);

			}
			
			// If this action is not allowed (i.e. The puzzle already had a value in that position, then the user is told this
			
			else if (allowedAction(sudoku, rowIndex(input), columnIndex(input))==false){
				
				System.out.println("You can't place a value there! Have another go");
				System.out.println();
			}
		}
		
		// Method ends 

	}
	
	/**
	 * 
	 * @param sudoku The SudokuInteractive object being "played"
	 * @param rowToBeAddedTo The row the user wants to add the value to
	 * @param columnToBeAddedTo The column the user wants to add the value to
	 * @return true if action allowed, else false
	 */
		
	public static boolean allowedAction(SudokuInteractive sudoku, int rowToBeAddedTo, int columnToBeAddedTo){
		
		// If the value is already filled (i.e. not equal to 0 on the master copy, then false is returned
		
		if(sudoku.getMaster()[rowToBeAddedTo][columnToBeAddedTo]!= 0){
			
			return false;
		}
		
		// returns true if action is allowed
		
		return true;
	}
	
	/**
	 * 
	 * @param input The users input as a string
	 * @return the value the user wants to enter as an int
	 */
	
	public static int addedValue(String input) {
		
		// Sets the input character to an integer, by taking the last character of the user input and setting it to a int

		char INPUT = input.charAt(input.length() - 1);

		return Character.getNumericValue(INPUT);

	}
	
	/**
	 * columnIndex method
	 * @param input The users input as a string
	 * @return columnIndex The column that the user wants to enter the value at
	 */
	
	public static int columnIndex(String input) {
		
		// Initialises an array of characters, equal to the numbers of the columns, from 1 at index 0 to 9 at index 8

		int[] columnNumbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		int columnIndex = -1;

		for (int j = 0; j < columnNumbers.length; j++) {
			
			// Matches the second character of the input string to a number in the array and returns the index

			if (Character.getNumericValue(input.charAt(1)) == columnNumbers[j]) {

				columnIndex = j;

			}

		}
		
		// returns the column index

		return columnIndex;
	}
	
	/**
	 * 
	 * @param input The users input as a string
	 * @return rowIndex An int which is equal the correct row
	 */
	
	public static int rowIndex(String input) {
		
		//Initialises row index to -1

		int rowIndex = -1;
		
		// An array of characters, equal to the letters of each row - a=0 to i=8

		char[] rowLetters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i' };
		
		// gets the character at index 0 of the string - i.e which row the user wants to enter the value at

		char ROW = input.charAt(0);
		
		// searches through 0 to 8

		for (int i = 0; i < rowLetters.length; i++) {
			
			// if the character ROW is equal to something in the array, then the index is returned as an int

			if (ROW == rowLetters[i]) {

				rowIndex = i;
			}

		}
		
		// Returns the index of the row

		return rowIndex;

	}
	
	
	/**
	 * toString() method - prints out the sudoku puzzle in a reader friendly format
	 * 
	 */
	public String toString() {

		String[] rowLetters = { "a", "b", "c", "d", "e", "f", "g", "h", "i" };

		// Initialises a blank string

		String complete = "      1   2   3    4   5   6    7   8   9  \n";

		// String 1 is for the line at the top of the grid and after every third
		// row, has \n to to start a new line

		String string1 = "   ++===+===+===++===+===+===++===+===+===++\n";

		// String 2 is for the line between every row in the grid (has \n at the
		// end to start a new line

		String string2 = "   ++---+---+---++---+---+---++---+---+---++\n";

		// Separator at the start of the columns and after every third column

		String separator1 = "||";

		// Separator between each column

		String separator2 = "|";

		// Separator after the very last column of the grid (includes \n to
		// start a new line)

		String finalSeparator = "||\n";

		int rowID = 0;

		for (int i = 0; i < this.getArray().length; i++) {

			if (i % 3 == 0 && i != 8) {

				complete += string1;
			}

			else if (i % 3 != 0) {

				complete += string2;

			}

			String row = "";

			for (int j = 0; j < getArray()[0].length; j++) {

				int element = getArray()[i][j];

				String elementString = "";

				if (element == 0) {

					elementString = "   ";
				}

				else if (element != 0 && (element == master[i][j])) {

					elementString = "*" + Integer.toString(element) + "*";
				}

				else if (element != 0) {

					elementString = " " + Integer.toString(element) + " ";

				}

				if (j == 0) {

					row += rowLetters[rowID] + "  " + separator1 + elementString;

				}

				else if (j == 8) {

					row += separator2 + elementString + finalSeparator;

				}

				else if (j != 0 && j % 3 == 0 && j != 8) {

					row += separator1 + elementString;

				}

				else if (j % 3 != 3) {

					row += separator2 + elementString;
				}

			}

			rowID++;

			complete += row;

		}

		complete += string1;

		return complete;
	}

	public static void main(String[] args) {


		play("sudoku-gametest1.txt");


			


	}



}
