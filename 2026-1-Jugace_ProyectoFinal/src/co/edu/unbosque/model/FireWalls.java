package co.edu.unbosque.model;
/**
 * Representa un FireWall dentro del tablero de juego.
 * <p>Los FireWalls son obstaculos estaticos que bloquean el paso del jugador
 * y del paquete. 
 * <p>Ademas, dos FireWalls ubicados en la misma columna con una
 * casilla de separacion entre ellos generan una Trampa en la casilla intermedia,
 * que penaliza al jugador con la perdida de movimientos si la pisa.
 *
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */
public class FireWalls {
	
	private int fila;
	private int columna;
	
	/**
     * Constructor del firewall con su posicion fija en el tablero.
     *
     * @param fila    fila donde se colocara el firewall
     * @param columna columna donde se colocara el firewall
     */
	public FireWalls(int fila, int columna) {
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