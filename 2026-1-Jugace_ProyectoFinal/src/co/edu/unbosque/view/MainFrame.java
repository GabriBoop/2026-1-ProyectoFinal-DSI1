package co.edu.unbosque.view;
import co.edu.unbosque.controller.Controller;


import java.awt.BorderLayout;
import java.awt.Color;


import javax.swing.*;

public class MainFrame extends JFrame{ 
	
	private static final int ANCHO = 800;
	private static final int ALTO = 600;
	
	public MainFrame() {
	
		setBounds(0, 0, ANCHO, ALTO);
		setTitle("--GAME--");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
	}
	
	 public void cambiarPanel(JPanel panel) {
	        getContentPane().removeAll();
	        getContentPane().add(panel, BorderLayout.CENTER);
	        revalidate();
	        repaint();
	    }
	
	
	
		
}

