package co.edu.unbosque.view;
import java.awt.Color;
import java.awt.GridLayout;
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
				
				switch(tp) {
				
				case "PLAYER":
					paneles[i].setBackground(Color.CYAN);
					break;
				case "PAQUETE":
					paneles[i].setBackground(Color.GRAY);
					break;
				case "PUERTO":
					for(int p = 0; p < Controller.PUERTOS; p++) { //PUERTOS: VISUAL
						JLabel lbl = new JLabel();
						
						if (f == puerto[p].getFila() && c == puerto[p].getColumna()) {  //Busca si hay un puerto en el panel
							
							if(puerto[p].isActivo() == false) { //Si esta desactivado
								paneles[i].setBackground(Color.RED);
								lbl.setText("X");
							}
							else { //So esta activado
								paneles[i].setBackground(Color.GREEN);
								lbl.setText(String.valueOf(puerto[p].getNum()));
							}
							 paneles[i].add(lbl); //Añade el txt al panel
						}
					}
					break;
				case "ANTIVIRUS":
					for(int a = 0; a < Controller.CANT_ANTIVIRUS; a++) {
						paneles[i].setBackground(Color.PINK);
				}
					break;
				case "NODO":
					for(int a = 0; a < Controller.CANT_NODOS; a++) {
						paneles[i].setBackground(Color.BLUE); 
					    
					    JLabel lbl = new JLabel("NODO");
					    lbl.setForeground(Color.WHITE);
					    
					    paneles[i].add(lbl);
					    break;
						
				}
					break;
				case "FIREWALL":
					
					break;
				case "SCANNER":
					
					break;
				}
				
				for (int a = 0; a < Controller.CANT_ANTIVIRUS; a++) {
				    int filaANT = antivirus[a].getFila();
				    int colANT = antivirus[a].getColumna();
				    
				    // Solo pinta si esta vacia
				    if (tablero.setCasilla(f, c).getTipo().equals("NA")) {
				        
				        if ((filaANT + 1 == f) && (colANT == c)) { // ABAJO DEL ANTIVIRUS
				            paneles[i].setBackground(new Color(255, 210, 210));
				            break;
				        }
				        else if ((filaANT - 1 == f) && (colANT == c)) { // ARRIBA DEL ANTIVIRUS
				            paneles[i].setBackground(new Color(255, 210, 210));
				            break;
				        }
				        else if ((filaANT == f) && (colANT + 1 == c)) { // DERECHA DEL ANTIVIRUS
				            paneles[i].setBackground(new Color(255, 210, 210));
				            break;
				        }
				        else if ((filaANT == f) && (colANT - 1 == c)) { // IZQUIERDA DEL ANTIVIRUS
				            paneles[i].setBackground(new Color(255, 210, 210));
				            break;
				        }
				    }
				}
				gridPanel.add(paneles[i]);
				i++; //Siguiente Panel
			}
			
		}
		gridPanel.revalidate();
		gridPanel.repaint();
		
	}
	

	public static JPanel getPanel() {
		return gridPanel;
	}
}
