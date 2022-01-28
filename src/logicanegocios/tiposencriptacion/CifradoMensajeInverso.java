/**
 * 
 */
package logicanegocios.tiposencriptacion;

import logicanegocios.metodosencriptacion.Transposicion;

/**
 * @author GMG418
 *
 */
public class CifradoMensajeInverso extends Transposicion {

	@Override
	public String encriptar(Object[] argumentos) {
		String frase = ((String) argumentos[1]).replace(">","");
		return transponerFrase(transponerPalabras (frase));
	}

	@Override
	public String desencriptar(Object[] argumentos) {
		String frase = ((String) argumentos[1]).replace(">","");
		return transponerFrase(transponerPalabras (frase));
	}
	
	private String transponerFrase(String fraseEncriptada) {
		String[] listaPalabras=fraseEncriptada.split(" ");
		String[] nuevaFrase= new String[listaPalabras.length];
		int j=0; 
		for(int i=listaPalabras.length-1;i>=0;i--) {
			nuevaFrase[j]= listaPalabras[i];
			j++;
		}
		return ""+String.join(" ", nuevaFrase);
	}

}
