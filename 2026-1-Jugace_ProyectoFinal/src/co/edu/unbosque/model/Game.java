package co.edu.unbosque.model;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import co.edu.unbosque.controller.Controller;

public class Game {
	
    private Player jugador; 
    private Puertos[] puerto;
    private AntiVirus[] antivirus;
    private Nodo[] nodo;
    private Scanner[] scanner;
    private FireWalls[] firewalls;
    private Tablero tablero;

    private boolean sigiloUSADO = false;
    private int cnt = 0;
    
    public Game(Tablero tablero, Player jugador, Puertos[] puerto, AntiVirus[] antivirus, Nodo[] nodo, Scanner[] scanner, FireWalls[] firewalls) {
        this.tablero = tablero; 
        this.jugador = jugador; 
        this.puerto = puerto;
        this.antivirus = antivirus;
        this.nodo = nodo;
        this.scanner = scanner;
        this.firewalls = firewalls;
        Controller.turno = 0;
        
    }


    public void ejecutarMovimiento(String mov) {
    	
        switch (mov) {
            case "ARRIBA":    
            	mover(-1, 0); 
            break;
            case "ABAJO":     
            	mover(1, 0);  
            break;
            case "IZQUIERDA": 
            	mover(0, -1); 
            break;
            case "DERECHA":   
            	mover(0, 1);  
            break;
            case "RESET":   
            	Controller.gameOver();
            break;
            case "SIGILO":
            	if(!sigiloUSADO) {
            		sigiloUSADO = true;
            		jugador.setSigilo(true);
            	}
            	else {
            		ImageIcon icon = (new ImageIcon("src/co/edu/unbosque/images/icon_playersigilo.png"));
            		JOptionPane.showMessageDialog(null, "EL SIGILO YA FUE USADO!", "SIGILO", JOptionPane.INFORMATION_MESSAGE, icon);
            	}
            break;
        }
    }

    
    private void mover(int fila, int columna) {
        int filaPL = jugador.getFila(); 
        int colPL = jugador.getColumna();
        int filaPL_temp = filaPL; 
        int colPL_temp = colPL;

        if (Controller.MOV > 1) {
            int new_FilaPL = filaPL + fila;
            int new_ColPL = colPL + columna;

            // VER LIMITES DE LA MATRIZ Y QUE NO CHOQUE CON FIREWALL
            if (new_FilaPL >= 0 && new_FilaPL < Controller.FILA && 
            	new_ColPL >= 0 && new_ColPL < Controller.COLUMNA && 
                !tablero.setCasilla(new_FilaPL, new_ColPL).getTipo().equals("FIREWALL")) {
                
                verificarTrampa(new_FilaPL, new_ColPL); //Verifica si paso por una trampa
                // JUGADOR SE MUEVE A CASILLA "NA" O PUERTO
                for (int i = 0; i < Controller.PUERTOS; i++) { 
                        if (new_FilaPL == puerto[i].getFila() && new_ColPL == puerto[i].getColumna()) {
                            int orden = jugador.getPuertosTocados() + 1;
                            if (Controller.INVERSO) orden = Controller.PUERTOS - jugador.getPuertosTocados(); 
                                
                            if (puerto[i].isActivo() == false) break; 
                            if (puerto[i].isActivo() && (puerto[i].getNum() == orden)) { 
                                puerto[i].setActivo(false);
                                jugador.setPuertosTocados(jugador.getPuertosTocados() + 1);
                            }                                
                        }
                    }
                	tablero.setCasilla(filaPL, colPL).setVisitada(true);
                    casillaRESTAURAR(filaPL, colPL);
                    jugador.setFila(new_FilaPL); 
                    jugador.setColumna(new_ColPL); 
                    tablero.setCasilla(new_FilaPL, new_ColPL).setTipo("PLAYER"); 
                
                contarSigilo();
                verificarWin();
            }
            
            if (jugador.getFila() == filaPL_temp && jugador.getColumna() == colPL_temp) {
                return; 
            }
            
            filaPL = jugador.getFila(); 
            colPL = jugador.getColumna();
            
            // LÓGICA DE NODOS ENERGÉTICOS
            
            for(int n = 0; n < Controller.CANT_NODOS; n++) {
                if(nodo[n] != null && nodo[n].isAct()) { 
                    if(nodo[n].getFila() == filaPL && nodo[n].getColumna() == colPL) {
                        int eng = (int)(Controller.GRID_TOTAL * 0.10);
                        Controller.MOV = Controller.MOV + eng;
                        nodo[n].setAct(false);
                        tablero.setCasilla(filaPL, colPL).setTipo("PLAYER");
                        break;
                    }
                }
            }
            
            // COLISION DE SCANNERS
            if(!jugador.isSigilo()) {
            for (int s = 0; s < scanner.length; s++) { 
                if (scanner[s] != null) {
                    int filaSC = scanner[s].getFila();
                    int colSC = scanner[s].getColumna();
                    
                    if ((filaSC + 1 == filaPL) && (colSC == colPL)) Controller.MOV -= (int)(Controller.MOV * 0.05);
                    else if ((filaSC - 1 == filaPL) && (colSC == colPL)) Controller.MOV -= (int)(Controller.MOV * 0.05);
                    else if ((filaSC == filaPL) && (colSC + 1 == colPL)) Controller.MOV -= (int)(Controller.MOV * 0.05);
                    else if ((filaSC == filaPL) && (colSC - 1 == colPL)) Controller.MOV -= (int)(Controller.MOV * 0.05);
                    else if (filaSC == filaPL && colSC == colPL) Controller.MOV -= (int)(Controller.MOV * 0.05);
                }
            }
            }
            // COLISIÓN ANTIVIRUS
            if(!jugador.isSigilo()) {
            for(int a = 0; a < antivirus.length; a++) { 
                int filaANT = antivirus[a].getFila();
                int colANT = antivirus[a].getColumna();
            
                if((filaANT + 1 == filaPL) && (colANT == colPL)) Controller.gameOver();
                else if((filaANT - 1 == filaPL) && (colANT == colPL)) Controller.gameOver();
                else if((filaANT == filaPL) && (colANT + 1 == colPL)) Controller.gameOver();
                else if((filaANT == filaPL) && (colANT - 1 == colPL)) Controller.gameOver();
                else if(filaANT == filaPL && colANT == colPL) {
                    Controller.gameOver();
                    break;
                }
            }
            }
            // MOVIMIENTO ALEATORIO DEL ANTIVIRUS """"IA""""
            for(int a = 0; a < antivirus.length; a++) { 
                int filaANT = antivirus[a].getFila();
                int colANT = antivirus[a].getColumna();
                int ran = (int) (Math.random() * 2); //Crea un Random, 50/50 de que se mueva
            	 
                if(ran == 1) { 
                    ran = (int) (Math.random() * 2);  //50 de que se mueva vertical o horizontal
                   
                    if(ran == 0) { //Se mueve vertical
                    	
                    	ran = (int) (Math.random() * 3) - 1; //Numero entre -1 a 1.
                        while(ran == 0) ran = (int) (Math.random() * 3) - 1; //Por si es 0
                        int filaANTnew = filaANT + ran; 
            			
                        if (filaANTnew >= 0 && filaANTnew < Controller.FILA ) {
                            
                            if(tablero.setCasilla(filaANTnew, colANT).getTipo().equals("NA")) {
                                
                            	casillaRESTAURAR(filaANT, colANT); 
                                antivirus[a].setFila(filaANTnew);
                                filaANT = filaANTnew; 
                                tablero.setCasilla(filaANT, colANT).setTipo("ANTIVIRUS"); 
                            }
                        }
                    } else { //Se mueve Horizontal
                    	
                    	ran = (int) (Math.random() * 3) - 1;
                        while(ran == 0) ran = (int) (Math.random() * 3) - 1; //Por si es 0
                        int colANTnew = colANT + ran;
           
                        if (colANTnew >= 0 && colANTnew < Controller.COLUMNA) {
                            
                            if(tablero.setCasilla(filaANT, colANTnew).getTipo().equals("NA")) {
                                
                            	casillaRESTAURAR(filaANT, colANT); 
                                antivirus[a].setColumna(colANTnew); 
                                colANT = colANTnew; 
                                tablero.setCasilla(filaANT, colANT).setTipo("ANTIVIRUS");
                            } 
                        }
                    }
                }
            }
         // MOVIMIENTO ALEATORIO DEL SCANNER """"IA""""
            for(int a = 0; a < scanner.length; a++) { 
                int filaSC = scanner[a].getFila();
                int colSC = scanner[a].getColumna();
                int ran = (int) (Math.random() * 2); //Crea un Random, 50/50 de que se mueva
            	 
                if(ran == 1) { 
                    ran = (int) (Math.random() * 2);  //50 de que se mueva vertical o horizontal
                   
                    if(ran == 0) { //Se mueve vertical
                    	
                    	ran = (int) (Math.random() * 3) - 1; //Numero entre -1 a 1.
                        while(ran == 0) ran = (int) (Math.random() * 3) - 1; //Por si es 0
                        int filaSCnew = filaSC + ran; 
            			
                        if (filaSCnew >= 0 && filaSCnew < Controller.FILA ) {
                            
                            if(tablero.setCasilla(filaSCnew, colSC).getTipo().equals("NA")) {
                                
                            	casillaRESTAURAR(filaSC, colSC); 
                                scanner[a].setFila(filaSCnew);
                                filaSC = filaSCnew; 
                                tablero.setCasilla(filaSC, colSC).setTipo("SCANNER"); 
                            }
                        }
                    } else { //Se mueve Horizontal
                    	
                    	ran = (int) (Math.random() * 3) - 1;
                        while(ran == 0) ran = (int) (Math.random() * 3) - 1; //Por si es 0
                        int colSCnew = colSC + ran;
           
                        if (colSCnew >= 0 && colSCnew < Controller.COLUMNA) {
                            
                            if(tablero.setCasilla(filaSC, colSCnew).getTipo().equals("NA")) {
                                
                            	casillaRESTAURAR(filaSC, colSC); 
                                scanner[a].setColumna(colSCnew); 
                                colSC = colSCnew; 
                                tablero.setCasilla(filaSC, colSC).setTipo("SCANNER");
                            } 
                        }
                    }
                }
            }
            Controller.MOV--;
            Controller.turno++;
            
        } else {
            Controller.gameOver();
        }
    }
    //Restaura Casilla que se le pueden caminar por encima, Puerto y Trampa.
public void casillaRESTAURAR(int fila, int col) {
        
        for (int n = 0; n < Controller.CANT_NODOS; n++) {
            if (nodo[n] != null && nodo[n].isAct() && nodo[n].getFila() == fila && nodo[n].getColumna() == col) {
                tablero.setCasilla(fila, col).setTipo("NODO");
                return;
            }
        }

        for (int i = 0; i < Controller.PUERTOS; i++) {
            if (puerto[i].getFila() == fila && puerto[i].getColumna() == col) {
                tablero.setCasilla(fila, col).setTipo("PUERTO");
                return;
            }
        }

        for (int a = 0; a < firewalls.length; a++) {
            if (firewalls[a] == null) continue;
            int fa = firewalls[a].getFila();
            int ca = firewalls[a].getColumna();

            for (int c = 0; c < firewalls.length; c++) {
                if (a == c || firewalls[c] == null) continue;
                int fa2 = firewalls[c].getFila();
                int ca2 = firewalls[c].getColumna();

                if (ca == ca2 && Math.abs(fa - fa2) == 2) {
                    if (fila == (fa + fa2) / 2 && col == ca) {
                        tablero.setCasilla(fila, col).setTipo("TRAMPA");
                        return; 
                    }
                }
            }
        }
        for (int a = 0; a < antivirus.length; a++) {
            if (antivirus[a] != null && (jugador.getFila() != antivirus[a].getFila() || jugador.getColumna() != antivirus[a].getColumna())) {
                tablero.setCasilla(antivirus[a].getFila(), antivirus[a].getColumna()).setTipo("ANTIVIRUS");
            }
        }
        
        for (int s = 0; s < scanner.length; s++) {
            if (scanner[s] != null && (jugador.getFila() != scanner[s].getFila() || jugador.getColumna() != scanner[s].getColumna())) {
                tablero.setCasilla(scanner[s].getFila(), scanner[s].getColumna()).setTipo("SCANNER");
            }
        }
        tablero.setCasilla(fila, col).setTipo("NA");
    }
    //Verifica si ganaste
    public void verificarWin() {
        if(puerto.length == jugador.getPuertosTocados()){
            Controller.win();
        }
    }
  //Verifica si tocaste una Trampa
    private void verificarTrampa(int f, int c) {
    	 if(!jugador.isSigilo()) {
        if (tablero.setCasilla(f, c).getTipo().equals("TRAMPA")) {
            int sum = f + c;
            Controller.MOV = Controller.MOV - sum;
        }
    }
    }
    private void contarSigilo() {
        if (jugador.isSigilo()) {
            cnt++;
            if(cnt > 1) {
            	jugador.setSigilo(false);
            }
        }
    }

    public Tablero getTablero() { 
    	return tablero;
    	}
    public Puertos[] getPuerto() { 
    	return puerto;
    	}
    public AntiVirus[] getAntivirus() { 
    	return antivirus;
    	}
    public Scanner[] getScanner() { 
    	return scanner;
    	}
    public Player getJugador() { 
    	return jugador;
    	}
}