package co.edu.unbosque.controller;
import co.edu.unbosque.view.*;
import co.edu.unbosque.model.*;

public class GeneradorTablero {
	
	public static void run(Player jugador, Paquete paquete, Puertos[] puertos, int cantpuertos, AntiVirus[] antivirus, int CANT_ANTIVIRUS) {
		//Importa objetos Controller
		jugador.setFila(0);
		jugador.setColumna(0);
		
		paquete.setFila(1);
		paquete.setColumna(1);
		
		/* --PRT = PUERTO
		 * Esto es medio pesado asi que atentos...
		 */
		
		int filaPrt = (int) (Math.random() * Controller.FILA); //Ramdon de Puertos
		int columnaPrt = (int) (Math.random() * Controller.COLUMNA);
		
		for(int i = 0; i < cantpuertos; i++) {
			filaPrt = (int) (Math.random() * Controller.FILA); //Genera el Puerto tandom
			columnaPrt = (int) (Math.random() * Controller.COLUMNA);
			
			while((filaPrt == jugador.getFila() && columnaPrt == jugador.getColumna()) ||
			      (filaPrt == paquete.getFila() && columnaPrt == paquete.getColumna())) { //Verifica q no se ponga encima del Jugador y Cajas
				filaPrt = (int) (Math.random() * Controller.FILA);
				columnaPrt = (int) (Math.random() * Controller.COLUMNA);
			}
			for(int f = 0; f < i; f++) { //Compara la ubicacion del Puerto actual con los pasados
				
		        if((puertos[f].getFila() == filaPrt) && (puertos[f].getColumna() == columnaPrt)) { //Si son iguales, volver a sacar fila y columna
		            filaPrt = (int) (Math.random() * Controller.FILA);
		            columnaPrt = (int) (Math.random() * Controller.COLUMNA);
		            		        
		            while((filaPrt == jugador.getFila() && columnaPrt == jugador.getColumna()) ||
		                  (filaPrt == paquete.getFila() && columnaPrt == paquete.getColumna())) { //Verifica q no se ponga encima del Jugador y Cajas, otra ve
		                filaPrt = (int) (Math.random() * Controller.FILA);
		                columnaPrt = (int) (Math.random() * Controller.COLUMNA);
		            }
		        }
			}
			puertos[i] = new Puertos(filaPrt, columnaPrt, i+1); //Añade el puerto
		}
		
		/* --ANT = ANTIVIRUS
		 * Mismo sistema pero sumando el chequeo de puertos
		 */
		
		int filaANT = (int) (Math.random() * Controller.FILA); //Ramdon de Antivirus
		int columnaANT = (int) (Math.random() * Controller.COLUMNA);
		
		for(int i = 0; i < CANT_ANTIVIRUS; i++) {
			filaANT = (int) (Math.random() * Controller.FILA); //Misma logica que los puertos, y asegurandose q no se pongan encimas de estos
			columnaANT = (int) (Math.random() * Controller.COLUMNA);
			
			while((filaANT == jugador.getFila() && columnaANT == jugador.getColumna()) ||
			      (filaANT == paquete.getFila() && columnaANT == paquete.getColumna())) { //Verifica q no se ponga encima del Jugador y Cajas
				filaANT = (int) (Math.random() * Controller.FILA);
				columnaANT = (int) (Math.random() * Controller.COLUMNA);
			}
			
			//f < cantpuertos para obligar a revisar siempre todos los puertos
			for(int f = 0; f < cantpuertos; f++) { //Compara la ubicacion del Puerto
				
		        if((puertos[f].getFila() == filaANT) && (puertos[f].getColumna() == columnaANT)) { //Si son iguales, volver a sacar fila y columna
		        	filaANT = (int) (Math.random() * Controller.FILA);
		            columnaANT = (int) (Math.random() * Controller.COLUMNA);
		            		        
		            while((filaANT == jugador.getFila() && columnaANT == jugador.getColumna()) ||
		                  (filaANT == paquete.getFila() && columnaANT == paquete.getColumna())) { //Verifica q no se ponga encima del Jugador y Cajas, otra ve
		                filaANT = (int) (Math.random() * Controller.FILA);
		                columnaANT = (int) (Math.random() * Controller.COLUMNA);
		            }
		        }
			}
			for(int a = 0; a < i; a++) { //Verificar que los virus no se sobrepongan
				if((antivirus[a].getFila() == filaANT) && (antivirus[a].getColumna() == columnaANT)) {
					filaANT = (int) (Math.random() * Controller.FILA);
					columnaANT = (int) (Math.random() * Controller.COLUMNA);
					
					while((filaANT == jugador.getFila() && columnaANT == jugador.getColumna()) ||
					      (filaANT == paquete.getFila() && columnaANT == paquete.getColumna())) {
						filaANT = (int) (Math.random() * Controller.FILA);
						columnaANT = (int) (Math.random() * Controller.COLUMNA);
					}
					// Volvemos a verificar puertos
					for(int p = 0; p < cantpuertos; p++) {
						if((puertos[p].getFila() == filaANT) && (puertos[p].getColumna() == columnaANT)) {
							filaANT = (int) (Math.random() * Controller.FILA);
							columnaANT = (int) (Math.random() * Controller.COLUMNA);
							p = -1; // Reinicia la verificación de puertos
						}
						while((filaANT == jugador.getFila() && columnaANT == jugador.getColumna()) ||
							      (filaANT == paquete.getFila() && columnaANT == paquete.getColumna())) {
								filaANT = (int) (Math.random() * Controller.FILA);
								columnaANT = (int) (Math.random() * Controller.COLUMNA);
							}
					}
				}
			}
			antivirus[i] = new AntiVirus(filaANT, columnaANT); //Añade el Antivirus
		}
	}

}
