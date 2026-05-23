package co.edu.unbosque.model;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import co.edu.unbosque.controller.Controller;
import java.util.ArrayList;
import java.util.List;

public class Game {
	
    private Player jugador;
    private Paquete paquete;
    private Salida salida;
    private Puertos[] puerto;
    private AntiVirus[] antivirus;
    private Nodo[] nodo;
    private Scanner[] scanner;
    private FireWalls[] firewalls;
    private Tablero tablero;

    private boolean sigiloUSADO = false;
    private int cnt = 0;
    
    // Lista del historial de movimientos integrada
    private List<String> historialMovimientos = new ArrayList<>();
    
    public Game(Tablero tablero, Player jugador, Paquete paquete, Salida salida, Puertos[] puerto, AntiVirus[] antivirus, Nodo[] nodo, Scanner[] scanner, FireWalls[] firewalls) {
        this.tablero = tablero; 
        this.jugador = jugador; 
        this.paquete = paquete;
        this.salida = salida;
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
            case "SIGILO":
            	if(!sigiloUSADO) {
            		sigiloUSADO = true;
            		jugador.setSigilo(true);
            		historialMovimientos.add("Turno " + Controller.turno
                            + " | SIGILO activado  (pos: "
                            + jugador.getFila() + "," + jugador.getColumna() + ")");
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
        
        int filaPAQ = paquete.getFila(); 
        int colPAQ = paquete.getColumna();

        if (Controller.MOV > 0) {
            int new_FilaPL = filaPL + fila;
            int new_ColPL = colPL + columna;

            int new_FilaPAQ = filaPAQ;
            int new_ColPAQ = colPAQ;
            
            if (new_FilaPL == filaPAQ && new_ColPL == colPAQ) {
                new_FilaPAQ = filaPAQ + fila;
                new_ColPAQ = colPAQ + columna;
            }

            // VER LIMITES DE LA MATRIZ Y QUE NO CHOQUE CON FIREWALL
            if (new_FilaPL >= 0 && new_FilaPL < Controller.FILA && 
            	new_ColPL >= 0 && new_ColPL < Controller.COLUMNA && 
                !tablero.setCasilla(new_FilaPL, new_ColPL).getTipo().equals("FIREWALL")) {
                
                int moverTodo = 1;
                if (new_FilaPL == filaPAQ && new_ColPL == colPAQ) {
                    if (new_FilaPAQ < 0 || new_FilaPAQ >= Controller.FILA || 
            	        new_ColPAQ < 0 || new_ColPAQ >= Controller.COLUMNA || 
                        tablero.setCasilla(new_FilaPAQ, new_ColPAQ).getTipo().equals("FIREWALL")) {
                        moverTodo = 0;
                    }
                }
                
                if (moverTodo == 1) {
                    verificarTrampa(new_FilaPL, new_ColPL); //Verifica si paso por una trampa
                    
                    if (new_FilaPL == filaPAQ && new_ColPL == colPAQ) {
                        casillaRESTAURAR(filaPAQ, colPAQ);
                        paquete.setFila(new_FilaPAQ);
                        paquete.setColumna(new_ColPAQ);
                        tablero.setCasilla(new_FilaPAQ, new_ColPAQ).setTipo("PAQUETE");
                        
                        // JUGADOR SE MUEVE A CASILLA "NA" O PUERTO
                        for (int i = 0; i < Controller.PUERTOS; i++) { 
                            if (new_FilaPAQ == puerto[i].getFila() && new_ColPAQ == puerto[i].getColumna()) {
                                int orden = jugador.getPuertosTocados() + 1;
                                if (Controller.INVERSO) orden = Controller.PUERTOS - jugador.getPuertosTocados(); 
                                    
                                if (!puerto[i].isActivo()) break; 
                                if (puerto[i].isActivo() && (puerto[i].getNum() == orden)) { 
                                    puerto[i].setActivo(1 == 0);
                                    jugador.setPuertosTocados(jugador.getPuertosTocados() + 1);
                                }                                
                            }
                        }
                    }

                	tablero.setCasilla(filaPAQ, colPAQ).setVisitada(true);
                    casillaRESTAURAR(filaPL, colPL);
                    jugador.setFila(new_FilaPL); 
                    jugador.setColumna(new_ColPL); 
                    paquete.setFila(new_FilaPAQ);
                    paquete.setColumna(new_ColPAQ);
                    tablero.setCasilla(new_FilaPL, new_ColPL).setTipo("PLAYER"); 
                    tablero.setCasilla(new_FilaPAQ, new_ColPAQ).setTipo("PAQUETE"); 
                
                    // Agregar log del movimiento del turno
                    String direccion = (fila == -1) ? "ARRIBA"
                                     : (fila ==  1) ? "ABAJO"
                                     : (columna == -1) ? "IZQUIERDA" : "DERECHA";
                    String entrada = "Turno " + (Controller.turno + 1)
                            + " | " + direccion
                            + "  →  pos(" + new_FilaPL + "," + new_ColPL + ")"
                            + "  | MOV restantes: " + (Controller.MOV - 1)
                            + "  | Puertos: " + jugador.getPuertosTocados()
                            + "/" + Controller.PUERTOS
                            + (jugador.isSigilo() ? "  [SIGILO]" : "");
                    historialMovimientos.add(entrada);

                    contarSigilo();
                    verificarSalida();
                    verificarWin();
                }
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
                        nodo[n].setAct(1 == 0);
                        tablero.setCasilla(filaPL, colPL).setTipo("PLAYER");
                        historialMovimientos.add("          └─ NODO recogido en ("
                                + filaPL + "," + colPL
                                + ")  +MOV: +" + eng);
                        break;
                    }
                }
            }
            
            // COLISION DE SCANNERS (agrupado para ser más limpio y añadir log)
            if(!jugador.isSigilo()) {
                for (int s = 0; s < scanner.length; s++) { 
                    if (scanner[s] != null) {
                        int filaSC = scanner[s].getFila();
                        int colSC = scanner[s].getColumna();
                        
                        if ((filaSC + 1 == filaPL && colSC == colPL) ||
                            (filaSC - 1 == filaPL && colSC == colPL) ||
                            (filaSC == filaPL && colSC + 1 == colPL) ||
                            (filaSC == filaPL && colSC - 1 == colPL) ||
                            (filaSC == filaPL && colSC == colPL)) {
                            
                            int penalizacion = (int)(Controller.MOV * 0.05);
                            Controller.MOV -= penalizacion;
                            historialMovimientos.add("          └─ SCANNER detectado  -MOV: -" + penalizacion);
                        }
                    }
                }
            }

            // COLISIÓN ANTIVIRUS (agrupado para ser más limpio y añadir log)
            if(!jugador.isSigilo()) {
                for(int a = 0; a < antivirus.length; a++) { 
                    int filaANT = antivirus[a].getFila();
                    int colANT = antivirus[a].getColumna();
                
                    if((filaANT + 1 == filaPL && colANT == colPL) ||
                       (filaANT - 1 == filaPL && colANT == colPL) ||
                       (filaANT == filaPL && colANT + 1 == colPL) ||
                       (filaANT == filaPL && colANT - 1 == colPL) ||
                       (filaANT == filaPL && colANT == colPL)) {
                        
                        historialMovimientos.add("          └─ ANTIVIRUS: GAME OVER");
                        guardarHistorial("DERROTA (Capturado por Antivirus)");
                        Controller.gameOver();
                        break;
                    }
                }
            }

            // MOVIMIENTO ALEATORIO DEL ANTIVIRUS """"IA""""
            for(int a = 0; a < antivirus.length; a++) { 
                int filaANT = antivirus[a].getFila();
                int colANT = antivirus[a].getColumna();
                int ran = (int) (Math.random() * 2); 
            	 
                if(ran == 1) { 
                    ran = (int) (Math.random() * 2); 
                   
                    if(ran == 0) { 
                    	ran = (int) (Math.random() * 3) - 1; 
                        while(ran == 0) ran = (int) (Math.random() * 3) - 1; 
                        int filaANTnew = filaANT + ran; 
            			
                        if (filaANTnew >= 0 && filaANTnew < Controller.FILA ) {
                            if(tablero.setCasilla(filaANTnew, colANT).getTipo().equals("NA")) {
                            	casillaRESTAURAR(filaANT, colANT); 
                                antivirus[a].setFila(filaANTnew);
                                filaANT = filaANTnew; 
                                tablero.setCasilla(filaANT, colANT).setTipo("ANTIVIRUS"); 
                            }
                        }
                    } else { 
                    	ran = (int) (Math.random() * 3) - 1;
                        while(ran == 0) ran = (int) (Math.random() * 3) - 1; 
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
                int ran = (int) (Math.random() * 2); 
            	 
                if(ran == 1) { 
                    ran = (int) (Math.random() * 2); 
                   
                    if(ran == 0) {
                    	ran = (int) (Math.random() * 3) - 1; 
                        while(ran == 0) ran = (int) (Math.random() * 3) - 1; 
                        int filaSCnew = filaSC + ran; 
            			
                        if (filaSCnew >= 0 && filaSCnew < Controller.FILA ) {
                            if(tablero.setCasilla(filaSCnew, colSC).getTipo().equals("NA")) {
                            	casillaRESTAURAR(filaSC, colSC); 
                                scanner[a].setFila(filaSCnew);
                                filaSC = filaSCnew; 
                                tablero.setCasilla(filaSC, colSC).setTipo("SCANNER"); 
                            }
                        }
                    } else { 
                    	ran = (int) (Math.random() * 3) - 1;
                        while(ran == 0) ran = (int) (Math.random() * 3) - 1; 
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
            
        } 
        else {
            historialMovimientos.add("Turno " + Controller.turno + " | Sin movimientos restantes → DERROTA");
            guardarHistorial("DERROTA (Sin movimientos)");
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
        tablero.setCasilla(Controller.FILA-1, Controller.COLUMNA-1).setTipo("SALIDA");
    }

    //Verifica si ganaste
    public void verificarWin() {
    	if (paquete.getFila() == salida.getFila() && paquete.getColumna() == salida.getColumna() && salida.isActivo()) {
            historialMovimientos.add("*** VICTORIA: paquete extraido en salida ***");
            guardarHistorial("VICTORIA");
            Controller.win();
        }
    }

    //Verifica si ya puedes ir a la salida
    public void verificarSalida() {
        if(puerto.length == jugador.getPuertosTocados()){
            salida.setActivo(true);
        }
    }

    //Verifica si tocaste una Trampa
    private void verificarTrampa(int f, int c) {
    	if(!jugador.isSigilo()) {
            if (tablero.setCasilla(f, c).getTipo().equals("TRAMPA")) {
                int sum = f + c;
                Controller.MOV = Controller.MOV - sum;
                historialMovimientos.add("          └─ TRAMPA pisada en (" + f + "," + c + ")  -MOV: -" + sum);
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
    
    // 	GUARDAR LOG
    public void guardarHistorial(String resultado) {
        HistorialPartida.guardar(
                historialMovimientos,
                resultado,
                Controller.turno,
                Controller.MOV,
                Controller.PUERTOS,
                jugador.getPuertosTocados()
        );
    }

    public List<String> getHistorial() {
        return historialMovimientos;
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
    public Salida getSalida() { 
    	return salida;
    	}
}