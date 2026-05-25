package co.edu.unbosque.model;

/**
 * Representa la Salida del sistema, la casilla objetivo del juego.
 * <p>La salida permanece bloqueada al inicio de la partida y se activa
 * unicamente cuando el jugador ha enlazado todos los puertos del tablero.
 * La victoria se logra cuando el paquete llega a la casilla de la salida
 * estando esta activa.
 *
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */
public class Salida {

	private int fila;
    private int columna;
    private boolean activo = false;

    /**
     * Constructor de la salida con su posicion fija en el tablero.
     * <p>La salida se inicializa como inactiva; se activa cuando todos
     * los puertos han sido enlazados.
     *
     * @param fila    fila donde se ubica la salida
     * @param columna columna donde se ubica la salida
     */
    public Salida(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.activo = false;
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

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}


