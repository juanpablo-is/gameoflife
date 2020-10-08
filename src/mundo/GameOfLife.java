package mundo;

import controlador.Controlador;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameOfLife {

    private Simulation simulation;
    private Controlador ctrl;
    private static boolean[][] matrizCopia = new boolean[36][36];
    private static boolean[][] matrizModi = new boolean[36][36];
    private static Thread hilo;
    private boolean var = true;
    private static int opcionFrame = 1;

    public GameOfLife(Controlador ctrl) {
        this.ctrl = ctrl;
        simulation = new Simulation(this.ctrl, this);
    }
    
    public void opcionFrame(int opcion){
        opcionFrame = opcion;
    }

    public void btnPlay() {
        var = true;
        ctrl.inhabilitar();
        hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                while (var) {
                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameOfLife.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    boolean[][] ma = ctrl.getMatriz();
                    copiar(ma);
                    verificar();
                    ctrl.setMatriz(matrizModi);
                }
            }
        });
        hilo.start();
    }

    public void btnStop() {
        var = false;
        hilo.stop();
        ctrl.habilitar();
    }

    public static void verificar() {
        matrizModi = new boolean[36][36];
        for (int i = 0; i < matrizModi.length; i++) {
            for (int j = 0; j < matrizModi.length; j++) {
                int vecinos = numeroVecinos(i, j, matrizCopia);
                if (matrizCopia[i][j]) {
                    if (vecinos == 2 || vecinos == 3) {
                        matrizModi[i][j] = true;
                    } else {
                        matrizModi[i][j] = false;
                    }
                } else {
                    if (vecinos == 3) {
                        matrizModi[i][j] = true;
                    } else {
                        matrizModi[i][j] = false;
                    }
                }
            }
        }
    }

    private static void copiar(boolean[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matrizCopia[i][j] = matriz[i][j];
            }
        }
    }

    /*
    
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (i >= 0 && j >= 0 && i <= matriz.length - 1 && j <= matriz.length - 1) {
                    if (matrizCopia[i][j]) {
                        if (i != x || j != y) {
                            cont++;
                        }
                    }
                    if ((i == -1 && j == -1) || (i == 36 && j == 36) || (i == 0 && j == 36) || (j == 0 && i == 36)) {

                    } else if () {

                    }
                }
            }
        }
     */
    public static int numeroVecinos(int x, int y, boolean[][] matriz) {
        int cont = 0;
        if (opcionFrame == 0) {
            if ((x == 0 && y == 0) || (x == 35 && y == 35) || (x == 0 && y == 35) || (y == 0 && x == 35)) {
                cont = 0;
                for (int i = x - 1; i < x + 2; i++) {
                    for (int j = y - 1; j < y + 2; j++) {
                        if (i >= 0 && j >= 0 && i <= matriz.length - 1 && j <= matriz.length - 1) {
                            if (matrizCopia[i][j]) {
                                if (i != x || j != y) {
                                    cont++;
                                }
                            }
                        }
                    }
                }
                // VERTICAL 0
            } else if (y == 0 && (x > 0 || x < 35)) {
                int aux = 35;
                cont = 0;
                for (int i = x - 1; i < x + 2; i++) {
                    for (int j = y - 1; j < y + 2; j++) {
                        if (j < 0) {
                            aux = 35;
                        } else {
                            aux = j;
                        }
                        if (matrizCopia[i][aux]) {
                            if (i != x || j != y) {
                                cont++;
                            }
                        }
                    }
                }
                //VALORES EN VERTICAL 35
            } else if (y == 35 && (x > 0 || x < 35)) {
                int aux = 0;
                cont = 0;
                for (int i = x - 1; i < x + 2; i++) {
                    for (int j = y - 1; j < y + 2; j++) {
                        if (j > 35) {
                            aux = 0;
                        } else {
                            aux = j;
                        }
                        if (matrizCopia[i][aux]) {
                            if (i != x || j != y) {
                                cont++;
                            }
                        }
                    }
                }
                //VALORES EN HORIZONTAL 0
            } else if (x == 0 && (y > 0 || y < 35)) {
                int aux = 35;
                cont = 0;
                for (int i = x - 1; i < x + 2; i++) {
                    for (int j = y - 1; j < y + 2; j++) {
                        if (i < 0) {
                            aux = 35;
                        } else {
                            aux = i;
                        }
                        if (matrizCopia[aux][j]) {
                            if (i != x || j != y) {
                                cont++;
                            }
                        }
                    }
                }
                //VALORES EN HORIZONTAL 35
            } else if (x == 35 && (y > 0 || y < 35)) {
                int aux = 0;
                cont = 0;
                for (int i = x - 1; i < x + 2; i++) {
                    for (int j = y - 1; j < y + 2; j++) {
                        if (i > 35) {
                            aux = 0;
                        } else {
                            aux = i;
                        }
                        if (matrizCopia[aux][j]) {
                            if (i != x || j != y) {
                                cont++;
                            }
                        }
                    }
                }
            } else {
                cont = 0;
                for (int i = x - 1; i < x + 2; i++) {
                    for (int j = y - 1; j < y + 2; j++) {
                        if (i >= 0 && j >= 0 && i <= matriz.length - 1 && j <= matriz.length - 1) {
                            if (matrizCopia[i][j]) {
                                if (i != x || j != y) {
                                    cont++;
                                }
                            }
                        }
                    }
                }
            }
        } else if (opcionFrame == 1) {
            cont = 0;
            for (int i = x - 1; i < x + 2; i++) {
                for (int j = y - 1; j < y + 2; j++) {
                    if (i >= 0 && j >= 0 && i <= matriz.length - 1 && j <= matriz.length - 1) {
                        if (matrizCopia[i][j]) {
                            if (i != x || j != y) {
                                cont++;
                            }
                        }
                    }
                }
            }
        }

        return cont;
    }
}
