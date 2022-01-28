/**
 * 
 */
package logicanegocios.tiposencriptacion;

import logicanegocios.metodosencriptacion.Telefonico;

/**
 * @author GMG418
 *
 */
public class CifradoTelefonico extends Telefonico {

	@Override
	public String encriptar(Object[] argumentos) {
		String fraseEncriptada="";
		String frase=((String) argumentos[1]).replace(">","");
		for (char letra: frase.toCharArray()) {
			fraseEncriptada=fraseEncriptada+" " + obtenerCodigoNumerico(letra);
		}
		return fraseEncriptada.trim();
	}

	@Override
	public String desencriptar(Object[] argumentos) {
		String fraseDesencriptada="";
		String frase=((String) argumentos[1]).replace(">","");
		for (String letra: frase.split(" ")) {
			fraseDesencriptada=fraseDesencriptada+obtenerCaracter(letra);
		}
		return fraseDesencriptada.trim();
	}

}
