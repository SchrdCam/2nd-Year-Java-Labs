package sudoku;

import java.io.IOException;

public class SudokuGrid {
	// Defines the empty 2-D grid
	private int[][] grid = new int[Utils.SIZE][Utils.SIZE];
	private boolean nulls = false;
	
	public SudokuGrid(String filename) throws IOException {
		// loads the file into a 1-D array, splits on commas
		String[] sudokuLine = new String[81];
		String[] data = Utils.loadGrid(filename).split(",");
		for (int i = 0; i < data.length; i++) {
			sudokuLine[i] = data[i];
		}
		for (int j = 0; j < sudokuLine.length; j++) {
			if ("".equals(sudokuLine[j])) {
				sudokuLine[j] = "0";
			}
			if (sudokuLine[j] == null) {
				sudokuLine[j] = "0";
			} else {
				continue;
			}
		}
		// these loops populate the 2-D array with the data from the imported file
		for (int i = 0; i < Utils.SIZE; i++) {
			for (int j = 0; j < Utils.SIZE; j++) {
				grid[i][j] = Integer.valueOf(sudokuLine[j + 9 * i]);
				if (grid[i][j] == 0) {
					nulls = true;
				}
			}
		}
	}

	public String check() {
		// Hands the check off to lineCheck function
		boolean linesValid = lineCheck(6, 6, 0, 0);
		boolean smallGridValid = smallGridCheck();
		if (linesValid && smallGridValid && !nulls ) {
			return Utils.VALID;
		}
		if (linesValid && smallGridValid && nulls ) {
			return Utils.INCOMPLETE;
		} else {
			return Utils.INVALID;
		}
	}

	public boolean lineCheck(int gridSizeX, int gridSizeY, int baseX, int baseY) {
		boolean checkPass = true;
		// Horizontal Check
		for (int i = baseX; i < gridSizeX + 3; i++) {
			// This loop checks each character in the current column/row
			boolean numFound = false;
			String rowNums = "123456789";
			for (int j = baseY; j < gridSizeY + 3; j++) {
				numFound = false;
				// This loop checks the current character against the string rowNums
				for (; numFound == false;) {
					// if the character is in the string rowNums, that character is removed form
					// rowNums and the checks continue
					if (rowNums.contains(String.valueOf(grid[i][j])) == false && grid[i][j] != 0) {
						checkPass = false;
						return false;
					}
					if (rowNums.contains(String.valueOf(grid[i][j])) == true || grid[i][j] == 0) {
						numFound = true;
						if (rowNums.contains(String.valueOf(grid[i][j])) == true) {
							rowNums = rowNums.replace(String.valueOf(grid[i][j]), "");
						}
						// if the character is not in rowNums, then the grid is invalid and the check
						// ends
					}
				}
			}

		}
		// Vertical Check
		// This for statement checks each column of the grid
		for (int i = baseY; i < gridSizeX + 3; i++) {
			boolean numFound = false;
			String colNums = "123456789";
			// This loop checks each character in the current column
			for (int j = baseX; j < gridSizeY + 3; j++) {
				numFound = false;

				// This loop checks the current character against the string colNums
				for (; numFound == false;) {
					// if the character is in the string rowNums, that character is removed form
					// rowNums and the checks continue
					if (colNums.contains(String.valueOf(grid[j][i])) == false && grid[j][i] != 0) {
						checkPass = false;
						return false;
					}
					if (colNums.contains(String.valueOf(grid[j][i])) == true || grid[j][i] == 0) {
						numFound = true;
						if (colNums.contains(String.valueOf(grid[j][i])) == true) {
							colNums = colNums.replace(String.valueOf(grid[j][i]), "");
						}
						// if the character is not in rowNums, then the grid is invalid and the check
						// ends
					}
				}

			}

		}

		if (checkPass == true) {
			return true;
		} else {
			return false;
		}
	}

	public boolean smallGridCheck() {
		boolean valid = true;
		for (int rowGrid = 0; rowGrid < 3; rowGrid++) {
			for (int colGrid = 0; colGrid < 3; colGrid++) {
				String boxNums = "123456789";
				for (int i = (rowGrid * 3); i < ((rowGrid * 3) + 3); i++) {
					boolean numFound = false;
					for (int j = (colGrid * 3); j < ((colGrid * 3)) + 3; j++) {
						numFound = false;
						// This loop checks the current character against the string rowNums
						for (; numFound == false;) {
							// if the character is in the string rowNums, that character is removed form
							// rowNums and the checks continue
							if (boxNums.contains(String.valueOf(grid[j][i])) == false && grid[j][i] != 0) {
								valid = false;
								return false;
							}
							if (boxNums.contains(String.valueOf(grid[j][i])) == true || grid[j][i] == 0) {
								numFound = true;
								if (boxNums.contains(String.valueOf(grid[j][i])) == true) {
									boxNums = boxNums.replace(String.valueOf(grid[j][i]), "");
								}
							}
						}
					}
				}
			}
		}
		return valid;
	}

	public String toString() {
		System.out.println("A 0 represents an empty space in the grid");
		String finalStr = "";
		for (int i = 0; i < Utils.SIZE; i++) {
			String currentRow = "";
			for (int j = 0; j < Utils.SIZE; j++) {
				currentRow = currentRow.concat(String.valueOf(grid[i][j]) + " ");
			}

			finalStr = finalStr + currentRow + "\n";
		}
		return finalStr;
	}
}