package co.edu.unbosque.controller;

import co.edu.unbosque.model.Player;
import co.edu.unbosque.view.VerTablero;
import co.edu.unbosque.view.MovimientoPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MovimientoController implements ActionListener{
	private Player jugador = new Player(0,0);
	
	private MovimientoPanel movimientoPanel = new MovimientoPanel();
	private VerTablero verTablero = new VerTablero();
	
	public MovimientoController(Player jugador,MovimientoPanel movimientoPanel,VerTablero verTablero ) {
		this.jugador = jugador;
		this.movimientoPanel = movimientoPanel;
		this.verTablero = verTablero;
	}
	
	public void darLectores() {
		movimientoPanel.getBtnABAJO().addActionListener(this);
		movimientoPanel.getBtnABAJO().setActionCommand("ABAJO");
		movimientoPanel.getBtnARRIBA().addActionListener(this);
		movimientoPanel.getBtnARRIBA().setActionCommand("ARRIBA");
		movimientoPanel.getBtnDERECHA().addActionListener(this);
		movimientoPanel.getBtnDERECHA().setActionCommand("DERECHA");
		movimientoPanel.getBtnIZQUIERDA().addActionListener(this);
		movimientoPanel.getBtnIZQUIERDA().setActionCommand("IZQUIERDA");
		}

	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "ARRIBA":
			if(jugador.getFila() > 0) jugador.setFila(jugador.getFila() - 1);
			break;
		case "ABAJO":
			if(jugador.getFila() < Controller.FILA - 1) jugador.setFila(jugador.getFila() + 1);
			break;	
		case "DERECHA":
			if(jugador.getColumna() < Controller.COLUMNA - 1) jugador.setColumna(jugador.getColumna() + 1);
			break;
		case "IZQUIERDA":
			if(jugador.getColumna() > 0) jugador.setColumna(jugador.getColumna() - 1);
			break;	
		}
		
		verTablero.verPlayer(jugador.getFila(), jugador.getColumna());
	
	}
	
}
