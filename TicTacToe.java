import java.util.Scanner;

public class TicTacToe {
    private static final char[][] board = {
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' }
    };
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to Tic-Tac-Toe!");
            printBoard();

            while (true) {
                System.out.println("Player " + currentPlayer + ", enter your move (e.g., 11 for row 1, column 1): ");
                String input = scanner.next();

                if (input.length() != 2 || !Character.isDigit(input.charAt(0)) || !Character.isDigit(input.charAt(1))) {
                    System.out.println("Invalid input format. Please enter two digits like 11 or 23.");
                    continue;
                }

                int row = input.charAt(0) - '1';
                int col = input.charAt(1) - '1';

                if (isValidMove(row, col)) {
                    board[row][col] = currentPlayer;
                    printBoard();

                    if (isWinner()) {
                        System.out.println("Player " + currentPlayer + " wins!");
                        break;
                    } else if (isBoardFull()) {
                        System.out.println("It's a tie!");
                        break;
                    }

                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            }
        }
    }

    private static void printBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i < 2)
                System.out.println("---|---|---");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    private static boolean isWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer)
                return true;
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)
                return true;
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer)
            return true;
        return board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }
}