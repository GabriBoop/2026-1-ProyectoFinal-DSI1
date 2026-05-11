package co.edu.unbosque.controller;
import co.edu.unbosque.view.MainFrame;

public class Controller {
	
	private static MainFrame vnt;
	
	public Controller(){
		vnt = new MainFrame();
	}
	
	public void run() {
		vnt.setVisible(true);
	}

}
