import java.io.IOException;
import java.util.Arrays;

public class SudokuSolve extends Sudoku {

	public int[][] array;

	public SudokuSolve(int[][] array) {
		super(array);

	}

	/**
	 * Method SolveMe() will go through and suggest possible values for each
	 * sudoku
	 */

	public void SolveMe() {

		int row;
		int col;

		int number;
		int count = 0;

		while (this.isFilled() == false && count < 100) {
			// For loop to go through the sudoku puzzle
			for (row = 0; row < 9; row++) {

				for (col = 0; col < 9; col++) {

					// If the array is == 0, the method starts to guess values

					if (getArray()[row][col] == 0) {

						// For loop starts with the lowest possible guess ends
						// at the highest (9)

						for (number = 1; number < 10; number++) {

							// If conditional - if the guess is "allowed" for
							// that subSquare, row or column, this evaluates to
							// "true". It serves to essentially
							// check which values are allowed for each subsquare

							if (checkGuessSquare(getArray(), setRow(row), setColumn(col), number)
									&& rowCheck(row, number) && columnCheck(col, number)) {

								System.out.println(number + " is valid for " + row + " " + col);

								// If this is the only possible guess for this
								// square (by checking the rest of the row) the
								// guess goes in

								// If the method is the only possible guess (by
								// checking the column) the guess goes in

								if (checkOnlyGuessColumn(number, col)) {

									getArray()[row][col] = number;

									System.out.println(this);

									break;
								}

								if (checkOnlyGuessRow(number, row)) {

									getArray()[row][col] = number;

									System.out.println(this);

									break;
								}

							}
						}

					}

				}

			}
			count++;
		}

	}
	
	public boolean checkOnlyGuessRow(int number, int row){
		
		int count=0;
		
		for(int i=0;i<9;i++){
			
			if(getArray()[row][i]==0){
				
				if(checkGuessSquare(getArray(), setRow(row), setColumn(i), number)
				   && rowCheck(row, number)	
				   && columnCheck(i, number))
				{
					count++;
				}
				
			}
		}
		
		if(count==1){
			return true;
		}
		
		else {
			
			return false;
		}
		
	}
	
	public boolean checkOnlyGuessColumn(int number, int col){
		
		int count=0;
		
		for(int i=0;i<9;i++){
			
			if(getArray()[i][col]==0){
				
				if(checkGuessSquare(getArray(), setRow(i), setColumn(col), number)
				   && rowCheck(i, number)	
				   && columnCheck(col, number))
				{
					count++;
				}
				
			}
		}
		
		if(count==1){
			return true;
		}
		
		else {
			
			return false;
		}
		
	}
	

	public boolean checkGuessSquare(int[][] array, int startRow, int startCol, int number) {

		for (int i = startRow; i < startRow + 3; i++) {

			for (int j = startCol; j < startCol + 3; j++) {

				if (number == array[i][j]) {

					return false;
				}

			}

		}

		return true;

	}

	public boolean columnCheck(int column, int number) {

		int checkedElement = number;

		for (int row1 = 0; row1 <= 8; row1++) {

			// The number to all the other elements in the column

			if (checkedElement == getArray()[row1][column]) {

				return false;
			}

		}

		return true;
	}

	public boolean rowCheck(int row, int number) {

		// loop to iterate through the "columns" of the row

		for (int column1 = 0; column1 < 9; column1++) {

			// If the number being suggested is in the row, then true is
			// returned

			if (number == getArray()[row][column1]) {

				return false;
			}

		}

		// If number is not present in the row, false is returned

		return true;
	}

	// setColumn sets the column at which to start

	public static int setColumn(int col) {

		int startColumn = 0;

		if (col >= 0 && col <= 2) {

			startColumn = 0;
		}

		else if (col >= 3 && col <= 5) {

			startColumn = 3;
		}

		else if (col >= 6 && col <= 8) {

			startColumn = 6;
		}

		return startColumn;
	}

	// Set rows will set the row to start at

	public static int setRow(int row) {

		int startRow = 0;

		if (row >= 0 && row <= 2) {

			startRow = 0;
		}

		else if (row >= 3 && row <= 5) {

			startRow = 3;
		}

		else if (row >= 6 && row <= 8) {

			startRow = 6;
		}

		return startRow;
	}

	public static void main(String[] args) throws IOException {


	}
}
