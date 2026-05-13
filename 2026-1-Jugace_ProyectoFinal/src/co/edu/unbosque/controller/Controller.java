package co.edu.unbosque.controller;
import co.edu.unbosque.view.*;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import co.edu.unbosque.model.*;

public class Controller {
	
	public static int FILA = 5;
	public static int COLUMNA = 5;
	public static int GRID_TOTAL = FILA*COLUMNA;
	
	private static Player jugador;
	
	public static MainFrame vnt;
	private static VerTablero verTablero;
	private static MovimientoPanel movimientoPanel;
	private static MovimientoController movimientoController;
	
	public Controller(){
		
		vnt = new MainFrame();
		
		verTablero = new VerTablero();
		verTablero.verNumeros();
		
		vnt.setVisible(true);
		
		
	}
	
	public static void run() {
		ConfigController.run();
	}
	
	public static void mostrarTablero() {
		jugador = new Player(0,0);
		movimientoPanel = new MovimientoPanel();
		JPanel game = new JPanel(new BorderLayout());
		
		movimientoController = new MovimientoController(jugador, movimientoPanel, verTablero);
		movimientoController.darLectores();
		
		game.add(verTablero.getPanel(), BorderLayout.CENTER);
		game.add(movimientoPanel.getPanel(), BorderLayout.NORTH);
		
		verTablero.verPlayer(jugador.getFila(), jugador.getColumna());
		vnt.cambiarPanel(game);
		
	}
	
	public static void actualizarTablero() {
		GRID_TOTAL = FILA*COLUMNA;
	    verTablero = new VerTablero(); 
	}

}
