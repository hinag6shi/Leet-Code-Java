package com.hinag6shi;

public class N37SudokuSolver {
    private static final int[][] BOX_INDEX = {
            {0, 0, 0, 1, 1, 1, 2, 2, 2},
            {0, 0, 0, 1, 1, 1, 2, 2, 2},
            {0, 0, 0, 1, 1, 1, 2, 2, 2},
            {3, 3, 3, 4, 4, 4, 5, 5, 5},
            {3, 3, 3, 4, 4, 4, 5, 5, 5},
            {3, 3, 3, 4, 4, 4, 5, 5, 5},
            {6, 6, 6, 7, 7, 7, 8, 8, 8},
            {6, 6, 6, 7, 7, 7, 8, 8, 8},
            {6, 6, 6, 7, 7, 7, 8, 8, 8}
    };

    private final int[] rows = new int[9];
    private final int[] cols = new int[9];
    private final int[] boxes = new int[9];

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = board[i][j];
                if (ch != '.') {
                    int num = ch - '1';
                    int bit = 1 << num;
                    int box = BOX_INDEX[i][j];

                    rows[i] |= bit;
                    cols[j] |= bit;
                    boxes[box] |= bit;
                }
            }
        }

        backtrack(board, 0, 0);
    }

    private boolean backtrack(char[][] board, int row, int col) {
        if (row == 9) {
            return true;
        }

        int nextRow = (col == 8) ? row + 1 : row;
        int nextCol = (col == 8) ? 0 : col + 1;

        if (board[row][col] != '.') {
            return backtrack(board, nextRow, nextCol);
        }

        int box = BOX_INDEX[row][col];

        for (char ch = '1'; ch <= '9'; ch++) {
            int num = ch - '1';
            int bit = 1 << num;

            if ((rows[row] & bit) != 0) continue;
            if ((cols[col] & bit) != 0) continue;
            if ((boxes[box] & bit) != 0) continue;

            board[row][col] = ch;
            rows[row] |= bit;
            cols[col] |= bit;
            boxes[box] |= bit;

            if (backtrack(board, nextRow, nextCol)) {
                return true;
            }

            board[row][col] = '.';
            rows[row] ^= bit;
            cols[col] ^= bit;
            boxes[box] ^= bit;
        }

        return false;
    }
}