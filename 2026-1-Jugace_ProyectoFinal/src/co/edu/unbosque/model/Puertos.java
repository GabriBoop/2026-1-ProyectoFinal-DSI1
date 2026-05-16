package co.edu.unbosque.model;

public class Puertos {
	
	private int fila;
    private int columna;
    private int num; // id / Numero de Puerto
    private boolean activo = true; // esta activo o no

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
