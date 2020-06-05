package OchoReinas;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Reinas {

    private static final int BOARD_START_X = 2;
    private static final int BOARD_START_Y = 2;

    private static final int TILE_OFFSET_X = 50;
    private static final int TILE_OFFSET_Y = 50;
    private int i;
    private boolean[] existSolution = new boolean[1];
    private boolean[] rows = new boolean[9];
    private boolean[] diagSup = new boolean[17];
    private boolean[] diagInf = new boolean[15];
    private int[] solution = new int[9];

    ReinasGui gui;

    public Reinas(ReinasGui gui) {

        this.gui = gui;

        for (int i = 1; i <= 8; i++) {
            rows[i] = true;
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

            for (int i = 1; i < 9; i++) {
                response += "(" + (i) + "," + (solution[i]) + ")";
                System.out.println(solution[i]);
            }
            System.out.println(solution.toString());

            System.out.println("Lo logramos!!");

            JOptionPane.showMessageDialog(gui, "La respuesta es: " + response);
        }
    }

    public boolean[] start(int col, boolean[] existSolution) {
        int row;
        row = 0;

        do {
            row = row + 1;
            existSolution[0] = false;

            if (rows[row] && diagSup[col + row] && diagInf[col - row + 7]) {

                solution[col] = row;

                rows[row] = false;
                diagSup[col + row] = false;
                diagInf[col - row + 7] = false;

                gui.createAndAddPiece(BOARD_START_X + TILE_OFFSET_X * (col - 1),
                        BOARD_START_Y + TILE_OFFSET_Y * (row - 1));
                gui.repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Reinas.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (col < 8) {
                    start((col + 1), existSolution);
                    if (!existSolution[0]) {
                        rows[row] = true;
                        diagSup[col + row] = true;
                        diagInf[col - row + 7] = true;

                        gui.removePiece(BOARD_START_X + TILE_OFFSET_X * (col - 1) + 2, BOARD_START_Y + TILE_OFFSET_Y * (row - 1) + 2);
                        gui.repaint();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Reinas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    existSolution[0] = true;
                }
            }
        } while (!existSolution[0] && row != 8);
        return existSolution;
    }
}
