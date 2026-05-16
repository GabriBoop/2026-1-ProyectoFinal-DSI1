package co.edu.unbosque.controller;
import co.edu.unbosque.view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigController implements ActionListener{

	private static ConfigPanel configPanel;
	
	public static void run() {
		configPanel = new ConfigPanel();
		
		ConfigController lector = new ConfigController();
		lector.darLectores(); //Se gemera y se llama asi mismo, esto lo hace por que si ponemos Static a los lectores no funcionan...
	    
		Controller.vnt.cambiarPanel(ConfigPanel.getPanel());
	}
	
	public void darLectores() {
	configPanel.getBotOK().addActionListener(this); //Botomes y Check
	configPanel.getBotOK().setActionCommand("BotOK");
	configPanel.getChkINVERSO().addActionListener(this);
    configPanel.getChkINVERSO().setActionCommand("Check");
	}		

	
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "BotOK":
			Controller.FILA = (int) configPanel.getSpinFILAS().getValue(); //Actualizacion de todas la variables
			Controller.COLUMNA = (int) configPanel.getSpinCOLUMNAS().getValue();
			Controller.GRID_TOTAL = Controller.FILA * Controller.COLUMNA;
			Controller.PUERTOS = (int) configPanel.getSpinPUERTOS().getValue();
			
			if(configPanel.getChkINVERSO().isSelected()) {  //asegura que se active eñ Inverso
				Controller.INVERSO = true;
			}
			int s = (int) configPanel.getSelectdiff().getSelectedIndex();
			if(s == 0) Controller.CANT_ANTIVIRUS = 2;
			if(s == 1) Controller.CANT_ANTIVIRUS = 3;
			if(s == 2) Controller.CANT_ANTIVIRUS = 4;
			if(s == 3) Controller.CANT_ANTIVIRUS = 5;
			
			Controller.actualizarTablero(); //Actualizar variables restantes
			Controller.mostrarTablero();; //Volver al controller
			break;
		}
	}
}
