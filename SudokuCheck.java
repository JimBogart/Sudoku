import java.io.IOException;
import java.util.Arrays;


/**
 * This is a class SudokuCheck.
 * 
 * 
 * @author James Bogart
 * @version 2016/11/24
 *
 */

public class SudokuCheck {

	/**
	 * Method check
	 * 
	 * @param sudoku  A object of class Sudoku
	 * @return result An array of Boolean values indicating if rows, columns and
	 *  subsquares are filled in correctly
	 * 
	 */

	public static boolean[][] check(int[][] array) {

		// Initialises a boolean array with 3 columns and 9 rows

		boolean[][] result = new boolean[3][9];

		// for loop to iterate through the array result

		for (int i = 0; i < 9; i++) {

			// Sets the value in the 1-d array to the true if that row, column
			// or sub-square is filled correctly, else it is set to false

			result[0][i] = rowCheck(array, i);
			result[1][i] = columnCheck(array, i);
			result[2][i] = subSquareCheck(array, i);

		}

		return result;

	}

	/**
	 * Static method RowCheck
	 * 
	 * @param sudoku
	 * @param x The row to be checked
	 * @return boolean
	 */

	public static boolean rowCheck(int[][] array, int row) {

		// Initialises an element to be checked

		int checkedElement;

		for (int column = 0; column < 9; column++) {

			// Sets the checked element to an element in the row. Will increase
			// by 1 with each iteration

			checkedElement = array[row][column];

			// Initialises and sets counter to 0

			int counter = 0;

			for (int column1 = 0; column1 < 9; column1++) {

				// Compares the checked element to each element in the row. If
				// true, counter is increased to 1

				if (checkedElement == array[row][column1]) {

					counter++;
				}
				
				if(array[row][column1]==0){
					
					return false;
				}

			}

			// If statement - if the counter is any other value than 1, the
			// sudoku has not been filled correctly

			if (counter != 1) {

				// If the counter is != 1 for any value, the row is not filled
				// correctly and false returned

				return false;
			}
		}

		// If all values filled correctly, returns true

		return true;
	}

	/**
	 * Static method columnCheck
	 * 
	 * @param sudoku
	 * @param x
	 *            The column to be check
	 * @return boolean
	 */

	public static boolean columnCheck(int[][] array, int column) {

		// Initialises variable checkedelement

		int checkedElement;

		for (int row = 0; row < 9; row++) {

			// Sets the variable to the first element in the column, then the
			// next with each iteration

			checkedElement = array[row][column];

			// counter initialised and set to 0

			int counter = 0;

			for (int row1 = 0; row1 <= 8; row1++) {

				// Compares the checked element to all other elements in the
				// column

				if (checkedElement == array[row1][column]) {

					counter++;
				}
				
				if (array[row1][column]==0){
					
					return false;
				}

			}

			// If counter is any other value other than 1, then column not
			// filled correctly and false returned

			if (counter != 1) {

				return false;
			}
		}

		// true is returned if all values in the column filled correctly

		return true;
	}

	/**
	 * Method setRow
	 * 
	 * @param x
	 *            The number of the subsquare being tested
	 * @return The row to begin with, depending on which subsquare is being
	 *         tested as an int
	 */

	public static int setRow(int subSquare) {

		int startRow = 0;

		if (subSquare == 0 || subSquare == 1 || subSquare == 2) {

			startRow = 0;
		} else if (subSquare == 3 || subSquare == 4 || subSquare == 5) {

			startRow = 3;

		} else if (subSquare == 6 || subSquare == 7 || subSquare == 8) {

			startRow = 6;
		}

		return startRow;

	}

	/**
	 * setColumn
	 * 
	 * @param x
	 *            The subsquare being tested
	 * @return The column to start with for the given subsquare as an int
	 */

	public static int setColumn(int subSquare) {

		int startColumn = 0;

		if (subSquare == 0 || subSquare == 3 || subSquare == 6) {

			startColumn = 0;
		}

		else if (subSquare == 1 || subSquare == 4 || subSquare == 7) {

			startColumn = 3;
		}

		else if (subSquare == 2 || subSquare == 5 || subSquare == 8) {

			startColumn = 6;
		}

		return startColumn;
	}

	/**
	 * Static method compareElements
	 * 
	 * @param sudoku The sudoku being checked
	 * @param checkedElement The element that is being checked, taken from the sudoku
	 *            puzzle
	 * @param x
	 *            The subsquare being investigated
	 * @return boolean True if square is filled correctly, false if not
	 */

	public static boolean compareElements(int[][] array,int checkedElement, int subSquare) {

		// Initialises and sets counter to 0

		int counter = 0;

		// for loop to iterate through the given elements of the subsquare being
		// tested

		for (int row = setRow(subSquare); row <= setRow(subSquare)+2; row++) {

			for (int column = setColumn(subSquare); column <= setColumn(subSquare)+2; column++) {

				// If the checked element is equal to the given element of the
				// subsquare, counter is increased by 1

				if (checkedElement == array[row][column]) {

					counter++;

				}
				
				if(checkedElement==0){
					
					return false;
				}

			}

		}

		// If counter is any value but 1, the subsquare has not been filled
		// correctly and false is returned

		if (counter != 1) {

			return false;
		}

		// If the subsquare is filled correctly, true is returned

		return true;
	}

	/**
	 * Static method subSquareCheck
	 * 
	 * @param sudoku
	 * @param x
	 *            The subsquare to be checked
	 * @return boolean True if subsquare is filled correctly, false otherwise
	 */

	public static boolean subSquareCheck(int[][] array, int subSquare) {

		// Initialises checkedElement as an int

		int checkedElement;

		for (int row = setRow(subSquare); row <= setRow(subSquare)+2; row++) {

			for (int column = setColumn(subSquare); column <= setColumn(subSquare)+2; column++) {

				// sets checked element to a given element in the sub-square

				checkedElement = array[row][column];

				// if statement - evaluates if compareElements is false (which
				// check the checkedelement against all other values in the
				// subsquare

				if (compareElements(array, checkedElement, subSquare) == false) {

					// returns false if any of the elements are not filled
					// correctly

					return false;
				}

			}

		}

		// If all elements in the subsquare are filled correctly, return true

		return true;
	}




}
