package co.edu.unbosque.controller;
import co.edu.unbosque.model.*;
import co.edu.unbosque.view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MovimientoController extends JPanel implements ActionListener {
	
    private Player jugador; //Objetos
    private Paquete paquete;
    private Puertos[] puerto;
    private AntiVirus[] antivirus;
    private Nodo[] nodo;
    private Scanner[] scanner;
    private FireWalls[] firewalls;
    
    private VerTablero verTablero; //Vista
    private Tablero tablero;
    private MovimientosPanel movimientospanel;

    public MovimientoController(Tablero tablero, Player jugador,Paquete paquete, Puertos[] puerto, AntiVirus[] antivirus,Nodo[] nodo, Scanner[] scanner, FireWalls[] firewalls,VerTablero verTablero, MovimientosPanel movimientospanel) {
        this.tablero = tablero; 
        this.jugador = jugador; 
        this.paquete = paquete;
        this.puerto = puerto;
        this.antivirus = antivirus;
        this.nodo = nodo;
        this.scanner = scanner;
        this.firewalls = firewalls;
        this.verTablero = verTablero;
        this.movimientospanel = movimientospanel;
    }

    public void input() { //Genuinamente me sorprende q esto sirva
        InputMap in = getInputMap();
        ActionMap act = getActionMap();
        
        in.put(KeyStroke.getKeyStroke("UP"), "ARRIBA");
        in.put(KeyStroke.getKeyStroke("W"), "ARRIBA");
        in.put(KeyStroke.getKeyStroke("DOWN"), "ABAJO");
        in.put(KeyStroke.getKeyStroke("S"), "ABAJO");
        in.put(KeyStroke.getKeyStroke("LEFT"), "IZQUIERDA");
        in.put(KeyStroke.getKeyStroke("A"), "IZQUIERDA");
        in.put(KeyStroke.getKeyStroke("RIGHT"), "DERECHA");
        in.put(KeyStroke.getKeyStroke("D"), "DERECHA");
        
        in.put(KeyStroke.getKeyStroke("R"), "RESET");
        
        act.put("ARRIBA", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { procesarMovimiento("ARRIBA"); }
        });
        act.put("ABAJO", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { procesarMovimiento("ABAJO"); }
        });
        act.put("IZQUIERDA", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { procesarMovimiento("IZQUIERDA"); }
        });
        act.put("DERECHA", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { procesarMovimiento("DERECHA"); }
        });
        act.put("RESET", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { procesarMovimiento("RESET"); }
        });
    }

    // Evita que se borren los puertos se fucking romoan y no salgan
    private void casillaPUERTO(int fila, int col) {
        for (int i = 0; i < Controller.PUERTOS; i++) {
            if (puerto[i].getFila() == fila && puerto[i].getColumna() == col) {
                tablero.setCasilla(fila, col).setTipo("PUERTO");
                return;
            }
            tablero.setCasilla(fila, col).setTipo("NA"); //no se pq arregla el bug pero lo arregla amen gracias dios
        }
    }

    private void procesarMovimiento(String direccion) {
    	
    	int filaPL = jugador.getFila(); //Me ahorro pomer tamto texto
        int colPL = jugador.getColumna();
        int filaPAQ = paquete.getFila();
        int colPAQ = paquete.getColumna();
        int filaPL_temp = filaPL; //Temporales para no contar movimientos si se esta contra una pared.
        int colPL_temp = colPL;
        
        if(Controller.MOV > 0) { //Te mueves solo si tienes movimientos
        	
        switch (direccion) {
            case "ARRIBA":
            	if (filaPL > 0) { //Verifica q mo salgas del GRID
            		if((filaPL - 1 == filaPAQ) && (colPL == colPAQ)) { //PAQUETE
            			if(filaPAQ > 0) {
            				casillaPUERTO(filaPAQ, colPAQ); // Usa el método de restauración
            			    paquete.setFila(filaPAQ - 1);
                            tablero.setCasilla(filaPAQ - 1, colPAQ).setTipo("PAQUETE"); // Mueve paquete en matriz

                            casillaPUERTO(filaPL, colPL); // Usa el método de restauración
            			    jugador.setFila(filaPL - 1);
                            tablero.setCasilla(filaPL - 1, colPL).setTipo("PLAYER"); // Actualiza jugador en matriz
            			}
            		}
            		else {
            			for(int i = 0; i < Controller.PUERTOS; i++) { // PUERTOS: Verfica si hay
            				if((filaPL - 1 == puerto[i].getFila()) && (colPL == puerto[i].getColumna())) {
            					
            					int orden = jugador.getPuertosTocados()+1;
            					if(Controller.INVERSO) orden = Controller.PUERTOS - jugador.getPuertosTocados(); //iNVERSA
            						
            					if(puerto[i].isActivo() == false) break; 
            					
            					if(puerto[i].isActivo() && (puerto[i].getNum() == orden)) { 
            						puerto[i].setActivo(false);
                					jugador.setPuertosTocados(jugador.getPuertosTocados() + 1);
            					}            					
            				}
            			}
            			casillaPUERTO(filaPL, colPL);// Usa el método de restauración
            			jugador.setFila(filaPL - 1); //Mueve el jugador hacia arriba
                        tablero.setCasilla(filaPL - 1, colPL).setTipo("PLAYER"); // Marca nuevo
            		}
                }
                break;  
            case "ABAJO":
                if (filaPL < Controller.FILA - 1) { 
                	if((filaPL + 1 == filaPAQ) && (colPL == colPAQ)) {
            			if(filaPAQ < Controller.FILA - 1) {
            			    paquete.setFila(filaPAQ + 1);
                            tablero.setCasilla(filaPAQ + 1, colPAQ).setTipo("PAQUETE");

                            casillaPUERTO(filaPL, colPL); // Usa el método de restauración
            			    jugador.setFila(filaPL + 1);
                            tablero.setCasilla(filaPL + 1, colPL).setTipo("PLAYER");
            			}
            		}
                	else {
            			for(int i = 0; i < Controller.PUERTOS; i++) { 
            				if((filaPL + 1 == puerto[i].getFila()) && (colPL == puerto[i].getColumna())) {
            					int orden = jugador.getPuertosTocados()+1;
            					if(Controller.INVERSO) orden = Controller.PUERTOS - jugador.getPuertosTocados();
            						
            					if(puerto[i].isActivo() == false) break;
            					if(puerto[i].isActivo() && (puerto[i].getNum() == orden)) {
            						puerto[i].setActivo(false);
                					jugador.setPuertosTocados(jugador.getPuertosTocados() + 1);
            					}
            				}
            			}
            			casillaPUERTO(filaPL, colPL); // Usa el método de restauración
            			jugador.setFila(filaPL + 1); 
                        tablero.setCasilla(filaPL + 1, colPL).setTipo("PLAYER");
            		}
                }
                break;
            case "DERECHA":
            	if (colPL < Controller.COLUMNA - 1) { 
                	if((filaPL == filaPAQ) && (colPL + 1 == colPAQ)) {
            			if(colPAQ < Controller.COLUMNA - 1) {
            			    paquete.setColumna(colPAQ + 1);
                            tablero.setCasilla(filaPAQ, colPAQ + 1).setTipo("PAQUETE");

                            casillaPUERTO(filaPL, colPL);// Usa el método de restauración
            			    jugador.setColumna(colPL + 1);
                            tablero.setCasilla(filaPL, colPL + 1).setTipo("PLAYER");
            			}
            		}
                	else {
            			for(int i = 0; i < Controller.PUERTOS; i++) { 
            				if((filaPL == puerto[i].getFila()) && (colPL + 1 == puerto[i].getColumna())) {
            					int orden = jugador.getPuertosTocados()+1;
            					if(Controller.INVERSO) orden = Controller.PUERTOS - jugador.getPuertosTocados(); 
            						
            					if(puerto[i].isActivo() == false) break;
            					if(puerto[i].isActivo() && (puerto[i].getNum() == orden)) {
            						puerto[i].setActivo(false);
                					jugador.setPuertosTocados(jugador.getPuertosTocados() + 1);
            					}
            				}
            			}
            			casillaPUERTO(filaPL, colPL); // Usa el método de restauración
            			jugador.setColumna(colPL + 1); 
                        tablero.setCasilla(filaPL, colPL + 1).setTipo("PLAYER");
            		}
                }
                break;
            case "IZQUIERDA":
            	if (colPL > 0) { 
                	if((filaPL == filaPAQ) && (colPL - 1 == colPAQ)) {
            			if(colPAQ > 0) {
            			    paquete.setColumna(colPAQ - 1);
                            tablero.setCasilla(filaPAQ, colPAQ - 1).setTipo("PAQUETE");

                            casillaPUERTO(filaPL, colPL); // Usa el método de restauración
            			    jugador.setColumna(colPL - 1);
                            tablero.setCasilla(filaPL, colPL - 1).setTipo("PLAYER");
            			}
            		}
                	else {
            			for(int i = 0; i < Controller.PUERTOS; i++) { 
            				if((filaPL == puerto[i].getFila()) && (colPL - 1 == puerto[i].getColumna())) {
            					int orden = jugador.getPuertosTocados()+1;
            					if(Controller.INVERSO) orden = Controller.PUERTOS - jugador.getPuertosTocados(); 
            						
            					if(puerto[i].isActivo() == false) break;
            					if(puerto[i].isActivo() && (puerto[i].getNum() == orden)) {
            						puerto[i].setActivo(false);
                					jugador.setPuertosTocados(jugador.getPuertosTocados() + 1);
            					}
            				}
            			}
            			casillaPUERTO(filaPL, colPL); // Usa el método de restauración
            			jugador.setColumna(colPL - 1); 
                        tablero.setCasilla(filaPL, colPL - 1).setTipo("PLAYER");
            		}
                }
                break;
            case "RESET":
            Controller.gameOver();
            break;
        }  
        // Obtener la fila y columna NUEVAS del player despues de moverse
        filaPL = jugador.getFila(); //Actualizar
    	colPL = jugador.getColumna();
    	if (nodo != null) {
    	for(int n = 0; n < Controller.CANT_NODOS; n++) {
    	    if(nodo[n] != null && nodo[n].isAct()) { // <-- El "!= null" evita que el juego se rompa
    	        if(nodo[n].getFila() == filaPL && nodo[n].getColumna() == colPL) {
    	            int eng = (int)(Controller.GRID_TOTAL * 0.10);
    	            Controller.MOV = Controller.MOV + eng;
    	            nodo[n].setAct(false);
    	            tablero.setCasilla(filaPL, colPL).setTipo("PLAYER");
    	            break;
    	        }
    	    }
    	}
    	}
        for(int a = 0; a < antivirus.length; a++) { 
        	int filaANT = antivirus[a].getFila();
        	int colANT = antivirus[a].getColumna();
        	
        	
    	
        	if((filaANT + 1 == filaPL) && (colANT == colPL)) { //ABAJO
        		Controller.gameOver();
        	}
        	else if((filaANT - 1 == filaPL) && (colANT == colPL)) { //ARRIBA
        		Controller.gameOver();
        	}
        	else if((filaANT == filaPL) && (colANT + 1 == colPL)) { //DERECHA
        		Controller.gameOver();
        	}
        	else if((filaANT == filaPL) && (colANT - 1 == colPL)) { //IZQUIERDA
        		Controller.gameOver();
        	}
        	else if(filaANT == filaPL && colANT == colPL) { //MISMA CASILLA Q PLAYER
        		Controller.gameOver();
        		break;
        	}
        }
        //Verificar jugador toca Antivirus
        for(int a = 0; a < antivirus.length; a++) { 
        	int filaANT = antivirus[a].getFila();
        	int colANT = antivirus[a].getColumna();
        	
        	int ran = (int) (Math.random() * 2);
        	 
        	if(ran == 1) { //50/50 de moverse
        		
        		ran = (int) (Math.random() * 2);
        		if(ran == 0) {
        			//Si el ran es 0, se movera verticalmente
        			int ran1 = (int) (Math.random() * 3) - 1;
        			int filaANTnew = filaANT + ran1; 
        			
        			if (filaANTnew >= 0 && filaANTnew < Controller.FILA) {
        				
                        String tp = tablero.setCasilla(filaANTnew, colANT).getTipo();

        				if(tp.equals("NA")) {
        					casillaPUERTO(filaANT, colANT);  // Usa el método de restauración para los virus también
        					antivirus[a].setFila(filaANTnew);
                			filaANT = filaANTnew; 
                            tablero.setCasilla(filaANT, colANT).setTipo("ANTIVIRUS"); // Marca nueva
        				}
            		}
        		}
        		else { // Movimiento horizontal
        			int ran2 = (int) (Math.random() * 3) - 1;
            		int colANTnew = colANT + ran2;
       
            		if (colANTnew >= 0 && colANTnew < Controller.COLUMNA) {
            			
            			String tp = tablero.setCasilla(filaANT, colANTnew).getTipo();

        				if(tp.equals("NA")) {
        					casillaPUERTO(filaANT, colANT); // Usa el método de restauración para los virus también
        					antivirus[a].setColumna(colANTnew); 
                			colANT = colANTnew; 
                            tablero.setCasilla(filaANT, colANT).setTipo("ANTIVIRUS");
        				} 
            		}
        		}
        	}
        	
        	
        	
        }
        
        if(jugador.getFila() != filaPL_temp || colPL_temp != jugador.getColumna()) { 
        	   Controller.MOV--;
               movimientospanel.getTxt().setText("MOVIMIENTOS: " + Controller.MOV + " / " + Controller.GRID_TOTAL);
               movimientospanel.getPrt().setText("PUERTOS: " + jugador.getPuertosTocados() + " / " + Controller.PUERTOS);
               
               verTablero.ver(tablero, puerto, antivirus);
        	}
        
        }
        else{
        	Controller.gameOver();
        }
    }

    public void actionPerformed(ActionEvent e) {
        procesarMovimiento(e.getActionCommand());
    }
}