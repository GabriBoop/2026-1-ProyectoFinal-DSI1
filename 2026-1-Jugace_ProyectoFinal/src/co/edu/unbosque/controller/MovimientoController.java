package co.edu.unbosque.controller;

import co.edu.unbosque.model.Game;
import co.edu.unbosque.model.Player;
import co.edu.unbosque.view.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MovimientoController extends JPanel implements ActionListener {
	
    private Game game;
    private VerTablero verTablero; 
    private MovimientosPanel movimientospanel;
    private Player jugador;
    
    public MovimientoController(Game game, VerTablero verTablero, MovimientosPanel movimientospanel, Player jugador) {
        this.game = game;
        this.verTablero = verTablero;
        this.movimientospanel = movimientospanel;
        this.jugador = jugador;
    }
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
            public void actionPerformed(ActionEvent e) { procesarMovimiento("SIGILO"); }
        });
    }

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

    public void actionPerformed(ActionEvent e) {
        procesarMovimiento(e.getActionCommand());
    }
}