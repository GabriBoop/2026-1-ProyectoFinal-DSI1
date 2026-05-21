package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import co.edu.unbosque.controller.Controller;

public class MovimientosPanel extends JPanel {
	
    private JPanel movPanel = new JPanel(); 
    private JLabel trn = new JLabel();
    private JLabel txt = new JLabel();
    
    // Componentes para la gestión visual de puertos
    private JLabel lblTituloPuertos = new JLabel("PUERTOS ENLAZADOS:");
    private JPanel puertosContenedor = new JPanel();
    private JLabel[] slotsPuertos;
    private ImageIcon iconoPuerto;

    public MovimientosPanel() {
        // Mantenemos el tamaño constante del panel lateral expandiéndolo a 220px
        // para dar espacio a los cuadrados más grandes sin romper el diseño
        setPreferredSize(new Dimension(300, 500));
        
        movPanel.setLayout(new GridBagLayout());
        txt.setText("MOVIMIENTOS: 0"); 
        txt.setForeground(Color.WHITE);
        txt.setFont(new Font("Arial", Font.BOLD, 13));
        
        trn.setText("TURNO: 0");
        trn.setForeground(Color.WHITE);
        trn.setFont(new Font("Arial", Font.BOLD, 13));
        
        lblTituloPuertos.setForeground(Color.WHITE);
        lblTituloPuertos.setFont(new Font("Arial", Font.BOLD, 12));
        
        // GRIDLAYOUT DE 3 COLUMNAS: Fuerza a que los últimos 2 bajen automáticamente
        puertosContenedor.setLayout(new GridLayout(0, 3, 10, 10));
        puertosContenedor.setBackground(new Color(105, 0, 190));

        // Inicializamos el arreglo basándonos en la cantidad exacta configurada
        slotsPuertos = new JLabel[Controller.PUERTOS];
        
        // Escalamos la imagen 'save.png' a 44x44 para que sea totalmente visible
        iconoPuerto = obtenerIconoEscalado("src/co/edu/unbosque/images/save.png", 70, 70);

        for (int i = 0; i < Controller.PUERTOS; i++) {
            slotsPuertos[i] = new JLabel();
            // Aumentamos los cuadrados negros a un tamaño mucho mayor (55x55)
            slotsPuertos[i].setPreferredSize(new Dimension(80, 80));
            slotsPuertos[i].setBackground(Color.BLACK);
            slotsPuertos[i].setOpaque(true);
            
            // Centramos el contenido dentro del cuadrado negro
            slotsPuertos[i].setHorizontalAlignment(SwingConstants.CENTER);
            slotsPuertos[i].setVerticalAlignment(SwingConstants.CENTER);
            
            slotsPuertos[i].setBorder(BorderFactory.createLineBorder(new Color(130, 20, 220), 2));
            puertosContenedor.add(slotsPuertos[i]);
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;          
        gbc.insets = new Insets(15, 10, 15, 10); 
        gbc.fill = GridBagConstraints.CENTER;
        
        gbc.gridy = 0;
        movPanel.add(trn, gbc);
        
        gbc.gridy = 1;
        movPanel.add(txt, gbc); 
        
        gbc.gridy = 2;
        movPanel.add(lblTituloPuertos, gbc);
        
        gbc.gridy = 3;
        movPanel.add(puertosContenedor, gbc);
        
        setLayout(new BorderLayout());
        movPanel.setBackground(new Color(105, 0, 190));
        add(movPanel, BorderLayout.CENTER);
    }

    private ImageIcon obtenerIconoEscalado(String ruta, int ancho, int alto) {
        try {
            ImageIcon original = new ImageIcon(ruta);
            Image img = original.getImage();
            Image nuevaImg = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(nuevaImg);
        } catch (Exception e) {
            return null;
        }
    }

    public void actualizarPuertosTocados(int cantidadTocados) {
        for (int i = 0; i < slotsPuertos.length; i++) {
            if (i < cantidadTocados) {
                slotsPuertos[i].setIcon(iconoPuerto); 
                slotsPuertos[i].setBackground(new Color(80, 0, 150)); 
            } else {
                slotsPuertos[i].setIcon(null);
                slotsPuertos[i].setBackground(Color.BLACK);
            }
        }
        puertosContenedor.revalidate();
        puertosContenedor.repaint();
    }

    public JPanel getPanel() {
        return movPanel;
    }

    public JLabel getTrn() {
        return trn;
    }

    public JLabel getTxt() {
        return txt;
    }
}