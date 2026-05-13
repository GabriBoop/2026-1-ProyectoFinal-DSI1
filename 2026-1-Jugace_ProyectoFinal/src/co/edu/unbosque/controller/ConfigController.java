package co.edu.unbosque.controller;
import co.edu.unbosque.view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigController implements ActionListener{

	private static ConfigPanel configPanel;
	
	public static void run() {
		configPanel = new ConfigPanel();
		
		ConfigController lector = new ConfigController();
		lector.darLectores();
	    
		Controller.vnt.cambiarPanel(ConfigPanel.getPanel());
	}
	
	public void darLectores() {
	configPanel.getBotOK().addActionListener(this);
	configPanel.getBotOK().setActionCommand("BotOK");
	}

	
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "BotOK":
			
			Controller.FILA = (int) configPanel.getSpinFILAS().getValue();
			Controller.COLUMNA = (int) configPanel.getSpinCOLUMNAS().getValue();
			Controller.GRID_TOTAL = Controller.FILA * Controller.COLUMNA;
			
			Controller.actualizarTablero();
			Controller.mostrarTablero();;
			break;
		}
	}
}
