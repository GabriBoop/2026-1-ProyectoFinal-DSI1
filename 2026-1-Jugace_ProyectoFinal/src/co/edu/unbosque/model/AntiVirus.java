package co.edu.unbosque.model;
/**
 * Representa un enemigo de tipo Antivirus dentro del tablero.
 * <p>El Antivirus se mueve aleatoriamente por el tablero cada turno y elimina
 * al jugador si este entra en su casilla o en alguna de las casillas adyacentes,
 * a menos que el jugador tenga el modo sigilo activo.
 *
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */
public class AntiVirus {
	
	   private int fila;
	   private int columna;
	   /**
	     * Constructor del antivirus con su posicion inicial en el tablero.
	     *
	     * @param fila    fila inicial donde se ubica el antivirus
	     * @param columna columna inicial donde se ubica el antivirus
	     */
	   
	   public AntiVirus(int fila, int columna) {
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
