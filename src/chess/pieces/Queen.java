package chess.pieces;

public class Queen extends Piece {

    public Queen(int x, int y, boolean isBlack) {
        super(x, y, isBlack);
    }

    @Override
    public String getImagePath() {
        if (this.isBlack) {
            return "src/images/blackQueen.png";
        }
        return "src/images/whiteQueen.png";
    }

    @Override
    public boolean isLegalMove(int currX, int currY, int nextY, int nextX, Piece[][] board) {

        if (currY == nextY && nextX != currX) {
            return checkRowsMove(currX, currY, nextY, nextX, board);
        } else if (currX == nextX && currY != nextY) {
            return checkColumnsMove(currX, currY, nextY, nextX, board);
        } else if (currX - nextX == currY - nextY && currX - nextX > 0) {
            return checkUpperLeftMove(currX, currY, board[nextY][nextX], nextX, board);
        } else if (nextX - currX == currY - nextY && nextX - currX > 0) {
            return checkUpperRightMove(currX, currY, board[nextY][nextX], nextX, board);
        } else if (currX - nextX == nextY - currY && currX - nextX > 0) {
            return checkLowerLeftMove(currX, currY, board[nextY][nextX], nextX, board);
        } else if (nextX - currX == nextY - currY && nextX - currX > 0) {
            return checkLowerRightMove(currX, currY, board[nextY][nextX], nextX, board);
        }
        return false;
    }

    private boolean checkLowerRightMove(int currX, int currY, Piece piece, int nextX, Piece[][] board) {
        for (int i = 1; i < nextX - currX; i++) {
            if (board[currY + i][currX + i] != null) return false;
        }
        return piece == null || piece.isBlack() != this.isBlack;
    }

    private boolean checkLowerLeftMove(int currX, int currY, Piece piece, int nextX, Piece[][] board) {
        for (int i = 1; i < currX - nextX; i++) {
            if (board[currY + i][currX - i] != null) return false;
        }
        return piece == null || piece.isBlack() != this.isBlack;
    }

    private boolean checkUpperRightMove(int currX, int currY, Piece piece, int nextX, Piece[][] board) {
        for (int i = 1; i < nextX - currX; i++) {
            if (board[currY - i][currX + i] != null) return false;
        }
        return piece == null || piece.isBlack() != this.isBlack;
    }

    private boolean checkUpperLeftMove(int currX, int currY, Piece piece, int nextX, Piece[][] board) {
        for (int i = 1; i < currX - nextX; i++) {
            if (board[currY - i][currX - i] != null) return false;
        }
        return piece == null || piece.isBlack() != this.isBlack;
    }

    private boolean checkColumnsMove(int currX, int currY, int nextY, int nextX, Piece[][] board) {
        if (nextY > currY) {
            for (int i = currY + 1; i < nextY; i++) {
                if (board[i][currX] != null) return false;
            }
            return board[nextY][nextX] == null || board[nextY][nextX].isBlack() != isBlack;
        } else {
            for (int i = currY - 1; i > nextY; i--) {
                if (board[i][currX] != null) return false;
            }
        }
        return board[nextY][nextX] == null || board[nextY][nextX].isBlack() != isBlack;
    }

    private boolean checkRowsMove(int currX, int currY, int nextY, int nextX, Piece[][] board) {
        if (nextX > currX) {
            for (int i = currX + 1; i < nextX; i++) {
                if (board[currY][i] != null) return false;
            }
            return board[nextY][nextX] == null || board[nextY][nextX].isBlack() != this.isBlack;
        } else {
            for (int i = currX - 1; i > nextX; i--) {
                if (board[currY][i] != null) return false;
            }
        }
        return board[nextY][nextX] == null || board[nextY][nextX].isBlack() != this.isBlack;
    }
}
