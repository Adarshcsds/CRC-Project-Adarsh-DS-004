import java.util.*;

public class TicTacToe {

    static char[] board = new char[9];
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    // Initialize board
    static void initBoard() {
        for (int i = 0; i < 9; i++) {
            board[i] = (char) ('1' + i);
        }
    }

    // Print board
    static void printBoard() {
        System.out.println();
        System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("---+---+---");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("---+---+---");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8]);
        System.out.println();
    }

    // Check win
    static boolean checkWin(char p) {
        int[][] win = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
        };

        for (int[] w : win) {
            if (board[w[0]] == p && board[w[1]] == p && board[w[2]] == p) {
                return true;
            }
        }
        return false;
    }

    // Check draw
    static boolean isDraw() {
        for (char c : board) {
            if (c != 'X' && c != 'O') return false;
        }
        return true;
    }

    // Player move
    static boolean playerMove(int pos, char symbol) {
        if (pos < 1 || pos > 9) return false;

        if (board[pos - 1] == 'X' || board[pos - 1] == 'O') {
            return false;
        }

        board[pos - 1] = symbol;
        return true;
    }

    // Bot move
    static void botMove() {
        while (true) {
            int pos = rand.nextInt(9);
            if (board[pos] != 'X' && board[pos] != 'O') {
                board[pos] = 'O';
                System.out.println("Bot chose position: " + (pos + 1));
                break;
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("==== TIC TAC TOE ====");
        System.out.println("1. Single Player");
        System.out.println("2. Multiplayer");
        System.out.print("Choose mode: ");

        int choice = sc.nextInt();
        sc.nextLine();

        initBoard();

        if (choice == 1) {
            // SINGLE PLAYER
            System.out.print("Enter your name: ");
            String player = sc.nextLine();

            char current = 'X';

            while (true) {
                printBoard();

                if (current == 'X') {
                    System.out.print(player + ", choose position (1-9): ");
                    int pos = sc.nextInt();

                    if (!playerMove(pos, 'X')) {
                        System.out.println("Invalid move! Try again.");
                        continue;
                    }

                    if (checkWin('X')) {
                        printBoard();
                        System.out.println(player + " WIN ");
                        System.out.println("Bot LOSE ");
                        break;
                    }

                    current = 'O';
                } else {
                    botMove();

                    if (checkWin('O')) {
                        printBoard();
                        System.out.println("Bot WIN ");
                        System.out.println(player + " LOSE ");
                        break;
                    }

                    current = 'X';
                }

                if (isDraw()) {
                    printBoard();
                    System.out.println("Game DRAW ");
                    break;
                }
            }

        } else {
            // MULTIPLAYER
            System.out.print("Enter Player 1 Name (X): ");
            String p1 = sc.nextLine();

            System.out.print("Enter Player 2 Name (O): ");
            String p2 = sc.nextLine();

            char current = 'X';

            while (true) {
                printBoard();

                String name = (current == 'X') ? p1 : p2;

                System.out.print(name + ", choose position (1-9): ");
                int pos = sc.nextInt();

                if (!playerMove(pos, current)) {
                    System.out.println("Invalid move! Try again.");
                    continue;
                }

                if (checkWin(current)) {
                    printBoard();
                    System.out.println(name + " WIN ");
                    System.out.println((current == 'X' ? p2 : p1) + " LOSE ❌");
                    break;
                }

                if (isDraw()) {
                    printBoard();
                    System.out.println("Game DRAW ");
                    break;
                }

                current = (current == 'X') ? 'O' : 'X';
            }
        }

        sc.close();
    }
}