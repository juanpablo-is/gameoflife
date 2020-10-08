package controlador;

import interfaz.PanelGrilla;
import interfaz.PanelOpcion;
import interfaz.PanelSimulacion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import mundo.GameOfLife;
import mundo.Simulation;

public class Controlador {

    // Relaciones
    private PanelGrilla pnlMundo;
    private PanelOpcion pnlOpcion;
    private GameOfLife gameOfLife;
    private PanelSimulacion pnlSimulacion;
    private Simulation simulation;
    private int numero = 0;
    private int guardar = 0;
    private int contadorGeneracion = 0;
    private boolean matriz[][] = new boolean[36][36];
    // Atributos

    // Constructor  
    public Controlador() {
        gameOfLife = new GameOfLife(this);
    }

    public void opcionFrame(int opcion) {
        gameOfLife.opcionFrame(opcion);
    }

    public void editarPoblacion(int cont) {
        numero = cont;
        pnlSimulacion.setTxtPoblacion("Population: " + cont);
    }

    public void editarGeneracion() {
        pnlSimulacion.setTxtGeneracion("Generation: " + (++contadorGeneracion));
    }

    public void aumentar() {
        pnlSimulacion.setTxtPoblacion("Population: " + (++numero));
    }

    public void disminuir() {
        pnlSimulacion.setTxtPoblacion("Population: " + (--numero));
    }

    public void limpiarVariable() {
        numero = 0;
        contadorGeneracion = 0;
        pnlSimulacion.setTxtGeneracion("Generation: 0");
        pnlSimulacion.setTxtPoblacion("Population: 0");
    }

    public void limpiarGrilla() {
        for (int i = 0; i < 36; i++) {
            for (int j = 0; j < 36; j++) {
                matriz[i][j] = false;
            }
        }
        pnlMundo.limpiar();
    }

    public void inhabilitar() {
        pnlMundo.inhabilitar();
    }

    public void habilitar() {
        pnlMundo.habilitar();
    }

    public void startBtnStop() {
        gameOfLife.btnStop();
    }

    public void matrizEnviar() {
        gameOfLife.btnPlay();
    }

    public void setMatriz(boolean[][] matri) {
        pnlMundo.llenarMatriz(matri);
        matriz = matri;
    }

    public void conectar(PanelGrilla pnlMundo, PanelSimulacion pnlSimulacion, PanelOpcion pnlOpcion) {
        this.pnlSimulacion = pnlSimulacion;
        this.pnlMundo = pnlMundo;
        this.pnlOpcion = pnlOpcion;
    }

    public void putCell(int x, int y, Boolean cell) {
        matriz[x][y] = cell;
    }

    public boolean[][] getMatriz() {
        return matriz;
    }

    public void llenarMatrizCarga(boolean[][] matriz) {
        this.matriz = matriz;
    }

    public void guardarMatriz() throws IOException {
        FileWriter archivo = new FileWriter("C:/Users/Juan Pablo/Desktop/GuardarMapa-3.txt");
        String cadena = "";
        for (int i = 0; i < 36; i++) {
            for (int j = 0; j < 36; j++) {
                cadena = cadena + matriz[i][j] + " ";
            }
        }
        archivo.write(cadena);
        archivo.close();
    }

    public boolean[][] leerMatriz(int id) throws FileNotFoundException {
        contadorGeneracion = 0;
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(new File("C:/Users/Juan Pablo/Desktop/GuardarMapa-" + id + ".txt"));
        boolean[][] matrizFinal = new boolean[36][36];
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            sb.append(line).append("\n");
        }
        String matrizLeer[] = sb.toString().split(" ");
        int con = 0;
        for (int j = 0; j < 36; j++) {
            for (int k = 0; k < 36; k++) {
                matrizFinal[j][k] = Boolean.parseBoolean(matrizLeer[con++]);
            }
        }
        return matrizFinal;
    }

    public void inhabilitarCheck(boolean opcion) {
        pnlOpcion.inhabilitarCheck(opcion);
    }

}
