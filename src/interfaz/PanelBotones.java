package interfaz;

import controlador.Controlador;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class PanelBotones extends JPanel {

    private JButton btnPlay;
    private JButton btnStop;
    private JButton btnClear;
    private JButton btnGuardar;
    private JButton btnLimpiar;

    private Controlador ctrl;
    private boolean opcionClic = true;
    private boolean boton = true;

    public PanelBotones(Controlador ctrl) {
        this.ctrl = ctrl;

        setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new TitledBorder("Botones")));
        setLayout(null);
        setBackground(Color.white);

        btnPlay = new JButton("JUGAR");
        btnPlay.setBackground(Color.white);
        btnPlay.setForeground(Color.black);
        btnPlay.setBorder(BorderFactory.createLineBorder(Color.blue));
        btnPlay.setBounds(30, 30, 100, 40);
        add(btnPlay);

        btnStop = new JButton("PARAR");
        btnStop.setBackground(Color.white);
        btnStop.setForeground(Color.black);
        btnStop.setBorder(BorderFactory.createLineBorder(Color.blue));
        btnStop.setBounds(30, 90, 100, 40);
        btnStop.setEnabled(false);

        add(btnStop);

        btnGuardar = new JButton("GUARDAR");
        btnGuardar.setBackground(Color.white);
        btnGuardar.setForeground(Color.black);
        btnGuardar.setBorder(BorderFactory.createLineBorder(Color.blue));
        btnGuardar.setBounds(30, 150, 100, 40);
        add(btnGuardar);

        btnLimpiar = new JButton("LIMPIAR");
        btnLimpiar.setBackground(Color.white);
        btnLimpiar.setForeground(Color.black);
        btnLimpiar.setBorder(BorderFactory.createLineBorder(Color.blue));
        btnLimpiar.setBounds(30, 210, 100, 40);
        add(btnLimpiar);

        btnPlay.addActionListener((ActionEvent ae) -> {
            if (boton) {
                ctrl.matrizEnviar();
                boton = false;
                btnStop.setEnabled(true);
                btnPlay.setEnabled(false);
                btnLimpiar.setEnabled(false);
                btnGuardar.setEnabled(false);
                ctrl.inhabilitarCheck(false);
            }
        });

        btnLimpiar.addActionListener((ActionEvent ae) -> {
            ctrl.limpiarGrilla();
        });

        btnStop.addActionListener((ActionEvent ae) -> {
            btnStop.setEnabled(false);
            btnPlay.setEnabled(true);
            btnLimpiar.setEnabled(true);
            btnGuardar.setEnabled(true);
            ctrl.inhabilitarCheck(true);
            ctrl.startBtnStop();
            boton = true;
        });

        btnGuardar.addActionListener((ActionEvent ae) -> {
            try {
                ctrl.guardarMatriz();
            } catch (IOException ex) {
                Logger.getLogger(PanelBotones.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnPlay.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_J) {
                    ctrl.matrizEnviar();
                    boton = false;
                    btnStop.setEnabled(true);
                    btnPlay.setEnabled(false);
                    btnLimpiar.setEnabled(false);
                    ctrl.inhabilitarCheck(false);
                    btnGuardar.setEnabled(false);
                } else if (ke.getKeyCode() == KeyEvent.VK_P) {
                    btnStop.setEnabled(false);
                    btnPlay.setEnabled(true);
                    btnLimpiar.setEnabled(true);
                    btnGuardar.setEnabled(true);
                    ctrl.inhabilitarCheck(true);
                    ctrl.startBtnStop();
                    boton = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {

            }
        });

        btnStop.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_J) {
                    ctrl.matrizEnviar();
                    boton = false;
                    btnStop.setEnabled(true);
                    btnPlay.setEnabled(false);
                    btnLimpiar.setEnabled(false);
                    ctrl.inhabilitarCheck(false);
                    btnGuardar.setEnabled(false);
                } else if (ke.getKeyCode() == KeyEvent.VK_P) {
                    btnStop.setEnabled(false);
                    btnPlay.setEnabled(true);
                    btnLimpiar.setEnabled(true);
                    ctrl.inhabilitarCheck(true);
                    btnGuardar.setEnabled(true);
                    ctrl.startBtnStop();
                    boton = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {

            }
        });
    }
}
