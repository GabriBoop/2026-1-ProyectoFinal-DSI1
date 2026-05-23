package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class WinPanel {
	private JPanel winPanel = new JPanel();
	private JButton btnRestart = new JButton("Reiniciar");
	
	public WinPanel(){
		winPanel.setBackground(Color.GREEN);
		winPanel.setLayout(new BorderLayout());
		
		JLabel txt = new JLabel("GANASTE GANADOR!!", SwingConstants.CENTER);
		txt.setFont(PonerFont.cargar(Font.BOLD, 50));//Creacion de Label donde el texto siempre salga en el centro
		winPanel.add(btnRestart, BorderLayout.SOUTH);
		winPanel.add(txt, BorderLayout.CENTER);
	}

	public JPanel getPanel() {
		return winPanel;
	}

	public JButton getBtnRestart() {
		return btnRestart;
	}

	public void setBtnRestart(JButton btnRestart) {
		this.btnRestart = btnRestart;
	}
}