package co.edu.unbosque.model;

/**
 * Representa un enemigo de tipo Scanner dentro del tablero.
 * <p>El Scanner se mueve aleatoriamente cada turno y penaliza al jugador
 * restando un porcentaje de sus movimientos restantes si este entra en
 * una casilla adyacente o en la misma casilla del scanner,
 * siempre que el jugador no tenga el modo sigilo activo.
 *
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */

public class Scanner {

    private int fila;
    private int columna;
    /**
     * Constructor del scanner con su posicion inicial en el tablero.
     *
     * @param fila    fila inicial donde se ubica el scanner
     * @param columna columna inicial donde se ubica el scanner
     */
    public Scanner(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
}