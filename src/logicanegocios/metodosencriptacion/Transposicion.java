/**
 * 
 */
package logicanegocios.metodosencriptacion;

import logicanegocios.IEncriptable;

/**
 * @author GMG418
 *
 */
public abstract class Transposicion implements IEncriptable {

	protected String transponerLetras(String frase) {
		String nuevaFrase="";
		for(int i=frase.length()-1;i>=0;i--) {
			nuevaFrase= nuevaFrase + frase.charAt(i);
		}
		return nuevaFrase;	
	}
	
	//Por cada palabra se llama a la sustitución de las letras que lo componen al métodos sustitucionLetras
	protected String transponerPalabras (String frase) {
		String [] listaPalabras=frase.split(" ");
		frase="";
		for (int i=0; i<listaPalabras.length;i++ ) {
			frase = frase+ " " + transponerLetras(listaPalabras[i]);
		}
		return frase.trim();
	}
}
