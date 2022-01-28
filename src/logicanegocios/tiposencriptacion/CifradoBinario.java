/**
 * 
 */
package logicanegocios.tiposencriptacion;

import logicanegocios.metodosencriptacion.Binario;

/**
 * @author GMG418
 *
 */
public class CifradoBinario extends Binario {

	@Override
	public String encriptar(Object[] argumentos) {
		String fraseEncriptada="";
		String frase=((String) argumentos[1]).replace(">","");
		for (char letra: frase.toCharArray()) {
			fraseEncriptada=fraseEncriptada+" " + obtenerCodigoBinario(letra);
		}
		return fraseEncriptada.trim();
	}

	@Override
	public String desencriptar(Object[] argumentos) {
		String fraseDesencriptada="";
		String frase=((String) argumentos[1]).replace(">","");
		for (String letra: frase.split(" ")) {
			fraseDesencriptada=fraseDesencriptada+obtenerLetra(letra);
		}
		return fraseDesencriptada.trim();
	}

}
