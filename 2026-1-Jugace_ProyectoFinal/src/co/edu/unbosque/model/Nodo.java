package co.edu.unbosque.model;
/**
 * Representa un Nodo energetico dentro del tablero.
 * <p>Al ser recogido por el jugador (al pasar por su casilla), el nodo otorga
 * movimientos adicionales equivalentes al 10% del total de casillas del tablero.
 * Una vez recogido, el nodo se desactiva y no vuelve a otorgar beneficios.
 *
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */
public class Nodo {
	
	private int fila;
	private int columna;
	private boolean act = true;
	/**
     * Constructor del nodo con su posicion inicial en el tablero.
     * <p>El nodo se inicializa como activo al crearse.
     *
     * @param fila    fila donde se colocara el nodo
     * @param columna columna donde se colocara el nodo
     */
	public Nodo(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
		this.act = true;
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

	public boolean isAct() {
		return act;
	}

	public void setAct(boolean act) {
		this.act = act;
	}
	
}