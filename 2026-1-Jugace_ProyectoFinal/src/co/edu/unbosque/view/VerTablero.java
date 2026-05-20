package co.edu.unbosque.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.*;
import co.edu.unbosque.controller.Controller;
import co.edu.unbosque.model.*;

public class VerTablero {
	
	private static JPanel gridPanel = new JPanel();
	private JPanel[] paneles = new JPanel[Controller.GRID_TOTAL];
	
	public VerTablero() {
		
		gridPanel.setLayout(new GridLayout(Controller.FILA, Controller.COLUMNA)); //Creacion GRID con datos de FILA y COLUMNA
		
		for(int i = 0; i < Controller.GRID_TOTAL; i++) { //Adicion de paneles al Grid segun el total de FILA X COLUMNA
			paneles[i] = new JPanel();
			
			paneles[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); 
			
			gridPanel.add(paneles[i]);
		}
	}
	
	
	public void ver(Tablero tablero, Puertos[] puerto, AntiVirus[] antivirus) {
		//Import de todo del Controller
		gridPanel.removeAll(); //Quita lo anterior del Grid
		
		
		int i = 0; //numero del Panel
		for (int f = 0; f < Controller.FILA; f++) {
			for (int c = 0; c < Controller.COLUMNA; c++) {
				
				Casilla casilla = tablero.setCasilla(f,c);
				String tp = casilla.getTipo();
				
				paneles[i] = new JPanel(); //Crea el panel
				paneles[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				paneles[i].setBackground(new Color(15, 0, 50));
				switch(tp) {
				
				case "PLAYER":
					ImageIcon icon = obtenerRENDER("src/co/edu/unbosque/images/player.png");
					JLabel lbl_icon = new JLabel(icon);
					paneles[i].add(lbl_icon);
					//paneles[i].setBackground(Color.CYAN);
					
					break;
				case "PAQUETE":
					paneles[i].setBackground(Color.GRAY);
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
				            num.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 60)); 
				            
				            if(puerto[p].isActivo() == false) { 
				            	num.setIcon(obtenerRENDER("src/co/edu/unbosque/images/puerto_x.png"));
				            }
				            else { 
				            	num.setIcon(obtenerRENDER("src/co/edu/unbosque/images/puerto.png"));
				            	num.setText(String.valueOf(puerto[p].getNum()));
				            	num.setForeground(Color.BLACK);
				            }
				            
				            paneles[i].add(num, BorderLayout.CENTER);
				            break;
				        }
				    }
				    break;
				case "ANTIVIRUS":
					for(int a = 0; a < Controller.CANT_ANTIVIRUS; a++) {
						icon = obtenerRENDER("src/co/edu/unbosque/images/virus.png");
						lbl_icon = new JLabel(icon);
						paneles[i].add(lbl_icon);
						//paneles[i].setBackground(new Color(255, 0, 230));
				}
					break;
				case "NODO":
					
					for(int a = 0; a < Controller.CANT_NODOS; a++) {
						icon = obtenerRENDER("src/co/edu/unbosque/images/nodo.png");
						lbl_icon = new JLabel(icon);
						paneles[i].add(lbl_icon);
					    break;
						
				}
					break;
				case "FIREWALL":
					icon = obtenerRENDER("src/co/edu/unbosque/images/wall.png");
					lbl_icon = new JLabel(icon);
					paneles[i].add(lbl_icon);
					break;
				case "TRAMPA":
					icon = obtenerRENDER("src/co/edu/unbosque/images/wall_path.png");
					lbl_icon = new JLabel(icon);
					paneles[i].add(lbl_icon);
					break;
				case "SCANNER":
					
					break;
				}
				
				for (int a = 0; a < Controller.CANT_ANTIVIRUS; a++) {
				    int filaANT = antivirus[a].getFila();
				    int colANT = antivirus[a].getColumna();
				    
				        
				        if ((filaANT + 1 == f) && (colANT == c)) { // ABAJO DEL ANTIVIRUS
				            paneles[i].setBackground(new Color(105, 0, 190));
				            break;
				        }
				        else if ((filaANT - 1 == f) && (colANT == c)) { // ARRIBA DEL ANTIVIRUS
				            paneles[i].setBackground(new Color(105, 0, 190));
				            break;
				        }
				        else if ((filaANT == f) && (colANT + 1 == c)) { // DERECHA DEL ANTIVIRUS
				            paneles[i].setBackground(new Color(105, 0, 190));
				            break;
				        }
				        else if ((filaANT == f) && (colANT - 1 == c)) { // IZQUIERDA DEL ANTIVIRUS
				            paneles[i].setBackground(new Color(105, 0, 190));
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
	
	public ImageIcon obtenerRENDER(String ruta) {
	    ImageIcon original = new ImageIcon(ruta);
	    
	    // Calcula el tamaño de cada casilla dividiendo el panel general
	    int anchoCasilla = gridPanel.getWidth() / Controller.COLUMNA;
	    int altoCasilla = gridPanel.getHeight() / Controller.FILA;
	    
	    // SOLUCIÓN: Subimos el respaldo a 90 para que al principio NO se vean chiquitos
	    if (anchoCasilla <= 0) anchoCasilla = 90;
	    if (altoCasilla <= 0) altoCasilla = 90;
	    
	    // Restamos unos píxeles para dejar margen y que no toque los bordes negros
	    int nuevoAncho = anchoCasilla - 8;
	    int nuevoAlto = altoCasilla - 8;
	    
	    // Evita que los valores sean negativos en grids extremadamente masivos
	    if (nuevoAncho < 5) nuevoAncho = 5;
	    if (nuevoAlto < 5) nuevoAlto = 5;
	    
	    Image imgOriginal = original.getImage();
	    Image imgEscalada = imgOriginal.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
	    
	    return new ImageIcon(imgEscalada);
	}
	

	public static JPanel getPanel() {
		return gridPanel;
	}
}
