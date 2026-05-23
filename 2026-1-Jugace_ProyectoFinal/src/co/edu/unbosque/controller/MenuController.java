package co.edu.unbosque.controller;

import co.edu.unbosque.view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Clase controladora de TODAS las acciones del menu, desde detectar botones, ver informacion del juego, inicializar juego predeterminado e
 * inicializar partidas con configuracion personalizada
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */


public class MenuController implements ActionListener {

    private static ConfigPanel configPanel; //Paneles
    private static MenuPanel menuPanel;
    private static TutorialPanel tutorialpanel;
    
    /**
     *  Inicializan los procesos del Menu, otorga titulo, musica, inicializa paneles que se van a usar en este y prepara
     *  el uso de los botones para redirigir a los paneles.
     *  <p> Al final, cambia el panel principal que es tenga actualmente, hacia el panel del menu.
     */
    public static void run() {
        Controller.vnt.setTitle("Cyber-Infiltrator: Menu"); //Se prepara todo
        ReproducirAudio.reproducir("/co/edu/unbosque/sound/menu.wav");
        configPanel = new ConfigPanel();
        menuPanel = new MenuPanel(); 
        tutorialpanel = new TutorialPanel();
        MenuController lector = new MenuController(); //Se autollama para Inputs
        lector.darLectores();
        Controller.vnt.cambiarPanel(menuPanel.getPanel());
    }
    /**
     *  Da los ActionListener y ActionComand a los botones de TODOS los paneles que
     *  se vayan a usar.
     *<p>Se llama al ActionPerformed.
     *<p>Da Logica a cada boton mediante ActionEvent.
     *<p>Cada boton tiene funcion distinta y algunos comparten el mismo PANEL, aqui
     *se pueden configurar los valores del juego como cantidad de enemigos, filas, columnas, orden y cantidad de puertos y mas.
     *<p>Seccion fundamental para el funcionamiento del juego, se reproduce un sonido cada vez que se presione
     *alguno de los botones.
     *
     */
    public void darLectores() {
        menuPanel.getBtnPredeterminado().addActionListener(this);
        menuPanel.getBtnPredeterminado().setActionCommand("Predeterminado");
        menuPanel.getBtnPersonalizado().addActionListener(this);
        menuPanel.getBtnPersonalizado().setActionCommand("Personalizado");
        menuPanel.getBtnTutorial().addActionListener(this);
        menuPanel.getBtnTutorial().setActionCommand("Tutorial");
        menuPanel.getBtnSalir().addActionListener(this);
        menuPanel.getBtnSalir().setActionCommand("Salir");
        
        configPanel.getBotOK().addActionListener(this);
        configPanel.getBotOK().setActionCommand("BotOK");
        configPanel.getChkINVERSO().addActionListener(this);
        configPanel.getChkINVERSO().setActionCommand("Check");
        configPanel.getBotRTN().addActionListener(this);
        configPanel.getBotRTN().setActionCommand("VOLVER");
        
        tutorialpanel.getVerPlayer().addActionListener(this);
        tutorialpanel.getVerPlayer().setActionCommand("PLAYER");
        tutorialpanel.getVerAntivirus().addActionListener(this);
        tutorialpanel.getVerAntivirus().setActionCommand("ANTIVIRUS");
        tutorialpanel.getVerScanner().addActionListener(this);
        tutorialpanel.getVerScanner().setActionCommand("SCANNER");
        tutorialpanel.getVerPaquete().addActionListener(this);
        tutorialpanel.getVerPaquete().setActionCommand("PAQUETE");
        tutorialpanel.getVerNodo().addActionListener(this);
        tutorialpanel.getVerNodo().setActionCommand("NODO");
        tutorialpanel.getVerPuerto().addActionListener(this);
        tutorialpanel.getVerPuerto().setActionCommand("PUERTO");
        tutorialpanel.getVerFirewall().addActionListener(this);
        tutorialpanel.getVerFirewall().setActionCommand("FIREWALL");
        tutorialpanel.getVerMas().addActionListener(this);
        tutorialpanel.getVerMas().setActionCommand("MAS");
        tutorialpanel.getBtnVolver().addActionListener(this);
        tutorialpanel.getBtnVolver().setActionCommand("VOLVER_T");
    }   
    
