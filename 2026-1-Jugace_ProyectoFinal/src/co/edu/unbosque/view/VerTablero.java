package co.edu.unbosque.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.*;
import co.edu.unbosque.controller.Controller;
import co.edu.unbosque.model.*;
/**
 * Clase de visualizacion del tablero de juego en tiempo real.
 * <p>Renderiza cada casilla del tablero como un JPanel dentro de un
 * GridLayout, representando visualmente los elementos del juego
 * (jugador, antivirus, scanner, paquete, nodo, firewall, puerto y salida)

 *
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */
public class VerTablero {
	
	private static JPanel gridPanel = new JPanel();
	private JPanel[] paneles = new JPanel[Controller.GRID_TOTAL];
	  /**
     * Constructor de la vista del tablero.
     * <p>Inicializa el grid con {@code GridLayout} usando las dimensiones de filas
     * y columnas definidas en {@code Controller}, y agrega un panel por cada casilla.
     */
	public VerTablero() {
		
		gridPanel.setLayout(new GridLayout(Controller.FILA, Controller.COLUMNA)); //Creacion GRID con datos de FILA y COLUMNA
		
		for(int i = 0; i < Controller.GRID_TOTAL; i++) {//Adicion de paneles al Grid segun el total de FILA X COLUMNA
			
			paneles[i] = new JPanel();
			paneles[i] = new JPanel(new BorderLayout());
			paneles[i].setBorder(BorderFactory.createLineBorder(new Color(25,0,80), 1)); 
			gridPanel.add(paneles[i]);
		}
	}
	
	 /**
     * Renderiza completamente el tablero con el estado actual de todos los objetos del juego.
     * <p>Recorre cada casilla del tablero, determina su tipo y coloca el icono correspondiente.
     * Adicionalmente pinta el rastro del jugador, y los rangos de peligro de antivirus
     * y scanners con colores de fondo diferenciados.
     *
     * @param tablero   Matriz de casillas del juego
     * @param salida    Indica si la puerta de escape esta activa
     * @param puerto    arreglo de puertos con posicion y estado de cada puerto
     * @param antivirus arreglo de antivirus con posicion de cada enemigo
     * @param scanner   arreglo de scanner con posicion de cada scanner
     * @param jugador   el player con su posicion y estado de sigilo actual
     */
	
