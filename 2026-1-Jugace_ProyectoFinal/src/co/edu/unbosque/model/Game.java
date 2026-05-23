package co.edu.unbosque.model;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import co.edu.unbosque.controller.Controller;
import java.util.ArrayList;
import java.util.List;

/**
 * Funcionamiento total del juego, que le sucede al jugador al tocar un enemigo, nodo o mover el paquete.
 * <p> aqui esta toda la logica de cuando se inicidaliza una nueva partida.
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */

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
    
    /**
     * Importacion de todos los objetos necesarios, junto al reincio de turno de partida anterior.
     * 
     * @param tablero <p> Importacion de Tablero con todas sus casillas
     * @param jugador <p> Importacion de jugador con su posicion predeterminada
     * @param paquete <p> Importacion de paquete con su posicion predeterminada
     * @param salida <p> Importacion de la salida con su posicion predeterminada
     * @param puerto  <p> Importacion de los Puertos con sus posiciones aleatorias.
     * @param antivirus <p> Importacion de los Antivirus con sus posiciones aleatorias.
     * @param nodo <p> Importacion de los Nodos con sus posiciones aleatorias.
     * @param scanner <p> Importacion de los Scanners con sus posiciones aleatorias.
     * @param firewalls <p> Importacion de los Firewalls y sus Trampas con sus posiciones aleatorias.
     */
    
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

    /**
     * Ejecucion de los movimientos importados de los inputs, junto a la activacion del SIGILO.
     * @param mov Movimiento depediendo del input importado de MovimientoController.
     */
    
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
    
    /**
     * Ejecucion de los movimientos, se verifica si se esta saliendo de la matriz, tocando una FIREWALL
     * o empujando el paquete.
     * @param fila <p>fila hacia donde se movera el jugador (arriba o abajo)
     * @param columna <p>columna a la que se movera el jugador (izquierda o  derecha)
     */

    
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
                
                boolean moverTodo = true; //Para comfirmar si se puede mover todo junto al paquete
                if (new_FilaPL == filaPAQ && new_ColPL == colPAQ) {
                    if (new_FilaPAQ < 0 || new_FilaPAQ >= Controller.FILA || 
            	        new_ColPAQ < 0 || new_ColPAQ >= Controller.COLUMNA || 
                        tablero.setCasilla(new_FilaPAQ, new_ColPAQ).getTipo().equals("FIREWALL")) {
                        moverTodo = false;
                    }
                }
                
                if (moverTodo) {
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
                
                   
                    String direccion = "";
                    if (fila == -1) {
                        direccion = "ARRIBA";
                    } else if (fila == 1) {
                        direccion = "ABAJO";
                    } else if (columna == -1) {
                        direccion = "IZQUIERDA";
                    } else {
                        direccion = "DERECHA";
                    }

                    String txtSigilo = "";
                    if (jugador.isSigilo()) {
                    	txtSigilo = "  [SIGILO]";
                    }

                    String entrada = "Turno " + (Controller.turno + 1)
                            + " | " + direccion
                            + "  →  pos(" + new_FilaPL + "," + new_ColPL + ")"
                            + "  | MOV restantes: " + (Controller.MOV - 1)
                            + "  | Puertos: " + jugador.getPuertosTocados()
                            + "/" + Controller.PUERTOS
                            + txtSigilo;

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
            
            objetosyEnemigos(filaPL,colPL); //Colisiones entre objetos y enemgios
            
            Controller.MOV--;
            Controller.turno++;
        } 
        else {
            historialMovimientos.add("Turno " + Controller.turno + " | Sin movimientos restantes → DERROTA");
            guardarHistorial("DERROTA (Sin movimientos)");
            Controller.gameOver();
        }
    }
    
    /**
     * Da logica al resto de objetos y enemigos del juego.
     * <p>se cuentan los nodos y se ve si se recogio o no, y mediante eso se desavtive
     * <p>se verifica la colision con el escaner, si sucede, te resta movimientos
     * <p>se verifica la colision con el antivirus, si sucede, te elimina.
     * <p>Movimiento de escaneres y antivirus basicamente son iguales, random de movimientos con limitaciones.
     * @param f <p>fila del jugador
     * @param c <p>columna del jugador
     */
    private void objetosyEnemigos(int f, int c){
    	// LÓGICA DE NODOS ENERGÉTICOS
        for(int n = 0; n < Controller.CANT_NODOS; n++) {
            if(nodo[n] != null && nodo[n].isAct()) { 
                if(nodo[n].getFila() == f && nodo[n].getColumna() == c) {
                    int eng = (int)(Controller.GRID_TOTAL * 0.10);
                    Controller.MOV = Controller.MOV + eng;
                    nodo[n].setAct(false);
                    tablero.setCasilla(f, c).setTipo("PLAYER");
                    historialMovimientos.add("          └─ NODO recogido en ("
                            + f + "," + c
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
                    
                    if ((filaSC + 1 == f && colSC == c) ||
                        (filaSC - 1 == f && colSC == c) ||
                        (filaSC == f && colSC + 1 == c) ||
                        (filaSC == f && colSC - 1 == c) ||
                        (filaSC == f && colSC == c)) {
                        
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
            
                if((filaANT + 1 == f && colANT == c) ||
                   (filaANT - 1 == f && colANT == c) ||
                   (filaANT == f && colANT + 1 == c) ||
                   (filaANT == f && colANT - 1 == c) ||
                   (filaANT == f && colANT == c)) {
                    
                    historialMovimientos.add("          - ANTIVIRUS: GAME OVER");
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
    }

    //Restaura Casilla que se le pueden caminar por encima, Puerto y Trampa.
    /**
     * Restauracion de casilla es un metodo que vuelve a añadir el tipo de casilla (NODO o PUERTO)
     * donde estaba si el jugador o el paquete pasa por encima de ellas, esto evita que desaparezcan
     * apenas la pisamos ya que el jugador siempre deja un "rastro" de tipo "NA" cada vez que se mueve.
     * @param fila fila del jugador
     * @param col columna del jugador
     */
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

    /**
     * Verificacion si ganaste la partida al tocar la salida y contar con todos los puertos desactivados.
     */
    public void verificarWin() {
    	if (paquete.getFila() == salida.getFila() && paquete.getColumna() == salida.getColumna() && salida.isActivo()) {
            historialMovimientos.add("*** VICTORIA: paquete extraido en salida ***");
            guardarHistorial("VICTORIA");
            Controller.win();
        }
    }

    /**
     * Verificacion de activacion de la salida si todos los puertos han sido desactivados.
     */
    public void verificarSalida() {
        if(puerto.length == jugador.getPuertosTocados()){
            salida.setActivo(true);
        }
    }
    /**
     * Verificacion EXTRA si hay una trampa, esta existe ya que el paquete puede llegar a borrar su existencia
     * por completo, al hacer que se asegure que esta exista por segunda vez, evitamos que solo se borre.
     * @param f fila del jugador
     * @param col columna del jugador
     */
    public boolean HayTrampa(int f, int col) {
        for (int a = 0; a < firewalls.length; a++) {
            if (firewalls[a] == null) continue;
            int fa = firewalls[a].getFila();
            int ca = firewalls[a].getColumna();

            for (int c = 0; c < firewalls.length; c++) {
                if (a == c || firewalls[c] == null) continue;
                int fa2 = firewalls[c].getFila();
                int ca2 = firewalls[c].getColumna();

                // Detecta trampa vertical
                if (ca == ca2 && Math.abs(fa - fa2) == 2) {
                    if (f == (fa + fa2) / 2 && col == ca) return true;
                }
                // Detecta trampa horizontal
                if (fa == fa2 && Math.abs(ca - ca2) == 2) {
                    if (f == fa && col == (ca + ca2) / 2) return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Verificacion si el jugador piso una TRAMPA y esta existe, si es asi, se le restan MOVIMIENTOS al jugador
     * dependiendo de su posicion
     * @param f fila del jugador
     * @param c columna del jugador
     */
    private void verificarTrampa(int f, int c) {
    	if(!jugador.isSigilo()) {
            if (tablero.setCasilla(f, c).getTipo().equals("TRAMPA") || HayTrampa(f,c)) {
                int sum = f + c;
                Controller.MOV = Controller.MOV - sum;
                historialMovimientos.add("          └─ TRAMPA pisada en (" + f + "," + c + ")  -MOV: -" + sum);
            }
        }
    }
    /**
     * Verificacion de Sigilo, si esta activo, se te activa un contador interno que no te permite estar en ese modo por mas de un
     * turno.
     */
    private void contarSigilo() {
        if (jugador.isSigilo()) {
            cnt++;
            if(cnt > 1) {
            	jugador.setSigilo(false);
            }
        }
    }
    
    /**
     * Guarda TODO lo hecho en el historial.txt
     */
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