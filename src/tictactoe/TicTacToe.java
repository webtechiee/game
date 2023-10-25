package tictactoe;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        char[][] gameBoard = {
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '}
        };

        printGameBoard(gameBoard);

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter a number for the position between 1-9:");
            int playerPosition = scan.nextInt();
            if (playerPosition < 1 || playerPosition > 9) {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
                continue;
            }

            if (isPositionTaken(gameBoard, playerPosition)) {
                System.out.println("Position is already taken. Choose another position.");
                continue;
            }

            placePiece(gameBoard, playerPosition, "player");
            printGameBoard(gameBoard);

            if (checkWin(gameBoard, "player")) {
                System.out.println("Congratulations! You win!");
                break;
            }

            if (isBoardFull(gameBoard)) {
                System.out.println("It's a draw!");
                break;
            }

            Random rand = new Random();
            int comPosition;
            do {
                comPosition = rand.nextInt(9) + 1;
            } while (isPositionTaken(gameBoard, comPosition));

            placePiece(gameBoard, comPosition, "computer");
            printGameBoard(gameBoard);

            if (checkWin(gameBoard, "computer")) {
                System.out.println("Computer wins!");
                break;
            }
        }
    }

    public static void printGameBoard(char[][] gameBoard) {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                System.out.print(gameBoard[row][col]);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int position, String user) {
        char symbol = (user.equals("computer")) ? 'O' : 'X';

        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
        }
    }

    public static boolean isPositionTaken(char[][] gameBoard, int position) {
        switch (position) {
            case 1:
                return gameBoard[0][0] != ' ';
            case 2:
                return gameBoard[0][2] != ' ';
            case 3:
                return gameBoard[0][4] != ' ';
            case 4:
                return gameBoard[2][0] != ' ';
            case 5:
                return gameBoard[2][2] != ' ';
            case 6:
                return gameBoard[2][4] != ' ';
            case 7:
                return gameBoard[4][0] != ' ';
            case 8:
                return gameBoard[4][2] != ' ';
            case 9:
                return gameBoard[4][4] != ' ';
            default:
                return true;
        }
    }

    public static boolean checkWin(char[][] gameBoard, String player) {
        char symbol = (player.equals("computer")) ? 'O' : 'X';

        for (int i = 0; i < 3; i++) {
            // Check rows
            if (gameBoard[i * 2][0] == symbol && gameBoard[i * 2][2] == symbol && gameBoard[i * 2][4] == symbol) {
                return true;
            }
            // Check columns
            if (gameBoard[0][i * 2] == symbol && gameBoard[2][i * 2] == symbol && gameBoard[4][i * 2] == symbol) {
                return true;
            }
        }
        // Check diagonals
        if ((gameBoard[0][0] == symbol && gameBoard[2][2] == symbol && gameBoard[4][4] == symbol) ||
            (gameBoard[0][4] == symbol && gameBoard[2][2] == symbol && gameBoard[4][0] == symbol)) {
            return true;
        }
        return false;
    }

    public static boolean isBoardFull(char[][] gameBoard) {
        for (int i = 0; i < 5; i += 2) {
            for (int j = 0; j < 5; j += 2) {
                if (gameBoard[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
