package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import co.edu.unbosque.controller.Controller;
import co.edu.unbosque.controller.MenuController;
/**
 * Panel de tutorial interactivo del juego.
 * <p>Permite al jugador explorar informacion sobre cada elemento del tablero
 * (jugador, antivirus, scanner, paquete, puerto, nodo, firewall, entre otros)
 * mediante botones de seleccion. Al presionar cada boton se actualiza el icono,
 *
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */
public class TutorialPanel extends JPanel {
	

    private JPanel botonPanel = new JPanel();
    private JPanel generalPanel = new JPanel(); 
    
    //Info y cosas que cambian
    private JLabel icon = new JLabel();
    private JLabel name = new JLabel();
    private JLabel info = new JLabel();
    
    // Botones
    private JButton ver_player = new JButton("PLAYER");
    private JButton ver_antivirus = new JButton("ANTIVIRUS");
    private JButton ver_scanner = new JButton("SCANNER");
    private JButton ver_paquete = new JButton("PAQUETE");
    private JButton ver_puerto = new JButton("PUERTO");
    private JButton ver_nodo = new JButton("NODO");
    private JButton ver_firewall = new JButton("FIREWALL");
    private JButton ver_mas = new JButton("MAS");
    private JButton btnvolver = new JButton("VOLVER");

    /**
     * Constructor del panel de tutorial.
     * <p>Inicializa el layout general con {@code BorderLayout}, crea y estiliza
     * cada boton.
     */
    public TutorialPanel() {
        
        setBackground(new Color(25, 0, 80));
        setLayout(new BorderLayout());

        botonPanel.setLayout(new GridLayout(1, 0, 0, 10));
        botonPanel.setBackground(new Color(25, 0, 80));
        botonPanel.setPreferredSize(new Dimension(150,150));
        botonPanel.setBorder(BorderFactory.createEmptyBorder(30, 10,30, 10));
        
        hacerBoton(ver_player, "PLAYER");
        hacerBoton(ver_antivirus, "ANTIVIRUS");
        hacerBoton(ver_scanner, "SCANNER");
        hacerBoton(ver_paquete, "PAQUETE");
        hacerBoton(ver_puerto, "PUERTO");
        hacerBoton(ver_nodo, "NODO");
        hacerBoton(ver_firewall, "FIREWALL");
        hacerBoton(ver_mas, "MAS");
        hacerBoton(btnvolver, "VOLVER");

        botonPanel.add(ver_player);
        botonPanel.add(ver_antivirus);
        botonPanel.add(ver_scanner);
        botonPanel.add(ver_paquete);
        botonPanel.add(ver_puerto);
        botonPanel.add(ver_nodo);
        botonPanel.add(ver_firewall);
        botonPanel.add(ver_mas);
        botonPanel.add(btnvolver);

        generalPanel.setLayout(new GridBagLayout());
        generalPanel.setBackground(new Color(15, 0, 50));

        name.setForeground(new Color(0, 255, 255));
        name.setFont(PonerFont.cargar(Font.BOLD, 28));
        name.setHorizontalAlignment(SwingConstants.CENTER);

        info.setForeground(Color.WHITE);
        info.setFont(PonerFont.cargar(Font.PLAIN, 16));
        info.setPreferredSize(new Dimension(400, 150));
        info.setVerticalAlignment(SwingConstants.TOP);
        
        icon.setHorizontalAlignment(SwingConstants.CENTER);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        //(arriba, izquierda, abajo, derecha)
        gbc.insets = new Insets(20, 20, 10, 20);
        gbc.gridy = 0;
        generalPanel.add(name, gbc);

        gbc.insets = new Insets(20, 20, 10, 20);
        gbc.gridy = 1;
        generalPanel.add(icon, gbc);

        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        generalPanel.add(info, gbc);


        add(botonPanel, BorderLayout.SOUTH);
        add(generalPanel, BorderLayout.CENTER);
    }
    /**
     * Aplica el estilo visual del tutorial a un boton de navegacion.
     * <p>Asigna el comando de accion, la fuente personalizada, color de texto blanco,
     * fondo oscuro y borde blanco, siguiendo la estetica del juego.
     *
     * @param btn   el {@code JButton} al que se aplicara el estilo
     * @param tecla el {@code ActionCommand} que identificara el boton al ser presionado
     */
    private void hacerBoton(JButton btn, String tecla) {
        btn.setActionCommand(tecla);
        btn.setFont(PonerFont.cargar(Font.BOLD, 15));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(15, 0, 50));
        btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        btn.setFocusPainted(false);
    }

    
    public void setNombre(String texto) {
        name.setText(texto);
    }

    public void setIcono(String ruta) {
            ImageIcon img = new ImageIcon(ruta);
            icon.setIcon(img);
    }

    public void setDescripcion(String texto) {
        info.setText(texto);
    }
    
    public JButton getVerPlayer() { 
    	return ver_player;
    }
    public JButton getVerAntivirus() { 
    	return ver_antivirus;
    }
    public JButton getVerScanner() { 
    	return ver_scanner;
    }
    public JButton getVerPaquete() { 
    	return ver_paquete;
    }
    public JButton getVerPuerto() { 
    	return ver_puerto; 
    }
    public JButton getVerNodo() { 
    	return ver_nodo; 
    }
    public JButton getVerFirewall() { 
    	return ver_firewall;
    }
    public JButton getVerMas() { 
    	return ver_mas;
    }
    public JButton getBtnVolver() { 
    	return btnvolver;
    }
    public JPanel getPanel() {
        return this;
    }
}