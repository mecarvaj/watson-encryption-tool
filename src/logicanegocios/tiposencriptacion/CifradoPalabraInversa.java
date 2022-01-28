package logicanegocios.tiposencriptacion;

import logicanegocios.metodosencriptacion.Transposicion;

public class CifradoPalabraInversa extends Transposicion {

	@Override
	public String encriptar(Object[] argumentos) {
		String frase = ((String) argumentos[1]).replace(">","");
		return transponerPalabras (frase);
	}

	@Override
	public String desencriptar(Object[] argumentos) {
		String frase = ((String) argumentos[1]).replace(">","");
		return transponerPalabras (frase);
	}
	

}
