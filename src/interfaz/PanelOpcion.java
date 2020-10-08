package interfaz;

import controlador.Controlador;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Juan Pablo
 */
public class PanelOpcion extends JPanel {

    private final JRadioButton jCheckInf, jCheckFin;
    private ButtonGroup btnGrupo;
    private final Controlador ctrl;

    public PanelOpcion(Controlador ctrl) {
        this.ctrl = ctrl;

        setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0), new TitledBorder("Modo de juego")));
        setBackground(Color.white);

        jCheckInf = new JRadioButton("Infinito", false);
        jCheckInf.setBackground(Color.white);
        add(jCheckInf);

        jCheckFin = new JRadioButton("Finito", true);
        jCheckFin.setBackground(Color.white);
        add(jCheckFin);

        btnGrupo = new ButtonGroup();
        btnGrupo.add(jCheckInf);
        btnGrupo.add(jCheckFin);

        jCheckFin.addActionListener((ActionEvent ae) -> {
            ctrl.opcionFrame(1);
        });

        jCheckInf.addActionListener((ActionEvent ae) -> {
            ctrl.opcionFrame(0);
        });
    }

    public void inhabilitarCheck(boolean opcion) {
        jCheckFin.setEnabled(opcion);
        jCheckInf.setEnabled(opcion);
    }
}
