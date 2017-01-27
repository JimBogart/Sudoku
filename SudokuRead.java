import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This is a public class SudokuRead. It contains the method readSudoku
 * 
 * 
 * @author James Bogart
 * @version 2016-18-11
 *
 */

public class SudokuRead {

	/**
	 * This is the method readSudoku. It will take in a file containing a
	 * possible partially filled Sudoku puzzle, analyse each character and then
	 * write them to a new Sudoku object
	 * 
	 * This method assumes that the first 9 lines and 9 characters of each line
	 * are correct
	 * 
	 * Method will throw an IOException if unable to access the file
	 * 
	 * Method will throw an IllegalArgumentException if any of the characters
	 * are invalid, i.e. not 1-9 or a blank space.
	 * 
	 * @param fileName
	 *            The name of the file containing the values for a Sudoku puzzle
	 * @return sudokuFilled A Sudoku object
	 * @throws IOException 
	 */

	public static Sudoku readSudoku(String fileName) throws  IOException {
		
		Sudoku s1 = new Sudoku(makeNewArray(fileName));
		
		return s1;
	}
	
	/**
	 * Method that generates a new array from a given file
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
		
		
			
	public static int[][] makeNewArray(String fileName) throws IOException{
		
		int [][] a = new int [9][9];

		// Initialises all variables

		String line;

		FileReader reader = new FileReader(fileName);

		BufferedReader bufferedReader = new BufferedReader(reader);

		try {
			

			int j = 0;
			
			// Ensures that either there is a line, and only reads in first 9 lines of the fke

			while ((line = bufferedReader.readLine()) != null && j < 9) {
				
				// Method looks at the "string" provided from the buffered reader, and loops through the first 9 characters

				for (int i = 0; i < 9; i++) {

					// This will throw illegal argument exception if the line is incomplete or has invalid characters
					
					if (validInt(line.charAt(i)) == false || line.length()<9) {

						throw new IllegalArgumentException();

					}
					
					// If blank, then array at given position is set to zero

					if (line.charAt(i) == ' ') {

						a[j][i] = 0;

					}

					// If it is not blank then the array position is set to the numeric value of the character
					
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
	 * Method that matches for legal inputs according to sudoku rules
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

	public static void main(String[] args) throws IOException  {


}
}