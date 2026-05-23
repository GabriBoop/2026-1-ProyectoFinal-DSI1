package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GameOver extends JPanel{
	
	private JPanel gameoverPanel = new JPanel();
	private JButton btnRestart = new JButton();
	private JLabel lbl = new JLabel();
	
	public GameOver(){
		gameoverPanel.setBackground(new Color(133,0,60));
		gameoverPanel.setLayout(new BorderLayout());
		gameoverPanel.setBorder(BorderFactory.createEmptyBorder(30, 10,30, 10));	
		
		ImageIcon imgV = new ImageIcon("src/co/edu/unbosque/images/btn_volver.png"); // Cambia por el nombre real de tu archivo
		btnRestart.setIcon(imgV);  
		btnRestart.setText("");
		btnRestart.setOpaque(false);
		btnRestart.setBorderPainted(false);
	    btnRestart.setContentAreaFilled(false);
		
	    ImageIcon img = new ImageIcon("src/co/edu/unbosque/images/icon_playersigilo.png");
	    lbl.setIcon(img);
	    lbl.setHorizontalAlignment(SwingConstants.CENTER);
	    
		JLabel txt = new JLabel("¡PERDISTE!", SwingConstants.CENTER); //Creacion de Label donde el texto siempre salga en el centro
		txt.setForeground(Color.WHITE);
		txt.setFont(PonerFont.cargar(Font.BOLD, 100));
		
		gameoverPanel.add(btnRestart, BorderLayout.SOUTH);
		gameoverPanel.add(lbl);
		gameoverPanel.add(txt, BorderLayout.NORTH);
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
