package co.edu.unbosque.model;

import co.edu.unbosque.controller.Controller;

public class Tablero {
	
	 private int filas;
	 private int columnas;
	 private Casilla[][] casillas;
	 
	 public Tablero() {
		 
	        filas = Controller.FILA;
	        columnas = Controller.COLUMNA;
	        casillas = new Casilla[Controller.FILA][Controller.COLUMNA];
	        
	        for (int i = 0; i < filas; i++) {
	            for (int j = 0; j < columnas; j++) {
	                casillas[i][j] = new Casilla(i, j);
	            }
	        }
	    }

	 public Casilla getCasilla(int fila, int col) {
		 return casillas[fila][col]; 
	 }
	 public Casilla[][] getCasillas() {
	    return casillas; 
	 }

}
