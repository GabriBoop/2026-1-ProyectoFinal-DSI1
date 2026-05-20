package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MovimientosPanel extends JPanel {
	
	private JPanel movPanel = new JPanel(); 
	private JLabel txt = new JLabel();
	private JLabel prt = new JLabel();

	
	public MovimientosPanel() {
		movPanel.setLayout(new FlowLayout());
        txt.setText("MOVIMIENTOS: 0"); //Label donde salen los movimientos totales y restantes, se acomoda en Controller y Movimiento Panel
        prt.setText("PUERTOS"); //Label donde salen los Puertos totales y restantes, se acomoda en Controller y Movimiento Panel
        txt.setForeground(Color.WHITE);
        prt.setForeground(Color.WHITE);
        movPanel.add(txt); 
        movPanel.add(prt);
        
        setLayout(new BorderLayout());
        movPanel.setBackground(new Color(105, 0, 190));
        add(movPanel, BorderLayout.CENTER);
	}

	public JPanel getPanel() {
		return movPanel;
	}

	public JLabel getTxt() {
		return txt;
	}

	public void setTxt(JLabel txt) {
		this.txt = txt;
	}

	public JLabel getPrt() {
		return prt;
	}

	public void setPrt(JLabel prt) {
		this.prt = prt;
	}
	
	
	
	

}
