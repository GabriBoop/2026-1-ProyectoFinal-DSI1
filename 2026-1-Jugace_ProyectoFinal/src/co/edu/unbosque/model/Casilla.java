package co.edu.unbosque.model;

public class Casilla {
	
	   private int fila;
	   private int columna;
	   private boolean visitada;

	    public Casilla(int fila, int columna) {
	        this.fila = fila;
	        this.columna = columna;
	        this.visitada = false;
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
	    
	    

}
