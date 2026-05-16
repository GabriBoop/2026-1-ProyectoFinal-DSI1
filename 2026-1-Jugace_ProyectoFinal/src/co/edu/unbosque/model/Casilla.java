package co.edu.unbosque.model;

public class Casilla {
	// ¡ESTO NO HA SIDO USADO!
	   private int fila;
	   private int columna;
	   private boolean visitada;
	   private String tipo;
//	   private String[] tipo = 
//		   {"NA","PLAYER","PAQUETE","FIREWALL","VIRUS","PUERTO"};

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
