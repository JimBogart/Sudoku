import java.io.IOException;


public class SudokuSolve extends Sudoku {

	public int[][] array;

	public SudokuSolve(int[][] array) {
		super(array);

	}
	
	/**
	 * Method SolveMe() will go through and suggest possible values 
	 * for each sudoku un
	 */

	public void SolveMe() {

		int row;
		int col;

		int number;
		int count = 0;

		while (count < 10) {
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

							if (checkGuessSquare(getArray(), setRow(row), setRow(col), number) && rowCheck(row, number)
									&& columnCheck(col, number)) {

								// We then look at possibilities here. This is

								// I have then put in some code that should
								// check, where there is a blank square in that
								// column, whether or not we can eliminate the
								// guess. This works by looking
								// at the blanks and working across. If the
								// guess is already present in that row, then it
								// means that the guess is valid.
								// If the number is NOT in the row where there
								// is a blank, then this means we cannot say for
								// sure if the guess is 100% right

								// I would need to add further methods that
								// eliminate more possibilities, but I could not
								// get that far - so this is my best attempt at
								// the problem. The method
								// and logic are sound, I just couldnt implement
								// it at the moment

								if (checkIfValidForColumn(col, number)) {

									System.out.println(number + " this guess is going in at col " + col );
									System.out.println(checkIfValidForColumn(col, number) + " " + col);
									getArray()[row][col] = number;
									
									Sudoku s1 = new Sudoku(getArray());
									
									System.out.println(s1);

									break;

								}
								
//								else if(checkIfValidForRow(row, number)){
//									
//									System.out.println(checkIfValidForRow(row, number) + " " + row);
//									
//									System.out.println(number + " this guess is going in at  row " + row);
//									
//									getArray()[row][col] = number;
//
//									break;
//									
//									
//								}

						

							}

						}

					}

				}

			}

			count++;
		}

	}
	
	
	
	public boolean checkIfValidForRow(int row, int number){
		
		int counterblanks =0;
		int counter = 0;
		
		for(int col=0;col<9;col++){
			
			
			if(getArray()[row][col] == 0){
				
				counterblanks++;
				
				for(int j=0;j<9;j++){
					
					
					if(getArray()[j][col]==number){
						
						counter++;
					}
				}

			}
			

		}
		
		if (counterblanks != counter+1){
			
			return false;
		}
		
		return true;
	}
	

	public boolean checkIfValidForColumn(int column, int number){
		
		int counterblanks =0;
		int counter = 0;
		
		for(int row=0;row<9;row++){
			
			
			if(getArray()[row][column] == 0){
				
				counterblanks++;
				
				for(int j=0;j<9;j++){
					
					
					if(getArray()[row][j]==number){
						
						counter++;
					}
				}

			}



		}
		
		
		if (counterblanks != counter+1){
			
			return false;
		}
		
		return true;
	}
	
	

	public boolean isOnlyPossibilityLeft(int col, int row, int number) {

		for (int row1 = 0; row1 <= 8; row1++) {

			if (number == getArray()[row1][col]) {

				return false;
			}

		}

		for (int col1 = 0; col1 <= 8; col1++) {

			if (number == getArray()[row][col1]) {

				return false;
			}

		}

		for (int i = setRow(row); i < setRow(row) + 3; i++) {

			for (int j = setColumn(col); j < setColumn(col) + 3; j++) {

				if (number == getArray()[i][j]) {

					return false;
				}

			}

		}

		return true;

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

		SudokuSolve SOLVE = new SudokuSolve(makeNewArray("sudoku-ex1.txt"));

		System.out.println(SOLVE);

		SOLVE.SolveMe();
		
		System.out.println(SOLVE);

	}
}
