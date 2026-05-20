package co.edu.unbosque.controller;
import co.edu.unbosque.view.*;
import co.edu.unbosque.model.*;

public class GeneradorTablero {
	
	public static void run(Tablero tablero,Player jugador, Paquete paquete, Puertos[] puertos, int cantpuertos, AntiVirus[] antivirus,Nodo[] nodo,FireWalls[] firewall, int CANT_ANTIVIRUS) {
		//Importa objetos Controller
		jugador.setFila(0);
		jugador.setColumna(0);
		tablero.setCasilla(0, 0).setTipo("PLAYER");
		
		paquete.setFila(1);
		paquete.setColumna(1);
		tablero.setCasilla(1, 1).setTipo("PAQUETE");
		
		tablero.setCasilla(0, 1).setTipo("NO");
		tablero.setCasilla(1, 0).setTipo("NO");
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
		//FIREWALL
		for(int f = 0; f < Controller.CANT_FIREWALLS; f++) {
		    
		    int filaFW = (int) (Math.random() * Controller.FILA);
		    int columnaFW = (int) (Math.random() * Controller.COLUMNA);
		    
		    while(!tablero.setCasilla(filaFW, columnaFW).getTipo().equals("NA")) {
		        filaFW = (int) (Math.random() * Controller.FILA);
		        columnaFW = (int) (Math.random() * Controller.COLUMNA);
		    }
		    
		    firewall[f] = new FireWalls(filaFW, columnaFW);
		    tablero.setCasilla(filaFW, columnaFW).setTipo("FIREWALL");
		}
		
		for (int a = 0; a < firewall.length; a++) {
	        if (firewall[a] == null) continue;
	        int fa = firewall[a].getFila();
	        int ca = firewall[a].getColumna();
	        
	        for (int c = 0; c < firewall.length; c++) {
	            if (a == c || firewall[c] == null) continue;
	            int fa2 = firewall[c].getFila();
	            int ca2 = firewall[c].getColumna();

	            if (ca == ca2 && Math.abs(fa - fa2) == 2) {
	                int fMedio = (fa + fa2) / 2;
	                
	                if (tablero.setCasilla(fMedio, ca).getTipo().equals("NA")) {
	                    tablero.setCasilla(fMedio, ca).setTipo("TRAMPA");
	                }
	            }
	        }
	    }

		tablero.setCasilla(0, 1).setTipo("NA");
		tablero.setCasilla(1, 0).setTipo("NA");
	}

}
