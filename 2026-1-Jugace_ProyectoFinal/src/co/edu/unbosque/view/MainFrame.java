package co.edu.unbosque.view;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
/**
 * Ventana principal del juego Cyber-Infiltrator.
 * <p>Es como contenedor principal de todos los paneles del juego.
 * Permite el intercambio de paneles para navegar entre el menu,
 * la configuracion, el tablero de juego y las pantallas de resultado.
 *
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */
public class MainFrame extends JFrame{ 
	
	private static final int ANCHO = 800; //Dimensiones de Pantalla
	private static final int ALTO = 600;
	
	public MainFrame() {
	
		setBounds(0, 0, ANCHO, ALTO);
		setTitle("Cyber-Infiltrator: Menu");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setBackground(new Color(15,0,50));
		setLocationRelativeTo(null); // Asegura que siempre salga en el cemtro de la pantalla del computador
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	/**
     * Reemplaza el panel actualmente visible en el frame por uno nuevo.
     * <p>Elimina el contenido anterior, agrga el nuevo panel al centro
     *
     * @param panel que se desea mostrar en pantalla
     */
	 public void cambiarPanel(JPanel panel) {//Cambio de pameñ (EJ: Config a Juego)
	        getContentPane().removeAll();
	        getContentPane().add(panel, BorderLayout.CENTER);
	        revalidate(); //Revalida todo lo del Frame y no se gemerem errpres visuales
	        repaint();
	    }
	
	 public void setTitulo(String txt) {
		    this.setTitle(txt); 
		}
	
		
}

