package co.edu.unbosque.model;
/**
 * Representa el paquete de datos que el jugador debe llevar hasta la salida.
 * <p>El paquete es empujado por el jugador al moverse hacia la casilla que ocupa,
 * desplazandose en la misma direccion del movimiento. El objetivo del juego
 * es llevar el paquete hasta la salida una vez que todos los puertos han sido enlazados.
 *
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */
public class Paquete {
	
	private int fila;
    private int columna;
    
    /**
     * Constructor del paquete con su posicion inicial en el tablero.
     *
     * @param fila    fila inicial donde se ubica el paquete
     * @param columna columna inicial donde se ubica el paquete
     */
    
    public Paquete(int fila, int columna) {
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
