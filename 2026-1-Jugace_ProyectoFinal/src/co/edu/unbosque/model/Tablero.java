package co.edu.unbosque.model;

import co.edu.unbosque.controller.*;
import co.edu.unbosque.model.*;

public class Tablero {

	 private int filas;
	 private int columnas;
	 private Player jugador;
	 private Casilla[][] casillas;
	 private Puertos puertos;
	 
	 public Tablero() {
		 
	        filas = Controller.FILA;
	        columnas = Controller.COLUMNA;
	        casillas = new Casilla[Controller.FILA][Controller.COLUMNA];
	        
	        for (int i = 0; i < filas; i++) {
	            for (int j = 0; j < columnas; j++) {
	                casillas[i][j] = new Casilla(i, j);
	                casillas[i][j].setTipo("NA");
	            }
	        }
	 }
	
	 
	 public Casilla setCasilla(int fila, int col) {
		 return casillas[fila][col]; 
	 }
	 
	 public Casilla[][] getCasilla() {
	    return casillas; 
	 }

}