    public void actionPerformed(ActionEvent e) {
    	ReproducirAudio.reproducirSFX(Controller.click);
        switch (e.getActionCommand()) {
        	case "VOLVER":
        		Controller.vnt.cambiarPanel(menuPanel.getPanel());
        		break;
        	case "VOLVER_T":
        		ReproducirAudio.detener();
        		ReproducirAudio.reproducir("/co/edu/unbosque/sound/menu.wav");
        		Controller.vnt.cambiarPanel(menuPanel.getPanel());
        		break;
        		
            case "Predeterminado":
            	Controller.vnt.setTitle("Cyber-Infiltrator: Predeterminado");
                Controller.FILA = 8;
                Controller.COLUMNA = 12;
                Controller.GRID_TOTAL = Controller.FILA * Controller.COLUMNA;
                Controller.MOV = Controller.GRID_TOTAL;
                Controller.PUERTOS = 5;
                Controller.CANT_NODOS = 3;
                Controller.CANT_ANTIVIRUS = 2;
                Controller.CANT_SCANNERS = 3;
                Controller.CANT_FIREWALLS = (int) (Controller.GRID_TOTAL * 0.12);
                Controller.INVERSO = false;
                Controller.musica = "/co/edu/unbosque/sound/diddy.wav";

                Controller.actualizarTablero();
                Controller.mostrarTablero();
                break;

            case "Personalizado":
                Controller.vnt.setTitle("Cyber-Infiltrator: Configuracion");
                Controller.vnt.cambiarPanel(ConfigPanel.getPanel());
                break;

            case "Tutorial":
            	ReproducirAudio.detener();
            	ReproducirAudio.reproducir("/co/edu/unbosque/sound/tutorial.wav"); 
            	Controller.vnt.setTitle("Cyber-Infiltrator: Tutorial / Informacion");
            	Controller.vnt.cambiarPanel(tutorialpanel.getPanel());
                break;

            case "Salir":
                ImageIcon icoexit = new ImageIcon("src/co/edu/unbosque/images/icon_exit.png");
                int resp = JOptionPane.showConfirmDialog(null, 
                    "<html>¿Estas seguro de salir del juego?<br><html><center>( A el... no le gustaria :( )<center><br>", 
                    "Salir", 0, JOptionPane.YES_NO_OPTION, icoexit);
                if (resp == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                break;
                
                // CONFIGURACION
            case "BotOK":
                Controller.FILA = (int) configPanel.getSpinFILAS().getValue();
                Controller.COLUMNA = (int) configPanel.getSpinCOLUMNAS().getValue();
                Controller.GRID_TOTAL =  Controller.FILA * Controller.COLUMNA;
                Controller.PUERTOS = (int) configPanel.getSpinPUERTOS().getValue();
                int s = configPanel.getSelectdiff().getSelectedIndex();
                
                if(s >= 3 && Controller.GRID_TOTAL < 40) { //Anuncio de Nivel prosiblemente imposible al hacer una Matriz tan pequeña con tantos enemigos
                	ImageIcon icon_a = new ImageIcon("src/co/edu/unbosque/images/icon_playersigilo.png");
                	int res = JOptionPane.showConfirmDialog(null, "<html>Tu dificultad es muy alta para el mapeo tan pequeño que pusiste...<br>"
                			+ "<html><center>¿Quieres volver a poner un Mapeo mas grande?<center><br>"
                			, "AVISO!!!",0,JOptionPane.YES_NO_OPTION,icon_a);
                	if(res == 0) {
                		return;
                	} 
                }

                if(s == 0) { // FACIL
                    Controller.CANT_ANTIVIRUS = 1;
                    Controller.CANT_NODOS = 4;
                    Controller.CANT_SCANNERS = 1;
                    Controller.CANT_FIREWALLS = (int) (Controller.GRID_TOTAL * 0.08);
                    Controller.musica = "/co/edu/unbosque/sound/sneakman.wav";
                }
                if(s == 1) { // NORMAL
                    Controller.CANT_ANTIVIRUS = 1;
                    Controller.CANT_NODOS = 3;
                    Controller.CANT_SCANNERS = 2;
                    Controller.CANT_FIREWALLS = (int) (Controller.GRID_TOTAL * 0.10);
                    Controller.musica = "/co/edu/unbosque/sound/sneakman.wav";
                }
                if(s == 2) { // MODERADO
                    Controller.CANT_ANTIVIRUS = 2;
                    Controller.CANT_NODOS = 2;
                    Controller.CANT_SCANNERS = 3;
                    Controller.CANT_FIREWALLS = (int) (Controller.GRID_TOTAL * 0.12);
                    Controller.musica = "/co/edu/unbosque/sound/diddy.wav";
                }
                if(s == 3) { //DIFICIL
                    Controller.CANT_ANTIVIRUS = 3;
                    Controller.CANT_NODOS = 1;
                    Controller.CANT_SCANNERS = 4;
                    Controller.CANT_FIREWALLS = (int) (Controller.GRID_TOTAL * 0.14);
                    Controller.musica = "/co/edu/unbosque/sound/diddy.wav";
                }
                if(s == 4) { // EXPERTO
                    Controller.CANT_ANTIVIRUS = 4;
                    Controller.CANT_NODOS = 0;
                    Controller.CANT_SCANNERS = 5;
                    Controller.CANT_FIREWALLS = (int) (Controller.GRID_TOTAL * 0.16);
                    Controller.musica = "/co/edu/unbosque/sound/rhythm-heaven.wav";
                }
                Controller.actualizarTablero();
                Controller.mostrarTablero();
                break;
            case "Check":
                if(configPanel.getChkINVERSO().isSelected()) {
                    Controller.INVERSO = true;
                } else {
                    Controller.INVERSO = false;
                }
                break;
                
            // TUTORIAL / INFORMACION
            // Escritura: <html> <br> lineas <center> centrado
            case "PLAYER":
                tutorialpanel.setNombre("JUGADOR");
                tutorialpanel.setIcono("src/co/edu/unbosque/images/player_tuto.png");
                tutorialpanel.setDescripcion("<html><center>¡Este eres TU!<center><br>"
                		+ "<html><center>El infiltrado que ayuda al Paquete a llegar a la salida de este laberinto...<center><br>"
                		+ "<html><center>Consigue movimientos para no perder y evita toparte con TRAMPAS o ENEMIGOS.<center><br>"
                		+ "<html><center>Tu ultima baza ante ellos es el SIGILO, que te dara un turno de inmunidad total.<center><br>");;
                break;

            case "ANTIVIRUS":
                tutorialpanel.setNombre("ANTIVIRUS");
                tutorialpanel.setIcono("src/co/edu/unbosque/images/antivirus_tuto.png");
                tutorialpanel.setDescripcion("<html><center>El peor de tus ENEMIGOS<center><br>"
                		+ "<html><center>El ANTIVIRUS apenas te toca en su rango que te eliminara inmediatamente.<center><br>"
                		+ "<html><center>¡Procura evitarlo a toda costa! o evadelo con SIGILO<center><br>"
                		+ "<html><center>\"jsiwdjfodjwsexfkj\" - ANTIVIRUS<center><br>");
                break;

            case "SCANNER":
                tutorialpanel.setNombre("ESCANER DE LATENCIA");
                tutorialpanel.setIcono("src/co/edu/unbosque/images/scanner_tuto.png");
                tutorialpanel.setDescripcion("<html><center>Otra gran MOLESTIA...<center><br>"
                		+ "<html><center>El ESCANER a diferencia del ANTIVIRUS, no te mata, pero si te restara el 5% de tus Movimientos.<center><br>"
                		+ "<html><center>Normalmente, hay mayores cantidades de ellos comparado con los ANTIVIRUS en los niveles."
                		+ "<html><center>\"YA TE VI *activa sigilo* uh?\" - ESCANER<center><br>");
                break;
                
            case "PAQUETE":
                tutorialpanel.setNombre("PAQUETE");
                tutorialpanel.setIcono("src/co/edu/unbosque/images/paquete_tuto.png");
                tutorialpanel.setDescripcion("<html><center>EL DESACTIVADOR DE PUERTOS<center><br>"
                		+ "<html><center>Al pasar el PAQUETE por todos PUERTOS en su orden, se desbloqueara la SALIDA.<center><br>"
                		+ "<html><center>Tip: No lo empujes hacia los limites verticales u horizontales del mapa, o no podras sacarlo de ahi.<center><br>"
                		+ "<html><center>\"No soy un fastidio... cierto?\" - FASTIDIO<center><br>");
                break;
                
            case "PUERTO":
                tutorialpanel.setNombre("PUERTO DE ENLACE");
                tutorialpanel.setIcono("src/co/edu/unbosque/images/puerto_tuto.png");
                tutorialpanel.setDescripcion("<html><center>HACIA DONDE LLEVAS EL PAQUETE<center><br>"
                		+ "<html><center>Estos cuentan con un Orden especifico, al desactivarlos todos, removeras la seguridad de la SALIDA del programa.<center><br>"
                		+ "<html><center>(Tienes que llevar el PAQUETE a la salida para GANAR)<center><br>");
                break;

            case "NODO":
                tutorialpanel.setNombre("NODO DE ENERGIA");
                tutorialpanel.setIcono("src/co/edu/unbosque/images/nodo_tuto.png");
                tutorialpanel.setDescripcion("<html><center>Te suman 10% de tus movimientos actuales, estan repartidos en todo el mapa.<center><br>");
                break;

            case "FIREWALL":
                tutorialpanel.setNombre("FIREWALL Y TRAMPA");
                tutorialpanel.setIcono("src/co/edu/unbosque/images/wall_tuto.png");
                tutorialpanel.setDescripcion("<html><center>Paredes y mas molestias...<center><br>"
                		+ "<html><center>Siempre que quede un espacio libre entre dos casillas, se creara una TRAMPA<center><br>"
                		+ "<html><center>La TRAMPA te quitara Fila + Columna en la que estes a tus Movimientos<center><br>");
                break;

            case "MAS":
                tutorialpanel.setNombre("COMO JUGAR");
                tutorialpanel.setIcono("src/co/edu/unbosque/images/manual_tuto.png");
                tutorialpanel.setDescripcion("<html><center>WASD / FLECHAS --> Moverse<center><br>"
                		+ "<html><center>SPACE / ENTER --> ACTIVAR modo SIGILO<center><br>"
                		+ "<html><center>R --> Re-generar Tablero / Mapa<center><br>"
                		+ "<html><center>ESCAPE --> Salir de la partida<center><br>"
                		+ "<html><center>¡BUENA SUERTE!<center><br>");
                break;
        }
    }
}