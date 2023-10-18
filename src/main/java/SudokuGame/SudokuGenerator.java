package SudokuGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SudokuGenerator {

    private int[][] board;
    private static final int SIZE = 9;
    private static final Random random = new Random();

    public SudokuGenerator() {
        board = new int[SIZE][SIZE];
    }

    public class BoardPair {
        public int[][] fullBoard;
        public int[][] gameBoard;

        public BoardPair(int[][] fullBoard, int[][] gameBoard) {
            this.fullBoard = fullBoard;
            this.gameBoard = gameBoard;
        }
    }

    public BoardPair generate(Difficulty difficulty) {
        fillBoard();
        int[][] gameBoard = deepCopyBoard(board);
        removeNumbers(gameBoard, difficulty);
        return new BoardPair(board, gameBoard);
    }

    private boolean fillBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    ArrayList<Integer> numbers = new ArrayList<>();
                    for (int i = 1; i <= SIZE; i++) {
                        numbers.add(i);
                    }
                    Collections.shuffle(numbers);

                    for (int num : numbers) {
                        if (SudokuChecker.isValidMove(board, row, col, num)) {
                            board[row][col] = num;
                            if (fillBoard()) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] deepCopyBoard(int[][] original) {
        int[][] copy = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                copy[i][j] = original[i][j];
            }
        }
        return copy;
    }

    private void removeNumbers(int[][] gameBoard, Difficulty difficulty) {
        int numbersToRemove;
        switch (difficulty) {
            case EASY:
                numbersToRemove = 20;
                break;
            case MEDIUM:
                numbersToRemove = 30;
                break;
            case HARD:
                numbersToRemove = 45;
                break;
            default:
                throw new IllegalArgumentException("Unknown difficulty: " + difficulty);
        }

        for (int i = 0; i < numbersToRemove; i++) {
            int x = random.nextInt(SIZE);
            int y = random.nextInt(SIZE);
            gameBoard[x][y] = 0;
        }
    }
}
