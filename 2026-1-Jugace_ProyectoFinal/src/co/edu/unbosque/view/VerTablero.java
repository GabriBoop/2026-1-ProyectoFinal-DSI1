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
	
	
	public void ver(int FILAP, int COLUMNAP,int FILAPP, int COLUMNAPP, Puertos[] puerto, AntiVirus[] antivirus) {
		//Import de todo del Controller
		gridPanel.removeAll(); //Quita lo anterior del Grid
		
		int i = 0; //numero del Panel
		for (int f = 0; f < Controller.FILA; f++) {
			for (int c = 0; c < Controller.COLUMNA; c++) {
				paneles[i] = new JPanel(); //Crea el panel
				paneles[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				
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
				for(int a = 0; a < Controller.CANT_ANTIVIRUS; a++) {
					if((f == antivirus[a].getFila()) && (c == antivirus[a].getColumna())) {
						paneles[i].setBackground(Color.PINK);
					}
				}
				
				if (f == FILAP && c == COLUMNAP) { //JUGADOR
					paneles[i].setBackground(Color.CYAN);
				}
				else if (f == FILAPP && c == COLUMNAPP) { //CAJA ESA FEA QUE VOY A BORRAR PERO SIGUE AHI 
					paneles[i].setBackground(Color.GRAY);
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
