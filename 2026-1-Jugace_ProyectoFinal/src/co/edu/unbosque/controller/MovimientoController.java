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
    
    private VerTablero verTablero; //Vista
    private Tablero tablero;
    private MovimientosPanel movimientospanel;

    public MovimientoController(Player jugador,Paquete paquete,VerTablero verTablero, MovimientosPanel movimientospanel, Puertos[] puerto, AntiVirus[] antivirus) {
        this.jugador = jugador; //Importacion de la informacion del Controller
        this.paquete = paquete;
        this.verTablero = verTablero;
        this.movimientospanel = movimientospanel;
        this.puerto = puerto;
        this.antivirus = antivirus;
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

        act.put("ARRIBA", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { 
            	procesarMovimiento("ARRIBA"); }
        });
        act.put("ABAJO", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { 
            	procesarMovimiento("ABAJO"); }
        });
        act.put("IZQUIERDA", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { 
            	procesarMovimiento("IZQUIERDA"); }
        });
        act.put("DERECHA", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { 
            	procesarMovimiento("DERECHA"); }
        });
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
            			jugador.setFila(filaPL - 1);
            			paquete.setFila(filaPAQ - 1);
            			}
            		}
            		else {
            			for(int i = 0; i < Controller.PUERTOS; i++) { // PUERTOS: Verfica si hay
            				if((filaPL - 1 == puerto[i].getFila()) && (colPL == puerto[i].getColumna())) {
            					
            					int orden = jugador.getPuertosTocados()+1;
            					if(Controller.INVERSO) orden = Controller.PUERTOS - jugador.getPuertosTocados(); //iNVERSA
            						
            					if(puerto[i].isActivo() == false) break; //si esta desactivada, rompe el Loop y se camina normal{
            					
            					if(puerto[i].isActivo() && (puerto[i].getNum() == orden)) { //Verifica si esta activo y que el puerto que se agarre coencida con el ordem normal o invertido
            						puerto[i].setActivo(false);
                					jugador.setPuertosTocados(jugador.getPuertosTocados() + 1);
            					}            					
            				}
            			}
            			jugador.setFila(filaPL - 1); //Mueve el jugador hacia arriba
            		}
                }
                break;
                
            case "ABAJO":
                if (filaPL < Controller.FILA - 1) { //El codigo es igual que "ARRIBA" pero cambiando la direccion a donde va el jugador
                	if((filaPL + 1 == filaPAQ) && (colPL == colPAQ)) {
            			if(filaPAQ < Controller.FILA - 1) {
            			jugador.setFila(filaPL + 1);
            			paquete.setFila(filaPAQ + 1);
            			}
            		}
                	else {
            			for(int i = 0; i < Controller.PUERTOS; i++) { // PUERTO OMGG
            				if((filaPL + 1 == puerto[i].getFila()) && (colPL == puerto[i].getColumna())) {
            					int orden = jugador.getPuertosTocados()+1;
            					if(Controller.INVERSO) orden = Controller.PUERTOS - jugador.getPuertosTocados(); //iNVERSA
            						
            					if(puerto[i].isActivo() == false) break;
            					if(puerto[i].isActivo() && (puerto[i].getNum() == orden)) {
            						puerto[i].setActivo(false);
                					jugador.setPuertosTocados(jugador.getPuertosTocados() + 1);
            					}
            				}
            			}
            			jugador.setFila(filaPL + 1); //Se mueve hacia ABAJO
            		}
                }
                break;
            case "DERECHA":
            	if (colPL < Controller.COLUMNA - 1) { //El codigo es igual que "ARRIBA" pero cambiando la direccion a donde va el jugador
                	if((filaPL == filaPAQ) && (colPL + 1 == colPAQ)) {
            			if(colPAQ < Controller.COLUMNA - 1) {
            			jugador.setColumna(colPL + 1);
            			paquete.setColumna(colPAQ + 1);
            			}
            		}
                	else {
            			for(int i = 0; i < Controller.PUERTOS; i++) { // PUERTO OMGG
            				if((filaPL == puerto[i].getFila()) && (colPL + 1 == puerto[i].getColumna())) {
            					int orden = jugador.getPuertosTocados()+1;
            					if(Controller.INVERSO) orden = Controller.PUERTOS - jugador.getPuertosTocados(); //iNVERSA
            						
            					if(puerto[i].isActivo() == false) break;
            					if(puerto[i].isActivo() && (puerto[i].getNum() == orden)) {
            						puerto[i].setActivo(false);
                					jugador.setPuertosTocados(jugador.getPuertosTocados() + 1);
            					}
            				}
            			}
            			jugador.setColumna(colPL + 1); //Se mueve a la Derecha
            		}
                }
                break;
            case "IZQUIERDA":
            	if (colPL > 0) { //El codigo es igual que "ARRIBA" pero cambiando la direccion a donde va el jugador
                	if((filaPL == filaPAQ) && (colPL - 1 == colPAQ)) {
            			if(colPAQ > 0) {
            			jugador.setColumna(colPL - 1);
            			paquete.setColumna(colPAQ - 1);
            			}
            		}
                	else {
            			for(int i = 0; i < Controller.PUERTOS; i++) { // PUERTO OMGG
            				if((filaPL == puerto[i].getFila()) && (colPL - 1 == puerto[i].getColumna())) {
            					int orden = jugador.getPuertosTocados()+1;
            					if(Controller.INVERSO) orden = Controller.PUERTOS - jugador.getPuertosTocados(); //iNVERSA
            						
            					if(puerto[i].isActivo() == false) break;
            					if(puerto[i].isActivo() && (puerto[i].getNum() == orden)) {
            						puerto[i].setActivo(false);
                					jugador.setPuertosTocados(jugador.getPuertosTocados() + 1);
            					}
            				}
            			}
            			jugador.setColumna(colPL - 1); //Se mueve a la izquierda
            		}
                }
                break;
        }
        //Verificar jugador toca Antivirus de forma adyancente
        for(int a = 0; a < antivirus.length; a++) { 
        	int filaANT = antivirus[a].getFila();
        	int colANT = antivirus[a].getColumna();
        	
        	int ran = (int) (Math.random() * 2);
        	
        	if(ran == 1) { //50/50 de moverse
        		ran = (int) (Math.random() * 2);
        		if(ran == 0) { //Si el ran es 0, se movera verticalmente
        			int ran1 = (int) (Math.random() * 3) - 1;
        			int filaANTnew = filaANT + ran1; //Me ahorro escribir
        			
        			if (filaANTnew >= 0 && filaANTnew < Controller.FILA) {
            			antivirus[a].setFila(filaANTnew);
            			filaANT = filaANTnew; 
            		}
        		}
        		else {
        			int ran2 = (int) (Math.random() * 3) - 1;
            		int colANTnew = colANT + ran2;
       
            		if (colANTnew >= 0 && colANTnew < Controller.COLUMNA) {
            			antivirus[a].setColumna(colANTnew); 
            			colANT = colANTnew; 
            		}
        		}
        	}
        	
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
        if(jugador.getFila() != filaPL_temp || colPL_temp != jugador.getColumna()) { //Por si choca o no, actualiza datos visuales y variables
        	   Controller.MOV--;
               movimientospanel.getTxt().setText("MOVIMIENTOS: " + Controller.MOV + " / " + Controller.GRID_TOTAL);
               movimientospanel.getPrt().setText("PUERTOS: " + jugador.getPuertosTocados() + " / " + Controller.PUERTOS);
               verTablero.ver(jugador.getFila(), jugador.getColumna(), paquete.getFila(), paquete.getColumna(), puerto, antivirus);
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