	public void ver(Tablero tablero ,Salida salida, Puertos[] puerto, AntiVirus[] antivirus, Scanner[] scanner, Player jugador) {
		//Import de todo del Controller
		gridPanel.removeAll(); //Quita lo anterior del Grid
		
		int i = 0; //numero del Panel
		for (int f = 0; f < Controller.FILA; f++) {
			for (int c = 0; c < Controller.COLUMNA; c++) {
				
				Casilla casilla = tablero.setCasilla(f,c);
				String tp = casilla.getTipo();
				
				paneles[i] = new JPanel(); //Crea el panel
				paneles[i].setBorder(BorderFactory.createLineBorder(new Color(25,0,80), 1)); 
				paneles[i].setLayout(new BorderLayout()); 
				paneles[i].setBackground(new Color(15, 0, 50));
				paneles[i].removeAll();
				
				switch(tp) {
				
				case "PLAYER":
					ImageIcon icon = obtenerRENDER_GIF("src/co/edu/unbosque/images/player_idle.gif");
					if(jugador.isSigilo()) icon = obtenerRENDER("src/co/edu/unbosque/images/player_s.png");
					JLabel lbl_icon = new JLabel(icon);
					lbl_icon.setHorizontalAlignment(SwingConstants.CENTER);
				    lbl_icon.setVerticalAlignment(SwingConstants.CENTER);
					paneles[i].add(lbl_icon);
					break;
				case "PAQUETE":
					icon = obtenerRENDER_GIF("src/co/edu/unbosque/images/paquete_idle.gif");
					lbl_icon = new JLabel(icon);
					lbl_icon.setHorizontalAlignment(SwingConstants.CENTER);
				    lbl_icon.setVerticalAlignment(SwingConstants.CENTER);
					paneles[i].add(lbl_icon);	
					break;
				case "SALIDA":
					icon = obtenerRENDER("src/co/edu/unbosque/images/salida_close.png");
					if(salida.isActivo())icon = obtenerRENDER("src/co/edu/unbosque/images/salida.png");
					lbl_icon = new JLabel(icon);
					lbl_icon.setHorizontalAlignment(SwingConstants.CENTER);
				    lbl_icon.setVerticalAlignment(SwingConstants.CENTER);
					paneles[i].add(lbl_icon);	
					break;
				case "PUERTO":
				    for(int p = 0; p < Controller.PUERTOS; p++) { 
				        if (f == puerto[p].getFila() && c == puerto[p].getColumna()) {  
				            paneles[i].setLayout(new BorderLayout());    
				            JLabel num = new JLabel();
				            num.setHorizontalAlignment(SwingConstants.CENTER);
				            num.setVerticalAlignment(SwingConstants.CENTER);
				            num.setHorizontalTextPosition(SwingConstants.CENTER); //CENTRA EL TECTO
				            num.setVerticalTextPosition(SwingConstants.CENTER);
				            num.setFont(PonerFont.cargar(Font.BOLD, 50));
				            
				            if(puerto[p].isActivo() == false) { 
				            	num.setIcon(obtenerRENDER("src/co/edu/unbosque/images/puerto_x.png"));
				            }
				            else { 
				            	num.setIcon(obtenerRENDER("src/co/edu/unbosque/images/puerto.png"));
				            	num.setText(String.valueOf(puerto[p].getNum()));
				            	num.setForeground(Color.WHITE);
				            }
				            
				            paneles[i].add(num, BorderLayout.CENTER);
				            break;
				        }
				    }
				    break;
				case "ANTIVIRUS":
					icon = obtenerRENDER_GIF("src/co/edu/unbosque/images/antivirus_idle.gif");
					lbl_icon = new JLabel(icon);
					lbl_icon.setHorizontalAlignment(SwingConstants.CENTER);
				    lbl_icon.setVerticalAlignment(SwingConstants.CENTER);
					paneles[i].add(lbl_icon);	
					break;
				case "NODO":
					icon = obtenerRENDER("src/co/edu/unbosque/images/nodo.png");
					lbl_icon = new JLabel(icon);
					lbl_icon.setHorizontalAlignment(SwingConstants.CENTER);
				    lbl_icon.setVerticalAlignment(SwingConstants.CENTER);
					paneles[i].add(lbl_icon);
					break;
				case "FIREWALL":
					icon = obtenerRENDER("src/co/edu/unbosque/images/wall.png");
					lbl_icon = new JLabel(icon);
					lbl_icon.setHorizontalAlignment(SwingConstants.CENTER);
				    lbl_icon.setVerticalAlignment(SwingConstants.CENTER);
					paneles[i].add(lbl_icon);
					break;
				case "TRAMPA":
					icon = obtenerRENDER_GIF("src/co/edu/unbosque/images/trampa.gif");
					lbl_icon = new JLabel(icon);
					lbl_icon.setHorizontalAlignment(SwingConstants.CENTER);
				    lbl_icon.setVerticalAlignment(SwingConstants.CENTER);
					paneles[i].add(lbl_icon);
					break;
				case "SCANNER":
					icon = obtenerRENDER_GIF("src/co/edu/unbosque/images/scanner_idle.gif");
					lbl_icon = new JLabel(icon);
					lbl_icon.setHorizontalAlignment(SwingConstants.CENTER);
				    lbl_icon.setVerticalAlignment(SwingConstants.CENTER);
					paneles[i].add(lbl_icon);
					break;
				}
				if (casilla.isVisitada()) { //RASTRO
					paneles[i].setBackground(new Color(95, 102, 32)); 
				}
				
				// RANGO DE PELIGRO SCANNER.
				for (int a = 0; a < Controller.CANT_SCANNERS; a++) {
				    int filaANT = scanner[a].getFila();
				    int colANT = scanner[a].getColumna();
				    
				        
				        if ((filaANT + 1 == f) && (colANT == c)) { // ABAJO
				            paneles[i].setBackground(new Color(74, 0, 151));
				            break;
				        }
				        else if ((filaANT - 1 == f) && (colANT == c)) { // ARRIBA
				            paneles[i].setBackground(new Color(74, 0, 151));
				            break;
				        }
				        else if ((filaANT == f) && (colANT + 1 == c)) { // DERECHA
				            paneles[i].setBackground(new Color(74, 0, 151));
				            break;
				        }
				        else if ((filaANT == f) && (colANT - 1 == c)) { // IZQUIERDA
				            paneles[i].setBackground(new Color(74, 0, 151));
				            break;
				    }
				}
				// RANGO DE PELIGRO ANTIVIRUS.
				for (int a = 0; a < Controller.CANT_ANTIVIRUS; a++) {
				    int filaANT = antivirus[a].getFila();
				    int colANT = antivirus[a].getColumna();
				    
				        
				        if ((filaANT + 1 == f) && (colANT == c)) { // ABAJO DEL ANTIVIRUS
				        	paneles[i].setBackground(new Color(133,0,60));
				            break;
				        }
				        else if ((filaANT - 1 == f) && (colANT == c)) { // ARRIBA DEL ANTIVIRUS
				        	paneles[i].setBackground(new Color(133,0,60));
				            break;
				        }
				        else if ((filaANT == f) && (colANT + 1 == c)) { // DERECHA DEL ANTIVIRUS
				        	paneles[i].setBackground(new Color(133,0,60));
				            break;
				        }
				        else if ((filaANT == f) && (colANT - 1 == c)) { // IZQUIERDA DEL ANTIVIRUS
				        	paneles[i].setBackground(new Color(133,0,60));
				            break;
				    }
				}
				gridPanel.add(paneles[i]);
				i++; //Siguiente Panel
			}
			
		}
		gridPanel.revalidate();
		gridPanel.repaint();
	}
    /**
     * Carga y escala una imagen estatica (PNG) al tamaño proporcional de la casilla.
     * <p>Calcula el tamaño optimo segun las dimensiones actuales del grid y la cantidad
     * de filas y columnas, con un margen de 18 pixeles.
     *
     * @param ruta ruta del archivo de imagen a cargar
     * @return un {@code ImageIcon} con la imagen escalada al tamaño de la casilla
     */
	public ImageIcon obtenerRENDER(String ruta) { //Ayudado con IA
	    ImageIcon original = new ImageIcon(ruta);
	    
	    int anchoCasilla = gridPanel.getWidth() / Controller.COLUMNA;
	    int altoCasilla = gridPanel.getHeight() / Controller.FILA;
	    
	    if (anchoCasilla <= 0) anchoCasilla = 90;
	    if (altoCasilla <= 0) altoCasilla = 90;
	    
	    int tamanoIcono = Math.min(anchoCasilla, altoCasilla);
	    
	    int dimensionFinal = tamanoIcono - 18;
	    if (dimensionFinal < 5) dimensionFinal = 5;
	    
	    Image imgOriginal = original.getImage();
	    Image imgEscalada = imgOriginal.getScaledInstance(dimensionFinal, dimensionFinal, Image.SCALE_SMOOTH);
	    
	    return new ImageIcon(imgEscalada);
	}
	  /**
     * Carga y escala un GIF animado al tamaño proporcional de la casilla,
     * preservando la animacion original.
     * <p>No escala los frames directamente (lo cual rompe la animacion), sino que
     * crea un {@code ImageIcon} anonimo que sobreescribe {@code paintIcon} para
     * dibujar la imagen escalada en tiempo real, manteniendo el loop de animacion
     * activo gracias al {@code ImageObserver}.
     *
     * @param ruta ruta del archivo GIF animado a cargar
     * @return un {@code ImageIcon} con el GIF animado escalado correctamente
     */
	public ImageIcon obtenerRENDER_GIF(String ruta) { //Ayudado con IA
	    ImageIcon original = new ImageIcon(ruta);
	    
	    int anchoCasilla = gridPanel.getWidth() / Controller.COLUMNA;
	    int altoCasilla = gridPanel.getHeight() / Controller.FILA;
	    
	    if (anchoCasilla <= 0) anchoCasilla = 90;
	    if (altoCasilla <= 0) altoCasilla = 90;
	    
	    int tamanoIcono = Math.min(anchoCasilla, altoCasilla);
	    
	    int dimensionFinal = tamanoIcono - 18;
	    if (dimensionFinal < 5) dimensionFinal = 5;
	    
	    final int size = dimensionFinal;
	    
	    return new ImageIcon(original.getImage()) {
	        @Override
	        public void paintIcon(Component c, Graphics g, int x, int y) {
	            // El parámetro 'c' actúa como el ImageObserver, lo que mantiene el loop de la animación
	            g.drawImage(getImage(), x, y, size, size, c);
	        }
	        
	        @Override
	        public int getIconWidth() {
	            return size;
	        }
	        
	        @Override
	        public int getIconHeight() {
	            return size;
	        }
	    };
	}
	
	public static JPanel getPanel() {
		return gridPanel;
	}
}
