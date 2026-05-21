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
	public static int turno = 0; //turno
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
	
	private static Puertos[] puerto = new Puertos[PUERTOS];
	private static AntiVirus[] antivirus = new AntiVirus[CANT_ANTIVIRUS];
	private static Nodo[] nodo = new Nodo[CANT_NODOS];
	private static Scanner[] scanner = new Scanner[CANT_SCANNERS];
	private static FireWalls[] firewall = new FireWalls[CANT_FIREWALLS];
	
	private static Game game;
	
	public static String musica = "/co/edu/unbosque/sound/SMOOTH_OPERATOR.wav";
	public static String click = "/co/edu/unbosque/sound/Click.wav";
	public Controller(){
		
		vnt = new MainFrame();
		verTablero = new VerTablero();
		vnt.setVisible(true);
		
	}
	
	public static void run() {
		ConfigController.run();
	}
	
	public static void mostrarTablero() {
		ReproducirAudio.reproducir(musica);
	    tablero = new Tablero();
	    movimientosPanel = new MovimientosPanel();
	    
	    jugador = new Player(0,0);
	    game = new Game(tablero, jugador, puerto, antivirus, nodo, scanner, firewall);
	    
	    
	    GeneradorTablero.run(tablero,jugador,puerto,PUERTOS, antivirus,nodo,firewall,scanner, CANT_ANTIVIRUS); //Genera Talbero
	    
	    JPanel gamePanel = new JPanel(new BorderLayout()); //Nuevo Panel que contiene el Grid del juego y panel de movimiento.
	    
	    movimientoController = new MovimientoController(game, verTablero, movimientosPanel, jugador);
	    movimientoController.input();
	   
	    gamePanel.add(movimientoController); 
	    gamePanel.add(verTablero.getPanel(), BorderLayout.CENTER);

	 // Como MovimientosPanel hereda directo de JPanel, lo agregas a pelo
	    gamePanel.add(movimientosPanel, BorderLayout.EAST);

	    
	    verTablero.ver(tablero, puerto, antivirus, scanner, jugador);
	    vnt.cambiarPanel(gamePanel);
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
		ReproducirAudio.detener();
		vnt.cambiarPanel(gameover.getPanel());
		gameover.getBtnRestart().addActionListener(new ActionListener() {
	        
	        public void actionPerformed(ActionEvent e) {
	            ConfigController.run();
	        }
	    });
	    
	}
	public static void win() { //Panel de GameOver
		WinPanel win = new WinPanel(); 
		ReproducirAudio.detener();
		vnt.cambiarPanel(win.getPanel());
		win.getBtnRestart().addActionListener(new ActionListener() {
	        
	        public void actionPerformed(ActionEvent e) {
	            ConfigController.run();
	        }
	    });
	    
	}
}
