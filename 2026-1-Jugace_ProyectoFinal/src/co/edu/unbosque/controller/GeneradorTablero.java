package co.edu.unbosque.controller;
import co.edu.unbosque.view.*;
import co.edu.unbosque.model.*;

/**
 * Clase la cual genera el tablero / mapa de la partida mediante los objetos importados por otras clases o metodos,
 * cuenta con un sistema casi completamente aleatorio para todos los objetos
 * (a excepcion del jugador, paquete y la salida, ellos siempre apareceran en el mismo lugar)
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */

public class GeneradorTablero {
	
	/**
	 * Generador de Tablero / mapa, corre ya todo el proceso de generacion semi aleatoria de puertos, enemigos y nodos.
	 * 
	 * @param tablero <p>Importacion del objeto de Tablero.java, con todas sus casillas
	 * @param jugador <p>Importacion del objeto de PLayer.java, debe tener una ubicacion puesta desde antes (EJ: PLAYER P = NEW PLAYER(0,0)) para inicializarla bien en este metodo
	 * @param paquete <p>Importacion del objeto de Paquete.java, debe tener una ubicacion puesta desde antes (EJ: PAQYETE PQ = NEW PAQUETE(1,1)) para inicializarla bien en este metodo
	 * @param salida <p>Importacion del objeto de Salida.java, debe tener una ubicacion puesta desde antes (EJ: SALIDA S = NEW SALIDA(2,2)) para inicializarla bien en este metodo
	 * @param puertos <p>Importacion del objeto de Puertos.java como ARRAY, cuenta con generacion aleatoria con limitaciones
	 * @param cantpuertos <p>Importacion de la variable de cantidad de PUERTOS
	 * @param antivirus <p>Importacion del objeto de AntiVirus.java como ARRAY, cuenta con generacion aleatoria con limitaciones
	 * @param nodo <p>Importacion del objeto de Nodo.java como ARRAY, cuenta con generacion aleatoria con limitaciones
	 * @param firewall <p>Importacion del objeto de FireWalls.java como ARRAY, cuenta con generacion aleatoria con limitaciones, tambien
	 * aca se generan las TRAMPAS mediante calculos de la distancia entre FIREWALLS verticales y horizontales.
	 * @param scanner <p>Importacion del objeto de Scanner.java como ARRAY, cuenta con generacion aleatoria con limitaciones
	 * @param CANT_ANTIVIRUS <p>Importacion de la variable de cantidad de ANTIVIRUS
	 */
	
	public static void run(Tablero tablero,Player jugador,Paquete paquete, Salida salida, Puertos[] puertos, int cantpuertos, AntiVirus[] antivirus,Nodo[] nodo,FireWalls[] firewall, Scanner[] scanner, int CANT_ANTIVIRUS) {
		//Importa objetos Controller
		VerTablero.getPanel().removeAll();
		
		jugador.setFila(0);
		jugador.setColumna(0);
		tablero.setCasilla(0, 0).setTipo("PLAYER");
		
		paquete.setFila(1);
		paquete.setColumna(1);
		tablero.setCasilla(1, 1).setTipo("PAQUETE");
		
		salida.setFila(Controller.FILA-1);
		salida.setColumna(Controller.COLUMNA-1);
		tablero.setCasilla(Controller.FILA-1,Controller.COLUMNA-1).setTipo("SALIDA");
		
		tablero.setCasilla(0, 1).setTipo("NO"); //PARA QUE NO SE GENERE NADA ALREDEDOR DE LA SALIDA NI
		tablero.setCasilla(1, 0).setTipo("NO");
		tablero.setCasilla(Controller.FILA-1,Controller.COLUMNA-2).setTipo("NO");
		tablero.setCasilla(Controller.FILA-2,Controller.COLUMNA-1).setTipo("NO");

		//PUERTOS
		for(int p = 0; p < Controller.PUERTOS; p++) {
			
			int filaPrt = (int) (Math.random() * Controller.FILA); //Random de Puertos
			int columnaPrt = (int) (Math.random() * Controller.COLUMNA);
			
			while(filaPrt == 0 || filaPrt == Controller.FILA - 1 || //Hace que no puedan salir en los bordes del mapa8888888888888
				  columnaPrt == 0 || columnaPrt == Controller.COLUMNA - 1 ||
				  !tablero.setCasilla(filaPrt, columnaPrt).getTipo().equals("NA")) { //Verifica que este vacia
				filaPrt = (int) (Math.random() * Controller.FILA); //Random de Puertos
				columnaPrt = (int) (Math.random() * Controller.COLUMNA);
			}
			
			puertos[p] = new Puertos(filaPrt, columnaPrt, p+1);
			tablero.setCasilla(filaPrt, columnaPrt).setTipo("PUERTO");
		}
		
		// SCANNERS
		for(int p = 0; p < Controller.CANT_SCANNERS; p++) {
			
			int filaSC = (int) (Math.random() * Controller.FILA); //Ramdon de Puertos
			int columnaSC = (int) (Math.random() * Controller.COLUMNA);
			
			while(!tablero.setCasilla(filaSC, columnaSC).getTipo().equals("NA")) {
				filaSC = (int) (Math.random() * Controller.FILA); //Ramdon de Puertos
				columnaSC = (int) (Math.random() * Controller.COLUMNA);	
			}
			
			scanner[p] = new Scanner(filaSC, columnaSC);
			tablero.setCasilla(filaSC, columnaSC).setTipo("SCANNER");
		}
		
		// ANTIVIRUS
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
	        
	        for (int c = 0; c < firewall.length; c++) { // DETECTA FIREWALLS 
	            if (a == c || firewall[c] == null) continue;
	            int fa2 = firewall[c].getFila();
	            int ca2 = firewall[c].getColumna();

	            if (ca == ca2 && ((fa - fa2) == 2 || (fa - fa2) == -2)) { // GENERA TRAMPAS VERTICALES
	                int f_MEDIO = (fa + fa2) / 2;
	                if (tablero.setCasilla(f_MEDIO, ca).getTipo().equals("NA")) {
	                    tablero.setCasilla(f_MEDIO, ca).setTipo("TRAMPA");
	                }
	            }
	            if (fa == fa2 && ((ca - ca2) == 2 || (ca - ca2) == -2)) { // GENERA TRAMPAS HORIZONTALES
	                int c_MEDIO = (ca + ca2) / 2;
	                if (tablero.setCasilla(fa, c_MEDIO).getTipo().equals("NA")) {
	                    tablero.setCasilla(fa, c_MEDIO).setTipo("TRAMPA");
	                }
	            }
	        }
	    }

		tablero.setCasilla(0, 1).setTipo("NA");
		tablero.setCasilla(1, 0).setTipo("NA");
		tablero.setCasilla(Controller.FILA-1,Controller.COLUMNA-2).setTipo("NA");
		tablero.setCasilla(Controller.FILA-2,Controller.COLUMNA-1).setTipo("NA");

	}

}
