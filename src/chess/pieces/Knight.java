package chess.pieces;

public class Knight extends Piece {

    public Knight(int x, int y, boolean isBlack) {
        super(x, y, isBlack);
    }

    @Override
    public String getImagePath() {
        if (this.isBlack) {
            return "src/images/blackKnight.png";
        }
        return "src/images/whiteKnight.png";
    }

    @Override
    public boolean isLegalMove(int currX, int currY, int nextY, int nextX, Piece[][] board) {
        return ((nextX >= 0 && nextX < 8 && nextY >= 0 && nextY < 8)
                && (checkMoveFromBottomToTop(currX, currY, nextY, nextX)
                || checkMoveFromTopToBottom(currX, currY, nextY, nextX))
                && (board[nextY][nextX] == null || this.isBlack != board[nextY][nextX].isBlack()));
    }

    private boolean checkMoveFromBottomToTop(int currX, int currY, int nextY, int nextX) {
        return (nextX - currX == 2 && currY - nextY == 1)
                || (currX - nextX == 2 && currY - nextY == 1)
                || (currY - nextY == 2 && nextX - currX == 1)
                || (currY - nextY == 2 && currX - nextX == 1);
    }

    private boolean checkMoveFromTopToBottom(int currX, int currY, int nextY, int nextX) {
        return (nextX - currX == 2 && nextY - currY == 1)
                || (nextY - currY == 2 && nextX - currX == 1)
                || (nextY - currY == 2 && currX - nextX == 1)
                || (currX - nextX == 2 && nextY - currY == 1);
    }

    @Override
    public void setPaintedX(int x) {
        System.out.println("set painted " + x);
        super.setPaintedX(x);
    }
}
