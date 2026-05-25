package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

/**
 * Panel del menu principal del juego.
 * <p>Contiene los botones para iniciar una partida predeterminada,
 * una partida personalizada, acceder al tutorial o salir de la aplicacion.
 * El logo del juego se muestra en la parte superior del panel.
 *
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */

public class MenuPanel {

    private JPanel menuPanel = new JPanel();
    
    private JButton btnPredeterminado = new JButton();
    private JButton btnPersonalizado = new JButton();
    private JButton btnTutorial = new JButton();
    private JButton btnSalir = new JButton();
    
    /**
     * Constructor del panel de menu principal.
     * <p>Inicializa el layout, el fondo y agrega el logo junto a los cuatro
     * botones de navegacion. Cada boton recibe su icono mediante {@link #styleButton(JButton, String)}.
     */
    
    public MenuPanel() {
        menuPanel.setLayout(new GridBagLayout());
        menuPanel.setBackground(new Color(15, 0, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        ImageIcon icon = new ImageIcon("src/co/edu/unbosque/images/logo.png");
        JLabel lbl_logo = new JLabel(icon);
        gbc.gridy = 0;
        menuPanel.add(lbl_logo, gbc);

        decoButton(btnPredeterminado, "src/co/edu/unbosque/images/btn_jgpdrt.png");
        decoButton(btnPersonalizado, "src/co/edu/unbosque/images/btn_jgper.png");
        decoButton(btnTutorial, "src/co/edu/unbosque/images/btn_tutorial.png");
        decoButton(btnSalir, "src/co/edu/unbosque/images/btn_salir.png");

        gbc.gridy = 1; 
        menuPanel.add(btnPredeterminado, gbc);
        gbc.gridy = 2; 
        menuPanel.add(btnPersonalizado, gbc);
        gbc.gridy = 3; 
        menuPanel.add(btnTutorial, gbc);
        gbc.gridy = 4; 
        menuPanel.add(btnSalir, gbc);
    }
    
    /**
     * Aplica el estilo visual a un boton asignandole un icono de imagen
     * y eliminando bordes, fondo y foco pintado.
     *
     * @param btn  el {@code JButton} al que se le aplicara el estilo
     * @param icon ruta de la imagen a usar como icono del boton
     */

    private void decoButton(JButton btn, String icon) {
        ImageIcon img = new ImageIcon(icon);
        btn.setIcon(img);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
    }

    public JPanel getPanel() {
        return menuPanel;
    }

    public JButton getBtnPredeterminado() {
        return btnPredeterminado;
    }

    public JButton getBtnPersonalizado() {
        return btnPersonalizado;
    }

    public JButton getBtnTutorial() {
        return btnTutorial;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }
}