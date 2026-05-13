package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class MovimientoPanel {
	
	private JPanel movPanel = new JPanel();
	private JButton btnARRIBA = new JButton("ARRIBA");
	private JButton btnABAJO = new JButton("ABAJO");
	private JButton btnIZQUIERDA = new JButton("IZQUIERDA");
	private JButton btnDERECHA = new JButton("DERECHA");
	
	public MovimientoPanel() {
		movPanel.setLayout(new FlowLayout());
		movPanel.add(btnARRIBA);
		movPanel.add(btnABAJO);
		movPanel.add(btnDERECHA);
		movPanel.add(btnIZQUIERDA);
	}

	public JPanel getPanel() {
		return movPanel;
	}

	public JButton getBtnARRIBA() {
		return btnARRIBA;
	}

	public JButton getBtnABAJO() {
		return btnABAJO;
	}

	public JButton getBtnIZQUIERDA() {
		return btnIZQUIERDA;
	}

	public JButton getBtnDERECHA() {
		return btnDERECHA;
	}
	
	
	

}
