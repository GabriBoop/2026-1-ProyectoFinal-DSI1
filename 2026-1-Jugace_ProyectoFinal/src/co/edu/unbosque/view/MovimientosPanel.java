package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
import co.edu.unbosque.controller.Controller;
/**
 * Panel lateral de informacion de partida en tiempo real.
 * <p>Muestra el turno actual, el contador de movimientos, el estado de sigilo
 * del jugador, los puertos enlazados, la cantidad de antivirus y scanners,
 * y el orden de los puertos. Se actualiza dinamicamente durante el juego.
 *
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */
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
    
    /**
     * Constructor del panel de movimientos.
     * <p>Inicializa y organiza todos los componentes visuales del panel lateral,
     * crea los slots de puertos segun la cantidad definida en {@code Controller.PUERTOS},
     * y configura la animacion inicial del jugador en estado de espera.
     */
    
    public MovimientosPanel() {
      
        setPreferredSize(new Dimension(300, 500));
        
        movPanel.setLayout(new GridBagLayout());
        txt.setText("MOVIMIENTOS: 0"); 
        txt.setForeground(Color.WHITE);
        txt.setFont(PonerFont.cargar(Font.BOLD, 25));
        
        trn.setText("TURNO: 0");
        trn.setForeground(Color.CYAN);
        trn.setFont(PonerFont.cargar(Font.BOLD, 35));
        
        //PUERTOS
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
    /**
     * Carga una imagen desde la ruta indicada y la escala al tamaño especificado.
     *
     * @param ruta  ruta del archivo de imagen a cargar
     * @param ancho ancho deseado de la imagen escalada en pixeles
     * @param alto  alto deseado de la imagen escalada en pixeles
     * @return un {@code ImageIcon} escalado, o {@code null} si ocurre un error
     */
    private ImageIcon obtenerIconoEscalado(String ruta, int ancho, int alto) {
        ImageIcon original = new ImageIcon(ruta);
        Image img = original.getImage();
        Image nuevaImg = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(nuevaImg);
    }
   
    /**
     * Actualiza la imagen animada del jugador segun su estado actual.
     *
     * @param ruta ruta del archivo GIF o imagen que representa el nuevo estado del jugador
     */
    public void actualizarGifJugador(String ruta) {
        ImageIcon newgif = new ImageIcon(ruta);
        plyState.setIcon(newgif);
    }
    
    /**
     * Actualiza visualmente los slots de puertos segun la cantidad que el jugador ya ha tocado.
     * <p>Los puertos visitados muestran el icono de puerto enlazado, los restantes se limpian.
     *
     * @param cantidadTocados numero de puertos que el jugador ha enlazado hasta el momento
     */
    public void actualizarPuertosTocados(int cantidadTocados) {
        for (int i = 0; i < slotsPuertos.length; i++) {
            if (i < cantidadTocados) {
                slotsPuertos[i].setIcon(iconoPuerto); 
            } 
            else {
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