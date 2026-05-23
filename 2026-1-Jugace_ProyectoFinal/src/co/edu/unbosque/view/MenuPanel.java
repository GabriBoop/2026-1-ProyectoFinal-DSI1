package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;


public class MenuPanel {

    private JPanel menuPanel = new JPanel();
    
    private JButton btnPredeterminado = new JButton();
    private JButton btnPersonalizado = new JButton();
    private JButton btnTutorial = new JButton();
    private JButton btnSalir = new JButton();

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

        styleButton(btnPredeterminado, "src/co/edu/unbosque/images/btn_jgpdrt.png");
        styleButton(btnPersonalizado, "src/co/edu/unbosque/images/btn_jgper.png");
        styleButton(btnTutorial, "src/co/edu/unbosque/images/btn_tutorial.png");
        styleButton(btnSalir, "src/co/edu/unbosque/images/btn_salir.png");

        gbc.gridy = 1; menuPanel.add(btnPredeterminado, gbc);
        gbc.gridy = 2; menuPanel.add(btnPersonalizado, gbc);
        gbc.gridy = 3; menuPanel.add(btnTutorial, gbc);
        gbc.gridy = 4; menuPanel.add(btnSalir, gbc);
    }

    private void styleButton(JButton btn, String icon) {
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