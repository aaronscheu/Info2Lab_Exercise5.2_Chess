/**
 * Created by amaridev on 19/01/16.
 * Package: PACKAGE_NAME for Exercise5.2_Chess.
 */
public class Board {
    int[][] chessboard;


    public Board(int size) {
        chessboard = new int[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                chessboard[y][x] = 0;
            }
        }
    }

    public void placeQueen (int x, int y) {
        chessboard[chessboard.length - y][x -1] = 1;
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
        if (row == col)
            for (int rows = 0, cols = 0; rows < chessboard.length; rows++, cols++)
                if (chessboard[rows][cols] == 1) return true;

        if (row > col)
            for (int rows = 0, cols = (col - row); cols < chessboard.length; rows++, cols++)
                if (chessboard[rows][cols] == 1) return true;

        if (row < col)
            for (int rows = (row - col), cols = 0; rows < chessboard.length; rows++, cols++)
                if (chessboard[rows][cols] == 1) return true;

        /*** descending ***/ //zero-based

        if (row == (chessboard.length - col))
            for (int rows = chessboard.length -1, cols = 0; rows > 0; rows--, cols++)
                if (chessboard[rows][cols] == 1) return true;

        if (row > (chessboard.length - col))
            for (int rows = chessboard.length -1, cols = ((chessboard.length - col) - row) -1; rows > 0; rows--, cols++)
                if (chessboard[rows][cols] == 1) return true;

        if (row < (chessboard.length - col))
            for (int rows = (row - (chessboard.length - col)) -1, cols = 0; rows > 0; rows--, cols++)
                if (chessboard[rows][cols] == 1) return true;

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
        board.placeQueen(1, 2);
        board.printBoard();
        System.out.println(board.isThreatened(0,6));
    }

}
