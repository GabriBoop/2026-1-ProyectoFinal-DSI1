package co.edu.unbosque.controller;
import co.edu.unbosque.view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ConfigController implements ActionListener{

	private static ConfigPanel configPanel;
	
	public static void run() {
		ReproducirAudio.reproducir("/co/edu/unbosque/sound/solo.wav");
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
			else {
				Controller.INVERSO = false;
			}
			int s = (int) configPanel.getSelectdiff().getSelectedIndex();
			if(s == 0) { //Facil
				Controller.CANT_ANTIVIRUS = 2;
				Controller.CANT_NODOS = 4;
				Controller.CANT_SCANNERS = 2;
				Controller.CANT_FIREWALLS = (int) (Controller.GRID_TOTAL * 0.06);
			}
			if(s == 1) { //NORMAL
				Controller.CANT_ANTIVIRUS = 3;
				Controller.CANT_NODOS = 3;
				Controller.CANT_SCANNERS = 3;
				Controller.CANT_FIREWALLS = (int) (Controller.GRID_TOTAL * 0.10);
			}
			if(s == 2) { //MODEDARO
				Controller.CANT_ANTIVIRUS = 4;
				Controller.CANT_NODOS = 2;
				Controller.CANT_SCANNERS = 4;
				Controller.CANT_FIREWALLS = (int) (Controller.GRID_TOTAL * 0.12);
			}
			if(s == 3) { //DIFICL
				Controller.CANT_ANTIVIRUS = 6;
				Controller.CANT_NODOS = 1;
				Controller.CANT_SCANNERS = 5;
				Controller.CANT_FIREWALLS = (int) (Controller.GRID_TOTAL * 0.18);
			}
			if(s == 4) { //EXPERTO
				Controller.CANT_ANTIVIRUS = 9;
				Controller.CANT_NODOS = 0;
				Controller.CANT_SCANNERS = 9;
				Controller.CANT_FIREWALLS = (int) (Controller.GRID_TOTAL * 0.20);
			}
			String diffTXT = configPanel.getSelectdiff().getSelectedItem().toString();
			Controller.vnt.setTitle("CYBER INFILTRATOR, DIFICULTAD: "+diffTXT);
			ReproducirAudio.detener();
			Controller.actualizarTablero(); //Actualizar variables restantes
			Controller.mostrarTablero();; //Volver al controller
			
			break;
		}
	}
}
