package co.edu.unbosque.controller;
import co.edu.unbosque.view.*;
import co.edu.unbosque.model.*;

public class GeneradorTablero {
	
	public static void run(Tablero tablero,Player jugador, Paquete paquete, Puertos[] puertos, int cantpuertos, AntiVirus[] antivirus,Nodo[] nodo, int CANT_ANTIVIRUS) {
		//Importa objetos Controller
		jugador.setFila(0);
		jugador.setColumna(0);
		tablero.setCasilla(0, 0).setTipo("PLAYER");
		
		paquete.setFila(1);
		paquete.setColumna(1);
		tablero.setCasilla(1, 1).setTipo("PAQUETE");
		
		/* --PRT = PUERTO
		 * Esto es medio pesado asi que atentos...
		 */
		//PUERTOS
		for(int p = 0; p < Controller.PUERTOS; p++) {
			
			int filaPrt = (int) (Math.random() * Controller.FILA); //Ramdon de Puertos
			int columnaPrt = (int) (Math.random() * Controller.COLUMNA);
			
			while(!tablero.setCasilla(filaPrt, columnaPrt).getTipo().equals("NA")) {
				filaPrt = (int) (Math.random() * Controller.FILA); //Ramdon de Puertos
				columnaPrt = (int) (Math.random() * Controller.COLUMNA);
			}
			
			puertos[p] = new Puertos(filaPrt, columnaPrt, p+1);
			tablero.setCasilla(filaPrt, columnaPrt).setTipo("PUERTO");
		}
		// ANTIVIUS
		for(int p = 0; p < Controller.CANT_ANTIVIRUS; p++) {
			
			int filaANT = (int) (Math.random() * Controller.FILA); //Ramdon de Puertos
			int columnaANT = (int) (Math.random() * Controller.COLUMNA);
			
			while(!tablero.setCasilla(filaANT, columnaANT).getTipo().equals("NA")) {
				filaANT = (int) (Math.random() * Controller.FILA); //Ramdon de Puertos
				columnaANT = (int) (Math.random() * Controller.COLUMNA);
			}
			
			antivirus[p] = new AntiVirus(filaANT, columnaANT);
			tablero.setCasilla(filaANT, columnaANT).setTipo("ANTIVIRUS");
		}
		
		// NODOS
		for(int p = 0; p < Controller.CANT_NODOS; p++) {
					
			int filaNOD = (int) (Math.random() * Controller.FILA); //Ramdon de Puertos
			int columnaNOD = (int) (Math.random() * Controller.COLUMNA);
					
			while(!tablero.setCasilla(filaNOD, columnaNOD).getTipo().equals("NA")) {
				filaNOD = (int) (Math.random() * Controller.FILA); //Ramdon de Puertos
				columnaNOD = (int) (Math.random() * Controller.COLUMNA);
			}
					
			nodo[p] = new Nodo(filaNOD, columnaNOD);
			tablero.setCasilla(filaNOD, columnaNOD).setTipo("NODO");
		}
	}

}
