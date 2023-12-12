package edu.guilford;

import java.util.Random;

import javax.swing.JLabel;

public class Santa2048Logic {
    private static final int SIZE = 4;
    private int[][] board;
    private Random random;
    private int score;
    private JLabel imageLabel;

    public Santa2048Logic() {
        board = new int[SIZE][SIZE];
        random = new Random();
        score = 0;
        addRandomTile(); //First random tile
        addRandomTile(); //Second random tile
        addImageLabel();
    }

    public int[][] getBoard() {
        return board;
    }

    public int getScore() {
        return score;
    }

    public void addImageLabel() {
        imageLabel = new JLabel();
        String santa1 = "santa1.png";
        String santa2 = "santa2.png";
        String santa3 = "santa3.png";
        String santa4 = "santa4.png";
        String santa5 = "santa5.png";
        String santa6 = "santa6.png";
    }

    public void addRandomTile() {
        int emptyTiles = 0;
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) {
                    emptyTiles++;
                }
            }
        }

        if (emptyTiles == 0) {
            // Game over logic
            System.out.println("Game Over");
            return;
        }

        int position = random.nextInt(emptyTiles) + 1;
        emptyTiles = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    emptyTiles++;
                    if (emptyTiles == position) {
                        board[i][j] = (random.nextInt(2) + 1) * 2; // 2 or 4
                        return;
                    }
                }
            }
        }
        repaint();
    }

    private void increaseScore(int value) {
        score += value;
    }

    private void moveTiles(int[] line) {
        for (int i = 0; i < SIZE - 1; i++) {
            if (line[i] == line[i + 1] && line[i] != 0) {
                line[i] *= 2;
                line[i + 1] = 0;
                increaseScore(line[i]);
            }
        }
    }

    public void moveUp() {
        for (int i = 0; i < SIZE; i++) {
            int[] row = board[i];
            int[] newRow = new int[SIZE];
            int index = 0;

            for (int j = 0; j < SIZE; j++) {
                if (row[j] != 0) {
                    if (index > 0 && newRow[index - 1] == row[j]) {
                        newRow[index - 1] *= 2;
                    } else {
                        newRow[index] = row[j];
                        index++;
                    }
                }
            }

            System.arraycopy(newRow, 0, board[i], 0, SIZE);
        }
        repaint();
    }

    public void moveDown() {
        for (int i = 0; i < SIZE; i++) {
            int[] row = board[i];
            int[] newRow = new int[SIZE];
            int index = SIZE - 1;

            for (int j = SIZE - 1; j >= 0; j--) {
                if (row[j] != 0) {
                    if (index < SIZE - 1 && newRow[index + 1] == row[j]) {
                        newRow[index + 1] *= 2;
                    } else {
                        newRow[index] = row[j];
                        index--;
                    }
                }
            }

            System.arraycopy(newRow, 0, board[i], 0, SIZE);
        }
        repaint();
    }

    public void moveLeft() {
        for (int j = 0; j < SIZE; j++) {
            int[] column = new int[SIZE];
            int[] newColumn = new int[SIZE];
            int index = 0;

            for (int i = 0; i < SIZE; i++) {
                if (board[i][j] != 0) {
                    if (index > 0 && newColumn[index - 1] == board[i][j]) {
                        newColumn[index - 1] *= 2;
                    } else {
                        newColumn[index] = board[i][j];
                        index++;
                    }
                }
            }

            for (int i = 0; i < SIZE; i++) {
                board[i][j] = newColumn[i];
            }
        }
        repaint();
    }

    public void moveRight() {
        for (int j = 0; j < SIZE; j++) {
            int[] column = new int[SIZE];
            int[] newColumn = new int[SIZE];
            int index = 0;

            for (int i = SIZE; i >= 0; i--) {
                if (board[i][j] != 0) {
                    if (index < SIZE - 1 && newColumn[index + 1] == board[i][j]) {
                        newColumn[index + 1] *= 2;
                    } else {
                        newColumn[index] = board[i][j];
                        index--;
                    }
                }
            }

            for (int i = SIZE - 1; i >= 0; i--) {
                board[i][j] = newColumn[SIZE - 1 - i];
            }
        }
        repaint();
    }

    public boolean isGameOver() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    return false; // Game not over if there is an empty tile
                }
                if (j < SIZE - 1 && board[i][j] == board[i][j + 1]) {
                    return false; // Game not over if adjacent horizontal tiles are the same
                }
                if (i < SIZE - 1 && board[i][j] == board[i + 1][j]) {
                    return false; // Game not over if adjacent vertical tiles are the same
                }
            }
        }
        return true; // No possible moves, game over
    }
        private void repaint() {
        // You might want to replace this with a call to the actual repaint
        // method of your GUI component. Assuming your GUI class is named Game2048GUI.
        //santa2048GUI.repaint();
        // For simplicity, let's print a message for now.
        System.out.println("Repaint triggered");
    }
}