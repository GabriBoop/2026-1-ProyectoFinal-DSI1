package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class GameOver extends JPanel{
	
	private JPanel gameoverPanel = new JPanel();
	private JButton btnRestart = new JButton("Reiniciar");
	
	public GameOver(){
		gameoverPanel.setBackground(Color.RED);
		gameoverPanel.setLayout(new BorderLayout());
		
		JLabel txt = new JLabel("PERDISTE PERDEDOR!!!1!", SwingConstants.CENTER); //Creacion de Label donde el texto siempre salga en el centro
		txt.setForeground(Color.WHITE);
		txt.setFont(PonerFont.cargar(Font.BOLD, 50));
		gameoverPanel.add(btnRestart, BorderLayout.SOUTH);
		gameoverPanel.add(txt, BorderLayout.CENTER);
	}

	public JPanel getPanel() {
		return gameoverPanel;
	}

	public JButton getBtnRestart() {
		return btnRestart;
	}

	public void setBtnRestart(JButton btnRestart) {
		this.btnRestart = btnRestart;
	}
}
