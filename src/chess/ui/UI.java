package chess.ui;

import chess.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UI implements MouseListener {

    private JFrame frame = new JFrame();
    private int width;
    private int height;
    private ChessBoard chessBoard = new ChessBoard(0, 0, 555, 555);
    private MousePressedListener mousePressedListener;

    public UI(int widthSize, int heightSize) {
        this.width = widthSize - 40;
        this.height = heightSize - 40;

        this.frame.setSize(widthSize - 24, heightSize - 2);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setTitle("Chess");
        this.frame.setResizable(false);
        this.frame.setVisible(true);

        this.frame.addMouseListener(this);
    }

    public void drawChessBoard() {
        this.frame.add(chessBoard);
        this.frame.setVisible(true);
    }

    public void addGraphic(Piece piece) {
        if (piece == null) {
            return;
        }

        piece.addMouseListener(this);
        this.frame.add(piece);
        this.frame.setVisible(true);
    }

    public void removePiece(Piece piece) {
        if (piece != null) {
            this.frame.remove(piece);
        }
    }

    public void setOnMousePressedListener(MousePressedListener listener) {
        this.mousePressedListener = listener;
    }

    public void movePiece(Piece piece) {
        if (piece != null) {
            piece.setPaintedX(getMouseX() - 34);
            piece.setPaintedY(getMouseY() - 34);
            piece.repaint();
        }
    }

    private int getMouseY() {
        return (int) MouseInfo.getPointerInfo().getLocation().getY() - this.frame.getLocation().y - 30;
    }

    private int getMouseX() {
        return (int) MouseInfo.getPointerInfo().getLocation().getX() - this.frame.getLocation().x - 10;
    }

    public void movePiece(Piece piece, int x, int y) {
        if (piece == null) {
            return;
        }
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        piece.setPaintedX(x);
        piece.setPaintedY(y);
        piece.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (this.mousePressedListener != null) {
            int x = getMouseX();
            int y = getMouseY();
            this.mousePressedListener.onMousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.mousePressedListener != null) {
            this.mousePressedListener.onMouseReleased(getMouseX(), getMouseY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
