package logicanegocios.tiposencriptacion;


import logicanegocios.metodosencriptacion.Sustitucion;

public class CifradoCesar extends Sustitucion {
	
	@Override
	public String encriptar(Object[] argumentos) {
		int posiciones = Integer.parseInt((String) argumentos[2]);
		char[] frase = (((String) argumentos[1]).replace(">","")).toCharArray();
		
		return sustitucionPalabras (frase, posiciones);
	}
	
	@Override
	public String desencriptar(Object[] argumentos) {
		
		int posiciones = Integer.parseInt((String) argumentos[2]);
		char[] frase = (((String) argumentos[1]).replace(">","")).toCharArray();
		
		return sustitucionPalabras(frase, -1*posiciones);
	}
	
	private String sustitucionPalabras(char[] frase, int posiciones) {
		for (int i = 0, n = frase.length; i < n; i++) {
			frase[i] = (char) Sustitucion.obtenerCaracterEncriptado(frase[i], posiciones);
		}
		return new String(frase);
	}
	

}
