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
        if (col < chessboard.length && row < chessboard.length)
            chessboard[row][col] = 'Q';
    }

    public void removeQueen (int row, int col) {
        if (col < chessboard.length && row < chessboard.length)
            chessboard[row][col] = '*';
    }

    public boolean isThreatened (int row, int col) {
        return horizontally(row) || vertically(col) || diagonally(row, col);
    }

    private boolean horizontally (int row) {
        for (int i = 0; i < chessboard.length; i++) {
            if (chessboard[row][i] == 1) return true;
        }
        return false;
    }

    private boolean vertically (int col) {
        for (int i = 0; i < chessboard.length; i++) {
            if (chessboard[i][col] == 1) return true;
        }
        return false;
    }

    private boolean diagonally (int row, int col) {
        /*** ascending ***/ //zero-based
        for (int rows = row, cols = col; rows < chessboard.length && cols < chessboard.length; rows++, cols++)
            if (chessboard[rows][cols] == 'Q') return true;

        for (int rows = row, cols = col; rows >= 0 && cols >= 0; rows--, cols--)
            if (chessboard[rows][cols] == 'Q') return true;


        /*** descending ***/ //zero-based
        for (int rows = row, cols = col; rows >= 0 && cols < chessboard.length; rows--, cols++)
            if (chessboard[rows][cols] == 'Q') return true;

        for (int rows = row, cols = col; rows < chessboard.length && cols >= 0; rows++, cols--)
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

    public boolean setQueensRecursive (int col) {
        if (col >= chessboard.length)
            return true;

        for (int row = 0; row < chessboard.length; row++) {
            if (!isThreatened(row, col))
                placeQueen(row, col);

            if (setQueensRecursive(col +1))
                return true;
            else
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
        board.setQueensRecursive(0);
        board.printBoard();
    }

}
