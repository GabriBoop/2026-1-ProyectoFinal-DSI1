package co.edu.unbosque.controller;

import co.edu.unbosque.model.Game;
import co.edu.unbosque.model.Player;
import co.edu.unbosque.view.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Clase controladora de diferentes INPUTS del jugador, como tanto su movimiento, como otros agregados.
 * <p>Requiere importacion de otra clase.
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */


public class MovimientoController extends JPanel implements ActionListener {
	
    private Game game;
    private VerTablero verTablero; 
    private MovimientosPanel movimientospanel;
    private Player jugador;
    
    /**
     * Importa los objetos que se van a usar
     * @param game <p> Importacion del juego y su logica principal, Game.java.
     * @param verTablero  <p> Importacion del juego visual.
     * @param movimientospanel <p> Importacion del panel derecho del juego donde se ven las estadisticas del juego y jugador.
     * @param jugador Importacion del jugador para poner sus inputs.
     */
    
    public MovimientoController(Game game, VerTablero verTablero, MovimientosPanel movimientospanel, Player jugador) {
        this.game = game;
        this.verTablero = verTablero;
        this.movimientospanel = movimientospanel;
        this.jugador = jugador;
    }
    
    /**
     *  Escucha los Inputs puestos por el jugador.
     *  <p> MOVIMIENTOS: ARRIBA, ABAJO, IZQUIERDA, DERECHA / W, A, S, D
     *  <p> ACTIVACION MODO SIGILO: SPACE / ENTER
     *  <p> SALIR DE LA PARTIDA: ESCAPE
     *  <p> REGENERAR MAPA / TALBLERO: R
     *  <p> Todo esto se escucha con InputMap y luego se llama con ActionMap que
     *  redirije el llamado al ActionPerformed.
     */
    
    public void input() {
    	
        InputMap in = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap act = getActionMap();
        
        in.put(KeyStroke.getKeyStroke("UP"), "ARRIBA");
        in.put(KeyStroke.getKeyStroke("W"), "ARRIBA");
        in.put(KeyStroke.getKeyStroke("DOWN"), "ABAJO");
        in.put(KeyStroke.getKeyStroke("S"), "ABAJO");
        in.put(KeyStroke.getKeyStroke("LEFT"), "IZQUIERDA");
        in.put(KeyStroke.getKeyStroke("A"), "IZQUIERDA");
        in.put(KeyStroke.getKeyStroke("RIGHT"), "DERECHA");
        in.put(KeyStroke.getKeyStroke("D"), "DERECHA");
        //Otros
        in.put(KeyStroke.getKeyStroke("R"), "RESET");
        in.put(KeyStroke.getKeyStroke("ESCAPE"), "OUT");
        in.put(KeyStroke.getKeyStroke("SPACE"), "SIGILO");
        in.put(KeyStroke.getKeyStroke("ENTER"), "SIGILO");
        
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
        act.put("RESET", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { 
            	procesarMovimiento("RESET"); }
        });
        act.put("OUT", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { 
            	procesarMovimiento("OUT"); }
        });
        act.put("SIGILO", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { 
            	procesarMovimiento("SIGILO"); }
        });
    }
    public void actionPerformed(ActionEvent e) {
        procesarMovimiento(e.getActionCommand());
    }
    
    /**
     * Procesa el movimiento hecho por Input(), revisa primero si s REINICIO / RESET el tablero o si se salio de la partida,
     * de ahi, manda la informacion a GAME y la logica principal
     * <p>Tambien actualiza los datos / valores del panel derecho del juego para que no se mantengan obsoletos y mayor inmersion.
     * @param direccion <p> el INPUT hecho por el jugador.
     */
    private void procesarMovimiento(String direccion) {
    	
    	if (direccion.equals("RESET")) {
            Controller.turno = 0;
            Controller.MOV = Controller.GRID_TOTAL;
            Controller.actualizarTablero();
            Controller.mostrarTablero();
            return; 
        }
    	
    	if (direccion.equals("OUT")) {
            ReproducirAudio.detener();
            MenuController.run();
            return; 
        }
    	
    	//Se lleva input a GAME
        game.ejecutarMovimiento(direccion);
        // COSAS DE VISTA
        
        movimientospanel.getTrn().setText("TURNO: " + Controller.turno);
        movimientospanel.getTxt().setText("MOVIMIENTOS: " + Controller.MOV);
        movimientospanel.actualizarPuertosTocados(jugador.getPuertosTocados());
        
        if(jugador.isSigilo()) {
        	movimientospanel.getTxtSigilo().setText("SIGILO: ACTIVO");
        	movimientospanel.getTxtSigilo().setForeground(Color.CYAN);
        	movimientospanel.actualizarGifJugador("src/co/edu/unbosque/images/player_sigilo.gif");
        }
        else {
        	movimientospanel.getTxtSigilo().setText("SIGILO: INACTIVO");
        	movimientospanel.getTxtSigilo().setForeground(Color.BLUE);
        	movimientospanel.actualizarGifJugador("src/co/edu/unbosque/images/player_wait.gif");
        }

        verTablero.ver(game.getTablero(), game.getSalida(), game.getPuerto(), game.getAntivirus(), game.getScanner(), game.getJugador() );
        Controller.vnt.revalidate();
        Controller.vnt.repaint();
    }

   
}