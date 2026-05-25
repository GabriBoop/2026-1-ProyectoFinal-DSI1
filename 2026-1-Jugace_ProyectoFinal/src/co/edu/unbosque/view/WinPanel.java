package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
/**
 * Panel de pantalla de victoria cuando el jugador completa la partida de forma exitosa.
 * <p>Muestra el mensaje "¡GANASTE!" junto a una imagen de victoria
 * y un boton para regresar al menu principal.
 *
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */
public class WinPanel {
	private JPanel winPanel = new JPanel();
	private JButton btnRestart = new JButton("");
	private JLabel lbl = new JLabel();
	/**
     * Constructor del panel de victoria.
     * <p>Configura el fondo oscuro, el layout,
     * la imagen del jugador victorioso y el boton de retorno al menu.
     * El titulo "¡GANASTE!" se muestra en amarillo en la zona superior.
     */
	public WinPanel(){
		winPanel.setBackground(new Color(15,0,50));
		winPanel.setLayout(new BorderLayout());
		winPanel.setBorder(BorderFactory.createEmptyBorder(30, 10,30, 10));
		
		ImageIcon imgV = new ImageIcon("src/co/edu/unbosque/images/btn_volver.png"); // Cambia por el nombre real de tu archivo
		btnRestart.setIcon(imgV);  
		btnRestart.setText("");
		btnRestart.setOpaque(false);
		btnRestart.setBorderPainted(false);
	    btnRestart.setContentAreaFilled(false);
	    
	    ImageIcon img = new ImageIcon("src/co/edu/unbosque/images/icon_playerWIN.png");
	    lbl.setIcon(img);
	    lbl.setHorizontalAlignment(SwingConstants.CENTER);
	    
		JLabel txt = new JLabel("¡GANASTE!", SwingConstants.CENTER);
		txt.setFont(PonerFont.cargar(Font.BOLD, 100));//Creacion de Label donde el texto siempre salga en el centro
		txt.setForeground(new Color(217,255,0));
		
		winPanel.add(btnRestart, BorderLayout.SOUTH);
		winPanel.add(txt, BorderLayout.NORTH);
		winPanel.add(lbl,BorderLayout.CENTER);
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