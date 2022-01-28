package logicanegocios.metodosencriptacion;

import logicanegocios.IEncriptable;

public abstract class Sustitucion implements IEncriptable {
		
	protected static int obtenerCaracterEncriptado(char letra, int posiciones) {
		int resultado=0;
		if (letra==32)
			resultado=32;
		else {
			resultado = ((int) letra )+posiciones;
			if (resultado>122)
				resultado=resultado-26;
			else if (resultado<97) {
				resultado=resultado+26;
			}
			
		}
		return resultado;
	}
	
}
