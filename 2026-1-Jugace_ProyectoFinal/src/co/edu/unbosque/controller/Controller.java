package co.edu.unbosque.controller;
import co.edu.unbosque.view.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import co.edu.unbosque.model.*;

public class Controller{
	
	public static int FILA = 5; //Filas
	public static int COLUMNA = 5; //Columnas
	public static int GRID_TOTAL = FILA*COLUMNA; //Filas * Columnas
	public static int MOV = GRID_TOTAL; //Movimientos, igual q GridTotal
	
	public static boolean INVERSO = false; //Check si el orden de los puertos es inverso o no
	
	public static int PUERTOS = 3; //Cantidad de puertos a tocar
	public static int CANT_ANTIVIRUS = 2; // Cantidad de enemigos y nodos calculada por dificultad 
	public static int CANT_NODOS = 2; 
	public static int CANT_SCANNERS = 2; 
	public static int CANT_FIREWALLS = 2;
	
	public static MainFrame vnt; //Importacion todo lo grafico
	private static VerTablero verTablero;
	private static MovimientosPanel movimientosPanel;
	private static MovimientoController movimientoController;
	
	private static Player jugador; //Creacion objetos
	private static Tablero tablero;
	private static Paquete paquete;
	
	private static Puertos[] puerto = new Puertos[PUERTOS];
	private static AntiVirus[] antivirus = new AntiVirus[CANT_ANTIVIRUS];
	private static Nodo[] nodo = new Nodo[CANT_NODOS];
	private static Scanner[] scanner = new Scanner[CANT_SCANNERS];
	private static FireWalls[] firewall = new FireWalls[CANT_FIREWALLS];
	
	public Controller(){
		
		vnt = new MainFrame();
		verTablero = new VerTablero();
		vnt.setVisible(true);
		
	}
	
	public static void run() {
		ConfigController.run();
	}
	
	public static void mostrarTablero() {
		
	    tablero = new Tablero();
	    movimientosPanel = new MovimientosPanel();
	    
	    jugador = new Player(0,0);
	    paquete = new Paquete(1,1);
	    
	    tablero.setCasilla(0, 0).setTipo("PLAYER");
	    tablero.setCasilla(1, 1).setTipo("PAQUETE");
	    
	    GeneradorTablero.run(tablero,jugador,paquete,puerto,PUERTOS, antivirus,nodo, CANT_ANTIVIRUS); //Genera Talbero
	    
	    JPanel game = new JPanel(new BorderLayout()); //Nuevo Panel que contiene el Grid del juego y panel de movimiento.
	    
	    movimientoController = new MovimientoController(tablero,jugador,paquete,puerto,antivirus,nodo,scanner,firewall,verTablero,movimientosPanel);
	    movimientoController.input();
	    
	    movimientosPanel.getTxt().setText("MOVIMIENTOS: " + MOV + " / " + GRID_TOTAL); //Cosas del Panel Superior: Movimiento y Puertos
	    movimientosPanel.getPrt().setText("PUERTOS: " + jugador.getPuertosTocados() + " / " + PUERTOS);
	    
	    game.add(movimientoController); 
	    game.add(verTablero.getPanel(), BorderLayout.CENTER);
	    game.add(movimientosPanel.getPanel(), BorderLayout.NORTH);

	    
	    verTablero.ver(tablero, puerto, antivirus);
	    vnt.cambiarPanel(game);
	    SwingUtilities.invokeLater(() -> { //Funcionamiento de Input
	        movimientoController.requestFocusInWindow();
	    });
	}
	
	public static void actualizarTablero() { //Actualizar tablero para Config
		GRID_TOTAL = FILA*COLUMNA;
		MOV = GRID_TOTAL;
		puerto = new Puertos[PUERTOS];
		antivirus = new AntiVirus[CANT_ANTIVIRUS];
		nodo = new Nodo[CANT_NODOS];
		scanner = new Scanner[CANT_SCANNERS];
		firewall = new FireWalls[CANT_FIREWALLS];
	    verTablero = new VerTablero(); 
	}
	
	public static void gameOver() { //Panel de GameOver
		GameOver gameover = new GameOver(); 
		vnt.cambiarPanel(gameover.getPanel());
		gameover.getBtnRestart().addActionListener(new ActionListener() {
	        
	        public void actionPerformed(ActionEvent e) {
	            ConfigController.run();
	        }
	    });
	    
	}
}
