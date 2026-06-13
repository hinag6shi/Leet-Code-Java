package com.hinag6shi;

import java.util.*;

public class N36ValidSudoku {
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

    public boolean isValidSudoku(char[][] board) {
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] boxes = new int[9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = board[i][j];
                if (ch == '.') continue;

                int bit = 1 << (ch - '1');
                int box = BOX_INDEX[i][j];

                if ((rows[i] & bit) != 0) return false;
                if ((cols[j] & bit) != 0) return false;
                if ((boxes[box] & bit) != 0) return false;

                rows[i] |= bit;
                cols[j] |= bit;
                boxes[box] |= bit;
            }
        }

        return true;
    }
//    public boolean isValidSudoku(char[][] board) {
//        Set<String> seen = new HashSet<>();
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                char ch = board[i][j];
//                if (ch == '.') continue;
//
//                String rowKey = "r" + i + ch; // Склейка строк
//                String colKey = "c" + j + ch; // Склейка строк
//                String boxKey = "b" + ((i / 3) * 3 + (j / 3)) + ch; // Склейка строк
//
//                if (!seen.add(rowKey)) return false;
//                if (!seen.add(colKey)) return false;
//                if (!seen.add(boxKey)) return false;
//            }
//        }
//
//        return true;
//    }
}
