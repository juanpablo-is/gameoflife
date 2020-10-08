package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.*;
import controlador.Controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Util;

public class InterfazApp extends JFrame {

    private PanelGrilla pnlMundo;
    private final PanelBotones pnlBotones;
    private final PanelSimulacion pnlSimulacion;
    private final PanelOpcion pnlOpcion;
    private final Controlador ctrl;

    private final JMenuBar mbrOpciones;
    private final JMenu mnuAcerca;
    private final JMenu mnuCargar;

    private final JMenuItem mitCarga1;
    private final JMenuItem mitCarga2;
    private final JMenuItem mitCarga3;
    private final JMenuItem mitCarga4;
    private final JMenuItem mitAcerca;

    private boolean[][] carga1 = new boolean[36][36];
    private boolean opcionFrame = true;

    public InterfazApp(Controlador ctrl) {
        setTitle("Game of Life");
        getContentPane().setLayout(null);
        setBackground(Color.white);

        this.ctrl = ctrl;

        mbrOpciones = new JMenuBar();

        mnuAcerca = new JMenu();
        mnuAcerca.setMnemonic('A');
        mnuAcerca.setText("Sobre");

        mitAcerca = new JMenuItem();
        mitAcerca.setMnemonic('G');
        mitAcerca.setText("Autor: Giovanni Fajardo Utria.");

        mnuCargar = new JMenu();
        mnuCargar.setMnemonic('C');
        mnuCargar.setText("Cargar Juego");

        mitCarga1 = new JMenuItem();
        mitCarga1.setMnemonic('1');
        mitCarga1.setText("Cargar Prueba 1");

        mitCarga2 = new JMenuItem();
        mitCarga2.setMnemonic('2');
        mitCarga2.setText("Cargar Prueba 2");

        mitCarga3 = new JMenuItem();
        mitCarga3.setMnemonic('3');
        mitCarga3.setText("Cargar Prueba 3");

        mitCarga4 = new JMenuItem();
        mitCarga4.setMnemonic('3');
        mitCarga4.setText("Cargar Prueba 4");

        mnuAcerca.add(mitAcerca);
        mbrOpciones.add(mnuAcerca);

        mnuCargar.add(mitCarga1);
        mnuCargar.add(mitCarga2);
        mnuCargar.add(mitCarga3);
        mnuCargar.add(mitCarga4);
        mbrOpciones.add(mnuCargar);

        setJMenuBar(mbrOpciones);

        /*
        String[] opciones = {"INFINITO", "FINITO (36)"};
        if (opcionFrame) {
            int resultadoFrame = JOptionPane.showOptionDialog(null, "Seleccione modo de juego.", "JUEGO DE LA VIDA", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            opcionFrame = false;
            ctrl.opcionFrame(resultadoFrame);
        }
         */
        mitCarga1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    boolean[][] m = ctrl.leerMatriz(0);
                    pnlMundo.llenarMatrizCarga(m);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(InterfazApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        mitCarga2.addActionListener((ActionEvent ae) -> {
            try {
                boolean[][] m = ctrl.leerMatriz(1);
                pnlMundo.llenarMatrizCarga(m);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InterfazApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        mitCarga3.addActionListener((ActionEvent ae) -> {
            try {
                boolean[][] m = ctrl.leerMatriz(2);
                pnlMundo.llenarMatrizCarga(m);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InterfazApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        mitCarga4.addActionListener((ActionEvent ae) -> {
            try {
                boolean[][] m = ctrl.leerMatriz(3);
                pnlMundo.llenarMatrizCarga(m);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InterfazApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        pnlBotones = new PanelBotones(ctrl);
        pnlBotones.setBounds(740, 160, 160, 280);

        pnlSimulacion = new PanelSimulacion();
        pnlSimulacion.setBounds(740, 20, 160, 140);

        pnlMundo = new PanelGrilla(ctrl, pnlSimulacion);
        pnlMundo.setBounds(10, 10, 730, 490);

        pnlOpcion = new PanelOpcion(ctrl);
        pnlOpcion.setBounds(740, 440, 160, 60);

        getContentPane().add(pnlMundo);
        getContentPane().add(pnlBotones);
        getContentPane().add(pnlSimulacion);
        getContentPane().add(pnlOpcion);

        ctrl.conectar(pnlMundo, pnlSimulacion, pnlOpcion);

        getContentPane().setBackground(Color.white);
        setSize(915, 555);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Util.centrarVentana(this);
    }

//  Ejecucion.		
    public static void main(String args[]) {
        InterfazApp frmMain = new InterfazApp(new Controlador());
        frmMain.setVisible(true);
    }
}
