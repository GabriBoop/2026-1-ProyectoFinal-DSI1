package co.edu.unbosque.model;
/**
 * Representa un Puerto dentro del tablero.
 * <p>Los puertos deben ser enlazados por el paquete en un orden especifico
 * (ascendente o inverso segun la configuracion de la partida). Al enlazar
 * todos los puertos, la salida se activa y el jugador puede completar la mision.
 * Un puerto desactivado ya no puede ser enlazado nuevamente.
 *
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */
public class Puertos {
	
	private int fila;
    private int columna;
    private int num; // id / Numero de Puerto
    private boolean activo = true; // esta activo o no

    /**
     * Constructor del puerto con su posicion y numero de orden en el tablero.
     *
     * @param fila    fila donde se colocara el puerto
     * @param columna columna donde se colocara el puerto
     * @param num     numero identificador que determina el orden de enlace del puerto
     */
    public Puertos(int fila, int columna, int num) {
        this.fila = fila;
        this.columna = columna;
        this.num = num;
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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
