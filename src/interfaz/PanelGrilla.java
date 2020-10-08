package interfaz;

import java.awt.event.*;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.*;
import controlador.Controlador;

public class PanelGrilla extends JPanel {

    private static final String BLOCK = "data/BlockNegro.png";
    private static JLabel lblMundo[][];

    private ImageIcon imgCell;
    private LabelClicMouse labelClicMouse;
    private PanelSimulacion pnlSimulacion;

    private final Controlador controlador;
    protected boolean booleanListener = true;

    public PanelGrilla(Controlador controlador, PanelSimulacion pnPanelSimulacion) {
        // ..............................................( T, L, B, R ).............................................
        setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new TitledBorder("")));
        setLayout(new GridLayout(36, 36));
        setBackground(Color.white);

        this.pnlSimulacion = pnPanelSimulacion;

        this.imgCell = new ImageIcon(BLOCK);

        // Enlaza el Controlador y el Panle de Simulación
        this.controlador = controlador;

        // Instancia atributos de la clase   
        lblMundo = new JLabel[36][36];

        // Agrega los atributos al panel   
        for (int i = 0; i < 36; i++) {
            for (int j = 0; j < 36; j++) {
                lblMundo[i][j] = new JLabel("");
                lblMundo[i][j].setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new TitledBorder("")));
                lblMundo[i][j].setHorizontalAlignment(JLabel.CENTER);
                lblMundo[i][j].setVerticalAlignment(JLabel.CENTER);
                lblMundo[i][j].setEnabled(true);
                lblMundo[i][j].addMouseListener(new LabelClicMouse(i, j, lblMundo[i][j], controlador, this));
                add(lblMundo[i][j]);
            }
        }

    }

    public void removeLabelClicMouse() {
        for (int i = 0; i < 36; i++) {
            for (int j = 0; j < 36; j++) {
                lblMundo[i][j].removeMouseListener(lblMundo[i][j].getMouseListeners()[0]);
            }
        }
    }

    public void llenarMatriz(boolean[][] matriz) {
        int conPoblacion = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j]) {
                    lblMundo[i][j].setIcon(imgCell);
                    conPoblacion++;
                } else {
                    lblMundo[i][j].setIcon(null);
                }
            }
        }
        controlador.editarGeneracion();
        controlador.editarPoblacion(conPoblacion);
    }

    public void llenarMatrizCarga(boolean[][] matriz) {
        controlador.llenarMatrizCarga(matriz);
        int conPoblacion = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j]) {
                    lblMundo[i][j].setIcon(imgCell);
                    conPoblacion++;
                } else {
                    lblMundo[i][j].setIcon(null);
                }
            }
        }
        controlador.editarPoblacion(conPoblacion);
    }

    public void limpiar() {
        for (int i = 0; i < 36; i++) {
            for (int j = 0; j < 36; j++) {
                lblMundo[i][j].setIcon(null);
            }
        }
        controlador.limpiarVariable();
    }

    public void inhabilitar() {
        booleanListener = false;
        for (int i = 0; i < 36; i++) {
            for (int j = 0; j < 36; j++) {
                lblMundo[i][j].setEnabled(false);
            }
        }
    }

    public void habilitar() {
        booleanListener = true;
        for (int i = 0; i < 36; i++) {
            for (int j = 0; j < 36; j++) {
                lblMundo[i][j].setEnabled(true);
            }
        }
    }
}

/**
 * Controlador de eventos del Mouse
 *
 * @author Giovanni Fajardo Utria
 */
class LabelClicMouse extends MouseAdapter {

    private static final String BLOCK = "data/BlockNegro.png";
    private int contadorPopulation = 0;

    private JLabel label;
    private Controlador ctrl;
    private int x, y;
    private ImageIcon imgCell;
    private Color color;
    private PanelGrilla pnlMundo;

    public LabelClicMouse(int x, int y, JLabel label, Controlador ctrl, PanelGrilla pnlMundo) {
        this.label = label;
        this.ctrl = ctrl;
        this.x = x;
        this.y = y;
        this.color = Color.black;
        this.imgCell = new ImageIcon(BLOCK);
        this.pnlMundo = pnlMundo;
    }

    @Override
    public void mouseClicked(MouseEvent evento) {
        if (pnlMundo.booleanListener) {
            if (evento.isShiftDown()) {
                if (evento.isMetaDown()) {
                } else {
                }
            } else {
                if (evento.isMetaDown()) {
                    if ((label.getText()).equals("") && label.getIcon() == null) {
                        label.setIcon(imgCell);
                        //System.out.println("Ponen celula: LabelClicMouse(" + x + "," + y + ")" );
                        ctrl.aumentar();
                        ctrl.putCell(x, y, true);
                    } else if (label.getIcon() != null) {
                        label.setIcon(null);
                        //System.out.println("Quitar celula: LabelClicMouse(" + x + "," + y + ")" ); 
                        ctrl.disminuir();
                        ctrl.putCell(x, y, false);
                    } else {
                    }
                } else {
                    if (evento.isAltDown()) {
                    } else {
                    }
                }
            }
        }
    }

}
