package co.edu.unbosque.model;

import co.edu.unbosque.controller.*;
import co.edu.unbosque.model.*;

/**
 * Representa el tablero de juego como una matriz bidimensional de casillas.
 * <p>Se inicializa con las dimensiones configuradas en {@code Controller}
 * y crea una casilla en cada posicion, todas con tipo "NA" (vacio).
 * Es la estructura central que los demas objetos del juego consultan y modifican
 * para reflejar el estado actual de cada celda del tablero.
 *
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */
public class Tablero {

	 private int filas;
	 private int columnas;
	 private Casilla[][] casillas;
	 
	 /**
	     * Constructor del tablero.
	     * <p>Crea la matriz de casillas con las dimensiones actuales de
	     * {@code Controller.FILA} y {@code Controller.COLUMNA}, e inicializa
	     * cada casilla con tipo {@code "NA"}.
	     */
	 
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
