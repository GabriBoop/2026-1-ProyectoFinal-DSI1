package co.edu.unbosque.model;

public class Player {
	
	private int fila;
	private int columna;
	private int puertosTocados = 0;
	private boolean sigilo = false;
	private Casilla[][] casilla; 
	
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

	public void setCasilla(Casilla[][] casilla) {
		this.casilla = casilla;
	}

	public Casilla setCasilla(int fila, int col) {
		 return casilla[fila][col]; 
	 }
	 public Casilla[][] getCasilla() {
	    return casilla; 
	 }

	public boolean isSigilo() {
		return sigilo;
	}

	public void setSigilo(boolean sigilo) {
		this.sigilo = sigilo;
	}
	 
	
	

}
