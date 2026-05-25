package co.edu.unbosque.controller;
import co.edu.unbosque.view.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import co.edu.unbosque.model.*;

/**
 * Clase incializadora del Menu, Paneles e Importacion de objetos a otros metodos o clases,
 * <strong>el cerebro principal de la aplicacion<strong>
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */

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
	//Creacion objetos
	private static Tablero tablero;
	private static Player jugador; 
	private static Paquete paquete;
	private static Salida salida;							
	
	private static Puertos[] puerto = new Puertos[PUERTOS];
	private static AntiVirus[] antivirus = new AntiVirus[CANT_ANTIVIRUS];
	private static Nodo[] nodo = new Nodo[CANT_NODOS];
	private static Scanner[] scanner = new Scanner[CANT_SCANNERS];
	private static FireWalls[] firewall = new FireWalls[CANT_FIREWALLS];
	
	private static Game game;
	
	public static String musica = "/co/edu/unbosque/sound/sneakman.wav";
	public static String click = "/co/edu/unbosque/sound/Click.wav";
	
	/**
	 * Clase incializadora del Menu, Paneles e Importacion de objetos a otros metodos o clases,
	 * <strong>el cerebro principal de la aplicacion<strong>
	 */
	public Controller(){
		vnt = new MainFrame();
		verTablero = new VerTablero();
		vnt.setVisible(true);	
	}
	
	/**
	 * Inicializa el Programa
	 */

	public static void run() {
		MenuController.run();
	}
	
	/**
	 * Al llamar este Metodo, se prepararan los objetos (Como el jugador, paquete y enemigos varios)
	 * y se importaran a otros Metodos para la inicializacion de la Partida.
	 */
	
	public static void mostrarTablero() {
		
		ReproducirAudio.reproducir(musica);
	    tablero = new Tablero();
	    movimientosPanel = new MovimientosPanel();
	    
	    jugador = new Player(0,0);
	    paquete = new Paquete(1,1);
	    salida = new Salida(FILA-1,COLUMNA-1);
	    
	    game = new Game(tablero, jugador, paquete, salida, puerto, antivirus, nodo, scanner, firewall);
	    
	    GeneradorTablero.run(tablero,jugador,paquete,salida,puerto,PUERTOS, antivirus,nodo,firewall,scanner, CANT_ANTIVIRUS); //Genera Talbero
	    
	    JPanel gamePanel = new JPanel(new BorderLayout()); //Nuevo Panel que contiene el Grid del juego y panel de movimiento.
	    
	    movimientoController = new MovimientoController(game, verTablero, movimientosPanel, jugador);
	    movimientoController.input();
	    
	    gamePanel.add(movimientoController); 
	    gamePanel.add(verTablero.getPanel(), BorderLayout.CENTER);

	 // Como MovimientosPanel hereda directo de JPanel, lo agregas a pelo
	    gamePanel.add(movimientosPanel, BorderLayout.EAST);

	    
	    verTablero.ver(tablero,salida, puerto, antivirus, scanner, jugador);
	    vnt.cambiarPanel(gamePanel);
	}
	
	/**
	 * <strong>Actualizacion del Tablero<strong>, al llamarlo, vuelve a redifinir variables por si se le han
	 * hecho cambios, principalementes a los <strong>ARRAYS<strong>,
	 */
	
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
	
	/**
	 * Se llama cuando se pierde en una partida, hace que <b>>se cambie por completo el panel actual de juego</b>
	 * y pase a ser el respectivo de muerte, tambien <b>cambia la musica actualiza la variable de orden INVERSO de PUERTOS</b>
	 */
	
	public static void gameOver() { //Panel de GameOver
		GameOver gameover = new GameOver(); 
		ReproducirAudio.detener();
		ReproducirAudio.reproducir("/co/edu/unbosque/sound/gameover.wav"); 
		INVERSO = false;
		vnt.cambiarPanel(gameover.getPanel());
		
		gameover.getBtnRestart().addActionListener(new ActionListener() {
	        
	        public void actionPerformed(ActionEvent e) {
	        	ReproducirAudio.detener();
	        	MenuController.run();
	        }
	    });
	    
	}
	/**
	 * Se llama cuando se gana en una partida, hace que <b>se cambie por completo el panel actual de juego</b>
	 * y pase a ser el respectivo de ganar, tambien  <b>cambia la musica actualiza la variable de orden INVERSO de PUERTOS</b>
	 */
	
	public static void win() { 
		WinPanel win = new WinPanel(); 
		ReproducirAudio.detener();
		ReproducirAudio.reproducir("/co/edu/unbosque/sound/win.wav"); 
		INVERSO = false;
		vnt.cambiarPanel(win.getPanel());
		win.getBtnRestart().addActionListener(new ActionListener() {
	        
	        public void actionPerformed(ActionEvent e) {
	        	ReproducirAudio.detener();
	        	MenuController.run();
	        }
	    });
	    
	}
}
