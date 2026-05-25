package co.edu.unbosque.view;

import javax.sound.sampled.*;
import java.net.URL;
/**
 * Clase para la reproduccion y control de audio del juego.
 * <p>Gestiona la musica de fondo y los efectos de sonido de forma estatica,
 * permitiendo reproducir, detener y cambiar pistas desde cualquier parte del codigo.
 *
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */
public class ReproducirAudio {

    // Guardamos el clip de manera estática para poder controlarlo desde cualquier método
    private static Clip clip;
    
    /**
     * Reproduce un archivo de audio como musica de fondo.
     * <p>Si ya hay una pista sonando, la detiene y libera antes de reproducir la nueva.
     * El audio se inicia en segundo plano sin bloquear el hilo principal.
     *
     * @param nombreArchivo ruta del recurso de audio dentro del classpath
     *                      
     */
    public static void reproducir(String nombreArchivo) {
        try {
            // Si ya hay algo sonando, lo detenemos antes de reproducir el siguiente
            detener();

            URL url = ReproducirAudio.class.getResource(nombreArchivo);
            
            if (url == null) {
                System.out.println("No se encontró el archivo: " + nombreArchivo);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start(); // Suena directo en segundo plano

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    /**
     * Reproduce un efecto de sonido corto de forma independiente a la musica de fondo.
     * <p>Cada llamada crea un clip separado para no interferir con la musica principal.
     * El clip se cierra y libera automaticamente al terminar la reproduccion,
     * evitando acumular multiples efectos de sonido.
     *
     * @param nombreArchivo ruta del recurso de audio dentro del classpath
     *                      
     */
    
    public static void reproducirSFX(String nombreArchivo) {
        try {
            URL url = ReproducirAudio.class.getResource(nombreArchivo);
            
            if (url == null) {
                System.out.println("No se encontró el archivo de efecto: " + nombreArchivo);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
            Clip clipEfecto = AudioSystem.getClip();
            clipEfecto.open(audioStream);
            clipEfecto.start();

        } catch (Exception e) {
            System.out.println("Error al reproducir efecto: " + e.getMessage());
        }
    }

    /**
     * Detiene y libera el clip de musica de fondo actualmente en reproduccion.
     */
    
    public static void detener() {
        try {
            if (clip != null && clip.isRunning()) {
                clip.stop();  // Detiene la reproducción
                clip.close(); // Libera la memoria del archivo de audio
            }
        } catch (Exception e) {
            System.out.println("Error al detener el audio: " + e.getMessage());
        }
    }

    public static void main(String[] args) {	        
        reproducir("/co/edu/unbosque/sound/solo.wav");	        	      
    }
}