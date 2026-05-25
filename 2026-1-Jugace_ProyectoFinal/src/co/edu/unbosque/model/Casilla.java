package co.edu.unbosque.model;
/**
 * Representa una casilla individual dentro del tablero de juego.
 * <p>Cada casilla tiene una posicion fija en la matriz, un tipo que determina
 * que elemento ocupa ese espacio (jugador, paquete, enemigo, nodo, etc.) y
 * un indicador de si el jugador ya paso por ella, lo que genera el rastro
 * visual en el tablero.
 *
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */

public class Casilla {

	   private int fila;
	   private int columna;
	   private boolean visitada;
	   private String tipo;
	   /**
	     * Constructor de la casilla con su posicion en el tablero.
	     * <p>Se inicializa como no visitada y con tipo {@code "NA"} (vacia).
	     *
	     * @param fila    fila de la casilla en la matriz
	     * @param columna columna de la casilla en la matriz
	     */
	    public Casilla(int fila, int columna) {
	        this.fila = fila;
	        this.columna = columna;
	        this.visitada = false;
	        this.tipo = "NA";
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

		public boolean isVisitada() {
			return visitada;
		}

		public void setVisitada(boolean visitada) {
			this.visitada = visitada;
		}

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}
		
		
	    
	    

}
