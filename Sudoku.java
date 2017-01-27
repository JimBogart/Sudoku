import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This is a class Sudoku. It contains a constructor, getter and toString()
 * method for a Sudoku object.
 * 
 * New object initialised by Sudoku x = new Sudoku(int[][])
 * 
 * Also contains the public method isFilled() which returns true if all elements
 * filled, and false otherwise
 * 
 * @author James Bogart
 * @version 2016-11-18
 *
 */

public class Sudoku {

	private int[][] array;

	/**
	 * Constructor for a Sudoku Object
	 * 
	 * @param array  A 2D array equivalent to the Sudoku grid
	 */

	public Sudoku(int[][] array) {

		this.array = array;

	}
	
	public Sudoku() {
		
		this.array = new int[9][9];
		
		
	}

	/**
	 * Getter for the array
	 * @return array The 2D array of integers
	 */

	public int[][] getArray() {
		return array;
	}

	/**
	 * toString() method for the Sudoku grid. Returns the answers printed in the
	 * specified format
	 */

	public String toString() {

		// Initialises a blank string

		String complete = "";

		// String 1 is for the line at the top of the grid and after every third
		// row, has \n to to start a new line

		String string1 = " ++===+===+===++===+===+===++===+===+===++\n";

		// String 2 is for the line between every row in the grid (has \n at the
		// end to start a new line

		String string2 = " ++---+---+---++---+---+---++---+---+---++\n";

		// Separator at the start of the columns and after every third column

		String separator1 = " || ";

		// Separator between each column

		String separator2 = " | ";

		// Separator after the very last column of the grid (includes \n to
		// start a new line)

		String finalSeparator = " || \n";

		for (int i = 0; i < this.getArray().length; i++) {

			if (i % 3 == 0 && i != 8) {

				complete += string1;
			}

			else if (i % 3 != 0) {

				complete += string2;

			}

			String row = "";

			for (int j = 0; j < getArray()[0].length; j++) {

				if (j == 8) {

					row += separator2 + getArray()[i][j] + finalSeparator;

				}

				else if (j % 3 == 0 && j != 8) {

					row += separator1 + getArray()[i][j];

				}

				else if (j % 3 != 3) {

					row += separator2 + getArray()[i][j];
				}

			}

			complete += row;

		}

		complete += string1;

		return complete;
	}

	/**
	 * isFilled() method - will check to see if the elements are filled in a Sudoku
	 * @return Boolean True if all elements are filled, else returns False
	 */

	public boolean isFilled() {

		// Nested for loop. Will go through each element of the array by each
		// column in each row

		for (int i = 0; i < this.getArray().length; i++) {

			for (int j = 0; j < this.getArray()[i].length; j++) {

				// If statement - if any of the elements are == 0, then false
				// will be returned

				if (this.getArray()[i][j] == 0) {

					return false;
				}

			}

		}

		// If none of the elements are filled (i.e none are 0, then true is
		// returned

		return true;
	}

	
	/**
	 * 
	 * @param fileName
	 * @return A sudoku object, filled in according to the values specified in the file given
	 * @throws IOException
	 */
	
	public static Sudoku readSudoku(String fileName) throws  IOException {
		
		Sudoku s1 = new Sudoku(makeNewArray(fileName));
		
		return s1;
		
		
		
	}
		
	/**
	 * 
	 * @param fileName The name of the file to be filled in
	 * @return  A 2-D array of values (mactching a sudoku puzzle)
	 * @throws IOException
	 */
			
	public static int[][] makeNewArray(String fileName) throws IOException{
		
		int [][] a = new int [9][9];


		String line;

		FileReader reader = new FileReader(fileName);

		BufferedReader bufferedReader = new BufferedReader(reader);

		try {

			int j = 0;
			
//			if(bufferedReader.ready()==false){
//				
//				throw new IOException();
//			}

			while ((line = bufferedReader.readLine()) != null && j < 9) {

				for (int i = 0; i < 9; i++) {

					if (validInt(line.charAt(i)) == false || line.length()<9) {

						throw new IllegalArgumentException();

					}

					if (line.charAt(i) == ' ') {

						a[j][i] = 0;

					}

					else {
						int x = Character.getNumericValue((line.charAt(i)));

						a[j][i] = x;

					}

				}

				j++;

			}

		}



		catch (IllegalArgumentException e2) {
			
			System.out.println("Invalid character entered");

			e2.printStackTrace();

		} finally {

			bufferedReader.close();
		}
		
		return a;
		
	}
	
	/**
	 * Method that "catches" any invalid character input, so that only blanks, or numbers 1-9 are entered
	 * @param value
	 * @return
	 */


	public static boolean validInt(char value) {


		if (value == ' ' || value == '1' || value == '2' || value == '3' || value == '4' || value == '5' || value == '6'
				|| value == '7' || value == '8' || value == '9') {

			return true;
		}

		return false;
	}

	
	public static void main(String[] args) throws IOException {
		

	}


}

