/*
 * Comments for TicTac.java
 */

import java.util.Scanner;

public class TicTac {
    public static void main(String[] args) {
        // Create a new instance of the Game class and start the game
        Game game = new Game();
        game.run();
    }
}

class Game {
    void run() {
        // Create a new board for the game
        Board newBoard = new Board();
        newBoard.fulfillTheTable();

        Scanner sc = new Scanner(System.in);

        int stepCounter = 1;
        String player = "First";
        
        // Display the initial message and show the empty game board
        System.out.println("Let's the Game begin!");
        System.out.println();
        newBoard.showTheTable();
        System.out.println();

        while (newBoard.hasEmptyCell()) {
            // Prompt the current player to choose a row and column
            System.out.println(player + " player, choose the row: ");
            int row = sc.nextInt() - 1;
            System.out.println(player + " player, choose the column: ");
            int column = sc.nextInt() - 1;
            System.out.println();

            // Check if the selected cell is already used
            if (newBoard.cellChecking(row, column)) {
                // Place the player's symbol in the selected cell based on the step counter
                if (stepCounter % 2 == 0) {
                    newBoard.board[row][column] = "0";
                } else {
                    newBoard.board[row][column] = "X";
                }
            } else {
                // Inform the player that the chosen cell is already used, and prompt for another choice
                int currStepCounter = stepCounter;
                System.out.println("This cell is already used. Choose another one!");
                System.out.println();
                stepCounter = currStepCounter;
                continue;
            }

            // Display the updated game board
            newBoard.showTheTable();
            System.out.println();

            // Check if the current player has won
            if (newBoard.boardVerification()) {
                System.out.println(player + " player wins!");
                break;
            }

            // Switch to the other player for the next turn
            if (player.equalsIgnoreCase("First")) {
                player = "Second";
            } else {
                player = "First";
            }
            stepCounter += 1;
        }
    }
}

class Board {
    String[][] board = new String[3][3];

    // Function to check for empty cell (null) in a 2D array
    public boolean hasEmptyCell() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals("-")) {
                    return true; // Empty cell found
                }
            }
        }
        return false;
    }

    // Display the current state of the game board
    public void showTheTable() {
        System.out.println("---------------------");
        System.out.println("Current board state: ");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------");
    }

    // Initialize the game board with empty cells
    public void fulfillTheTable() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = "-";
            }
        }
    }

    // Check for winning conditions in rows, columns, and diagonals
    boolean boardVerification() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] != "-" && board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2])) ||
                (board[0][i] != "-" && board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i]))) {
                return true; // Winning condition found in rows or columns
            }
        }

        // Check diagonals
        if ((board[0][0] != "-" && board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2])) ||
            (board[0][2] != "-" && board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]))) {
            return true; // Winning condition found in diagonals
        }

        return false; // No winning condition found
    }

    // Check if the selected cell is empty
    boolean cellChecking(int row, int column) {
        if (!board[row][column].equals("-")) {
            return false;
        }
        return true;
    }
}
