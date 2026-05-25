package co.edu.unbosque.model;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Encargado de guardar historial de partidas (log), aqui se generan y guardan como .txt.
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */

public class HistorialPartida {

    /**
     * Genera y guarda el archivo de historial al finalizar la partida.
     *
     * @param movimientos   Lista de strings con cada movimiento registrado.
     * @param resultado     "VICTORIA" o "DERROTA".
     * @param turnosFinal   Número de turnos jugados.
     * @param movFinal      Movimientos restantes al terminar.
     * @param puertosTotal  Total de puertos configurados.
     * @param puertosTocados Puertos que el jugador logró activar.
     */
    public static void guardar(List<String> movimientos, String resultado,
                               int turnosFinal, int movFinal,
                               int puertosTotal, int puertosTocados) {

        // Nombre de archivo con fecha y hora para que cada partida genere uno nuevo

        String nombreArchivo = "historial" + ".txt";

        try (FileWriter fw = new FileWriter(nombreArchivo)) {

            fw.write("--------------------------------------------\n");
            fw.write("       CYBER INFILTRATOR - HISTORIAL\n");
            fw.write("--------------------------------------------\n");
            fw.write("Fecha y hora : " + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\n");
            fw.write("Resultado    : " + resultado + "\n");
            fw.write("Turnos       : " + turnosFinal + "\n");
            fw.write("Mov restantes: " + movFinal + "\n");
            fw.write("Puertos      : " + puertosTocados + " / " + puertosTotal + "\n");
            fw.write("--------------------------------------------\n");
            fw.write("           SECUENCIA DE MOVIMIENTOS\n");
            fw.write("--------------------------------------------\n");

            if (movimientos.isEmpty()) {
                fw.write("(Sin movimientos registrados)\n");
            } else {
                for (int i = 0; i < movimientos.size(); i++) {
                    fw.write(String.format("  %3d. %s%n", i + 1, movimientos.get(i)));
                }
            }

            fw.write("--------------------------------------------\n");
            fw.write("Total de movimientos ejecutados: " + movimientos.size() + "\n");
            fw.write("--------------------------------------------\n");

            System.out.println("[Historial] Archivo guardado: " + nombreArchivo);

        } catch (IOException e) {
            System.out.println("[Historial] Error al guardar el archivo: " + e.getMessage());
        }
    }
}