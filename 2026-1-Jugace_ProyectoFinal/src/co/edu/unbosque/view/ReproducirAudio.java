package co.edu.unbosque.view;

import javax.sound.sampled.*;
import java.net.URL;

public class ReproducirAudio {

    // Guardamos el clip de manera estática para poder controlarlo desde cualquier método
    private static Clip clip;

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

    // NUEVO MÉTODO: Llama a esto desde el Controller para apagar la música por completo
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