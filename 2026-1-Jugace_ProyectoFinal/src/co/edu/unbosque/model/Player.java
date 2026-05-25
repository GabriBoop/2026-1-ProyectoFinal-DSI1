package co.edu.unbosque.model;
/**
 * Representa al jugador dentro del tablero de juego.
 * <p>Almacena la posicion actual del jugador, la cantidad de puertos enlazados,
 * el estado del modo sigilo.
 *
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */
public class Player {
	
	private int fila;
	private int columna;
	private int puertosTocados = 0;
	private boolean sigilo = false;
	
	/**
     * Constructor del jugador con su posicion inicial en el tablero.
     *
     * @param fila    fila inicial donde aparece el jugador
     * @param columna columna inicial donde aparece el jugador
     */
	public Player(int fila, int columna) {
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
	
	public int getPuertosTocados() {
		return puertosTocados;
	}

	public void setPuertosTocados(int puertosTocados) {
		this.puertosTocados = puertosTocados;
	}

	public boolean isSigilo() {
		return sigilo;
	}

	public void setSigilo(boolean sigilo) {
		this.sigilo = sigilo;
	}
	 
	
	

}
