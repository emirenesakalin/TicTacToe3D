# 3D Tic-Tac-Toe

This project is a Java-based implementation of a 3D Tic-Tac-Toe game played on a 4x4x4 board. It offers both player-versus-player and player-versus-computer game modes through a console interface.

## Features

- **3D Gameplay:** Experience Tic-Tac-Toe in a three-dimensional 4x4x4 grid.
- **Game Modes:**
  - **Player vs. Player:** Compete against another player on the same machine.
  - **Player vs. Computer:** Challenge a basic AI opponent that makes random moves.
- **Win Detection:** The game automatically detects a win when a player aligns four of their marks on any of the following:
  - A single row
  - A single column
  - A single depth-wise line
  - Any of the four main 3D diagonals
- **Draw Detection:** The game ends in a draw if all 64 cells are filled without a winner.
- **Interactive Console:** Play the game through a user-friendly console interface that displays the board and prompts for moves.

## How to Play

1. **Choose Opponent:** Decide whether to play against another player or the computer.
2. **Choose Turn:** Select whether to go first or second.
3. **Make a Move:** Enter the coordinates (row, column, and depth) for your move, with each value ranging from 0 to 3.
4. **Winning:** The first player to get four of their marks in a row, column, or diagonal wins.
5. **Drawing:** If the entire board is filled and no one has won, the game is a draw.

## Implementation Details

- **Language:** Java
- **Data Structure:** The game board is represented by a 3D array (`Cell[][][]`).
- **Game Logic:**
  - The game alternates turns between players.
  - Input validation ensures that moves are within the board's boundaries and in unoccupied cells.
  - The computer opponent's moves are generated randomly.

## How to Run

1. Compile the `TicTacToe3D.java` file.
2. Run the compiled `TicTacToe3D` class from the command line.