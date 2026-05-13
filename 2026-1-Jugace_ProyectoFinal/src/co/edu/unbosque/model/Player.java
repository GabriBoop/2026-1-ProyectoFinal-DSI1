package co.edu.unbosque.model;
import co.edu.unbosque.controller.Controller;

public class Player {
	
	static int[] pos = new int[Controller.GRID_TOTAL];
	private int fila;
	private int columna;
	
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
	
	

}
