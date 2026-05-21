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
		
		for(int i = 0; i < Controller.GRID_TOTAL; i++) {//Adicion de paneles al Grid segun el total de FILA X COLUMNA
			
			paneles[i] = new JPanel();
			paneles[i] = new JPanel(new BorderLayout());
			paneles[i].setBorder(BorderFactory.createLineBorder(new Color(25,0,80), 1)); 
			
			gridPanel.add(paneles[i]);
		}
	}
	
	
	public void ver(Tablero tablero, Puertos[] puerto, AntiVirus[] antivirus, Scanner[] scanner, Player jugador) {
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
					ImageIcon icon = obtenerRENDER("src/co/edu/unbosque/images/player.png");
					if(jugador.isSigilo()) icon = obtenerRENDER("src/co/edu/unbosque/images/player_s.png");
					JLabel lbl_icon = new JLabel(icon);
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
					icon = obtenerRENDER("src/co/edu/unbosque/images/virus.png");
					lbl_icon = new JLabel(icon);
					lbl_icon.setHorizontalAlignment(SwingConstants.CENTER);
				    lbl_icon.setVerticalAlignment(SwingConstants.CENTER);
					paneles[i].add(lbl_icon);	
					break;
				case "NODO":
					icon = obtenerRENDER("src/co/edu/unbosque/images/nodo.png");
					lbl_icon = new JLabel(icon);
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
					icon = obtenerRENDER("src/co/edu/unbosque/images/wall_path.png");
					lbl_icon = new JLabel(icon);
					lbl_icon.setHorizontalAlignment(SwingConstants.CENTER);
				    lbl_icon.setVerticalAlignment(SwingConstants.CENTER);
					paneles[i].add(lbl_icon);
					break;
				case "SCANNER":
					icon = obtenerRENDER("src/co/edu/unbosque/images/scanner.png");
					lbl_icon = new JLabel(icon);
					lbl_icon.setHorizontalAlignment(SwingConstants.CENTER);
				    lbl_icon.setVerticalAlignment(SwingConstants.CENTER);
					paneles[i].add(lbl_icon);
					break;
				}
				if (casilla.isVisitada()) { //RASTRO
					paneles[i].setBackground(new Color(10, 0, 125)); 
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
				gridPanel.add(paneles[i]);
				i++; //Siguiente Panel
			}
			
		}
		gridPanel.revalidate();
		gridPanel.repaint();
	}
	
	public ImageIcon obtenerRENDER(String ruta) {
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
	
	public static JPanel getPanel() {
		return gridPanel;
	}
}
