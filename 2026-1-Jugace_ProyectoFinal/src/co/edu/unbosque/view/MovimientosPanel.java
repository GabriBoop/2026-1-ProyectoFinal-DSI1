package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import co.edu.unbosque.controller.Controller;

public class MovimientosPanel extends JPanel {
	
    private JPanel movPanel = new JPanel(); 
    private JLabel trn = new JLabel();
    private JLabel txt = new JLabel();
    
    // Componentes PUERTOS
    private JLabel lblTituloPuertos = new JLabel("PUERTOS ENLAZADOS:");
    private JPanel puertosContenedor = new JPanel();
    private JLabel[] slotsPuertos;
    
    private JLabel plyState = new JLabel(); 

    private JLabel prtOrden = new JLabel(); 
    private JLabel txtAntivirus = new JLabel(); 
    private JLabel txtScanners = new JLabel(); 
    
    private JLabel txtSigilo = new JLabel("SIGILO: INACTIVO");
   
    private ImageIcon iconoPuerto;

    public MovimientosPanel() {
      
        setPreferredSize(new Dimension(300, 500));
        
        movPanel.setLayout(new GridBagLayout());
        txt.setText("MOVIMIENTOS: 0"); 
        txt.setForeground(Color.WHITE);
        txt.setFont(PonerFont.cargar(Font.BOLD, 25));
        
        trn.setText("TURNO: 0");
        trn.setForeground(Color.CYAN);
        trn.setFont(PonerFont.cargar(Font.BOLD, 35));
        
        lblTituloPuertos.setForeground(Color.WHITE);
        lblTituloPuertos.setFont(PonerFont.cargar(Font.BOLD, 20));
        
        puertosContenedor.setLayout(new GridLayout(0, 3, 5, 5));
        puertosContenedor.setBackground(new Color(5,0,65));
        slotsPuertos = new JLabel[Controller.PUERTOS];
        iconoPuerto = obtenerIconoEscalado("src/co/edu/unbosque/images/save.png", 70, 70);

        for (int i = 0; i < Controller.PUERTOS; i++) {
            slotsPuertos[i] = new JLabel();
            slotsPuertos[i].setPreferredSize(new Dimension(80, 80));
            slotsPuertos[i].setBackground(new Color(5,0,65));
            
            slotsPuertos[i].setHorizontalAlignment(SwingConstants.CENTER);
            slotsPuertos[i].setVerticalAlignment(SwingConstants.CENTER);
            
            slotsPuertos[i].setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
            puertosContenedor.add(slotsPuertos[i]);
        }
        
        plyState.setPreferredSize(new Dimension(200, 200));
        plyState.setHorizontalAlignment(SwingConstants.CENTER);
        plyState.setVerticalAlignment(SwingConstants.CENTER);
        ImageIcon pl = new ImageIcon("src/co/edu/unbosque/images/player_wait.gif");
        plyState.setIcon(pl);
        
        txtSigilo.setFont(PonerFont.cargar(Font.BOLD, 25));
        txtSigilo.setForeground(Color.BLUE);
        
        prtOrden.setText("ORDEN DE PUERTOS: COMUN");
        if(Controller.INVERSO) prtOrden.setText("ORDEN DE PUERTOS: INVERSO");
        prtOrden.setForeground(Color.WHITE);
        prtOrden.setFont(PonerFont.cargar(Font.PLAIN, 18));
        
        txtAntivirus.setText("ANTIVIRUS: " + Controller.CANT_ANTIVIRUS);
        txtAntivirus.setForeground(Color.WHITE);
        txtAntivirus.setFont(PonerFont.cargar(Font.PLAIN, 18));
        
        txtScanners.setText("SCANNERS: " + Controller.CANT_SCANNERS);
        txtScanners.setForeground(Color.WHITE);
        txtScanners.setFont(PonerFont.cargar(Font.PLAIN, 18));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;          
        gbc.insets = new Insets(15, 10, 15, 10); 
        gbc.fill = GridBagConstraints.CENTER;
        
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;          
        gbc2.insets = new Insets(2, 2, 2, 2); 
        gbc2.fill = GridBagConstraints.CENTER;
        
        gbc.gridy = 0;
        movPanel.add(trn, gbc);
        
        gbc.gridy = 1;
        movPanel.add(txt, gbc); 
        
        gbc.gridy = 2;
        movPanel.add(lblTituloPuertos, gbc);
        
        gbc.gridy = 3;
        movPanel.add(puertosContenedor, gbc);
        
        gbc.gridy = 4;
        movPanel.add(plyState, gbc);
        
        gbc.gridy = 5;
        movPanel.add(txtSigilo, gbc);

        gbc2.gridy = 7;
        movPanel.add(prtOrden, gbc2);
        gbc2.gridy = 8;
        movPanel.add(txtAntivirus, gbc2);
        gbc2.gridy = 9;
        movPanel.add(txtScanners, gbc2);

        
        setLayout(new BorderLayout());
        movPanel.setBackground(new Color(5,0,65));
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
   
    public void actualizarGifJugador(String ruta) {
        ImageIcon newgif = new ImageIcon(ruta);
        plyState.setIcon(newgif);
    }
    

    public void actualizarPuertosTocados(int cantidadTocados) {
        for (int i = 0; i < slotsPuertos.length; i++) {
            if (i < cantidadTocados) {
                slotsPuertos[i].setIcon(iconoPuerto); 
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
    
    public JLabel getTxtSigilo() {
        return txtSigilo;
    }
    
    public JLabel getPlPanel() {
        return plyState;
    }
    
}