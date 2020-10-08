package mundo;

import controlador.Controlador;

public class Simulation {

    private Controlador ctrl;
    private GameOfLife gameOfLife;
   
    
    public Simulation(Controlador ctlr,GameOfLife gameOfLife) {
        this.ctrl = ctlr;
        this.gameOfLife = gameOfLife;
    }
    
    
}