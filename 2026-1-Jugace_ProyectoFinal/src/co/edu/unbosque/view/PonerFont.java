package co.edu.unbosque.view;

import java.awt.Font;
import java.io.InputStream;

public class PonerFont {

	
	private static Font font = null;

	/**
	 * Carga la fuente VCR OSD Mono con un estilo y tamaño específico
	 */
	public static Font cargar(int estilo, int size) {
		
		
		if (font == null) {
			try {
				// Buscamos el archivo de forma segura dentro del proyecto (Sirve para el .JAR final)
				InputStream is = PonerFont.class.getResourceAsStream("/co/edu/unbosque/fonts/VCR_OSD_MONO.ttf");
				
				if (is != null) {
					font = Font.createFont(Font.TRUETYPE_FONT, is);
				} else {
					font = new Font("Monospaced", Font.PLAIN, 12);
				}
			} catch (Exception e) {
				font = new Font("Monospaced", Font.PLAIN, 12);
				e.printStackTrace();
			}
		}
		
		// Retorna la fuente clonada con las especificaciones que le pidas
		return font.deriveFont(estilo, size);
	}

	/**
	 * Sobrecarga por si solo quieres cambiarle el tamaño rápido sin especificar estilo.
	 * Lo deja en PLAIN (normal) por defecto.
	 */
	public static Font cargar(int size) {
		return cargar(Font.PLAIN, size);
	}
}