package co.edu.unbosque.view;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

public class MainFrame extends JFrame{ 
	
	
	private static final int ANCHO = 800;
	private static final int ALTO = 600;
	private static final int GRID = 6;
	private static JPanel[] paneles = new JPanel[GRID*GRID];
	
	public MainFrame() {
	
		setBounds(0, 0, ANCHO, ALTO);
		setTitle("--GAME--");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(GRID, GRID));
		setLocationRelativeTo(null);
		
		insertPanel_PRUEBA();
	}
	
	public void insertPanel_PRUEBA() {
		for(int i = 0; i < GRID*GRID; i++) {
			paneles[i] = new JPanel();
			
			paneles[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			
			add(paneles[i]);
		}
		
	}
}
