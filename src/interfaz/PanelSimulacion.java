package interfaz;

import controlador.Controlador;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class PanelSimulacion extends JPanel{
    
    private JLabel txtGeneracion;
    private JLabel txtPoblacion;

    public PanelSimulacion() {
        
        setBorder(new CompoundBorder(new EmptyBorder(0,0,0,0), new TitledBorder("Simulación")));
        setLayout(null);
        setBackground(Color.white);
        
        txtGeneracion = new JLabel("Generation: 0");
        txtGeneracion.setBounds(30, 30, 100, 40);
        txtPoblacion = new JLabel("Population: 0");
        txtPoblacion.setBounds(30, 70, 100, 40);
        add(txtGeneracion);
        add(txtPoblacion);
    }

    public JLabel getTxtGeneracion() {
        return txtGeneracion;
    }

    public void setTxtGeneracion(String txtGeneracion) {
        this.txtGeneracion.setText(txtGeneracion);
    }

    public JLabel getTxtPoblacion() {
        return txtPoblacion;
    }
    
    public void setTxtPoblacion(String txtPoblacion) {
        this.txtPoblacion.setText(txtPoblacion);
    }
}