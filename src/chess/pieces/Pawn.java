package chess.pieces;

public class Pawn extends Piece {

    public Pawn(int x, int y, boolean isBlack) {
        super(x, y, isBlack);
    }

    @Override
    public String getImagePath() {
        if (this.isBlack) {
            return "src/images/blackPawn.png";
        }
        return "src/images/whitePawn.png";
    }

    @Override
    public boolean isLegalMove(int currX, int currY, int nextY, int nextX, Piece[][] board) {
        if (!this.isBlack) {
            return checkMoveForWhitePawn(currX, currY, nextY, nextX, board);
        } else {
            return checkMoveForBlackPawn(currX, currY, nextY, nextX, board);
        }
    }

    private boolean checkMoveForBlackPawn(int currX, int currY, int nextY, int nextX, Piece[][] board) {
        if (((currY == 1 && nextY == 3 && board[nextY][currX] == null)
                || (nextY - currY == 1)) && board[currY + 1][currX] == null && currX == nextX) {
            return true;
        } else {
            return nextY - currY == 1 && (currX - nextX == 1 || nextX - currX == 1)
                    && (board[nextY][nextX] != null && !board[nextY][nextX].isBlack);
        }
    }

    private boolean checkMoveForWhitePawn(int currX, int currY, int nextY, int nextX, Piece[][] board) {
        if (((currY == 6 && nextY == 4 && board[nextY][currX] == null)
                || (currY - nextY == 1)) && board[currY - 1][currX] == null && currX == nextX) {
            return true;
        } else {
            return currY - nextY == 1 && (currX - nextX == 1 || nextX - currX == 1)
                    && (board[nextY][nextX] != null && board[nextY][nextX].isBlack);
        }
    }
}
