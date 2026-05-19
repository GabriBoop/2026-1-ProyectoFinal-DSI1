package co.edu.unbosque.view;
import co.edu.unbosque.controller.Controller;


import java.awt.BorderLayout;
import java.awt.Color;


import javax.swing.*;

public class MainFrame extends JFrame{ 
	
	private static final int ANCHO = 800; //Dimensiones de Pantalla
	private static final int ALTO = 600;
	
	public MainFrame() {
	
		setBounds(0, 0, ANCHO, ALTO);
		setTitle("--GAME--");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null); // Asegura que siempre salga en el cemtro de la pantalla del computador
		
	}
	
	 public void cambiarPanel(JPanel panel) { //Cambio de pameñ (EJ: Config a Juego)
	        getContentPane().removeAll();
	        getContentPane().add(panel, BorderLayout.CENTER);
	        revalidate(); //Revalida todo lo del Frame y no se gemerem errpres visuales
	        repaint();
	    }
	
	 public void setTitulo(String txt) {
		    this.setTitle(txt); 
		}
	
		
}

