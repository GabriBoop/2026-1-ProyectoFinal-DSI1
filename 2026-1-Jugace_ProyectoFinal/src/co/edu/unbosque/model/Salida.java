package co.edu.unbosque.model;

public class Salida {

	private int fila;
    private int columna;
    private boolean activo = false;

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


