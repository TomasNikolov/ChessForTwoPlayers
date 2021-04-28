package chess.pieces;

import javax.swing.*;
import java.awt.*;

public abstract class Piece extends JButton {

    private int x;
    private int y;
    public boolean isBlack;

    public Piece(int x, int y, boolean isBlack) {
        this.x = x;
        this.y = y;
        this.isBlack = isBlack;
        setPreferredSize(new Dimension(40, 40));

        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    public void paintComponent(Graphics g) {

        Graphics2D graph = (Graphics2D) g;

        ImageIcon imageIcon = new ImageIcon(getImagePath());
        Image image = imageIcon.getImage();

        graph.drawImage(image, this.x, this.y, 60, 60, null);
    }

    public abstract String getImagePath();

    public void setPaintedX(int x) {
        this.x = x;
    }

    public void setPaintedY(int y) {
        this.y = y;
    }

    public boolean isBlack() {
        return this.isBlack;
    }

    public int getPaintedX() {
        return this.x;
    }

    public int getPaintedY() {
        return this.y;
    }

    public abstract boolean isLegalMove(int currX, int currY, int nextY, int nextX, Piece[][] board);
}
