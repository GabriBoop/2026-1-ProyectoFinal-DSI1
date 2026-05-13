package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import co.edu.unbosque.controller.Controller;

public class VerTablero {
	
	private static JPanel gridPanel = new JPanel();
	private JPanel[] paneles = new JPanel[Controller.GRID_TOTAL];
	
	public VerTablero() {
		
		gridPanel.setLayout(new GridLayout(Controller.FILA, Controller.COLUMNA));
		
		for(int i = 0; i < Controller.GRID_TOTAL; i++) {
			paneles[i] = new JPanel();
			
			paneles[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			
			gridPanel.add(paneles[i]);
		}
	}
	
	public void verNumeros() {
		
		gridPanel.removeAll();
		
		   int i = 0;
	        for (int f = 0; f < Controller.FILA; f++) {
	            for (int c = 0; c < Controller.COLUMNA; c++) {
	                paneles[i] = new JPanel();
	                paneles[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

	                JLabel lbl = new JLabel("[" + (f+1) + "," + (c+1) + "]");
	                paneles[i].add(lbl);

	                gridPanel.add(paneles[i]);
	                i++;
	            }
	        }
	}
	
	public void verPlayer(int FILAP, int COLUMNAP) {
		
		gridPanel.removeAll();
		
		int i = 0;
		for (int f = 0; f < Controller.FILA; f++) {
			for (int c = 0; c < Controller.COLUMNA; c++) {
				paneles[i] = new JPanel();
				paneles[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				 
				if (f == FILAP && c == COLUMNAP) {
					paneles[i].setBackground(Color.CYAN);
				}
				gridPanel.add(paneles[i]);
				i++;
			}
			gridPanel.revalidate();
			gridPanel.repaint();
		}
	}
	

	public static JPanel getPanel() {
		return gridPanel;
	}
}
