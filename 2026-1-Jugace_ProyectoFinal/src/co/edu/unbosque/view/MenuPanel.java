package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import co.edu.unbosque.controller.Controller;

public class MenuPanel {

    private JPanel menuPanel = new JPanel();
    
    private JButton btnPredeterminado = new JButton("Juego Predeterminado");
    private JButton btnPersonalizado   = new JButton("Juego Personalizado");
    private JButton btnTutorial        = new JButton("Tutorial");
    private JButton btnSalir           = new JButton("Salir");

    public MenuPanel() {
        menuPanel.setLayout(new GridBagLayout());
        menuPanel.setBackground(new Color(15, 0, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Logo (igual que en ConfigPanel)
        ImageIcon icon = new ImageIcon("src/co/edu/unbosque/images/logo.png");
        JLabel lbl_logo = new JLabel(icon);
        gbc.gridy = 0;
        menuPanel.add(lbl_logo, gbc);

        gbc.gridy = 1;
        menuPanel.add(Box.createVerticalStrut(40), gbc);

        // Estilo de botones
        styleButton(btnPredeterminado,"src/co/edu/unbosque/images/btn_jgpdrt.png");
        styleButton(btnPersonalizado,"src/co/edu/unbosque/images/btn_jgper.png");
        styleButton(btnTutorial,"src/co/edu/unbosque/images/btn_tutorial.png");
        styleButton(btnSalir,"src/co/edu/unbosque/images/btn_salir.png");

        gbc.gridy = 2;
        menuPanel.add(btnPredeterminado, gbc);

        gbc.gridy = 3;
        menuPanel.add(btnPersonalizado, gbc);

        gbc.gridy = 4;
        menuPanel.add(btnTutorial, gbc);

        gbc.gridy = 5;
        menuPanel.add(btnSalir, gbc);

        // Acciones
        btnPersonalizado.addActionListener(e -> {
        	ReproducirAudio.reproducirSFX(Controller.click);
            Controller.vnt.cambiarPanel(ConfigPanel.getPanel());
        });

        btnPredeterminado.addActionListener(e -> {
            // Inicia juego con valores por defecto
        	ReproducirAudio.reproducirSFX(Controller.click);
            Controller.FILA = 6;
            Controller.COLUMNA = 12;
            Controller.GRID_TOTAL = Controller.FILA*Controller.COLUMNA;
            Controller.PUERTOS = 5;
            Controller.CANT_NODOS = 3;
            Controller.CANT_ANTIVIRUS = 2;
            Controller.CANT_SCANNERS = 4;
            Controller.CANT_FIREWALLS = (int) (Controller.GRID_TOTAL * 0.25);
            Controller.INVERSO = false;
            Controller.musica ="/co/edu/unbosque/sound/diddy.wav";

            Controller.actualizarTablero();
            Controller.mostrarTablero();
        });

        btnTutorial.addActionListener(e -> {
        	ReproducirAudio.reproducirSFX(Controller.click);
            JOptionPane.showMessageDialog(null, "Tutorial próximamente...");
        });

        btnSalir.addActionListener(e -> {
        	ReproducirAudio.reproducirSFX(Controller.click);
        	ImageIcon icoexit = (new ImageIcon("src/co/edu/unbosque/images/icon_exit.png"));
            int resp = JOptionPane.showConfirmDialog(null, 
                "<html>¿Estas seguro de salir del juego?<br>"
                +"<html><center>( A el... no le gustaria :( )<center><br>", "Salir",0, 
                JOptionPane.YES_NO_OPTION,icoexit);
            if (resp == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    private void styleButton(JButton btn, String icon) {
        ImageIcon img = new ImageIcon(icon); // Ahorarr lineas
	    btn.setIcon(img);  
	    btn.setText(""); //Boton
	    btn.setOpaque(false);
	    btn.setBorderPainted(false);
	    btn.setContentAreaFilled(false);
    }

    public JPanel getPanel() {
        return menuPanel;
    }
}