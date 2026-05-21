package co.edu.unbosque.controller;

import co.edu.unbosque.model.Game;
import co.edu.unbosque.model.Player;
import co.edu.unbosque.view.*;
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
        //Otros
        in.put(KeyStroke.getKeyStroke("R"), "RESET");
        in.put(KeyStroke.getKeyStroke("SPACE"), "SIGILO");
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
        act.put("SIGILO", new AbstractAction() {
            public void actionPerformed(ActionEvent e) { procesarMovimiento("SIGILO"); }
        });
    }

    private void procesarMovimiento(String direccion) {
        //Da el Movimiento al juego
        game.ejecutarMovimiento(direccion);
        // COSAS DE VISTA
        movimientospanel.getTrn().setText("TURNO: " + Controller.turno);
        movimientospanel.getTxt().setText("MOVIMIENTOS: " + Controller.MOV);
        movimientospanel.actualizarPuertosTocados(jugador.getPuertosTocados());

        verTablero.ver(game.getTablero(), game.getPuerto(), game.getAntivirus(), game.getScanner(), game.getJugador() );
    }

    public void actionPerformed(ActionEvent e) {
        procesarMovimiento(e.getActionCommand());
    }
}