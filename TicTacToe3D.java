import java.util.*;

public class TicTacToe3D {
    private enum Cell { // To represent possible Cell states which are X, O and Empty
        X, O, EMPTY
    }

    private final Cell[][][] board; // 3D board
    private boolean isPlayerTurn; // Check if it's player's turn or not
    private Cell playerMark; // Stores the player's mark (X or O)
    private Cell computerMark; // Stores the computer's mark (X or O)
    private int countMoves; // Counts the number of moves made
    private boolean isComputer; // Check if the opponent is computer or not
    private Random random; // Generate random moves for computer

    // Constructor - Initialize board and other variables
    public TicTacToe3D() {
        board = new Cell[4][4][4]; // 3D 4x4x4 array - Game Board
        initializeBoard();
        random = new Random();
        countMoves = 0;
    }

    // Initializing method - Set all characters to Empty
    private void initializeBoard() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                for (int depth = 0; depth < 4; depth++) {
                    board[row][col][depth] = Cell.EMPTY;
                }
            }
        }
    }

    // Method to display the Current state of the Board
    private void displayBoard() {
        System.out.println("Current Board:");
        for (int depth = 0; depth < 4; depth++) {
            System.out.println("Depth " + depth + ":");
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    switch (board[row][col][depth]) { // Print X, 0 or Blank Cell
                        case X:
                            System.out.print(" X ");
                            break;
                        case O:
                            System.out.print(" O ");
                            break;
                        case EMPTY:
                            System.out.print("   ");
                            break;
                    }
                    if (col < 3) {
                        System.out.print("|"); // Separate columns
                    }
                }
                System.out.println();
                if (row < 3) {
                    System.out.println("---+---+---+---"); // Separate rows
                }
            }
            System.out.println();
        }
    }

    // Makes a move, Check if it's valid or not
    private boolean makeMove(int row, int col, int depth, Cell mark) {
        if (row < 0 || row >= 4 || col < 0 || col >= 4 || depth < 0 || depth >= 4 || board[row][col][depth] != Cell.EMPTY) {
            return false; // If it's an invalid move returns false
        }
        board[row][col][depth] = mark;
        countMoves++; // Increment move count
        return true; // If it's a valid move returns true
    }

    // Check if there's a win condition on the Current Board
    private boolean checkWin() {
        // Check rows, columns and depths
        for (int i = 0; i < 4; i++) {
            // Rows and columns in each depth
            for (int depth = 0; depth < 4; depth++) {
                if ((board[i][0][depth] == board[i][1][depth] && board[i][1][depth] == board[i][2][depth] && board[i][2][depth] == board[i][3][depth] && board[i][0][depth] != Cell.EMPTY) ||
                        (board[0][i][depth] == board[1][i][depth] && board[1][i][depth] == board[2][i][depth] && board[2][i][depth] == board[3][i][depth] && board[0][i][depth] != Cell.EMPTY)) {
                    return true;
                }
            }
            // Depths for each row and column
            for (int row = 0; row < 4; row++) {
                if ((board[row][i][0] == board[row][i][1] && board[row][i][1] == board[row][i][2] && board[row][i][2] == board[row][i][3] && board[row][i][0] != Cell.EMPTY) ||
                        (board[i][row][0] == board[i][row][1] && board[i][row][1] == board[i][row][2] && board[i][row][2] == board[i][row][3] && board[i][row][0] != Cell.EMPTY)) {
                    return true;
                }
            }
        }

        // Check the two main diagonals in each depth
        for (int depth = 0; depth < 4; depth++) {
            if ((board[0][0][depth] == board[1][1][depth] && board[1][1][depth] == board[2][2][depth] && board[2][2][depth] == board[3][3][depth] && board[0][0][depth] != Cell.EMPTY) ||
                    (board[0][3][depth] == board[1][2][depth] && board[1][2][depth] == board[2][1][depth] && board[2][1][depth] == board[3][0][depth] && board[0][3][depth] != Cell.EMPTY)) {
                return true;
            }
        }

        // Check the four main diagonals across the 3D space
        return (board[0][0][0] == board[1][1][1] && board[1][1][1] == board[2][2][2] && board[2][2][2] == board[3][3][3] && board[0][0][0] != Cell.EMPTY) ||
                (board[0][0][3] == board[1][1][2] && board[1][1][2] == board[2][2][1] && board[2][2][1] == board[3][3][0] && board[0][0][3] != Cell.EMPTY) ||
                (board[0][3][0] == board[1][2][1] && board[1][2][1] == board[2][1][2] && board[2][1][2] == board[3][0][3] && board[0][3][0] != Cell.EMPTY) ||
                (board[0][3][3] == board[1][2][2] && board[1][2][2] == board[2][1][1] && board[2][1][1] == board[3][0][0] && board[0][3][3] != Cell.EMPTY);
    }

    // Check for a draw - If there's not a place to make move, then there's no winner
    private boolean checkDraw() {
        return countMoves == 64 && !checkWin(); // 4x4x4 = 64
    }

    // Computer randomly selects a move for row, column and depth
    private void computerMove() {
        int row, col, depth;
        do {
            row = random.nextInt(4);
            col = random.nextInt(4);
            depth = random.nextInt(4);
        } while (!makeMove(row, col, depth, computerMark));

        System.out.println("Computer played at (" + row + ", " + col + ", " + depth + ")");
    }

    //Main Game Loop
    public void play() {
        Scanner scan = new Scanner(System.in);
        int row, col, depth;
        boolean gameWon = false;

        System.out.println("Welcome to 3D 4x4x4 Tic-Tac-Toe!");

        //Ask the player if he/she wants to play against the computer or another player
        System.out.print("Would you like to play against the computer? (Yes/No): ");
        String againstComputer = scan.nextLine();
        isComputer = againstComputer.equalsIgnoreCase("yes");

        // Ask the player if he/she wants to go first or second
        System.out.print("Do you want to go first? (Yes/No): ");
        String goesFirst = scan.nextLine();

        // Set marks based on who goes first
        if (goesFirst.equalsIgnoreCase("yes")) {
            isPlayerTurn = true;
            playerMark = Cell.X;
            computerMark = Cell.O;
        } else {
            isPlayerTurn = false;
            playerMark = Cell.O;
            computerMark = Cell.X;
        }

        // Loop until there's a winner, or it's a draw
        while (!gameWon && !checkDraw()) {
            displayBoard();
            if (isPlayerTurn) { // Player's move
                System.out.println("Player " + (playerMark == Cell.X ? "X" : "O") + ", It's your turn.");
                do {
                    System.out.print("Enter row (0-3), column (0-3), and depth (0-3): ");
                    row = scan.nextInt();
                    col = scan.nextInt();
                    depth = scan.nextInt();
                } while (!makeMove(row, col, depth, playerMark)); // Repeat if the input is invalid

            } else if (isComputer) { // Computer's move
                System.out.println("Computer's turn:");
                computerMove();

            } else { // Opponent's move (If not a computer)
                System.out.println("Player " + (computerMark == Cell.X ? "X" : "O") + ", It's your turn.");
                do {
                    System.out.print("Enter row (0-3), column (0-3), and depth (0-3): ");
                    row = scan.nextInt();
                    col = scan.nextInt();
                    depth = scan.nextInt();
                } while (!makeMove(row, col, depth, computerMark)); // Repeat if the input is invalid
            }

            // Check if the game has been won or if the turn should switch
            gameWon = checkWin();
            if (!gameWon) {
                isPlayerTurn = !isPlayerTurn;
            }
        }

        // Display final board and announce results
        displayBoard();

        if (gameWon) {
            System.out.println("Player " + (isPlayerTurn ? playerMark : computerMark) + " WINS!");
        } else {
            System.out.println("It's a DRAW!");
        }

        scan.close();
    }

    // Main method to run the game
    public static void main(String[] args) {
        TicTacToe3D game = new TicTacToe3D();
        game.play();
    }
}
