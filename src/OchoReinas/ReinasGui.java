package OchoReinas;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import OchoReinas.Piece;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ReinasGui extends JPanel {

    private Image imgBackground;

    private List<Piece> pieces = new ArrayList<Piece>();

    public ReinasGui() {
        URL urlBackgroundImg = getClass().getResource("/OchoReinas/img/board2.png");
        this.imgBackground = new ImageIcon(urlBackgroundImg).getImage();
        JFrame f = new JFrame();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this);
        f.setResizable(false);
        f.setSize(this.imgBackground.getWidth(null), this.imgBackground.getHeight(null));

        reinas app = new reinas(this);
    }

    public void createAndAddPiece(int x, int y) {
        Image img = this.getImageForPiece();
        Piece piece = new Piece(img, x, y);
        this.pieces.add(piece);
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void removePiece(int x, int y) {
        for (int i = this.pieces.size() - 1; i >= 0; i--) {
            Piece piece = this.pieces.get(i);
            if (piece.getX() <= x
                    && piece.getX() + piece.getWidth() >= x
                    && piece.getY() <= y
                    && piece.getY() + piece.getHeight() >= y) {
                this.pieces.remove(piece);
            }
        }
    }

    public Image getImageForPiece() {

        URL urlPieceImg = getClass().getResource("/OchoReinas/img/wq.png");
        return new ImageIcon(urlPieceImg).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.imgBackground, 0, 0, null);
        for (Piece piece : this.pieces) {
            g.drawImage(piece.getImage(), piece.getX(), piece.getY(), null);
        }
    }

    public static void main(String[] args) {
        new ReinasGui();
    }

}
