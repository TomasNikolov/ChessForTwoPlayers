package chess;

import chess.pieces.*;
import chess.ui.MousePressedListener;
import chess.ui.UI;

public class Game {

    private static final int BOARD_HEIGHT = 8;
    private static final int BOARD_WIDTH = 8;

    private UI ui;
    private Piece[][] board = new Piece[BOARD_HEIGHT][BOARD_WIDTH];
    private Piece pressedPiece;
    private boolean isWhiteTurn = true;

    public Game() {

        this.ui = new UI(600, 600);
        putFiguresOnBoard();
        this.ui.drawChessBoard();
        this.ui.setOnMousePressedListener(new MousePressedListener() {
            private int selectedRow;
            private int selectedCol;
            private int selectedX;
            private int selectedY;

            @Override
            public void onMousePressed(int x, int y) {
                System.out.println("pressed x " + x + " y " + y);
                int col = getCellByCoordinate(x);
                int row = getCellByCoordinate(y);

                Piece piece = board[row][col];
                if (piece != null) {
                    if ((piece.isBlack && isWhiteTurn) || (!piece.isBlack && !isWhiteTurn)) {
                        return;
                    }

                    System.out.println("set pressed " + piece);
                    pressedPiece = piece;
                    this.selectedCol = col;
                    this.selectedRow = row;
                    this.selectedX = piece.getPaintedX();
                    this.selectedY = piece.getPaintedY();
                }

                System.out.println("row " + col + "col " + row);
            }

            @Override
            public void onMouseReleased(int x, int y) {
                if (pressedPiece != null) {
                    int col = getCellByCoordinate(x);
                    int row = getCellByCoordinate(y);
                    boolean legalMove = pressedPiece.isLegalMove(this.selectedCol, this.selectedRow, row, col, board);
                    if (legalMove) {
                        Piece takenPiece = board[row][col];
                        ui.removePiece(takenPiece);
                        board[this.selectedRow][this.selectedCol] = null;
                        board[row][col] = pressedPiece;

                        boolean isKingUnderThread = isKingUnderThread();
                        if (isKingUnderThread) {
                            makesMoveWhenKingIsUnderThread(col, row, takenPiece);
                        }
                        System.out.println("isKingUnderThread " + isKingUnderThread);
                        pressedPiece = null;

                        if (!isKingUnderThread) {
                            isWhiteTurn = !isWhiteTurn;
                        }
                    } else {
                        Piece temp = pressedPiece;
                        pressedPiece = null;
                        System.out.println("selectedX " + this.selectedX + " selectedY " + this.selectedY);

                        ui.movePiece(temp, this.selectedX, this.selectedY);
                    }
                    System.out.println("legalMove " + legalMove);
                }
            }

            private void makesMoveWhenKingIsUnderThread(int col, int row, Piece takenPiece) {
                Piece temp = pressedPiece;
                pressedPiece = null;
                board[this.selectedRow][this.selectedCol] = temp;
                board[row][col] = takenPiece;
                System.out.println(temp + " selectedX thread " + this.selectedX + " selectedY " + this.selectedY);
                ui.movePiece(temp, this.selectedX, this.selectedY);
                ui.addGraphic(takenPiece);
                ui.drawChessBoard();
            }
        });
    }

    private int getCellByCoordinate(int cord) {
        int row = (cord / 67);
        if (row > 7) {
            row = 7;
        }
        return row;
    }

    private boolean isKingUnderThread() {
        int kingCol = 0;
        int kingRow = 0;
        //find position of King
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Piece playingPiece = board[i][j];
                if (playingPiece instanceof King && playingPiece.isBlack == !this.isWhiteTurn) {
                    kingCol = i;
                    kingRow = j;
                }
            }
        }

        //check every opponent figure if it has valid move to the king
        boolean isKingUnderThread = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Piece playingPiece = board[i][j];
                if (playingPiece != null && playingPiece.isBlack == this.isWhiteTurn) {

                    if (playingPiece.isLegalMove(j, i, kingCol, kingRow, board)) {
                        isKingUnderThread = true;
                        break;
                    }
                }
            }
            if (isKingUnderThread) {
                break;
            }
        }
        return isKingUnderThread;
    }

    public void putFiguresOnBoard() {
        int figureWidth = 70;

        putPawnsOnBoard(figureWidth);
        putRooksOnBoard(figureWidth);
        putKnightsOnBoard(figureWidth);
        putBishopsOnBoard(figureWidth);
        putQueensOnBoard(figureWidth);
        putKingsOnBoard(figureWidth);

        visualizePieceOnBoard();
    }

    private void visualizePieceOnBoard() {
        for (Piece[] playingPieces : board) {
            for (Piece playingPiece : playingPieces) {
                if (playingPiece != null) {
                    this.ui.addGraphic(playingPiece);
                }
            }
        }
    }

    private void putKingsOnBoard(int figureWidth) {
        this.board[0][4] = new King(figureWidth * 4, 0, true);
        this.board[7][4] = new King(figureWidth * 4, figureWidth * 7, false);
    }

    private void putQueensOnBoard(int figureWidth) {
        this.board[0][3] = new Queen(figureWidth * 3, 0, true);
        this.board[7][3] = new Queen(figureWidth * 3, figureWidth * 7, false);
    }

    private void putBishopsOnBoard(int figureWidth) {
        this.board[0][2] = new Bishop(figureWidth * 2, 0, true);
        this.board[0][5] = new Bishop(figureWidth * 5, 0, true);
        this.board[7][2] = new Bishop(figureWidth * 2, figureWidth * 7, false);
        this.board[7][5] = new Bishop(figureWidth * 5, figureWidth * 7, false);
    }

    private void putKnightsOnBoard(int figureWidth) {
        this.board[0][1] = new Knight(figureWidth, 0, true);
        this.board[0][6] = new Knight(figureWidth * 6, 0, true);
        this.board[7][1] = new Knight(figureWidth, figureWidth * 7, false);
        this.board[7][6] = new Knight(figureWidth * 6, figureWidth * 7, false);
    }

    private void putRooksOnBoard(int figureWidth) {
        this.board[0][0] = new Rook(0, 0, true);
        this.board[0][7] = new Rook(figureWidth * 7, 0, true);
        this.board[7][0] = new Rook(0, figureWidth * 7, false);
        this.board[7][7] = new Rook(figureWidth * 7, figureWidth * 7, false);
    }

    private void putPawnsOnBoard(int figureWidth) {
        for (int i = 0; i < this.board.length; i++) {
            this.board[1][i] = new Pawn(figureWidth * i, figureWidth * 1, true);
            this.board[6][i] = new Pawn(figureWidth * i, figureWidth * 6, false);
        }
    }

    public void play() {
        while (true) {
            System.out.print("");
            ui.movePiece(pressedPiece);
        }
    }
}
