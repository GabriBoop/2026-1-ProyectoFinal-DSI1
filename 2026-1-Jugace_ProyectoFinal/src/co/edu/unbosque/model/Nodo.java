package co.edu.unbosque.model;

public class Nodo {
	
	private int fila;
	private int columna;
	private boolean act = true;
	
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