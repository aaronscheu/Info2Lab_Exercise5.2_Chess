/**
 * Created by amaridev on 19/01/16.
 * Package: PACKAGE_NAME for Exercise5.2_Chess.
 */
public class Board {
    char[][] chessboard;


    public Board(int size) {
        chessboard = new char[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                chessboard[y][x] = '*';
            }
        }
    }

    public void placeQueen (int row, int col) {
        chessboard[row][col] = 'Q';
    }

    public void removeQueen (int row, int col) {
        chessboard[row][col] = '*';
    }

    public boolean isThreatened (int row, int col) {
        return horizontally(row, col) || vertically(row, col) || diagonally(row, col);
    }

    private boolean horizontally (int row, int col) {
        for (int cols = 0; cols < chessboard.length; cols++) {
            if (cols == col) continue;
            if (chessboard[row][cols] == 'Q') return true;
        }
        return false;
    }

    private boolean vertically (int row, int col) {
        for (int rows = 0; rows < chessboard.length; rows++) {
            if (rows == row) continue;
            if (chessboard[rows][col] == 'Q') return true;
        }
        return false;
    }

    private boolean diagonally (int row, int col) {
        /*** ascending ***/ //zero-based
        for (int rows = row +1, cols = col +1; rows < chessboard.length && cols < chessboard.length; rows++, cols++)
            if (chessboard[rows][cols] == 'Q') return true;

        for (int rows = row -1, cols = col -1; rows >= 0 && cols >= 0; rows--, cols--)
            if (chessboard[rows][cols] == 'Q') return true;


        /*** descending ***/ //zero-based
        for (int rows = row -1, cols = col +1; rows >= 0 && cols < chessboard.length; rows--, cols++)
            if (chessboard[rows][cols] == 'Q') return true;

        for (int rows = row +1, cols = col -1; rows < chessboard.length && cols >= 0; rows++, cols--)
            if (chessboard[rows][cols] == 'Q') return true;

        return false;
    }

    /** Pseudo Code **
     * boolean solveNQ (int col)
     *  if col >= size then all done!
     *  for row 0 to row n-1
     *      if (row, col) is a safe(non-threatened) position place a Queen at (row, col)
     *      if solveNQ (col + 1) is true then // ** recursive step **
     *        return true
     *      else
     *        remove Queen from (row, col)    // ** backtracking step **
     *  (Outside of loop:) return false
     **/

    public boolean setQueensRecursive () {
        return setQueensRecursive(0);
    }

    public boolean setQueensRecursive (int col) {
        if (col >= chessboard.length)
            return true;

        for (int row = 0; row < chessboard.length; row++) {
            if (!isThreatened(row, col))
                placeQueen(row, col);

            if (setQueensRecursive(col +1))
                removeQueen(row, col);
        }
        return false;
    }

    public void printBoard () {
        String output = "";

        for (int y = 0; y < chessboard.length; y++) {
            output += "\n" + (chessboard.length - y) + "| ";
            for (int x = 0; x < chessboard.length; x++) {
                output += chessboard[y][x] + " ";
            }
        }
        output += "\n  ";

        for (int i = 0; i < chessboard.length; i++) {
            output += "--";
        }
        output += "\n   ";
        for (int i = 0; i < chessboard.length; i++) {
            output += (i +1) + " ";
        }

        System.out.println(output);
    }

    public static void main(String[] args) {
        Board board = new Board(8);
        board.setQueensRecursive();
        board.printBoard();
    }

}
