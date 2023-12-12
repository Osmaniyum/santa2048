package edu.guilford;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class Santa2048GUI extends JPanel implements KeyListener {
    private static final int SIZE = 4;
    private static final int TILE_SIZE = 80;
    private Santa2048Logic gameLogic;
    private JLabel imageLabel;

    public Santa2048GUI() {
        gameLogic = new Santa2048Logic();
        setPreferredSize(new Dimension(SIZE * TILE_SIZE, SIZE * TILE_SIZE));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawTiles(g);
        drawScore(g);
    }

    private void drawScore(Graphics g) {
        // Draw the score at the top of the board
        g.setColor(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 16);
        g.setFont(font);
        g.drawString("Score: " + gameLogic.getScore(), 400, 20);
    }

    private void drawBoard(Graphics g) {
        // Draw the background of the game board
        g.setColor(new Color(187, 173, 160)); // Board color
        g.fillRect(0, 0, SIZE * TILE_SIZE, SIZE * TILE_SIZE);

        // Draw grid lines
        g.setColor(Color.BLACK);
        for (int i = 0; i <= SIZE; i++) {
            g.drawLine(i * TILE_SIZE, 0, i * TILE_SIZE, SIZE * TILE_SIZE);
            g.drawLine(0, i * TILE_SIZE, SIZE * TILE_SIZE, i * TILE_SIZE);
        }
    }

    private void drawTiles(Graphics g) {
        int[][] board = gameLogic.getBoard();

        // Draw tiles with values on the board
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int value = board[i][j];
                if (value != 0) {
                    drawTile(g, i * TILE_SIZE, j * TILE_SIZE, value);
                }
            }
        }
    }

    private void drawTile(Graphics g, int x, int y, int value) {
        // Draw a colored tile with the given value
        g.setColor(getTileColor(value));
        g.fillRect(x, y, TILE_SIZE, TILE_SIZE);

        // Draw the value in the center of the tile
        g.setColor(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 24);
        g.setFont(font);
        String valueStr = String.valueOf(value);
        FontMetrics fontMetrics = g.getFontMetrics();
        int textX = x + (TILE_SIZE - fontMetrics.stringWidth(valueStr)) / 2;
        int textY = y + (TILE_SIZE + fontMetrics.getAscent()) / 2;
        g.drawString(valueStr, textX, textY);
    }

    private Color getTileColor(int value) {
        // Provide different colors for different tile values
        switch (value) {
            case 2:
                return new Color(238, 228, 218);
            case 4:
                return new Color(237, 224, 200);
            case 8:
                return new Color(242, 177, 121);
            case 16:
                return new Color(245, 149, 99);
            case 32:
                return new Color(246, 124, 95);
            case 64:
                return new Color(246, 94, 59);
            case 128:
                return new Color(237, 207, 114);
            case 256:
                return new Color(237, 204, 97);
            case 512:
                return new Color(237, 200, 80);
            case 1024:
                return new Color(237, 197, 63);
            case 2048:
                return new Color(237, 194, 46);
            default:
                return Color.BLACK;
        }
    }

    private JLabel addImageLabel() {
        return imageLabel;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                gameLogic.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                gameLogic.moveRight();
                break;
            case KeyEvent.VK_UP:
                gameLogic.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                gameLogic.moveDown();
                break;
        }

        if (!gameLogic.isGameOver()) {
            gameLogic.addRandomTile();
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}