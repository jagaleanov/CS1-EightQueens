package OchoReinas;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class reinas {

    private static final int BOARD_START_X = 2;
    private static final int BOARD_START_Y = 2;

    private static final int TILE_OFFSET_X = 50;
    private static final int TILE_OFFSET_Y = 50;
    private int i;
    private boolean[] existSolution = new boolean[1];
    private boolean[] columns = new boolean[9];
    private boolean[] diagSup = new boolean[17];
    private boolean[] diagInf = new boolean[15];
    private int[] solution = new int[9];

    ReinasGui gui;

    public reinas(ReinasGui gui) {

        this.gui = gui;

        for (int i = 1; i <= 8; i++) {
            columns[i] = true;
        }
        for (int i = 2; i <= 16; i++) {
            diagSup[i] = true;
        }
        for (int i = -7; i <= 7; i++) {
            diagInf[i + 7] = true;
        }

        start(1, existSolution);

        if (existSolution[0]) {

            String response = "";

            for (int i = 0; i < 8; i++) {
                response += "(" + (i + 1) + "," + (solution[i] + 1) + ")";
            }
            System.out.println("");

            System.out.println("Lo logramos!!");

            JOptionPane.showMessageDialog(gui, "La respuesta es: " + response);
        }
    }

    public boolean[] start(int row, boolean[] existSolution) {
        int col;
        col = 0;

        do {
            col = col + 1;
            existSolution[0] = false;

            if (columns[col] && diagSup[row + col] && diagInf[row - col + 7]) {

                solution[row] = col;

                columns[col] = false;
                diagSup[row + col] = false;
                diagInf[row - col + 7] = false;

                gui.createAndAddPiece(BOARD_START_X + TILE_OFFSET_X * (row - 1),
                        BOARD_START_Y + TILE_OFFSET_Y * (col - 1));
                gui.repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(reinas.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (row < 8) {
                    start((row + 1), existSolution);
                    if (!existSolution[0]) {
                        columns[col] = true;
                        diagSup[row + col] = true;
                        diagInf[row - col + 7] = true;

                        gui.removePiece(BOARD_START_X + TILE_OFFSET_X * (row - 1) + 2, BOARD_START_Y + TILE_OFFSET_Y * (col - 1) + 2);
                        gui.repaint();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(reinas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    existSolution[0] = true;
                }
            }
        } while (!existSolution[0] && col != 8);
        return existSolution;
    }
}
