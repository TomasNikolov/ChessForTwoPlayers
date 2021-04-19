package chess.pieces;

public class King extends Piece{
    public King(int x, int y, boolean isBlack) {
        super(x, y, isBlack);
    }

    @Override
    public String getImagePath() {
        if (this.isBlack) {
            return "src/images/blackKing.png";
        }
        return "src/images/whiteKing.png";
    }

    @Override
    public boolean isLegalMove(int currX, int currY, int nextY, int nextX, Piece[][] board) {
        return (Math.abs(currX - nextX) == 1 || currX == nextX) && (Math.abs(currY - nextY) == 1 || currY == nextY)
                && (board[nextY][nextX] == null || board[nextY][nextX].isBlack() != isBlack);
    }
}
