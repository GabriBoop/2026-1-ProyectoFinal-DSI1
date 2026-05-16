package co.edu.unbosque.model;

public class Paquete {
	// ¡ESTO POSIBLEMENTE SE QUITE!
	private int fila;
	private int columna;
	private Casilla[][] casilla; // ¡ESTO NO HA SIDO USADO!
	
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
	
	public Casilla setCasilla(int fila, int col) {
		 return casilla[fila][col]; 
	}
	 public Casilla[][] getCasilla() {
	    return casilla; 
    }
	

	
}
