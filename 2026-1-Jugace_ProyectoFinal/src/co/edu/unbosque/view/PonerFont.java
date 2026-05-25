package co.edu.unbosque.view;

import java.awt.Font;
import java.io.InputStream;
/**
 * Utilidad para cargar y aplicar la fuente personalizada del juego.
 * <p>Carga la fuente desde los recursos del proyecto
 * y la pone a disposicion de todos los componentes graficos con el estilo
 * y tamano que se requiera. Si la fuente no puede cargarse, se usa
 * {@code Monospaced} como alternativa.
 *
 * @author Gabriel Alejandro Morales Diaz
 * @author Cesar David Reyes Ruiz
 * @author Juan David Barrera Lopez
 */
public class PonerFont {

	private static Font font = null;

	/**
     * Carga la fuente VCR OSD Mono con un estilo y tamano especifico.
     * <p>La primera vez que se invoca, lee el archivo {@code .ttf} desde los recursos.
     * En llamadas posteriores reutiliza la instancia ya cargada y solo aplica
     * el estilo y tamano indicados mediante {@code deriveFont}.
     * Si ocurre algun error al cargar la fuente, usa {@code Monospaced} como respaldo.
     *
     * @param estilo estilo de la fuente 
     * @param size   tamano de la fuente en puntos
     * @return la {@code Font} configurada con el estilo y tamano indicados
     */
	public static Font cargar(int estilo, int size) { //Mayor parte hecha con IA, ni idea como hacer esto
		
		if (font == null) {
			try {
				InputStream ft = PonerFont.class.getResourceAsStream("/co/edu/unbosque/fonts/VCR_OSD_MONO.ttf");
				
				if (ft != null) {
					font = Font.createFont(Font.TRUETYPE_FONT, ft);
				} else {
					font = new Font("Monospaced", Font.PLAIN, 12);
				}
			} catch (Exception e) {
				font = new Font("Monospaced", Font.PLAIN, 12);
				e.printStackTrace();
			}
		}
		return font.deriveFont(estilo, size);
	}
}