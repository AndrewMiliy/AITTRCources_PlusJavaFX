package SudokuGame;

public class SudokuChecker {

    public static boolean isValid(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0 || !isValidMove(board, i, j, board[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isValidMove(int[][] board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num && i != col) return false;  // проверка строки
            if (board[i][col] == num && i != row) return false;  // проверка столбца

            // проверка 3x3 квадрата
            int boxRow = 3 * (row / 3) + i / 3;
            int boxCol = 3 * (col / 3) + i % 3;
            if (board[boxRow][boxCol] == num && boxRow != row && boxCol != col) return false;
        }
        return true;
    }
}
