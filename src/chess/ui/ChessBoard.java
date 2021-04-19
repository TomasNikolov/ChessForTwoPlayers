package chess.ui;

import javax.swing.*;
import java.awt.*;

public class ChessBoard extends JComponent {
    int x;
    int y;
    int width;
    int height;

    public ChessBoard(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graph = (Graphics2D) g;

        ImageIcon imageIcon = new ImageIcon("src/images/chessboard.png");
        Image image = imageIcon.getImage();

        graph.drawImage(image, this.x, this.y, this.width, this.height, null);
    }
}
