package chess.pieces;

public class Rook extends Piece {

    public Rook(int x, int y, boolean isBlack) {
        super(x, y, isBlack);
    }

    @Override
    public String getImagePath() {
        if (this.isBlack) {
            return "src/images/blackRook.png";
        }
        return "src/images/whiteRook.png";
    }

    @Override
    public boolean isLegalMove(int currX, int currY, int nextY, int nextX, Piece[][] board) {

        if (currY == nextY && nextX != currX) {
            return checkRowsMove(currX, currY, nextY, nextX, board);
        } else if (currX == nextX && currY != nextY) {
            return checkColumnsMove(currX, currY, nextY, nextX, board);
        }
        return false;
    }

    private boolean checkColumnsMove(int currX, int currY, int nextY, int nextX, Piece[][] board) {
        if (nextY > currY) {
            for (int i = currY + 1; i < nextY; i++) {
                if (board[i][currX] != null) return false;
            }
            return board[nextY][nextX] == null || board[nextY][nextX].isBlack() != this.isBlack;
        } else {
            for (int i = currY - 1; i > nextY; i--) {
                if (board[i][currX] != null) return false;
            }
        }
        return board[nextY][nextX] == null || board[nextY][nextX].isBlack() != this.isBlack;
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
